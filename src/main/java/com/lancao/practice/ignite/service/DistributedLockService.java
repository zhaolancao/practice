package com.lancao.practice.ignite.service;

import jakarta.annotation.Resource;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

// We no longer need the import from the 'transactions' package
// import static org.apache.ignite.transactions.TransactionConcurrency.PESSIMISTIC;

@Service
public class DistributedLockService {

    private static final Logger log = LoggerFactory.getLogger(DistributedLockService.class);

    private int safeCount = 0; // safe count
    private int unsafeCount = 0; // unsafe count

    @Resource
    private Ignite igniteClient;

    public void safeIncrement() {
        String resourceId = "SAFE_INCREMENT";
        final String lockName = "RESOURCE_LOCK_" + resourceId;

        try (IgniteLock lock = igniteClient.reentrantLock(lockName, true, false, true)) {
            // This log line is the key to debugging the hang.
            log.info("Attempting to acquire distributed lock for lockName: {}", lockName);
            if (lock.tryLock(10, TimeUnit.SECONDS)) {
                log.info("Lock acquired for resource: {}. Performing critical operation...", resourceId);
                increment("safeIncrement", safeCount++);
                log.info("Critical operation finished for resource: {}.", resourceId);
            } else {
                log.warn("Could not acquire lock for resource: {}. Another process may be holding it.", resourceId);
                throw new IllegalStateException("Failed to acquire lock for resource " + resourceId);
            }
        }
        log.info("Lock for resource {} has been released.", resourceId);
    }

    public void unsafeIncrement() {
        increment("unsafeIncrement", unsafeCount++);
    }

    private void increment(String caller, int count) {
        log.info("increment is invoked, {} count will be set as: {}", caller, count);
    }
}
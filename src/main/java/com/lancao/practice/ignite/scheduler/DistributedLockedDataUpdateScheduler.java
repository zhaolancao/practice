package com.lancao.practice.ignite.scheduler;

import com.lancao.practice.ignite.service.DistributedLockService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Component
public class DistributedLockedDataUpdateScheduler {
    @Resource
    private DistributedLockService service;
    @Resource
    private Executor igniteExecutor;
    @Resource
    private Executor unsafeIgniteExecutor;

    @Scheduled(fixedDelay = 10000)
    public void performCriticalUpdate() {
        for (int i = 0; i < 100; i++) {
            CompletableFuture.runAsync(() -> {
                service.unsafeIncrement();
            }, igniteExecutor);
            CompletableFuture.runAsync(() -> {
                service.safeIncrement();
            }, unsafeIgniteExecutor);
        }
    }
}

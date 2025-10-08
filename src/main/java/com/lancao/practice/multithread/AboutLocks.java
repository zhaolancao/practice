package com.lancao.practice.multithread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class AboutLocks {

    private static Integer count;

    // synchronized 修饰实例方法
    synchronized public void countIncrement_synchronized(int pace) {
        countPrint_synchronized("before");
        count += pace;
        countPrint_synchronized("after");
    }

    private void countPrint_synchronized(String operation) {
        // synchronized 修饰代码块
        synchronized (count) {
            log.info("Count value {} synchronized increment: {}", operation.toUpperCase(), count);
        }
    }

    private final ReentrantLock lock = new ReentrantLock(true);

    public void countIncrement_reentrantLock(int pace) {
        try {
            lock.lock();
            countPrint_reentrantLock("before");
            count += pace;
            countPrint_reentrantLock("after");
        } catch (Exception e) {
            log.error("Error occurred during reentrantLock testing: ", e);
        } finally {
            lock.unlock();
        }
    }

    private void countPrint_reentrantLock(String operation) {
        try {
            if (lock.tryLock(100, TimeUnit.MILLISECONDS)) { // 设置等待超时时间
                log.info("Count value {} reentrantLock increment: {}", operation.toUpperCase(), count);
            } else {
                log.error("Failed to acquire reentrantLock!");
            }
        } catch (Exception e) {
            log.error("Error occurred during reentrantLock printing: ", e);
        } finally {
            lock.unlock();
        }
    }
}

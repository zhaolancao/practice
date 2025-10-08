package com.lancao.practice.multithread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

class AboutLocksTest {
    AboutLocks classUnderTest = new AboutLocks();
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

    @BeforeEach
    void setup() {
        executor.initialize();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(10);
    }

    @Test
    void countIncrement() {
        AboutLocks anotherClassUnderTest = new AboutLocks();
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                classUnderTest.countIncrement_synchronized(2);
                anotherClassUnderTest.countIncrement_reentrantLock(3);
            });
        }
    }

    @Test
    void countIncrement_reentrantLock() {
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                classUnderTest.countIncrement_reentrantLock(3);
            });
        }
    }
}
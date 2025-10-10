package com.lancao.practice.multithread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Configuration
public class ThreadPoolTaskExecutorExample {

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2); // 核心线程数
        executor.setMaxPoolSize(4); // 最大线程数
        executor.setQueueCapacity(10); // 任务队列容量
        executor.setThreadNamePrefix("Spring-Executor-"); // 线程名前缀
        executor.initialize(); // 初始化线程池
        return executor;
    }

    public static void main(String[] args) {
        // 创建 Spring 上下文（简化版，实际项目中通常由 Spring 容器管理）
        ThreadPoolTaskExecutorExample example = new ThreadPoolTaskExecutorExample();
        ThreadPoolTaskExecutor executor = example.taskExecutor();

        // 创建 Callable 任务
        Callable<String> task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("任务正在执行，线程: " + threadName);
            Thread.sleep(1000); // 模拟耗时操作
            return "任务完成，线程: " + threadName;
        };

        // 提交任务并获取 Future 对象
        Future<String> future = executor.submit(task);

        // 获取任务结果
        try {
            String result = future.get(); // 阻塞直到任务完成
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            executor.shutdown();
        }
    }
}

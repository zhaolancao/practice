package com.lancao.practice.multithread;

import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class ThreadPoolExecutorCallableExample {
    public static void main(String[] args) {
        // 1. 创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2, // 核心线程数
            4, // 最大线程数
            60, // 空闲线程存活时间
            TimeUnit.SECONDS, // 时间单位
            new LinkedBlockingQueue<>(10) // 任务队列
        );

        // 2. 创建 Callable 任务列表
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            tasks.add(() -> {
                System.out.println("任务 " + taskId + " 正在执行，线程: " + Thread.currentThread().getName());
                Thread.sleep(1000); // 模拟耗时操作
                return "任务 " + taskId + " 完成";
            });
        }

        // 3. 提交任务并获取 Future 对象
        List<Future<String>> futures = new ArrayList<>();
        for (Callable<String> task : tasks) {
            futures.add(executor.submit(task));
        }

        // 4. 获取任务结果
        for (Future<String> future : futures) {
            try {
                String result = future.get(); // 阻塞直到任务完成
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // 5. 关闭线程池
        executor.shutdown();
    }
}


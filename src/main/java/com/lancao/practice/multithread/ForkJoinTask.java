package com.lancao.practice.multithread;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.RecursiveTask;

@Getter
@AllArgsConstructor
public class ForkJoinTask extends RecursiveTask<Integer> {
    private int first;
    private int last;
    @Setter
    private double[] d;

    @Override
    protected Integer compute() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " computing: " + first + "," + last);
        int subCount;
        if (last - first < 10) {
            subCount = 0;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = first; i <= last; i++) {
                if (d[i] > 0.5) {
                    subCount++;
                }
            }
        } else {
            int mid = (first + last) >>> 1;
            ForkJoinTask left = new ForkJoinTask(first, mid, d);
            left.fork();
            ForkJoinTask right = new ForkJoinTask(mid + 1, last, d);
            right.fork();
            subCount = left.join();
            subCount += right.join();
        }
        System.out.println(threadName + " completed: " + first + "," + last);
        return subCount;
    }
}

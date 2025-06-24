package com.lancao.practice.multithread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

class ForkJoinTaskTest {

    @Test
    void compute() {
        double[] d = new double[100];
        for (int i = 0; i < 100; i++) {
            d[i] = ((double) i) / 20;
        }
        Integer result = new ForkJoinPool().invoke(new ForkJoinTask(0, 99, d));
        System.out.println("result: " + result);
    }
}
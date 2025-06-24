package com.lancao.practice.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.lang.System.currentTimeMillis;

class RedPackageTest {

    @Test
    void solution() {
        long start = currentTimeMillis();
        double[] result = RedPackage.solution(100, 10, 0.1);
        Arrays.stream(result).forEach(ele -> System.out.print(ele + " "));
        System.out.println();
        System.out.println("RedPackage cost: " + (currentTimeMillis() - start) + "ms");
    }
}
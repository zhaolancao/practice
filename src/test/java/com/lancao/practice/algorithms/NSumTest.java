package com.lancao.practice.algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.System.currentTimeMillis;

class NSumTest {
    private int[] elements = new int[]{1, 3, 5, 7, 8, 9, 10};
    int target = 9;

    @Test
    void solution() {
        long start = currentTimeMillis();
        List<List<Integer>> result = NSum.solution(elements, target);
        Assertions.assertFalse(result.isEmpty());
        result.forEach(seq -> {
            for (int i = 0; i < elements.length; i++) {
                String sign = seq.get(i) == 1 ? "+" : "-";
                System.out.print(sign + elements[i]);
            }
            System.out.println(" = " + target);
        });
        System.out.println("solution cost: " + (currentTimeMillis() - start) + "ms");
    }
}
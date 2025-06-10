package com.lancao.practice.algorithms.searching;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.System.currentTimeMillis;

class BinarySearchTest {
    private int[] elements = new int[]{1, 3, 5, 7, 8, 9, 10};
    int target = 9;
    int falseTarget = 2;

    @Test
    void solution() {
        long start = currentTimeMillis();
        Assertions.assertEquals(5, BinarySearch.solution(elements, target));
        Assertions.assertEquals(-1, BinarySearch.solution(elements, falseTarget));
        System.out.println("solution cost: " + (currentTimeMillis() - start) + "ms");
    }

    @Test
    void whatever() {
        System.out.println((int) 'a');
        System.out.println((int) 'b');
        System.out.println((int) ('z'-'a'));
    }
}
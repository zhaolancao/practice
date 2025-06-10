package com.lancao.practice.algorithms.dynamicprogramming;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.System.currentTimeMillis;

class PermutationTest {

    private int[] elements = new int[]{1, 3, 5, 7, 8, 9, 10};

    @Test
    void solution() {
        long start = currentTimeMillis();
        List<List<Integer>> result = Permutation.solution(elements);
//        result.forEach(seq -> {
//            seq.forEach(ele -> System.out.print(ele + ", "));
//            System.out.println();
//        });
        System.out.println("solution got sequences.size()=" + result.size() + ", cost: " + (currentTimeMillis() - start) + "ms");
    }
}
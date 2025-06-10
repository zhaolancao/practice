package com.lancao.practice.algorithms.dynamicprogramming;

import org.junit.jupiter.api.Test;

import static java.lang.System.currentTimeMillis;

class FibonacciSequenceTest {

    int number = 40;
    @Test
    void solution() {
        long start = currentTimeMillis();
        for (int i = 1; i < number; i++) {
            System.out.print(FibonacciSequence.solution(i) + ", ");
        }
        System.out.println();
        System.out.println("solution cost: " + (currentTimeMillis() - start) + "ms");
    }

    @Test
    void solution_recursion_worstPerformance() {
        long start = currentTimeMillis();
        for (int i = 1; i < number; i++) {
            System.out.print(FibonacciSequence.solution_recursion_worstPerformance(i) + ", ");
        }
        System.out.println();
        System.out.println("solution_recursion_worstPerformance cost: " + (currentTimeMillis() - start) + "ms");
    }

    @Test
    void solution_recursion_lessWorsePerformance() {
        long start = currentTimeMillis();
        for (int i = 1; i < number; i++) {
            System.out.print(FibonacciSequence.solution_recursion_lessWorsePerformance(i) + ", ");
        }
        System.out.println();
        System.out.println("solution_recursion_lessWorsePerformance cost: " + (currentTimeMillis() - start) + "ms");
    }
}
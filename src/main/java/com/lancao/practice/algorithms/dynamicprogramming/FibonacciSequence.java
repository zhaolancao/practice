package com.lancao.practice.algorithms.dynamicprogramming;

public class FibonacciSequence {

    public static int solution(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Illegal input for Fibonacci Sequence!");
        }
        if (number == 1 || number == 2) {
            return 1;
        }
        int previous = 1;
        int preOfPre = 1;
        int i = 3;
        while (i++ < number) {
            int tmpResult = preOfPre + previous;
            preOfPre = previous;
            previous = tmpResult;
        }
        return preOfPre + previous;
    }

    public static int solution_recursion_worstPerformance(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Illegal input for Fibonacci Sequence!");
        }
        if (number == 1 || number == 2) {
            return 1;
        }
        return solution_recursion_worstPerformance(number - 1) + solution_recursion_worstPerformance(number - 2);
    }

    public static int solution_recursion_lessWorsePerformance(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Illegal input for Fibonacci Sequence!");
        }
        Integer[] cache = new Integer[number + 1];
        return recursionWithImprovement(cache, number);
    }

    private static int recursionWithImprovement(Integer[] cache, int number) {
        if (cache[number] != null) {
            return cache[number];
        }
        if (number == 1 || number == 2) {
            cache[number] = 1;
            return 1;
        }
        int result = recursionWithImprovement(cache, number - 1) + recursionWithImprovement(cache, number - 2);
        cache[number] = result;
        return result;
    }
}

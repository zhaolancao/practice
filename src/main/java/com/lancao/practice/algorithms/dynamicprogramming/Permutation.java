package com.lancao.practice.algorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public static List<List<Integer>> solution(int[] elements) {
        return recursion(new ArrayList<>(), elements);
    }

    private static List<List<Integer>> recursion(List<Integer> prefix, int[] elements) {
        List<List<Integer>> result = new ArrayList<>();
        if (elements.length == 0) {
            result.add(prefix);
            return result;
        }
        for (int i = 0; i < elements.length; i++) {
            List<Integer> curPrefix = new ArrayList<>(prefix);
            curPrefix.add(elements[i]);
            int[] remaining = getRemainingElements(elements, i);
            result.addAll(recursion(curPrefix, remaining));
        }
        return result;
    }

    private static int[] getRemainingElements(int[] elements, int i) {
        int[] remaining = new int[elements.length - 1];
        if (i >= 0) {
            System.arraycopy(elements, 0, remaining, 0, i);
        }
        if (elements.length - (i + 1) >= 0) {
            System.arraycopy(elements, i + 1, remaining, i + 1 - 1, elements.length - (i + 1));
        }
        return remaining;
    }
}

package com.lancao.practice.algorithms;

import java.util.ArrayList;
import java.util.List;

public class NSum {
    private static final int[] signs = new int[]{-1, 1};

    public static List<List<Integer>> solution(int[] elements, int target) {
        return signedSequences(new ArrayList<>(), 0, 0, elements, target);
    }

    private static List<List<Integer>> signedSequences(List<Integer> prefix, int sum, int index, int[] elements, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (prefix.size() == elements.length) {
            if (sum == target) {
                result.add(prefix);
            }
            return result;
        }

        for (int sign : signs) {
            List<Integer> curPrefix = new ArrayList<>(prefix);
            curPrefix.add(sign);
            result.addAll(signedSequences(curPrefix, sum + elements[index] * sign, index + 1, elements, target));
        }

        return result;
    }
}
package com.lancao.practice.algorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class DpLeetcode {
    //    70. 爬楼梯
    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int oneStepBack = 2;
        int twoStepsBack = 1;
        for (int i = 3; i <= n; i++) {
            int currentWays = oneStepBack + twoStepsBack;
            twoStepsBack = oneStepBack;
            oneStepBack = currentWays;
        }
        return oneStepBack;
    }

    /**
     * 118. 杨辉三角
     * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
     */
    public static List<List<Integer>> pascalsTriangle(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        int index = 0;
        if (index >= numRows) {
            return result;
        }
        result.add(new ArrayList<>() {{
            add(1);
        }});
        index++;
        if (index >= numRows) {
            return result;
        }
        result.add(new ArrayList<>() {{
            add(1);
            add(1);
        }});
        index++;
        while (index < numRows) {
            List<Integer> previousRow = result.get(index - 1);
            List<Integer> currentRow = new ArrayList<>();
            currentRow.add(1);
            for (int i = 0; i < previousRow.size() - 1; i++) {
                currentRow.add(previousRow.get(i) + previousRow.get(i + 1));
            }
            currentRow.add(1);
            result.add(currentRow);
            index++;
        }
        return result;
    }

    /**
     * 121. 买卖股票的最佳时机
     */
    public static int maxProfit(int[] prices) {
        int result = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (minPrice > prices[i]) {
                minPrice = prices[i];
            } else {
                result = Math.max(result, prices[i] - minPrice);
            }
        }
        return result;
    }

    /**
     * 338. 比特位计数
     * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
     */
    public static int[] countBits(int n) {
        int[] result = new int[n + 1];
        int preCeil = 0;
        int ceiling = 1;
        int index = 1;
        while (index <= n) {
            while (index <= n && preCeil < index && index < ceiling) {
                result[index] = result[index - preCeil] + 1;
                index++;
            }
            if (ceiling <= n) {
                result[ceiling] = 1;
                preCeil = ceiling;
                ceiling = ceiling << 1;
                index++;
            }
        }
        return result;
    }

    /**
     * 392. 判断子序列
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
     * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     */
    public static boolean isSubsequence(String s, String t) {
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            boolean isCurMatched = false;
            for (; j < t.length(); j++) {
                if (curChar == t.charAt(j)) {
                    isCurMatched = true;
                    j++;
                    break;
                }
            }
            if (!isCurMatched) {
                return false;
            }
        }
        return true;
    }

    /**
     * 509. 斐波那契数
     * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给定 n ，请计算 F(n) 。
     */
    public static int fib(int n) {
        int result = 0;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int prePrevious = 0;
        int previous = 1;
        int index = 2;
        while (index <= n) {
            result = prePrevious + previous;
            prePrevious = previous;
            previous = result;
            index++;
        }
        return result;
    }

    /**
     * 746. 使用最小花费爬楼梯
     * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
     * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
     * 请你计算并返回达到楼梯顶部的最低花费。
     */
    public static int minCostClimbingStairs(int[] cost) {
        if (cost.length == 1) {
            return cost[0];
        }
        if (cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }
        int prePrevious = cost[0];
        int previous = Math.min(cost[0], cost[1]);
        for (int i = 2; i < cost.length; i++) {
            int current = cost[i] + Math.min(previous, prePrevious);
            prePrevious = previous;
            previous = current;
        }

        return previous;
    }
}

package com.lancao.practice.algorithms.leetcode;

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

    @Test
    void threeSum() {
        long start = currentTimeMillis();
        
        // 测试用例1：基本用例
        int[] elements1 = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result1 = NSum.threeSum(elements1);
        Assertions.assertFalse(result1.isEmpty());
        System.out.println("测试用例1结果：");
        result1.forEach(triplet -> {
            System.out.println(triplet.get(0) + " + " + triplet.get(1) + " + " + triplet.get(2) + " = 0");
        });
        
        // 验证结果中包含预期的三元组
        boolean containsExpected1 = result1.stream().anyMatch(triplet -> 
            triplet.contains(-1) && triplet.contains(0) && triplet.contains(1));
        boolean containsExpected2 = result1.stream().anyMatch(triplet -> 
            triplet.contains(-1) && triplet.contains(-1) && triplet.contains(2));
        Assertions.assertTrue(containsExpected1, "结果应包含三元组 [-1, 0, 1]");
        Assertions.assertTrue(containsExpected2, "结果应包含三元组 [-1, -1, 2]");
        
        // 测试用例2：全零数组
        int[] elements2 = new int[]{0, 0, 0, 0};
        List<List<Integer>> result2 = NSum.threeSum(elements2);
        Assertions.assertFalse(result2.isEmpty());
        System.out.println("\n测试用例2结果：");
        result2.forEach(triplet -> {
            System.out.println(triplet.get(0) + " + " + triplet.get(1) + " + " + triplet.get(2) + " = 0");
        });
        
        // 验证结果中包含预期的三元组
        boolean containsZeroTriplet = result2.stream().anyMatch(triplet -> 
            triplet.get(0) == 0 && triplet.get(1) == 0 && triplet.get(2) == 0);
        Assertions.assertTrue(containsZeroTriplet, "结果应包含三元组 [0, 0, 0]");
        
        // 测试用例3：没有符合条件的三元组
        int[] elements3 = new int[]{1, 2, 3, 4, 5};
        List<List<Integer>> result3 = NSum.threeSum(elements3);
        Assertions.assertTrue(result3.isEmpty());
        System.out.println("\n测试用例3结果：空（没有符合条件的三元组）");
        
        // 测试用例4：包含重复元素的更复杂用例
        int[] elements4 = new int[]{-2, -1, 0, 0, 1, 2, 2};
        List<List<Integer>> result4 = NSum.threeSum(elements4);
        Assertions.assertFalse(result4.isEmpty());
        System.out.println("\n测试用例4结果：");
        result4.forEach(triplet -> {
            System.out.println(triplet.get(0) + " + " + triplet.get(1) + " + " + triplet.get(2) + " = 0");
        });
        
        System.out.println("\nthreeSum cost: " + (currentTimeMillis() - start) + "ms");
    }
    
    @Test
    void threeSumClosest() {
        // 测试用例1：基本用例
        int[] nums1 = {-1, 2, 1, -4};
        int target1 = 1;
        int expected1 = 2; // -1 + 2 + 1 = 2，与目标值1最接近
        long start = currentTimeMillis();
        int result1 = NSum.threeSumClosest(nums1, target1);
        Assertions.assertEquals(expected1, result1);
        System.out.println("threeSumClosest case 1 result: " + result1);
        
        // 测试用例2：数组长度正好为3
        int[] nums2 = {0, 0, 0};
        int target2 = 1;
        int expected2 = 0; // 0 + 0 + 0 = 0，与目标值1最接近
        int result2 = NSum.threeSumClosest(nums2, target2);
        Assertions.assertEquals(expected2, result2);
        System.out.println("threeSumClosest case 2 result: " + result2);
        
        // 测试用例3：有多个组合与目标值距离相同
        int[] nums3 = {1, 1, 1, 0};
        int target3 = 100;
        int expected3 = 3; // 1 + 1 + 1 = 3，与目标值100最接近
        int result3 = NSum.threeSumClosest(nums3, target3);
        Assertions.assertEquals(expected3, result3);
        System.out.println("threeSumClosest case 3 result: " + result3);
        
        // 测试用例4：更复杂的用例
        int[] nums4 = {-100, -98, -2, -1, 0, 1, 2, 3, 4, 5, 98, 100};
        int target4 = 0;
        int expected4 = 0; // -2 + 1 + 1 = 0，与目标值0最接近（实际上等于目标值）
        int result4 = NSum.threeSumClosest(nums4, target4);
        Assertions.assertEquals(expected4, result4);
        System.out.println("threeSumClosest case 4 result: " + result4);
        
        System.out.println("threeSumClosest cost: " + (currentTimeMillis() - start) + "ms");
    }
}
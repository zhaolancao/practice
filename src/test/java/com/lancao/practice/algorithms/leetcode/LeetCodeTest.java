package com.lancao.practice.algorithms.leetcode;

import org.junit.jupiter.api.Test;

class LeetCodeTest {

    @Test
    public void test() {
        int[] nums1 = new int[]{1, 2, 3, 4, 5};
        int[] nums2 = new int[]{6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        double result = LeetCode.findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }

    @Test
    void myAtoi() {
        System.out.println(LeetCode.myAtoi("1"));
    }

    @Test
    void minimumDeletions() {
//        System.out.println(LeetCode.minimumDeletions("ahahnhahhah", 1));
//        System.out.println(LeetCode.minimumDeletions("zzfzzzzppfp", 1));
//        System.out.println(LeetCode.minimumDeletions("dabdcbdcdcd", 2));
    }
}
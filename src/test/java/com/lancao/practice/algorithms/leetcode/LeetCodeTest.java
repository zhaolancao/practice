package com.lancao.practice.algorithms.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void reverse() {
        System.out.println(LeetCode.reverse(1534236469));
    }

    @Test
    void longestValidParentheses() {
//        System.out.println(LeetCode.longestValidParentheses("(()"));

        System.out.println(LeetCode.longestValidParentheses("((()()(()((()"));
    }

    @Test
    void lengthOfLongestSubstring() {
        System.out.println(LeetCode.lengthOfLongestSubstring("abba"));
    }

    @Test
    void groupAnagrams() {
        System.out.println(LeetCode.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    @Test
    void findSubstring() {
        System.out.println(LeetCode.findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"}));
    }

    @Test
    void removeElement() {
        System.out.println(LeetCode.removeElement(new int[]{3, 2, 2, 3}, 3));
    }

    @Test
    void minimumMoves() {
        // In a 4x4 empty grid, the snake needs to:
        // 1. Move right to (0,1)
        // 2. Move down to (1,1)
        // 3. Move down to (2,1)
        // 4. Move right to (2,2)
        // 5. Move down to (3,2)
        assertEquals(11, LeetCode.minimumMoves(new int[][]{
                {0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0, 0}
        }));
//        assertEquals(5, LeetCode.minimumMoves(new int[][]{
//                {0, 0, 0, 0},
//                {0, 0, 0, 0},
//                {0, 0, 0, 0},
//                {0, 0, 0, 0}
//        }));
    }

    @Test
    void maximumBobPoints() {
        System.out.println(LeetCode.maximumBobPoints(10, new int[]{0,0,1,1,0,1,2,1,0,0,4,0}));
    }
}
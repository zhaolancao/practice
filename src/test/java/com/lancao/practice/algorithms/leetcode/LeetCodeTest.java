package com.lancao.practice.algorithms.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println(LeetCode.maximumBobPoints(10, new int[]{0, 0, 1, 1, 0, 1, 2, 1, 0, 0, 4, 0}));
    }

    @Test
    void countValidWords() {
        System.out.println(LeetCode.countValidWords("he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."));
    }

    @Test
    void orderlyQueue() {
        System.out.println(LeetCode.orderlyQueue("hmg", 2));
    }

    @Test
    void maxDepthAfterSplit() {
        System.out.println(LeetCode.maxDepthAfterSplit("(()())"));
        System.out.println(LeetCode.maxDepthAfterSplit("()(())()"));
    }

    /**
     * 示例 1：
     * <p>
     * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * 输出：true
     * 解释：我们可以按以下顺序执行：
     * push(1), push(2), push(3), push(4), pop() -> 4,
     * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
     * 示例 2：
     * <p>
     * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
     * 输出：false
     */
    @Test
    void validateStackSequences() {
        System.out.println(LeetCode.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 2, 1}));
        System.out.println(LeetCode.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 2, 1}));
        System.out.println(LeetCode.validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2}));
    }

    @Test
    void leastBricks() {
        //wall =
        //[[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
        List<List<Integer>> wall = new ArrayList<>(){{
        add(new ArrayList<>(){{add(1);add(2);add(2);add(1);}});
        add(new ArrayList<>(){{add(3);add(1);add(2);}});
        add(new ArrayList<>(){{add(1);add(3);add(2);}});
        add(new ArrayList<>(){{add(2);add(4);}});
        add(new ArrayList<>(){{add(3);add(1);add(2);}});
        add(new ArrayList<>(){{add(1);add(3);add(1);add(1);}});
        }};
        System.out.println(LeetCode.leastBricks(wall));
    }
}
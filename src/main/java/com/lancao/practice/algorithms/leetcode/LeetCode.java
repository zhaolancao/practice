package com.lancao.practice.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode {
    /**
     * 4. 寻找两个正序数组的中位数
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];
        int i = 0, j = 0, m = 0;
        while (i < nums1.length && j < nums2.length) {
            while (i < nums1.length && nums1[i] <= nums2[j]) {
                merged[m++] = nums1[i++];
            }
            if (i >= nums1.length) {
                break;
            }
            while (j < nums2.length && nums1[i] > nums2[j]) {
                merged[m++] = nums2[j++];
            }
        }
        if (i < nums1.length) {
            while (i < nums1.length) {
                merged[m++] = nums1[i++];
            }
        }
        if (j < nums2.length) {
            while (j < nums2.length) {
                merged[m++] = nums2[j++];
            }
        }
        int length = merged.length;
        if (length % 2 == 0) {
            return ((double) merged[length / 2] + (double) merged[length / 2 - 1]) / 2;
        } else {
            return merged[length / 2];
        }
    }

    /**
     * 8. 字符串转换整数 (atoi)
     */
    public static int myAtoi(String s) {
        char zero = '0';
        char nine = '9';
        char[] nums = new char[s.length()];
        int count = 0;
        int i = 0;
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        if (i >= s.length()) {
            return 0;
        }
        char c1 = s.charAt(i++);
        int signed = c1 == '-' ? -1 : 1;
        if (c1 > zero && c1 <= nine) {
            nums[count++] = c1;
        } else if (c1 != '+' && c1 != '-' && c1 != zero) {
            return 0;
        }
        if (count == 0) {
            while (i < s.length() && s.charAt(i) == zero) {
                i++;
            }
        }
        while (i < s.length() && s.charAt(i) >= zero && s.charAt(i) <= nine) {
            nums[count++] = s.charAt(i++);
        }
        if (count == 0) {
            return 0;
        }

        double max = Integer.MAX_VALUE;
        double min = Integer.MIN_VALUE;
        double result = nums[0] - zero;
        result = signed * result;
        i = 1;
        while (i < count) {
            int num = nums[i++] - zero;
            result = result * 10 + signed * num;
            if (min > result) {
                return Integer.MIN_VALUE;
            }
            if (max < result) {
                return Integer.MAX_VALUE;
            }
        }
        return (int) result;
    }

    public static int minimumDeletions_unresolved(String word, int k) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            Integer count = countMap.get(ch);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            countMap.put(ch, count);
        }
        Map<Integer, Integer> ccMap = new HashMap<>();
        for (Integer count : countMap.values()) {
            Integer cc = ccMap.get(count);
            if (cc == null) {
                cc = 1;
            } else {
                cc++;
            }
            ccMap.put(count, cc);
        }
        int[] sortedCount = new int[ccMap.size()];
        int size = 0;
        for (int chCount : ccMap.keySet()) {
            size = addToSortedArray(chCount, size, sortedCount);
        }
        if (sortedCount.length == 0) {
            return 0;
        }
        int minIndex = 0, maxIndex = size - 1;
        int delCount = 0;
        while (minIndex < maxIndex && sortedCount[maxIndex] - sortedCount[minIndex] > k) {
            int maxCount = sortedCount[maxIndex];
            int minCount = sortedCount[minIndex];
            int delMin = 0;
            int lowIndex = minIndex;
            while (lowIndex < maxIndex && maxCount - sortedCount[lowIndex] > k) {
                int lowCount = sortedCount[lowIndex];
                int lowCc = ccMap.get(lowCount);
                delMin += lowCount * lowCc;
                lowIndex++;
            }
            int delMax = 0;
            int highIndex = maxIndex;
            while (minIndex < highIndex && sortedCount[highIndex] - minCount > k) {
                int highCount = sortedCount[highIndex];
                int highCc = ccMap.get(highCount);
                int dd = highCount - minCount - k;
                delMax += dd * highCc;
                highIndex--;
            }
            if (delMin < delMax) {
                delCount += delMin;
                while (minIndex < lowIndex) {
                    minIndex++;
                }
            } else {
                delCount += delMax;
                while (maxIndex > highIndex) {
                    int highCount = sortedCount[maxIndex];
                    int dd = highCount - minCount - k;
                    sortedCount[maxIndex] = highCount - dd;
                    maxIndex--;
                }
            }
        }
        return delCount;
    }

    private static int addToSortedArray(int count, int size, int[] sortedCount) {
        if (size == 0) {
            sortedCount[size] = count;
            return 1;
        }
        int half = size / 2;
        if (sortedCount[half] > count) {
            while (half > 0 && sortedCount[half] >= count) {
                half--;
            }
        } else if (sortedCount[half] < count) {
            while (half < size && sortedCount[half] < count) {
                half++;
            }
        }
        int i = size;
        while (i > half) {
            sortedCount[i] = sortedCount[i - 1];
            i--;
        }
        sortedCount[half] = count;
        return size + 1;
    }
}

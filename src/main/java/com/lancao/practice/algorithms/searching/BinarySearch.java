package com.lancao.practice.algorithms.searching;

public class BinarySearch {
    public static int solution(int[] elements, int target) {
        int left = 0;
        int right = elements.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int midValue = elements[mid];
            if (midValue == target) {
                return mid;
            } else if (midValue < target) {
                // target in tail part
                left = mid + 1;
            } else {
                // target in front part
                right = mid - 1;
            }
            if (right < 0 || left >= elements.length) {
                break;
            }
        }
        return -1;
    }
}

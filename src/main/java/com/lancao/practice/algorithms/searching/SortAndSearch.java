package com.lancao.practice.algorithms.searching;

import java.util.Arrays;

public class SortAndSearch {
    public static int binarySearch(int[] elements, int target) {
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

    public static int[] mergeSort(int[] elements) {
        return mergeSort(elements, 0, elements.length - 1);
    }

    private static int[] mergeSort(int[] elements, int left, int right) {

        if (left >= right) {
            return elements;
        }
        if (left == right - 1) {
            if (elements[left] > elements[right]) {
                int temp = elements[left];
                elements[left] = elements[right];
                elements[right] = temp;
            }
            return elements;
        }
        int mid = (left + right) / 2;
        elements = mergeSort(elements, left, mid);
        elements = mergeSort(elements, mid + 1, right);
        return merge(elements, left, right, mid);
    }

    private static int[] merge(int[] elements, int left, int right, int mid) {
        int i = left;
        int j = mid + 1;
        int[] sorted = new int[right - left + 1];
        Arrays.fill(sorted, -1);
        int si = 0;
        while (i <= mid && j <= right) {
            while (i <= mid && elements[j] >= elements[i]) {
                sorted[si++] = elements[i++];
            }
            if (i > mid) {
                break;
            }
            while (j <= right && elements[i] > elements[j]) {
                sorted[si++] = elements[j++];
            }
        }
        while (i <= mid) {
            sorted[si++] = elements[i++];
        }
        while (j <= right) {
            sorted[si++] = elements[j++];
        }
        for (i = left, j = 0; i <= right; i++) {
            elements[i] = sorted[j++];
        }
        return elements;
    }

    public static int[] quickSort(int[] elements) {
        quickSort(elements, 0, elements.length - 1);
        return elements;
    }

    private static void quickSort(int[] elements, int start, int end) {
        if (start >= end) {
            return;
        }
        int cursor = start, i = cursor + 1, j = end;
        int temp = elements[cursor];
        while (i < j) {
            while (i <= j && temp >= elements[i]) {
                elements[cursor] = elements[i];
                cursor = i;
                i++;
            }
            while (i <= j && temp < elements[j]) {
                j--;
            }
            if (i <= j) {
                elements[cursor] = elements[j];
                elements[j] = elements[i];
                cursor = i;
                i++;
            }
            if (cursor == start) {
                return;
            }
        }
        if (cursor >= start && cursor <= end) {
            elements[cursor] = temp;
        }
        quickSort(elements, start, cursor);
        quickSort(elements, cursor + 1, end);
    }

    /**
     * 无序数组排序后的最大相邻差
     */
    public static int maxNeighbourDistance(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxDistance = 0, low = nums[0], high = nums[0], curLow = low, curHigh = high;
        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            if (n < low) {
                int tempDistance = low - n;
                if (tempDistance > maxDistance) {
                    maxDistance = tempDistance;
                    curLow = n;
                    curHigh = low;
                }
                low = n;
                continue;
            }
            if (n > high) {
                int tempDistance = n - high;
                if (tempDistance > maxDistance) {
                    maxDistance = tempDistance;
                    curLow = high;
                    curHigh = n;
                }
                high = n;
                continue;
            }
            if (curLow < n && n < curHigh) {
                int tdLeft = n - curLow;
                int tdRight = curHigh - n;
                if (tdLeft >= tdRight) {
                    maxDistance = tdLeft;
                    curHigh = n;
                } else {
                    maxDistance = tdRight;
                    curLow = n;
                }
            }
        }
        return maxDistance;
    }
}

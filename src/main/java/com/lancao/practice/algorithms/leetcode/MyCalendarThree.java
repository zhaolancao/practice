package com.lancao.practice.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 732. 我的日程安排表 III
 * 当 k 个日程安排有一些时间上的交叉时（例如 k 个日程安排都在同一时间内），就会产生 k 次预订。
 * 给你一些日程安排 [start, end) ，请你在每个日程安排添加后，返回一个整数 k ，表示所有先前日程安排会产生的最大 k 次预订。
 * 实现一个 MyCalendarThree 类来存放你的日程安排，你可以一直添加新的日程安排。
 */
public class MyCalendarThree {
    private final Map<Integer, Integer> calendar;
    private final int[] sortedKeys;
    private int count = 0;

    public MyCalendarThree() {
        calendar = new HashMap<>();
        sortedKeys = new int[1000];
    }

    public int book(int start, int end) {
        addDateKey(start);
        addDateKey(end);
        calendar.put(start, calendar.getOrDefault(start, 0) + 1);
        calendar.put(end, calendar.getOrDefault(end, 0) - 1);
        return getMaxOverlapped(end);
    }

    private int getMaxOverlapped(int end) {
        int maxOverlapped = 0;
        int overlapped = 0;
        for (int i = 0; i < count && sortedKeys[i] <= end; i++) {
            overlapped += calendar.get(sortedKeys[i]);
            if (overlapped > maxOverlapped) {
                maxOverlapped = overlapped;
            }
        }
        return maxOverlapped;
    }

    private void addDateKey(int date) {
        if (!calendar.containsKey(date)) {
            int i = count;
            while (i > 0 && sortedKeys[i - 1] > date) {
                sortedKeys[i] = sortedKeys[i - 1];
                i--;
            }
            sortedKeys[i] = date;
            count++;
        }
    }
}

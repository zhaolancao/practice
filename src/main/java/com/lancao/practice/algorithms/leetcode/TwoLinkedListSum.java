package com.lancao.practice.algorithms.leetcode;

/**
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * </p>
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 */
public class TwoLinkedListSum {
    //    Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 2. 两数相加
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode result = l1;
        ListNode previous = null;
        boolean isOverflowed = false;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val;
            if (isOverflowed) {
                sum++;
            }
            isOverflowed = false;
            if (sum >= 10) {
                sum -= 10;
                isOverflowed = true;
            }
            l1.val = sum;
            previous = l1;
            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode remaining = l1 == null ? l2 : l1;
        while (remaining != null) {
            previous.next = remaining;
            if (isOverflowed) {
                isOverflowed = false;
                int sum = remaining.val + 1;
                if (sum >= 10) {
                    sum -= 10;
                    isOverflowed = true;
                }
                remaining.val = sum;
            }
            previous = remaining;
            remaining = remaining.next;
        }
        if (isOverflowed) {
            previous.next = new ListNode(1);
        }
        return result;
    }

    /**
     * 86. 分隔链表
     * </p>
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     */
    public static ListNode partition(ListNode head, int x) {
        // [1,4,3,2,5,2]:3
        ListNode cursor = head;
        ListNode preNoLess = null;
        while (cursor != null && cursor.val < x) {
            preNoLess = cursor;
            cursor = cursor.next;
        }
        if (preNoLess == null) {
            while (cursor != null && cursor.val >= x) {
                preNoLess = cursor;
                cursor = cursor.next;
            }
            if (cursor == null) {
                return head;
            }
            preNoLess.next = cursor.next;
            cursor.next = head;
            head = cursor;
            cursor = cursor.next;
            preNoLess = head;
        }
        while (cursor != null) {
            ListNode firstNoLess = preNoLess.next;
            ListNode preLess = cursor;
            while (cursor != null && cursor.val >= x) {
                preLess = cursor;
                cursor = cursor.next;
            }
            while (cursor != null && cursor.val < x) {
                preNoLess.next = cursor;
                preNoLess = preNoLess.next;
                preLess.next = cursor.next;
                cursor = cursor.next;
            }
            preNoLess.next = firstNoLess;
        }
        return head;
    }

    /**
     * 61. 旋转链表
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode tail = head;
        while (tail.next != null) {
            length++;
            tail = tail.next;
        }
        length++;
        if (k >= length) {
            k = k % length;
        }
        if (k == 0) {
            return head;
        }
        length -= k;
        int i = 1;
        ListNode newTail = head;
        while (i++ < length) {
            newTail = newTail.next;
        }
        tail.next = head;
        head = newTail.next;
        newTail.next = null;
        return head;
    }
}

package com.lancao.practice.algorithms.leetcode;

import com.lancao.practice.algorithms.leetcode.TwoLinkedListSum.ListNode;
import org.junit.jupiter.api.Test;

class TwoLinkedListSumTest {

    @Test
    void addTwoNumbers() {
        print(TwoLinkedListSum.addTwoNumbers(buildListNode(new int[]{2, 0, 2, 5}), buildListNode(new int[]{6, 2, 8})));
        print(TwoLinkedListSum.addTwoNumbers(buildListNode(new int[]{2, 0, 2, 5}), buildListNode(new int[]{0})));
        print(TwoLinkedListSum.addTwoNumbers(buildListNode(new int[]{0}), buildListNode(new int[]{6, 2, 8})));

    }

    private static ListNode buildListNode(int[] elements) {
        if (elements.length == 0) {
            return null;
        }
        ListNode node = new ListNode(elements[0]);
        int i = 1;
        ListNode cursor = node;
        while (i < elements.length) {
            cursor.next = new ListNode(elements[i]);
            cursor = cursor.next;
            i++;
        }
        return node;
    }

    private static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
}
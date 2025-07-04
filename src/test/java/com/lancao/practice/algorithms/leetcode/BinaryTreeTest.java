package com.lancao.practice.algorithms.leetcode;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

class BinaryTreeTest {
    BinaryTree classToTest = new BinaryTree();

    @Test
    void buildTree() {
        printTradeNode(classToTest.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
        printTradeNode(classToTest.buildTree(new int[]{3, 1, 2, 4}, new int[]{1, 2, 3, 4}));
    }

    private static void printTradeNode(TreeNode treeNode) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(treeNode);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            System.out.print(node.val + " ");
            if (node.left != null) {
                q.add(node.left);
            }
            if (node.right != null) {
                q.add(node.right);
            }
        }
    }
}
package com.lancao.practice.algorithms.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    /**
     * 105. 从前序与中序遍历序列构造二叉树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        Queue<Integer> preOrderQueue = new LinkedList<>();
        for (int i = 0; i < preorder.length; i++) {
            preOrderQueue.add(preorder[i]);
        }
        return buildNode(preOrderQueue, inorder, 0, inorder.length - 1);
    }

    private static int getInOrderIndex(int[] inorder, int left, int right, int rootVal) {
        int inOrderIndex = left;
        for (int i = left; i <= right; i++) {
            if (inorder[i] == rootVal) {
                inOrderIndex = i;
                break;
            }
        }
        return inOrderIndex;
    }

    private TreeNode buildNode(Queue<Integer> preorder, int[] inorder, int left, int right) {
        int rootVal = preorder.poll();
        TreeNode curRoot = new TreeNode(rootVal);
        if (left == right) {
            return curRoot;
        }
        int inOrderIndex = getInOrderIndex(inorder, left, right, rootVal);
        if (inOrderIndex > left && !preorder.isEmpty()) {
            curRoot.left = buildNode(preorder, inorder, left, inOrderIndex - 1);
        }
        if (inOrderIndex < right && !preorder.isEmpty()) {
            curRoot.right = buildNode(preorder, inorder, inOrderIndex + 1, right);
        }
        return curRoot;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int x) {
        val = x;
    }
}

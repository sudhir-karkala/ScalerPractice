package com.scaler.trees;

/**
 * Given a binary tree, determine if it is height-balanced.
 *
 * @author sudhir on 06-May-2020
 */
public class BalancedBinaryTree {
    private boolean isHeightBalanced = true;

    public int isBalanced(TreeNode A) {
        int height = findHeight(A);
        return isHeightBalanced ? 1 : 0;
    }

    private int findHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int hl = findHeight(root.left);
        int hr = findHeight(root.right);
        // while finding height recursively, if the height of left and right subtrees are not balanced,
        // then we immediately set the boolean variable isHeightBalanced to false.
        if (Math.abs(hl - hr) > 1) {
            isHeightBalanced = false;
        }
        return Math.max(hl, hr) + 1;
    }
}

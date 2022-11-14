package com.scaler.bst;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST). Assume a BST is defined as follows:<br/>
 * The left subtree of a node contains only nodes with keys less than the node's key.<br/>
 * The right subtree of a node contains only nodes with keys greater than the node's key.<br/>
 * Both the left and right subtrees must also be binary search trees.<br/>
 * Return 0 / 1 ( 0 for false, 1 for true ) for this problem
 *
 * @author sudhir on 09-May-2020
 */
public class ValidBinarySearchTree {
    static class Pair {
        int max;
        int min;

        public Pair(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }

    private int isBST = 1;

    public int isValidBST(TreeNode A) {
        Pair max_min_root = max_min(A);
        return isBST;
    }

    private Pair max_min(TreeNode root) {
        if (root == null) {
            return new Pair(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        Pair L = max_min(root.left);
        Pair R = max_min(root.right);
        if (root.val <= L.max || root.val >= R.min) {
            isBST = 0;
        }
        return new Pair(Math.max(root.val, R.max), Math.min(root.val, L.min));
    }
}

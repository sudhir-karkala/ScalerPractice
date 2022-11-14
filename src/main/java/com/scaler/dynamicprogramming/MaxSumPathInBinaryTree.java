package com.scaler.dynamicprogramming;

/**
 * Given a binary tree T, find the maximum path sum.
 * <p>
 * The path may start and end at any node in the tree.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= Number of Nodes <= 7e4
 * <p>
 * -1000 <= Value of Node in T <= 1000
 * <p>
 * Input Format
 * <p>
 * The first and the only argument contains a pointer to the root of T, A.
 * <p>
 * Output Format
 * Return an integer representing the maximum sum path.
 *
 * @author sudhir on 28-Sep-2020
 */
public class MaxSumPathInBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    private static int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode A) {
        ans = Integer.MIN_VALUE;
        maxPathSumUtil(A);
        return ans;
    }

    private int maxPathSumUtil(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(maxPathSumUtil(root.left), 0);
        int right = Math.max(maxPathSumUtil(root.right), 0);
        ans = Math.max(ans, left + right + root.val);
        return root.val + Math.max(left, right);
    }
}

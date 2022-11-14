package com.scaler.trees;

/**
 * Given a binary tree. Check whether the given tree is a Sum-binary Tree or not.
 * <p>
 * Sum-binary Tree is a Binary Tree where the value of a every node is equal to sum of the nodes
 * present in its left subtree and right subtree.
 * <p>
 * An empty tree is Sum-binary Tree and sum of an empty tree can be considered as 0.
 * A leaf node is also considered as SumTree.
 * <p>
 * Return 1 if it sum-binary tree else return 0.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of the array <= 100000
 * <p>
 * 0 <= node values <= 50
 * <p>
 * Input Format
 * <p>
 * The only argument given is the root node of tree A.
 * <p>
 * Output Format
 * <p>
 * Return 1 if it is sum-binary tree else return 0.
 *
 * @author sudhir on 16-Sep-2020
 */
public class SumBinaryTreeOptimized {
    private boolean isSumTree = true;

    /**
     * Time complexity: O(n) and space complexity is O(h) where h = height of the tree
     *
     * @param A
     * @return
     */
    public int solve(TreeNode A) {
        isSumTree = true;
        calculateSumTree(A);
        return isSumTree ? 1 : 0;
    }

    private int calculateSumTree(TreeNode root) {
        // empty tree or leaf node is a sumtree
        if (root == null) {
            return 0;
        }
        int leftSum = calculateSumTree(root.left);
        int rightSum = calculateSumTree(root.right);
        if (root.val != (leftSum + rightSum) && (leftSum > 0 || rightSum > 0)) {
            isSumTree = false;
        }
        return leftSum + rightSum + root.val;
    }
}

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
public class SumBinaryTreeNonOptimized {
    /**
     * This takes O(n^2) time in the worst case and space complexity is O(h) where h = height of the tree
     *
     * @param A
     * @return
     */
    public int solve(TreeNode A) {
        return isSumTree(A) ? 1 : 0;
    }

    private boolean isSumTree(TreeNode A) {
        if (A == null) {
            return true;
        }
        if (A.left == null && A.right == null) {
            return true;
        }
        int leftSum = sum(A.left);
        int rightSum = sum(A.right);
        // if the current node satisfies sumTree property, then we check for left subtree and right subtree
        // and repeat the same steps. Hence time taken is O(n^2).
        return (A.val == (leftSum + rightSum)) && isSumTree(A.left) && isSumTree(A.right);
    }

    // Method to sum all nodes rooted at 'root'
    private int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        return root.val + sum(root.left) + sum(root.right);
    }
}

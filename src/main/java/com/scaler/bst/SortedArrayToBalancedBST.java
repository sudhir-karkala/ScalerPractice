package com.scaler.bst;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height Balanced Binary Search Tree (BBST).
 * <p>
 * Balanced tree : a height-balanced binary tree is defined as a binary tree in which the
 * depth of the two subtrees of every node never differ by more than 1.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of array <= 100000
 * <p>
 * Input Format
 * <p>
 * First argument is an integer array A.
 * <p>
 * Output Format
 * <p>
 * Return a root node of the Binary Search Tree.
 *
 * @author sudhir on 22/08/20
 */
public class SortedArrayToBalancedBST {
    public TreeNode sortedArrayToBST(final int[] A) {
        TreeNode root = null;
        root = sortedArrayToBST(A, 0, A.length-1);
        return root;
    }
    private TreeNode sortedArrayToBST(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int  mid = start + (end-start) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = sortedArrayToBST(arr, start, mid - 1);
        root.right = sortedArrayToBST(arr, mid + 1, end);
        return root;
    }
}

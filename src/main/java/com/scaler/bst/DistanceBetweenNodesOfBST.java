package com.scaler.bst;

/**
 * Given a binary search tree.
 * Return the distance between two nodes with given two keys B and C. It may be assumed that both keys exist in BST.
 * <p>
 * NOTE: Distance between two nodes is number of edges between them.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= Number of nodes in binary tree <= 1000000
 * <p>
 * 0 <= node values <= 10^9
 * <p>
 * Input Format
 * <p>
 * First argument is a root node of the binary tree, A.
 * <p>
 * Second argument is an integer B.
 * <p>
 * Third argument is an integer C.
 * <p>
 * Output Format
 * <p>
 * Return an integer denoting the distance between two nodes with given two keys B and C
 *
 * @author sudhir on 23-May-2020
 */
public class DistanceBetweenNodesOfBST {
    public int solve(TreeNode A, int B, int C) {
        int key1 = Math.min(B, C);
        int key2 = Math.max(B, C);
        return distanceBetween2Nodes(A, key1, key2);
    }

    private int distanceBetween2Nodes(TreeNode root, int key1, int key2) {
        if (root.val < key1 && root.val < key2) {
            // go to right subtree
            return distanceBetween2Nodes(root.right, key1, key2);
        } else if (root.val > key1 && root.val > key2) {
            // go to left subtree
            return distanceBetween2Nodes(root.left, key1, key2);
        } else {
            // here root.val >= key1 and root.val <= key2
            // this means root is the ancestor for key1 and key2
            return distanceFromRoot(root, key1) + distanceFromRoot(root, key2);
        }
    }

    private int distanceFromRoot(TreeNode root, int key) {
        if (root.val == key) {
            return 0;
        } else if (root.val > key) {
            return 1 + distanceFromRoot(root.left, key);
        } else {
            return 1 + distanceFromRoot(root.right, key);
        }
    }
}

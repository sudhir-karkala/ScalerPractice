package com.scaler.trees;

import java.util.ArrayList;

/**
 * Given a binary tree of integers. Return an array of integers representing the Top view of the Binary tree. <br/>
 * Top view of a Binary Tree is a set of nodes visible when the tree is visited from Top side.<br/>
 * Note: Return the nodes in any order.
 *
 * @author sudhir on 06-May-2020
 */
public class TopViewOfBinaryTree {
    public ArrayList<Integer> solve(TreeNode A) {
        ArrayList<Integer> result = new ArrayList<>();
        TreeNode root = A;
        if (root == null) {
            return result;
        }
        result.add(root.val);
        while (root.left != null) {
            result.add(root.left.val);
            root = root.left;
        }
        root = A;
        while (root.right != null) {
            result.add(root.right.val);
            root = root.right;
        }
        return result;
    }
}

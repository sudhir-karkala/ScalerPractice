package com.scaler.trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a binary tree of integers. Return an array of integers representing the right view of the Binary tree.
 * Right view of a Binary Tree is a set of nodes visible when the tree is visited from Right side.
 *
 * @author sudhir on 06-May-2020
 */
public class RightViewOfBinaryTree {
    public ArrayList<Integer> solve(TreeNode A) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(A);
        TreeNode end = A;
        ArrayList<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            if (p.left != null) {
                queue.add(p.left);
            }
            if (p.right != null) {
                queue.add(p.right);
            }
            if (p == end) {
                result.add(p.val);
                end = queue.peekLast();
            }
        }
        return result;
    }
}

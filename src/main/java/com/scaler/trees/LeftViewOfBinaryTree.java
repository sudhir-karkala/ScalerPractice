package com.scaler.trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a binary tree of integers. Return an array of integers representing the left view of the Binary tree.
 * Left view of a Binary Tree is a set of nodes visible when the tree is visited from Left side.
 *
 * @author sudhir on 07-May-2020
 */
public class LeftViewOfBinaryTree {
    public ArrayList<Integer> solve(TreeNode A) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(A);
        TreeNode first = A;
        ArrayList<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode p = queue.peek();
            if (p == first) {
                result.add(p.val);
            }
            int size = queue.size();
            for (int i = 1; i <= size; i++) {
                p = queue.poll();
                if (p.left != null) {
                    queue.add(p.left);
                }
                if (p.right != null) {
                    queue.add(p.right);
                }
            }
            first = queue.peek();
        }
        return result;
    }
}

package com.scaler.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given two binary trees, write a function to check if they are equal or not.
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 *
 * @author sudhir on 07-May-2020
 */
public class IdenticalBinaryTrees {
    public int isSameTree(TreeNode A, TreeNode B) {
        return isSameTreeUtil(A, B) ? 1 : 0;
    }

    // approach 2: recursive approach
    private boolean isSameTreeUtil(TreeNode A, TreeNode B) {
        if (A == null && B == null) {
            return true;
        }
        if (A == null || B == null) {
            return false;
        }
        return ((A.val == B.val) && isSameTreeUtil(A.left, B.left) && isSameTreeUtil(A.right, B.right));
    }

    // approach 1: iterative approach using level order traversal
    private boolean isSameTreeIterative(TreeNode A, TreeNode B) {
        if (A == null && B == null) {
            return true;
        }
        if (A == null || B == null) {
            return false;
        }

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(A);
        queue2.add(B);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            TreeNode p = queue1.poll();
            TreeNode q = queue2.poll();
            if (p.val != q.val) {
                return false;
            }
            if (p.left != null && q.left != null) {
                queue1.add(p.left);
                queue2.add(q.left);
            } else if (p.left != null || q.left != null) {
                return false;
            }
            if (p.right != null && q.right != null) {
                queue1.add(p.right);
                queue2.add(q.right);
            } else if (p.right != null || q.right != null) {
                return false;
            }
        }
        return true;
    }
}

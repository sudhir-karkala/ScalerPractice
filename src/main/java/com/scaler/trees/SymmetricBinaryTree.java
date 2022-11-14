package com.scaler.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center). <br/>
 * Return 0 / 1 ( 0 for false, 1 for true ) for this problem.
 *
 * @author sudhir on 08-May-2020
 */
public class SymmetricBinaryTree {
    // recursive approach: symmetric binary tree means that the binary tree is a mirror of itself.
    public int isSymmetric(TreeNode A) {
        return isSymmetricBinaryTree(A) ? 1 : 0;
    }

    // iterative approach
    public int isSymmetric2(TreeNode A) {
        return isSymmetricBinaryTree2(A) ? 1 : 0;
    }

    // this uses recursive approach to check if the given tree is a mirror image of itself
    private boolean isSymmetricBinaryTree(TreeNode A) {
        // to check if binary tree is symmetric tree or not, we pass the same root node as second parameter.
        return isMirror(A, A);
    }

    // this uses iterative approach to check if the given tree is a mirror image of itself
    private boolean isSymmetricBinaryTree2(TreeNode A) {
        // to check if binary tree is symmetric tree or not, we pass the same root node as second parameter.
        return isMirror2(A, A);
    }

    // approach 1: recursive approach to check if two binary trees are mirrors of each other or not
    private boolean isMirror(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return (root1.val == root2.val) && isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
    }

    // approach 2: iterative approach to check if two binary trees are mirrors of each other or not
    private boolean isMirror2(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root1);
        queue.offer(root2);
        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            // if both are null, continue and check for further nodes
            if (t1 == null && t2 == null) {
                continue;
            }
            if (t1 == null || t2 == null || t1.val != t2.val) {
                return false;
            }
            queue.offer(t1.left);
            queue.offer(t2.right);
            queue.offer(t1.right);
            queue.offer(t2.left);
        }
        return true;
    }
}

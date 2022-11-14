package com.scaler.trees;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a binary tree with root node pointer A and an integer B.
 * <p>
 * A number S is lost in the tree but you somehow find how to restore it.
 * <p>
 * S is formed by concatenating all the node values from left to right at level B.
 * <p>
 * Find and return the number S.
 * <p>
 * If there are no nodes at level B then return an empty string.
 * <p>
 * Note: Consider the level of root node as 0.
 * <p>
 * 1 <= Total number of nodes <= 100000
 * <p>
 * 0 < = Nodes values <= 9
 * <p>
 * 1 <= B <= 100
 *
 * @author sudhir on 05-Aug-2020
 */
public class AmazingTree {
    public String solve(TreeNode A, int B) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(A);
        StringBuilder builder = new StringBuilder();
        TreeNode endNode = A;
        int level = 0;
        while (!queue.isEmpty()) {
            TreeNode t = queue.remove();
            if (t.left != null) {
                queue.add(t.left);
            }
            if (t.right != null) {
                queue.add(t.right);
            }
            builder.append(t.val);
            if (t == endNode) {//means we have completed the current level
                endNode = queue.peekLast();//set the current end pointer for the next level
                if (level == B) {
                    return builder.toString();
                }
                builder = new StringBuilder();
                level++;
            }
        }
        return "";
    }
}

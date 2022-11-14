package com.scaler.trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 *
 * @author sudhir on 05-May-2020
 */
public class ZigZagLevelOrderTraversalBT {

    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode A) {
        Deque<TreeNode> queue = new LinkedList<>();
        boolean isLeftToRight = true;
        queue.add(A);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        TreeNode endNode = A;
        while (!queue.isEmpty()) {
            TreeNode t = queue.remove();
            if (t.left != null) {
                queue.add(t.left);
            }
            if (t.right != null) {
                queue.add(t.right);
            }
            if (isLeftToRight) {
                temp.add(t.val);
            } else {
                temp.add(0, t.val);
            }
            if (t == endNode) {//means we have completed the current level
                result.add(new ArrayList<>(temp));
                temp.clear();
                endNode = queue.peekLast();//set the current end pointer for the next level
                // toggle the flag for the next level
                isLeftToRight = !isLeftToRight;
            }
        }
        return result;
    }
}

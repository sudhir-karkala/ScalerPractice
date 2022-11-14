package com.scaler.trees;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * @author sudhir on 11-Apr-2020
 */
public class LevelOrder {

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode A) {
        Deque<TreeNode> queue = new LinkedList<>();
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
            temp.add(t.val);
            if (t == endNode) {//means we have completed the current level
                result.add(new ArrayList<>(temp));
                temp.clear();
                endNode = queue.peekLast();//set the current end pointer for the next level
            }
        }
        return result;
    }
}

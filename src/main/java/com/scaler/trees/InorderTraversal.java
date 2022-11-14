package com.scaler.trees;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Inorder traversal without using recursion.
 *
 * @author sudhir on 11-Apr-2020
 */
public class InorderTraversal {

    public ArrayList<Integer> inorderTraversal(TreeNode A) {
        Stack<TreeNode> st = new Stack<>();
        ArrayList<Integer> result = new ArrayList<>();
        TreeNode p = A;
        while (!st.isEmpty() || p != null) {
            if (p != null) {
                st.push(p);
                p = p.left;
            } else {
                if (!st.isEmpty()) {
                    TreeNode top = st.pop();
                    p = top.right;
                    result.add(top.val);
                }
            }
        }
        return result;
    }
}

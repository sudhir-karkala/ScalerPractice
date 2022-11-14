package com.scaler.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values
 *
 * @author sudhir on 03-May-2020
 */
public class PostorderTraversal {

    public ArrayList<Integer> postorderTraversal(TreeNode A) {
        Stack<TreeNode> st = new Stack<>();
        st.push(A);
        ArrayList<Integer> result = new ArrayList<>();
        while (!st.isEmpty()) {
            TreeNode top = st.pop();
            if (top.left != null) {
                st.push(top.left);
            }
            if (top.right != null) {
                st.push(top.right);
            }
            result.add(top.val);
        }
        Collections.reverse(result);
        return result;
    }
}

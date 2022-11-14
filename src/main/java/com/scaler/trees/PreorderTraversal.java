package com.scaler.trees;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Preorder traversal without using recursion.
 *
 * @author sudhir on 15-Apr-2020
 */
public class PreorderTraversal {

    public ArrayList<Integer> preorderTraversal(TreeNode A) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        TreeNode p = A;
        if (p == null) {
            return result;
        }
        st.push(p);
        while (!st.isEmpty()) {
            TreeNode t = st.pop();
            result.add(t.val);
            if (t.right != null) {
                st.push(t.right);
            }
            if (t.left != null) {
                st.push(t.left);
            }
        }
        return result;
    }
}

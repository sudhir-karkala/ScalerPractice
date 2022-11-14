package com.scaler.bst;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Given a binary search tree of integers. You are given a range [B, C].
 * Return the count of the number of nodes that lies in this range.<br/>
 * 0 <= B < = C <= 10^9 <br/>
 * 1 <= Number of nodes in binary tree <= 100000
 *
 * @author sudhir on 22-May-2020
 */
public class BSTNodesInARangeSol1 {
    public int solve(TreeNode A, int B, int C) {
        ArrayList<Integer> inorder = inorderTraversal(A);
        int size = inorder.size();
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (inorder.get(i) >= B && inorder.get(i) <= C) {
                count++;
            }
        }
        return count;

    }

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

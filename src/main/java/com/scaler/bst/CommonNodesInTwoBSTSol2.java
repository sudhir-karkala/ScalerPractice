package com.scaler.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given two BST's A and B, return the (sum of all common nodes in both A and B) % (10^9 +7) .
 * <p>
 * In case there is no common node, return 0.
 * <p>
 * NOTE:
 * <p>
 * Try to do it one pass through the trees.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= Number of nodes in the tree A and B <= 10^5
 * <p>
 * 1 <= Node values <= 10^6
 * <p>
 * Input Format
 * <p>
 * First argument represents the root of BST A.
 * <p>
 * Second argument represents the root of BST B.
 * <p>
 * Output Format
 * Return an integer denoting the (sum of all common nodes in both BST's A and B) % (10^9 +7) .
 *
 * @author sudhir on 30-Aug-2020
 */
public class CommonNodesInTwoBSTSol2 {
    /**
     * Efficient solution in terms of memory usage.
     * Time complexity: O(m + n) where m = size of tree A, n = size of tree B
     * Space complexity: O(h1 + h2) where h1 = height of A, h2 = height of B
     *
     * @param A
     * @param B
     * @return
     */
    public int solve(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return 0;
        }
        long sum = 0L;
        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        TreeNode root1 = A;
        TreeNode root2 = B;
        int mod = (int) 1e9 + 7;
        while (true) {
            if (root1 != null) {
                st1.push(root1);
                root1 = root1.left;
            } else if (root2 != null) {
                st2.push(root2);
                root2 = root2.left;
            } else if (!st1.isEmpty() && !st2.isEmpty()) {
                // if there are items to compare in st1 and st2, only then we enter here.
                root1 = st1.peek();
                root2 = st2.peek();
                // if both values are equal, then we pop item from both stacks.
                if (root1.val == root2.val) {
                    st1.pop();
                    st2.pop();
                    sum = ((sum % mod) + (1L * root1.val) % mod) % mod;
                    root1 = root1.right;
                    root2 = root2.right;
                } else if (root1.val < root2.val) {
                    // if val from st1 < val from st2, then we should understand that
                    // there can be greater items from root1's in order successor.
                    // so we set root1 = root1.right and set root2 = null so that we can
                    // push items into st1.
                    st1.pop();
                    root1 = root1.right;
                    root2 = null;
                } else {
                    // if val from st1 > val from st2, then we should understand that
                    // there can be greater items from root2's in order successor.
                    // so we set root2 = root2.right and set root1 = null so that we can
                    // push items into st2.
                    st2.pop();
                    root2 = root2.right;
                    root1 = null;
                }
            } else {
                // if one of the stacks is empty or root1 and root2 are null, then we are done.
                break;
            }
        }
        return (int) sum;
    }
}

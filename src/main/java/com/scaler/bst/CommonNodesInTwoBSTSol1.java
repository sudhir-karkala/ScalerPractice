package com.scaler.bst;

import java.util.ArrayList;
import java.util.List;

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
public class CommonNodesInTwoBSTSol1 {
    /**
     * This solution doesn't pass efficiency
     * Time complexity: O(m + n) where m = size of tree A, n = size of tree B
     * Space complexity: O(m + n)
     * @param A
     * @param B
     * @return
     */
    public int solve(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return 0;
        }
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        // get the inorder traversals of A and B.
        inorder(A, l1);
        inorder(B, l2);
        int mod = (int) 1e9 + 7;
        int i = 0, j = 0;
        int m = l1.size();
        int n = l2.size();
        long sum = 0L;
        // find the intersection nodes between l1 and l2 and sum them.
        while (i < m && j < n) {
            if (l1.get(i) == l2.get(j)) {
                sum = ((sum % mod) + ((1L * l1.get(i)) % mod)) % mod;
                i++;
                j++;
            } else if (l1.get(i) < l2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return (int) sum;
    }

    // inorder traversal of tree rooted at A stored in list 'res'.
    private void inorder(TreeNode A, List<Integer> res) {
        if (A == null) {
            return;
        }
        inorder(A.left, res);
        res.add(A.val);
        inorder(A.right, res);
    }
}

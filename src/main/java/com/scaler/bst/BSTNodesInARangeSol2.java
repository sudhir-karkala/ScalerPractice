package com.scaler.bst;

/**
 * Given a binary search tree of integers. You are given a range [B, C].
 * Return the count of the number of nodes that lies in this range.<br/>
 * 0 <= B < = C <= 10^9 <br/>
 * 1 <= Number of nodes in binary tree <= 100000
 *
 * @author sudhir on 22-May-2020
 */
public class BSTNodesInARangeSol2 {
    public int solve(TreeNode A, int B, int C) {
        return countOfBSTNodes(A, B, C);
    }

    private int countOfBSTNodes(TreeNode root, int start, int end) {
        // if root is null, then return 0.
        if (root == null) {
            return 0;
        }
        // if root.val == start and end, then return 1, no need to recurse further.
        if (root.val == start && root.val == end) {
            return 1;
        }
        // if root.val lies in between start and end, then add it to the count and
        // recurse to both left and right to find other nodes.
        if (root.val >= start && root.val <= end) {
            return 1 + countOfBSTNodes(root.left, start, end)
                    + countOfBSTNodes(root.right, start, end);
        } else if (root.val < start) {
            // if root.val < start, it means that we will get still lesser values going left.
            // so take right.
            return countOfBSTNodes(root.right, start, end);
        } else if (root.val > end) {
            // if root.val > end, it means that we will get still greater values going right.
            // so take left.
            return countOfBSTNodes(root.left, start, end);
        } else {
            // if none of the cases hold, then return 0.
            return 0;
        }
    }
}

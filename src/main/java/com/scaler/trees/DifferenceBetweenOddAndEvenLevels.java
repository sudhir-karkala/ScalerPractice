package com.scaler.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree of integers. Find the difference between the sum of nodes at odd level and sum of nodes at even level. <br/>
 * Note: Consider the level of root node as 1.
 *
 * @author sudhir on 07-May-2020
 */
public class DifferenceBetweenOddAndEvenLevels {
    public int solve(TreeNode A) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(A);
        int level = 1;
        int evenSum = 0;
        int oddSum = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            int sum = 0;
            // find the sum of all node values at the current level
            for (int i = 1; i <= n; i++) {
                TreeNode t = queue.remove();
                if (t.left != null) {
                    queue.add(t.left);
                }
                if (t.right != null) {
                    queue.add(t.right);
                }
                sum += t.val;
            }
            if (level % 2 == 0) {// means we are at even level, so we update evenSum
                evenSum += sum;
            } else {// means we are at odd level, so we update oddSum
                oddSum += sum;
            }
            level++;
        }
        return (oddSum - evenSum);
    }
}

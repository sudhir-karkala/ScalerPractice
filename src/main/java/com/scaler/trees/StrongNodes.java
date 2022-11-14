package com.scaler.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Solo likes to solve simple problems, but this time she is stuck with an easy problem.
 * Since she has to go to her friend house, can you solve this problem for her?
 * <p>
 * Given a binary Tree denoted by root node A, you need to count the number of strong nodes in a binary tree.
 * <p>
 * A node x is strong, if in the path from root to the node x there is no node with greater value than x's.
 * <p>
 * 1 <= number of nodes <= 10^5
 * <p>
 * 1 <= value of nodes <= 10^5
 *
 * @author sudhir on 21-Jul-2020
 */
public class StrongNodes {
    static class Pair {
        int max;
        TreeNode node;

        Pair(int max, TreeNode node) {
            this.max = max;
            this.node = node;
        }
    }

    public int solve(TreeNode A) {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(A.val, A));
        int count = 0;
        while (!queue.isEmpty()) {
            Pair p = queue.remove();
            if (p.node.val >= p.max) {
                count++;
            }
            TreeNode t = p.node;
            if (t.left != null) {
                queue.add(new Pair(Math.max(p.max, t.left.val), t.left));
            }
            if (t.right != null) {
                queue.add(new Pair(Math.max(p.max, t.right.val), t.right));
            }
        }
        return count;
    }
}

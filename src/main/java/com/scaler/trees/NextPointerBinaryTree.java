package com.scaler.trees;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.<br/>
 * Note:<br/>
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 *
 * @author sudhir on 05-May-2020
 */
public class NextPointerBinaryTree {
    static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    // approach 1: this method uses O(n) extra space in the form of queue.
    public void connect(TreeLinkNode root) {
        Deque<TreeLinkNode> queue = new LinkedList<>();
        queue.add(root);
        TreeLinkNode temp = null;
        while (!queue.isEmpty()) {
            int n = queue.size();
            // once we get the queue size at that level, we iterate over that set and link nodes at that level.
            // once a level is done, we would have pushed the child nodes to the queue, and calculate the queue
            // size again and continue with the next set of tree nodes.
            for (int i = 0; i < n; i++) {
                TreeLinkNode prev = temp;
                temp = queue.poll();
                if (i > 0) {
                    prev.next = temp;
                }
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            temp.next = null;// set the next pointer of last node in the level to null
        }
    }

    // approach 2: this method uses constant extra space and links next pointers level by level
    public void connect2(TreeLinkNode root) {
        TreeLinkNode list = root;
        while (list != null) {
            TreeLinkNode p = list;
            while (p != null) {
                if (p.left != null) {
                    p.left.next = p.right;
                }
                if (p.right != null && p.next != null) {
                    p.right.next = p.next.left;
                }
                p = p.next;
            }
            // go to the start of the next level
            list = list.left;
        }
    }
}

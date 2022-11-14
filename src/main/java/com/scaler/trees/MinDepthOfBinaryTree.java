package com.scaler.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path
 * from the root node down to the nearest leaf node.
 *
 * @author sudhir on 25-Aug-2020
 */
public class MinDepthOfBinaryTree {
    /**
     * This is efficient as we will stop traversing the whole tree once we get the node
     * which has no left and right child. This is done using level order traversal.
     *
     * @param A
     * @return
     */
    public int minDepth(TreeNode A) {
        if (A == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(A);
        int minDepth = 0;
        int queueSize = 1;
        while (!queue.isEmpty()) {
            minDepth++;
            int count = 0;
            for (int i = 0; i < queueSize; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return minDepth;
                }
                // if node has left child, add it to the queue.
                if (node.left != null) {
                    queue.offer(node.left);
                    count++;
                }
                // if node has right child, add it to the queue.
                if (node.right != null) {
                    queue.offer(node.right);
                    count++;
                }
            }
            // queueSize() function takes O(queue_size) time to compute each time.
            // hence we can manually track the count of nodes inserted into the queue
            // and use it for the purpose of polling from the queue.
            queueSize = count;
        }
        return minDepth;
    }

    /**
     * This uses recursion and traverses the complete tree to find min depth.
     *
     * @param A
     * @return
     */
    public int minDepth2(TreeNode A) {
        if (A == null) {
            return 0;
        }
        if (A.left == null && A.right == null) {
            // means it is a leaf node.
            return 1;
        }
        // if left subtree is null, then recur for right subtree.
        if (A.left == null) {
            return minDepth(A.right) + 1;
        }
        // if right subtree is null, then recur for left subtree.
        if (A.right == null) {
            return minDepth(A.left) + 1;
        }
        // if both left and right subtrees are not null, then find min of depth of left
        // and right subtrees.
        return Math.min(minDepth2(A.left), minDepth2(A.right)) + 1;
    }

    public static void main(String[] args) {
        MinDepthOfBinaryTree m = new MinDepthOfBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(m.minDepth(root));
        System.out.println(m.minDepth2(root));
    }
}

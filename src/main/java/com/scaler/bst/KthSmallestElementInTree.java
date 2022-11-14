package com.scaler.bst;

/**
 * Given a binary search tree, write a function to find the kth smallest element in the tree.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= Number of nodes in binary tree <= 100000
 * <p>
 * 0 <= node values <= 10^9
 *
 * @author sudhir on 19-May-2020
 */
public class KthSmallestElementInTree {
    private static int count = 0;

    /**
     * kth smallest using inorder traversal by maintaining count variable.
     * When we reach kth node, we can stop traversing the tree and return the answer.
     * This takes recursive stack space of O(height) and time complexity of O(number of nodes)
     *
     * @param A
     * @param B
     * @return
     */
    public int kthsmallest(TreeNode A, int B) {
        count = 0;
        TreeNode node = kthsmallestNode(A, B);
        return node.val;
    }

    private TreeNode kthsmallestNode(TreeNode root, int k) {
        if (root == null) {
            return null;
        }

        TreeNode left = kthsmallestNode(root.left, k);
        // only when count == k, then left will have the required answer.
        if (left != null) {
            return left;
        }
        count++;
        if (count == k) {
            return root;
        }
        return kthsmallestNode(root.right, k);
    }

    public static void main(String[] args) {
        KthSmallestElementInTree kthSmallest = new KthSmallestElementInTree();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        System.out.println(kthSmallest.kthsmallest(root, 3));
    }
}

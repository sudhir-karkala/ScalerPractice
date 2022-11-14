package com.scaler.trees;

import java.util.ArrayList;

/**
 * Given a binary tree, return the values of its boundary in
 * anti-clockwise direction starting from the root.
 * Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
 * <p>
 * Left boundary is defined as the path from the root to the left-most node.
 * Right boundary is defined as the path from the root to the right-most node.
 * If the root doesn't have left subtree or right subtree,
 * then the root itself is left boundary or right boundary.
 * Note this definition only applies to the input binary tree, and not applies to any subtrees.
 * <p>
 * The left-most node is defined as a leaf node you could reach when you always
 * firstly travel to the left subtree if exists.
 * If not, travel to the right subtree. Repeat until you reach a leaf node.
 * <p>
 * The right-most node is also defined by the same way with left and right exchanged.
 * <p>
 * Return an array of integers denoting the boundary values of tree in anti-clockwise order.
 *
 * @author sudhir on 25-Aug-2020
 */
public class BoundaryTraversalOfBinaryTree {
    public ArrayList<Integer> solve(TreeNode A) {
        ArrayList<Integer> result = new ArrayList<>();
        if (A == null) {
            return result;
        }
        result.add(A.val);
        boundaryTraversalLeft(A.left, result);
        boundaryTraversalLeaves(A.left, result);
        boundaryTraversalLeaves(A.right, result);
        boundaryTraversalRight(A.right, result);
        return result;
    }

    private void boundaryTraversalLeft(TreeNode node, ArrayList<Integer> result) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            result.add(node.val);
            boundaryTraversalLeft(node.left, result);
        } else if (node.right != null) {
            result.add(node.val);
            boundaryTraversalLeft(node.right, result);
        }
        // else do nothing as we are avoiding leaf nodes in this method.
    }

    private void boundaryTraversalRight(TreeNode node, ArrayList<Integer> result) {
        if (node == null) {
            return;
        }
        if (node.right != null) {
            boundaryTraversalRight(node.right, result);
            result.add(node.val);
        } else if (node.left != null) {
            boundaryTraversalRight(node.left, result);
            result.add(node.val);
        }
        // else do nothing as we are avoiding leaf nodes in this method.
    }

    private void boundaryTraversalLeaves(TreeNode node, ArrayList<Integer> result) {
        if (node == null) {
            return;
        }
        boundaryTraversalLeaves(node.left, result);
        if (node.left == null && node.right == null) {
            result.add(node.val);
        }
        boundaryTraversalLeaves(node.right, result);
    }

    public static void main(String[] args) {
        BoundaryTraversalOfBinaryTree b = new BoundaryTraversalOfBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(b.solve(root));
    }
}

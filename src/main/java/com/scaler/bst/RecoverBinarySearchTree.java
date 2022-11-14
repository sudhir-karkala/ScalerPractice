package com.scaler.bst;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Tell us the 2 values swapping which the tree will be restored.<br/>
 * Note: A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 *
 * @author sudhir on 19-May-2020
 */
public class RecoverBinarySearchTree {
    /**
     * This solution takes O(N) extra space to store inorder traversal.
     * Logic:
     * 1. Perform inorder traversal of the given tree
     * 2. a. Use first for loop from end of the array to find the first element
     * of the swapped pair.
     * b. Use the second for loop from the start of the array to find the second element
     * of the swapped pair.
     * Since, we need to return the elements like this => {smaller number, larger number},
     * first for loop gives smaller number and second for loop gives larger number.
     *
     * @param A
     * @return
     */
    public ArrayList<Integer> recoverTree(TreeNode A) {
        ArrayList<Integer> inorder = inorderTraversal(A);
        int size = inorder.size();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = size - 1; i > 0; i--) {
            int cur = inorder.get(i);
            if (cur < inorder.get(i - 1)) {
                result.add(cur);
                break;
            }
        }
        for (int i = 0; i < size - 1; i++) {
            int cur = inorder.get(i);
            if (cur > inorder.get(i + 1)) {
                result.add(cur);
                break;
            }
        }
        return result;
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

    /**
     * Method which uses O(1) extra space except for the recursive stack space.
     */
    private TreeNode previousNode = new TreeNode(Integer.MIN_VALUE);
    private TreeNode firstNode = null;
    private TreeNode secondNode = null;

    /**
     * We do inorder traversal and find two elements which are swapped.
     * This takes O(1) extra space if we ignore the recursive stack space used for the traversal.
     *
     * @param A
     * @return
     */
    public ArrayList<Integer> recoverTreeOptimized(TreeNode A) {
        traverse(A);
        ArrayList<Integer> result = new ArrayList<>();
        result.add(firstNode.val);
        result.add(secondNode.val);
        return result;
    }

    /**
     * Inorder traversal of the tree
     *
     * @param root
     */
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        // do some logic to get the swapped elements
        // Example: [6 3 4 5 2], firstNode will be 2 and secondNode will be 6
        //
        if (secondNode == null && previousNode.val > root.val) {
            secondNode = previousNode;
        }
        if (secondNode != null && previousNode.val > root.val) {
            firstNode = root;
        }
        // end of logic
        previousNode = root;
        traverse(root.right);
    }
}

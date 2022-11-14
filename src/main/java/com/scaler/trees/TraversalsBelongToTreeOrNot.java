package com.scaler.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given 3 array of integers A, B and C.<br/>
 * A represents preorder traversal of a binary tree.<br/>
 * B represents inorder traversal of a binary tree.<br/>
 * C represents postorder traversal of a binary tree.<br/>
 * Check whether these tree traversals are of the same tree or not. If they are of same tree return 1 else return 0.<br/>
 *
 * @author sudhir on 12-Apr-2020
 */
public class TraversalsBelongToTreeOrNot {
    private int result = 1;
    private int pIndex = 0;

    // approach 1: this method traverses the given arrays without constructing the tree and
    // maintains a map of inorder elements
    // Also, we use inorder and preorder arrays to check the given elements and check at respective positions
    // of the portorder
    // array if elements are in place or not.
    public int solve(ArrayList<Integer> preorder, ArrayList<Integer> inorder, ArrayList<Integer> postorder) {
        result = 1;
        int len1 = preorder.size();
        int len2 = inorder.size();
        int len3 = postorder.size();
        if (len1 == len2 && len2 == len3) {
            // construct a map of <key, value> where key=>element in the inorder_array, value=>index of that element
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < len2; i++) {
                map.put(inorder.get(i), i);
            }
            // continue checking the array contents
            // we pass the following parameters: preorder_array, preorder_index(start index),
            // inorder_start_index, inorder_end_index, postorder_array, postorder_index(end index) and map
            check(preorder, 0, 0, len2 - 1, postorder, len3 - 1, map);
            return result;
        } else {
            // since lengths are different, they don't belong to the same tree
            return 0;
        }
    }

    private void check(ArrayList<Integer> preorder, int preorderIndex, int inorderStart, int inorderEnd, ArrayList<Integer> postorder, int postorderIndex, Map<Integer, Integer> map) {
        if (inorderStart > inorderEnd) {
            return;
        }
        // check the index of preorder_array element in inorder_array
        Integer index = map.get(preorder.get(preorderIndex));
        if (index == null || !preorder.get(preorderIndex).equals(postorder.get(postorderIndex))) {
            result = 0;
            return;
        }
        // recurse for left subtree
        check(preorder, preorderIndex + 1, inorderStart, index - 1, postorder,
                postorderIndex - (inorderEnd - index) - 1, map);
        // recurse for right subtree
        check(preorder, preorderIndex + (index - inorderStart) + 1, index + 1, inorderEnd, postorder,
                postorderIndex - 1, map);
    }

    // approach 2: this method constructs a tree using given inorder and preorder arrays and then compares its
    // postorder traversal with the given postorder array
    public int solve2(ArrayList<Integer> preorder, ArrayList<Integer> inorder, ArrayList<Integer> postorder) {
        result = 1;
        pIndex = 0;
        int len1 = preorder.size();
        int len2 = inorder.size();
        int len3 = postorder.size();
        TreeNode root = null;
        if (len1 == len2 && len2 == len3) {
            // construct a map of <key, value> where key=>element in the inorder_array, value=>index of that element
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < len2; i++) {
                map.put(inorder.get(i), i);
            }
            // construct a binary tree using given inorder and preorder arrays
            root = buildTree(preorder, 0, len1 - 1, map);
            boolean check = checkIfPostOrderMatches(root, postorder);
            result = check ? 1 : 0;
            return result;
        } else {
            // since lengths are different, they don't belong to the same tree
            return 0;
        }
    }

    private boolean checkIfPostOrderMatches(TreeNode root, ArrayList<Integer> postorder) {
        // construct current postorder array
        ArrayList<Integer> currentPostOrderArray = new ArrayList<>();
        postorderTraversal(root, currentPostOrderArray);
        // compare elements one by one if they are same or not.
        return currentPostOrderArray.equals(postorder);
    }

    private void postorderTraversal(TreeNode root, ArrayList<Integer> postorderArray) {
        if (root == null) {
            return;
        }
        postorderTraversal(root.left, postorderArray);
        postorderTraversal(root.right, postorderArray);
        postorderArray.add(root.val);
    }

    private TreeNode buildTree(ArrayList<Integer> preorder, int start, int end, Map<Integer, Integer> map) {
        if (start > end || pIndex > preorder.size() - 1) {
            return null;
        }
        TreeNode root = new TreeNode(preorder.get(pIndex++));
        int mid = map.get(root.val);
        root.left = buildTree(preorder, start, mid - 1, map);
        root.right = buildTree(preorder, mid + 1, end, map);
        return root;
    }

    public static void main(String[] args) {
        TraversalsBelongToTreeOrNot t = new TraversalsBelongToTreeOrNot();
        ArrayList<Integer> preorder1 = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 3));
        ArrayList<Integer> inorder1 = new ArrayList<>(Arrays.asList(4, 2, 5, 1, 3));
        ArrayList<Integer> postorder1 = new ArrayList<>(Arrays.asList(4, 5, 2, 3, 1));
        System.out.println(t.solve(preorder1, inorder1, postorder1));
        System.out.println(t.solve2(preorder1, inorder1, postorder1));

        ArrayList<Integer> preorder2 = new ArrayList<>(Arrays.asList(13, 33, 41, 11, 49, 48, 23));
        ArrayList<Integer> inorder2 = new ArrayList<>(Arrays.asList(41, 33, 11, 13, 48, 49, 23));
        ArrayList<Integer> postorder2 = new ArrayList<>(Arrays.asList(41, 11, 33, 48, 23, 49, 13));
        System.out.println(t.solve(preorder2, inorder2, postorder2));
        System.out.println(t.solve2(preorder2, inorder2, postorder2));
    }
}

package com.scaler.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.<br/>
 * Note: You may assume that duplicates do not exist in the tree.
 *
 * @author sudhir on 11-Apr-2020
 */
public class BinaryTreeFromInorderAndPostorder {
    private static int pIndex = 0;// this pointer is for postorder_array

    public TreeNode buildTree(ArrayList<Integer> inorder, ArrayList<Integer> postorder) {
        int start = 0;
        int end = inorder.size() - 1;
        pIndex = postorder.size() - 1;
        // construct a map of <key, value> where key=>element in the inorder_array, value=>index of that element
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= end; i++) {
            map.put(inorder.get(i), i);
        }
        // start and end pointers are for inorder array
        // map is to store <value,index> for inorder array. i.e. inorder element x stored at index y <x,y>
        return buildTree(postorder, start, end, map);
    }

    private TreeNode buildTree(ArrayList<Integer> postorder, int start, int end, Map<Integer, Integer> map) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(postorder.get(pIndex--));
        int mid = map.get(root.val);
        root.right = buildTree(postorder, mid + 1, end, map);
        root.left = buildTree(postorder, start, mid - 1, map);
        return root;
    }
}

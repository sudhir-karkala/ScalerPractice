package com.scaler.trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Construct Binary Tree From Inorder And Preorder<br/>
 * Note: You may assume that duplicates do not exist in the tree.
 *
 * @author sudhir on 03-May-2020
 */
public class BinaryTreeFromInorderAndPreorder {
    private static int pIndex = 0;

    public TreeNode buildTree(ArrayList<Integer> preorder, ArrayList<Integer> inorder) {
        int start = 0;
        int end = inorder.size() - 1;
        // construct a map of <key, value> where key=>element in the inorder_array, value=>index of that element
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= end; i++) {
            map.put(inorder.get(i), i);
        }
        //start and end pointers are for inorder array
        //map is to store <value,index> for inorder array. i.e. inorder element x stored at index y <x,y>
        return buildTree(preorder, start, end, map);
    }

    private TreeNode buildTree(ArrayList<Integer> preorder, int start, int end, Map<Integer, Integer> map) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(preorder.get(pIndex++));
        int mid = map.get(root.val);
        root.left = buildTree(preorder, start, mid - 1, map);
        root.right = buildTree(preorder, mid + 1, end, map);
        return root;
    }

    public static void main(String[] args) {
        BinaryTreeFromInorderAndPreorder b = new BinaryTreeFromInorderAndPreorder();
        ArrayList<Integer> preorder = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> inorder = new ArrayList<>(Arrays.asList(3, 2, 4, 1, 5));
        TreeNode root = b.buildTree(preorder, inorder);
    }
}

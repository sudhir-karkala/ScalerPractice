package com.scaler.trees;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a binary tree of integers. Return whether it contains a duplicate sub-tree of size 2 or more. <br/>
 * All node values lie between 0 and 9 inclusive.
 * Return 1 if it contains a duplicate sub-tree of size 2 or more else return 0. <br/>
 * Note: Two same leaf nodes are not considered as a subtree, As the size of a leaf node is one.
 *
 * @author sudhir on 23-May-2020
 */
public class DuplicatesInBinaryTree {
    private static char MARKER = '#';

    /**
     * The solution is based on tree serialization and hashing.
     * The idea is to serialize subtrees as strings and store the strings in hash table.
     * Once we find a serialized tree (which is not a leaf) already existing in hash-set, we return true.
     *
     * @param A
     * @return
     */
    public int solve(TreeNode A) {
        String result = ""; // if the method returns empty string i.e. "", then it means duplicate exists.
        Set<String> subtrees = new HashSet<>();
        result = checkForDuplicates(A, subtrees);
        return "".equals(result) ? 1 : 0;
    }

    private String checkForDuplicates(TreeNode root, Set<String> subtrees) {
        String s = "";
        if (root == null) {
            return s + MARKER;
        }
        String lStr = checkForDuplicates(root.left, subtrees);

        // if the left subtree returns empty string "", it means it is a duplicate subtree. hence we return the same from the current node.
        if (lStr.equals(s)) {
            return s;
        }
        String rStr = checkForDuplicates(root.right, subtrees);

        // if the right subtree returns empty string "", it means it is a duplicate subtree. hence we return the same from the current node.
        if (rStr.equals(s)) {
            return s;
        }
        // compute the serialized string for the current node.
        s = s + root.val + lStr + rStr;
        // serializing leaf node will have string length as 3.
        // if a child is null, we assign '#' as the marker, similarly for right child if it is empty.
        // now serialized leaf node will be the concatenation of it's value and child nodes, resulting in a string of length 3
        // For example: for leaf node 3, the serialized string is "3##", similarly "5##" for leaf node 5, if their parent node is
        // 4, then it's serialized string will be "43##5##".
        if (s.length() > 3 && subtrees.contains(s)) {// means we have found a duplicate subtree.
            return "";
        }
        subtrees.add(s);
        // return the serialized string computed for the current node.
        return s;
    }
}

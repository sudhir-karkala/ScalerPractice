package com.scaler.bst;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Given a binary search tree T, where each node contains a positive integer, and an integer K,
 * you have to find whether or not there exist two different nodes A and B
 * such that A.value + B.value = K. <br/>
 * Return 1 to denote that two such nodes exist. Return 0, otherwise. <br/>
 * Notes: Your solution should run in linear time and not take memory more than O(height of T).<br/>
 * Assume all values in BST are distinct.
 *
 * @author sudhir on 18-May-2020
 */
public class TwoSumBinaryTree {
    /**
     * This approach uses stack to find inorder traversal and arraylist to store the inorder array.
     * Once the inorder array is obtained, two pointer approach is used to check
     * if 2-sum exists or not in the array.
     * Space complexity is O(number of nodes). Time complexity is O(number of nodes) which is linear.
     *
     * @param A
     * @param B
     * @return
     */
    public int t2Sum(TreeNode A, int B) {
        ArrayList<Integer> inorder = new ArrayList<>();
        // perform inorder traversal of the tree in iterative fashion
        TreeNode p = A;
        Stack<TreeNode> st = new Stack<>();
        while (!st.isEmpty() || p != null) {
            if (p != null) {
                st.push(p);
                p = p.left;
            } else {
                if (!st.isEmpty()) {
                    p = st.pop();
                    inorder.add(p.val);
                    p = p.right;
                }
            }
        }
        // two pointer approach to check if 2-sum exists or not in the array
        int start = 0;
        int end = inorder.size() - 1;
        while (start < end) {
            if (inorder.get(start) + inorder.get(end) == B) {
                return 1;
            } else if (inorder.get(start) + inorder.get(end) > B) {
                end--;
            } else {
                start++;
            }
        }
        return 0;
    }

    /**
     * HashSet based approach to check if 2-sum exists or not.
     * Time complexity is O(number of nodes) and Space complexity is O(number of nodes).
     *
     * @param A
     * @param B
     * @return
     */
    public int t2Sum2(TreeNode A, int B) {
        Set<Integer> set = new HashSet<>();
        return checkIfPairExists(A, B, set) ? 1 : 0;
    }

    private boolean checkIfPairExists(TreeNode root, int B, Set<Integer> set) {
        // if the node is null, then no pair exists. so return false.
        if (root == null) {
            return false;
        }
        // if such pair exists in the left subtree, then return true.
        if (checkIfPairExists(root.left, B, set)) {
            return true;
        }
        // if set already contains B-root.val element, then it means we have found a pair.
        // so return true.
        if (set.contains(B - root.val)) {
            return true;
        } else {
            // add the current node's value into the set.
            set.add(root.val);
        }
        // recursively check if pair exists in right subtree.
        return checkIfPairExists(root.right, B, set);
    }

    /**
     * This method traverses in inorder and reverse inorder from both ends of the BST.
     * In a sorted array we use two pointers to check if there exists a sum.
     * This logic is similar to that.
     * If both pointers reach the root, it means we haven't found a pair, so return false.
     * Time complexity: O(number of nodes)
     * Space complexity: O(height) as at any point, only O(height) number of nodes
     * will be present in the stack
     *
     * @param A
     * @param B
     * @return
     */
    public int t2Sum3(TreeNode A, int B) {
        return pairExistsOrNotInBst(A, B) ? 1 : 0;
    }

    private boolean pairExistsOrNotInBst(TreeNode A, int B) {
        Stack<TreeNode> st1 = new Stack<>();
        Stack<TreeNode> st2 = new Stack<>();
        TreeNode cur1 = A, cur2 = A;
        while (cur1 != null || !st1.isEmpty() || !st2.isEmpty() || cur2 != null) {
            while (cur1 != null) {
                st1.push(cur1);
                cur1 = cur1.left;
            }
            while (cur2 != null) {
                st2.push(cur2);
                cur2 = cur2.right;
            }

            cur1 = st1.pop();
            cur2 = st2.pop();
            // this means we are at the root node. so no solution exists. return false
            if (cur1.val == cur2.val) {
                return false;
            }
            // if pair exists, then return true
            if (cur1.val + cur2.val == B) {
                return true;
            } else if (cur1.val + cur2.val < B) {
                // if current sum is less than B, it means that we have to get
                // larger element from stack1 so recurse cur1
                cur1 = cur1.right;
                st2.push(cur2);
                cur2 = null;// this is needed so that we recurse only cur1
            } else {
                // if current sum is greater than B, it means that we have to get
                // smaller element from stack2 so recurse cur2
                cur2 = cur2.left;
                st1.push(cur1);
                cur1 = null;// this is needed so that we recurse only cur2
            }
        }
        return false;
    }
}

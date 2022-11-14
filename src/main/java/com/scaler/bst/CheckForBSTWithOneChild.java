package com.scaler.bst;

/**
 * Given preorder traversal of a binary tree, check if it is possible that
 * it is also a preorder traversal of a Binary Search Tree (BST),
 * where each internal node (non-leaf nodes) have exactly one child.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= number of nodes <= 100000
 * <p>
 * Input Format
 * <p>
 * First and only argument is an integer array denoting the preorder traversal of binary tree.
 * <p>
 * Output Format
 * <p>
 * Return a string "YES" if true else "NO".
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A : [4, 10, 5, 8]
 * <p>
 * Input 2:
 * <p>
 * A : [1, 5, 6, 4]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * "YES"
 * <p>
 * Output 2:
 * <p>
 * "NO"
 *
 * @author sudhir on 01-Sep-2020
 */
public class CheckForBSTWithOneChild {
    /**
     * Solution 1 where we traverse the array from right to left.
     *
     * @param A
     * @return
     */
    public String solve(int[] A) {
        int n = A.length;
        if (n <= 2) {
            return "YES";
        }
        // we traverse the array from right to left
        // set max and max depending on the values of last 2 elements of the array.
        int min = Math.min(A[n - 2], A[n - 1]);
        int max = Math.max(A[n - 2], A[n - 1]);
        // for the rest of the elements, if any element lies between min and max,
        // then BST with one child is not possible, else update min and max accordingly.
        // By updating min and max, we are increasing the range and we see to it that
        // A[i] falls outside the range for the tree to be BST with one child.
        // If A[i] is within the range, then BST cannot have one child.
        for (int i = n - 3; i >= 0; i--) {
            if (A[i] < min) {
                min = A[i];
            } else if (A[i] > max) {
                max = A[i];
            } else {
                return "NO";
            }
        }
        return "YES";
    }

    /**
     * Solution 2 where we traverse the array from left to right.
     *
     * @param A
     * @return
     */
    public String solve2(int[] A) {
        int n = A.length;
        if (n <= 2) {
            return "YES";
        }
        int max = -1;
        int min = -1;
        int currentNode = A[0];
        for (int i=1;i<n;i++) {
            if (A[i] > currentNode) {
                // we branch to the right. this means min has to change.
                // if A[i] > max, then we are moving out of range, so return "NO".
                if (max != -1 && A[i] > max) {
                    return "NO";
                }
                min = currentNode;
                currentNode = A[i];
            } else {
                // we branch to the left. this means max has to change.
                // if A[i] < min, then we are moving out of range, so return "NO".
                if (min != -1 && A[i] < min) {
                    // means A[i] is not in the range
                    return "NO";
                }
                max = currentNode;
                currentNode = A[i];
            }
        }
        return "YES";
    }

    public static void main(String[] args) {
        CheckForBSTWithOneChild check = new CheckForBSTWithOneChild();
        int[] a1 = {4, 10, 5, 8};
        int[] a2 = {1, 5, 6, 4};
        System.out.println(check.solve(a1));
        System.out.println(check.solve2(a1));
        System.out.println(check.solve(a2));
        System.out.println(check.solve2(a2));
    }
}

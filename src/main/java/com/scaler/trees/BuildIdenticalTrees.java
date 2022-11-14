package com.scaler.trees;

/**
 * Given two binary trees T1 and T2, you have to find minimum number of insertions to be done in T1
 * to make it structurally identical to T2.
 * <p>
 * Return -1 if not possible.
 * <p>
 * NOTE: You can insert any positive or negative integer.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= Number of nodes <= 5 * 10^5
 * <p>
 * Input Format
 * <p>
 * First argument A denotes the root of tree T1.
 * <p>
 * Second argument B denotes the root of tree T2.
 * <p>
 * Output Format
 * <p>
 * Return an integer denoting the above described output.
 *
 * @author sudhir on 17-Sep-2020
 */
public class BuildIdenticalTrees {
    /**
     * Algorithm:
     * We need to check if there is a node present in the tree T1 then there should be node
     * exactly at same position in node T2.
     * If this condition is not true then we can’t make it structurally equal.
     * Further, we only need to count the number of nodes in tree T1 and T2.
     * It is guaranteed that the number of nodes in T2 is greater than equal to T1 If the first condition is true.
     * Return the difference between the number of nodes.
     *
     * @param A
     * @param B
     * @return
     */
    public int cntMatrix(TreeNode A, TreeNode B) {
        boolean isIdentical = checkIdenticalTrees(A, B);
        if (isIdentical) {
            int size1 = sizeOfTree(A);
            int size2 = sizeOfTree(B);
            return (size2 - size1);
        }
        return -1;
    }

    private int sizeOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right == null) {
            return 1;
        } else {
            return 1 + sizeOfTree(root.left) + sizeOfTree(root.right);
        }
    }

    private boolean checkIdenticalTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 != null && root2 == null) {
            return false;
        } else if (root1 == null && root2 != null) {
            return true;
        } else {
            return checkIdenticalTrees(root1.left, root2.left) && checkIdenticalTrees(root1.right, root2.right);
        }
    }
}

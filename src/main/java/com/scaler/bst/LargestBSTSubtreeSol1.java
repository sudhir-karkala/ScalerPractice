package com.scaler.bst;

/**
 * Given a Binary Tree A with N nodes. Write a function that returns the size of the
 * largest subtree which is also a Binary Search Tree (BST). <br/>
 * If the complete Binary Tree is BST, then return the size of whole tree. <br/>
 * NOTE:Largest subtree means subtree with most number of nodes.
 *
 * @author sudhir on 18-May-2020
 */
public class LargestBSTSubtreeSol1 {
    static class NodeInfo {
        int max;
        int min;
        int size;
        boolean isBST;
        int ans;

        public NodeInfo(int max, int min, int size, boolean isBST, int ans) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
            this.size = size;
            this.ans = ans;
        }
    }

    private NodeInfo max_min(TreeNode root) {
        if (root == null) {
            return new NodeInfo(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, true, 0);
        }
        NodeInfo L = max_min(root.left);
        NodeInfo R = max_min(root.right);
        if (root.val > L.max && root.val < R.min && L.isBST && R.isBST) {
            NodeInfo info = new NodeInfo(Math.max(root.val, R.max), Math.min(root.val, L.min),
                    L.size + R.size + 1, true, L.size + R.size + 1);
            return info;
        }
        return new NodeInfo(Math.max(root.val, R.max), Math.min(root.val, L.min),
                L.size + R.size + 1, false, Math.max(L.ans, R.ans));
    }

    public int solve(TreeNode A) {
        NodeInfo result = max_min(A);
        return result.ans;
    }
}

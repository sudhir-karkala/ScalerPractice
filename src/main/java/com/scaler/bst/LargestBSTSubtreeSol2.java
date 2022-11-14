package com.scaler.bst;

/**
 * @author sudhir on 18-May-2020
 */
public class LargestBSTSubtreeSol2 {
    static class NodeInfo {
        int max;
        int min;
        int size;
        boolean isBST;

        public NodeInfo(int max, int min, int size, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
            this.size = size;
        }
    }

    private int globalSize = 0;

    private NodeInfo max_min(TreeNode root) {
        if (root == null) {
            return new NodeInfo(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, true);
        }
        NodeInfo L = max_min(root.left);
        NodeInfo R = max_min(root.right);
        if (root.val > L.max && root.val < R.min && L.isBST && R.isBST) {
            globalSize = Math.max(globalSize, L.size + R.size + 1);
            return new NodeInfo(Math.max(root.val, R.max), Math.min(root.val, L.min), L.size + R.size + 1, true);
        }
        return new NodeInfo(Math.max(root.val, R.max), Math.min(root.val, L.min), L.size + R.size + 1, false);

    }

    public int solve(TreeNode A) {
        globalSize = 0;
        max_min(A);
        return globalSize;
    }
}

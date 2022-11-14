package com.scaler.tries;

/**
 * Given a 2D integer array A of size N X M.
 * <p>
 * You need to find the Maximum scaler value of any submatrix.
 * <p>
 * Scaler Value of a submatrix?
 * <p>
 * Scaler Value of a rectangular portion (rectangular sub matrix)
 * as xor of all the integers in that rectangular portion.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 10^3
 * <p>
 * 1 <= M <= 20
 * <p>
 * 1 <= A[i][j] <= 10^8
 * <p>
 * N >= M for each testcase
 * <p>
 * Input Format
 * <p>
 * First and only argument is an 2D integer array A of size N X M.
 * <p>
 * <p>
 * <p>
 * Output Format
 * <p>
 * Return a integer denoting the Maximum scaler value of any submatrix.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [  [1, 2, 3]
 * [4, 5, 6]
 * [7, 8, 9]
 * ]
 * <p>
 * Input 2:
 * <p>
 * A = [ [1]
 * ]
 * <p>
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 15
 * <p>
 * Output 2:
 * <p>
 * 1
 *
 * @author sudhir on 02-Nov-2020
 */
public class MaximumXorSubmatrix {
    static class BinaryTrieNode {
        private BinaryTrieNode[] children;
        private int index; // index of the associated element in the array

        public BinaryTrieNode() {
            children = new BinaryTrieNode[2];
        }

        public BinaryTrieNode[] getChildren() {
            return children;
        }
    }

    private BinaryTrieNode root;

    private void insertKey(int key) {
        BinaryTrieNode trieNode = root;
        for (int i = 31; i >= 0; i--) {
            int temp = key >> i;
            temp = temp & 1;
            if (trieNode.getChildren()[temp] == null) {
                trieNode.getChildren()[temp] = new BinaryTrieNode();
            }
            trieNode = trieNode.getChildren()[temp];
        }
    }

    private int searchMaxXORInTrie(int currentBlock) {
        int num = 0;
        BinaryTrieNode trieNode = root;
        for (int k = 31; k >= 0; k--) {
            int temp = currentBlock >> k;
            temp = temp & 1;
            if (trieNode.getChildren()[temp ^ 1] != null) {
                trieNode = trieNode.getChildren()[temp ^ 1];
                num |= (temp ^ 1) << k;
            } else {
                trieNode = trieNode.getChildren()[temp];
                num |= temp << k;
            }
        }
        return num ^ currentBlock;
    }

    public int solve(int[][] A) {
        int m = A.length;
        int n = A[0].length;

        int[][] prefix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefix[i][j] = A[i][j];
                if ((i - 1) >= 0) {
                    prefix[i][j] ^= prefix[i - 1][j];
                }
                if ((j - 1) >= 0) {
                    prefix[i][j] ^= prefix[i][j - 1];
                }
                if ((i - 1) >= 0 && (j - 1) >= 0) {
                    prefix[i][j] ^= prefix[i - 1][j - 1];
                }
            }
        }

        int finalAns = 0;
        for (int j = 0; j < m; j++) {
            for (int k = j; k < m; k++) {
                root = new BinaryTrieNode();
                insertKey(0);
                for (int i = 0; i < n; i++) {
                    int currentBlock = prefix[k][i];
                    if (j > 0) {
                        currentBlock = currentBlock ^ prefix[j - 1][i];
                    }
                    finalAns = Math.max(finalAns, searchMaxXORInTrie(currentBlock));
                    insertKey(currentBlock);
                }
            }
        }
        return finalAns;
    }

    public static void main(String[] args) {
        MaximumXorSubmatrix m = new MaximumXorSubmatrix();
        int[][] a1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(m.solve(a1));
    }
}

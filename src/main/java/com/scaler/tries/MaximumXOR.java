package com.scaler.tries;

/**
 * Given an array of integers A, find and return the maximum result of A[i] XOR A[j], where i, j
 * are the indexes of the array.
 * <p>
 * 1 <= length of the array <= 100000
 * <p>
 * 0 <= A[i] <= 10^9
 * <p>
 * Return an integer denoting the maximum result of A[i] XOR A[j].
 *
 * @author sudhir on 26-Jul-2020
 */
public class MaximumXOR {
    static class BinaryTrieNode {
        private BinaryTrieNode[] children;
        public BinaryTrieNode() {
            children = new BinaryTrieNode[2];
        }

        public BinaryTrieNode[] getChildren() {
            return children;
        }
    }
    private BinaryTrieNode root;

    private void insertKey(int num, int maxBits) {
        BinaryTrieNode trieNode = root;
        for (int i = maxBits - 1; i >= 0; i--) {
            int temp = num >> i;
            temp = temp & 1;
            if (trieNode.getChildren()[temp] == null) {
                trieNode.getChildren()[temp] = new BinaryTrieNode();
            }
            trieNode = trieNode.getChildren()[temp];
        }
    }

    public int solve(int[] A) {
        root = new BinaryTrieNode();
        // find the max number in the input.
        int maxNum = A[0];
        for (int i = 1; i < A.length; i++) {
            if (maxNum < A[i]) {
                maxNum = A[i];
            }
        }
        // check the number of bits required for the maximum number
        // Math.log(0) will give -Infinity if 0 is the max number in the array.
        // Hence we consider max(1, math.log(maxNum)/math.log(2) + 1) as maxBits needed.
        int maxBits = Math.max(1, (int) (Math.log(maxNum) / Math.log(2) + 1));
        // insert first number in trie
        insertKey(A[0], maxBits);
        // initially, maxXOR is set to MIN_VALUE
        int maxXOR = Integer.MIN_VALUE;

        for (int i = 1; i < A.length; i++) {
            // to maximize XOR value, we can check for every j if the corresponding bits of ith number and the
            // number represented in the trie are different. If the such bit doesn't exist in the trie,
            // then we do XOR with the available bit in the trie.
            BinaryTrieNode trieNode = root;
            int num = 0;
            for (int j = maxBits - 1; j >= 0; j--) {
                // extract jth bit of A[i]
                int temp = A[i] >> j;
                // & the bit with 1 to get the actual bit(if the bit was 1, it will return 1, if it was 0,
                // it will return 0)
                temp = temp & 1;
                // check if any of the trie node's children has bit set to (temp ^ 1),
                // i.e the opposite of the current bit
                // this is done to choose the path in the trie node to maximize XOR.
                // If such bit is not present, then travel the remaining path.
                if (trieNode.getChildren()[temp ^ 1] != null) {
                    trieNode = trieNode.getChildren()[temp ^ 1];
                    num |= (temp ^ 1) << j;
                } else {
                    trieNode = trieNode.getChildren()[temp];
                    num |= temp << j;
                }
            }
            // num will hold the number corresponding to the path travelled. we perform (num ^ A[i])
            // and update maxXOR
            if ((num ^ A[i]) > maxXOR) {
                maxXOR = num ^ A[i];
            }
            insertKey(A[i], maxBits);
        }
        return maxXOR;
    }

    public static void main(String[] args) {
        MaximumXOR maximumXOR = new MaximumXOR();
        int[] a1 = {9, 8, 10, 7};
        int[] a2 = {5, 17, 100, 11};
        int[] a3 = {1, 2, 3, 4, 5};
        int[] a4 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(maximumXOR.solve(a1));
        System.out.println(maximumXOR.solve(a2));
        System.out.println(maximumXOR.solve(a3));
        System.out.println(maximumXOR.solve(a4));
    }
}

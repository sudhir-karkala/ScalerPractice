package com.scaler.tries;

import java.util.*;

/**
 * Given a tree with A nodes and A-1 edges which is rooted at node 1.
 * <p>
 * There are C queries and for each query you are given two integers d (the node number) and e
 * and you have to find the maximum value when e is xor’ed with any of the ancestors of d or d itself.
 * <p>
 * More formally, find the maximum value which can be obtained when e is XOR with any node in the path from d to the root.
 * <p>
 * NOTE: XOR is the bitwise XOR operator for example, 1 XOR 1 = 0 and 1 XOR 0 = 1 etc.
 * <p>
 * Problem Constraints<br/>
 * 2 <= A <= 100000, |B| = A-1 and B[i] <= i for all i from 1 till A-1
 * <p>
 * 1 <= C <= 300000
 * <p>
 * |D| = |E| = C
 * <p>
 * 1 <= D[i] <= A
 * <p>
 * 1 <= E[i] <= 300000
 * <p>
 * Input:
 * The first argument given is the Integer A.
 * The second argument given is the parent array B, where B[i] denotes the parent of (i+1)'th node for all i from 1 to A-1.
 * Note: Size of the array B is A-1 and its indexing starts form 1.
 * The third argument given is the Integer C denoting the number of queries.
 * The fourth argument given is the array D, where D[i] denotes the value of d for the i'th query.
 * The fifth argument given is the array E, where E[i] denotes the value of e for the i'th query.
 * <p>
 * Output:
 * Return the integer array where each index denotes the answer for every query in the same order as input.
 *
 * @author sudhir on 28-Jul-2020
 */
public class XORQueriesOnTree {
    static class BinaryTrieNode {
        private BinaryTrieNode[] children;

        public BinaryTrieNode() {
            children = new BinaryTrieNode[2];
        }

        public BinaryTrieNode[] getChildren() {
            return children;
        }
    }

    /**
     * This will maintain pairs of values in the TreeNode.
     * value = value E[i] for all i in the given array E
     * index = index of the array element in the queries array of size C.
     */
    static class PairInTree {
        int value;
        int index;

        public PairInTree() {
        }

        public PairInTree(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    /**
     * This is used to push trie nodes to the stack for the purpose of deletion
     */
    static class PairInTrie {
        int val;
        BinaryTrieNode node;

        public PairInTrie() {
        }

        public PairInTrie(int val, BinaryTrieNode node) {
            this.val = val;
            this.node = node;
        }
    }

    static class TreeNode {
        int data;
        List<TreeNode> children;
        List<PairInTree> pairs;

        public TreeNode(int data) {
            this.data = data;
            this.children = new ArrayList<>();
            this.pairs = new ArrayList<>();
        }
    }

    private void insertTreeNode(int[] parent) {
        for (int i = 0; i < parent.length; i++) {
            TreeNode p = valToTreeNodeMap.get(parent[i]);
            // parent[i] is the parent of (i+2)th node (0-indexed. In the question, it says (i+1) as is 1-indexed).
            // so we create new node with the value (i+2).
            TreeNode newNode = new TreeNode(i + 2);
            p.children.add(newNode);
            valToTreeNodeMap.put(i + 2, newNode);
        }
    }

    // This method is not used due to TLE. Instead valToTreeNodeMap is used to get the required TreeNode.
    private TreeNode searchTreeNode(TreeNode root, int val) {
        if (root.data == val) {
            return root;
        }
        TreeNode toSearch = null;
        for (TreeNode child : root.children) {
            if (toSearch != null) {
                return toSearch;
            }
            toSearch = searchTreeNode(child, val);
        }
        return toSearch;
    }

    private void updatePairsInTree(int[] d, int[] e) {
        for (int i = 0; i < d.length; i++) {
            TreeNode node = valToTreeNodeMap.get(d[i]);
            node.pairs.add(new PairInTree(e[i], i));
        }
    }

    private void insertKeyInTrie(int num, int maxBits) {
        BinaryTrieNode trieNode = trieRoot;
        for (int i = maxBits - 1; i >= 0; i--) {
            int temp = num >> i;
            temp = temp & 1;
            if (trieNode.getChildren()[temp] == null) {
                trieNode.getChildren()[temp] = new BinaryTrieNode();
            }
            trieNode = trieNode.getChildren()[temp];
        }
    }

    private void deleteKeyFromTrie(int data, int maxBits) {
        BinaryTrieNode trieNode = trieRoot;
        Stack<PairInTrie> st = new Stack<>();
        for (int i = maxBits - 1; i >= 0; i--) {
            int temp = data >> i;
            temp = temp & 1;
            st.push(new PairInTrie(temp, trieNode));
            trieNode = trieNode.getChildren()[temp];
        }
        while (!st.isEmpty()) {
            PairInTrie parent = st.pop();
            parent.node.getChildren()[parent.val] = null;
            // if there is another child, then we cannot delete it further.
            if (parent.node.getChildren()[1 - parent.val] != null) {
                break;
            }
        }
    }

    private void maxXORFromTrie(TreeNode treeRoot, int[] result, int maxBits) {
        // insert the value in the trie which is represented by the current tree node.
        insertKeyInTrie(treeRoot.data, maxBits);
        for (PairInTree pair : treeRoot.pairs) {
            // for every pair present in the list, we update max XOR using trie.
            updateMaxXOR(result, pair.index, pair.value, maxBits);
        }
        // we recursively call the function for all child nodes so that we can insert children one by one in
        // the trie and calculate max XOR from the trie which will have all the ancestor values already present.
        for (TreeNode child : treeRoot.children) {
            maxXORFromTrie(child, result, maxBits);
        }
        // once we are done exploring the nodes, we will delete the value from the trie so that
        // at any given time, it will have only those values which represent the ancestors of the
        // current node which is under consideration.
        deleteKeyFromTrie(treeRoot.data, maxBits);
    }

    private void updateMaxXOR(int[] result, int index, int val, int maxBits) {
        BinaryTrieNode trieNode = trieRoot;
        int num = 0;
        for (int j = maxBits - 1; j >= 0; j--) {
            // extract jth bit of A[i].
            int temp = val >> j;
            // & the bit with 1 to get the actual bit(if the bit was 1, it will return 1, if it was 0, it will return 0).
            temp = temp & 1;
            if (trieNode.getChildren()[temp ^ 1] != null) {
                trieNode = trieNode.getChildren()[temp ^ 1];
                num |= (temp ^ 1) << j;
            } else {
                trieNode = trieNode.getChildren()[temp];
                num |= temp << j;
            }
        }
        // here num will have the value which represents one of the ancestors(including itself) of the node
        // at 'index' and (num XOR val) will give the maximum XOR.
        result[index] = num ^ val;
    }

    private BinaryTrieNode trieRoot;
    private TreeNode treeRoot;
    private Map<Integer, TreeNode> valToTreeNodeMap;

    public int[] solve(int A, int[] B, int C, int[] D, int[] E) {
        // it is given that tree is rooted at node 1.
        treeRoot = new TreeNode(1);
        trieRoot = new BinaryTrieNode();
        valToTreeNodeMap = new HashMap<>();
        valToTreeNodeMap.put(1, treeRoot);
        int[] result = new int[C];
        // construct the tree using the values given in the array B.
        insertTreeNode(B);
        // update the list of pairs for tree nodes using D[] and E[].
        updatePairsInTree(D, E);
        // this is done to calculate the number of bits required to represent a number in the trie.
        int maxBits = Math.max(1, (int) (Math.log(A) / Math.log(2) + 1));
        maxXORFromTrie(treeRoot, result, maxBits);
        return result;
    }

    public static void main(String[] args) {
        XORQueriesOnTree x = new XORQueriesOnTree();
        int a1 = 8;
        int[] b1 = {1, 1, 2, 2, 3, 3, 1};
        int c1 = 5;
        int[] d1 = {2, 3, 5, 6, 8};
        int[] e1 = {1, 1, 5, 4, 10};

        int a2 = 3;
        int[] b2 = {1, 1};
        int c2 = 2;
        int[] d2 = {2, 3};
        int[] e2 = {3, 1};

        System.out.println(Arrays.toString(x.solve(a1, b1, c1, d1, e1)));
        System.out.println(Arrays.toString(x.solve(a2, b2, c2, d2, e2)));
    }
}

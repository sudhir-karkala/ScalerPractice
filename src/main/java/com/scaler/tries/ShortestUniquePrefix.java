package com.scaler.tries;

import java.util.Arrays;

/**
 * Given a list of N words. Find shortest unique prefix to represent each word in the list.
 * <p>
 * NOTE: Assume that no word is prefix of another. In other words, the representation is always possible
 * <p>
 * <p>
 * <p>
 * Problem Constraints
 * 1 <= Sum of length of all words <= 10^6
 * <p>
 * Return a string array B where B[i] denotes the shortest unique prefix to represent the ith word.
 *
 * @author sudhir on 24-Jul-2020
 */
public class ShortestUniquePrefix {
    private TrieNode root;

    private void insertKey(String key) {
        int n = key.length();
        TrieNode trieNode = root;
        for (int i = 0; i < n; i++) {
            if (!trieNode.getChildren().containsKey(key.charAt(i))) {
                trieNode.getChildren().put(key.charAt(i), new TrieNode());
            }
            trieNode = trieNode.getChildren().get(key.charAt(i));
            trieNode.setCount(trieNode.getCount() + 1);// update the references to that character.
        }
    }

    private String getUniquePrefix(String key) {
        int n = key.length();
        TrieNode trieNode = root;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(key.charAt(i));
            if (trieNode.getChildren().get(key.charAt(i)).getCount() == 1) {
                break;
            }
            trieNode = trieNode.getChildren().get(key.charAt(i));
        }
        return builder.toString();
    }

    public String[] prefix(String[] A) {
        // initialize trie before starting with a new input
        root = new TrieNode();
        // insert all words into a trie structure
        int n = A.length;
        for (int i = 0; i < n; i++) {
            insertKey(A[i]);
        }
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = getUniquePrefix(A[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        ShortestUniquePrefix s = new ShortestUniquePrefix();
        String[] keys1 = {"zebra", "dog", "duck", "dove"};
        String[] keys2 = {"apple", "ball", "cat"};
        String[] keys3 = {"tie", "trap", "plate", "place", "cat", "part"};
        System.out.println(Arrays.toString(s.prefix(keys1)));
        System.out.println(Arrays.toString(s.prefix(keys2)));
        System.out.println(Arrays.toString(s.prefix(keys3)));
    }
}

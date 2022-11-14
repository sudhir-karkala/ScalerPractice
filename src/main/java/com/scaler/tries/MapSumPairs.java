package com.scaler.tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Rishabh was sitting ideally in his office and then suddenly his boss gave him some operations to perform.
 * <p>
 * You being his friend tried to help him in finishing the task fast.
 * <p>
 * So you have to perform Q operation of two types:
 * <p>
 * Operation 1: INSERT : You are given an pair of (string, integer).
 * The string represents the key and the integer represents the value.
 * Insert the key-value pair in the hash and If the key already exists in hash,
 * then the original key-value pair will be overridden to the new one.
 * <p>
 * Operation 2: SUM : you'll be given an pair of (string, -1) where string representing the prefix,
 * and you need to return the sum of all the pairs' value in the hash whose key starts with the prefix.
 * <p>
 * Problem Constraints:
 * <p>
 * 1 <= Q <= 10^3
 * <p>
 * 1 <= Length of string in any operation <= 30
 * <p>
 * Strings in each operations only consists of lowercase characters.
 * <p>
 * 1 <= Integer in Operation 1 <= 100
 * <p>
 * Integer in operation 2 is always -1 so this parameter will help you in distinguishing between the two opearations.
 * <p>
 * Input Format
 * <p>
 * First argument is an string array A of size Q denoting the first parameter of each operations.
 * <p>
 * Second argument is an integer array B of size Q denoting the second parameter of each operations.
 * <p>
 * NOTE: ith query will be like (A[i], B[i])
 * <p>
 * Output Format
 * <p>
 * Return an integer array denoting the answer for each operation of type: 2
 *
 * @author sudhir on 15-Aug-2020
 */
public class MapSumPairs {
    private TrieNode trieRoot;
    private Map<String, Integer> hashMap;

    public ArrayList<Integer> solve(ArrayList<String> A, ArrayList<Integer> B) {
        ArrayList<Integer> result = new ArrayList<>();
        trieRoot = new TrieNode();
        hashMap = new HashMap<>();
        int size = A.size();
        for (int i = 0; i < size; i++) {
            if (B.get(i) == -1) {
                //type 2
                // we fetch the count associated with the given prefix from the trie
                result.add(returnCountFromTrie(A.get(i)));
            } else {
                // type 1
                if (hashMap.containsKey(A.get(i))) {
                    // if the map contains the key, then we need to update trie with the new value.
                    // we do this way: trieNode.count = trieNode.count - oldValue + newValue.
                    updateTrieNode(A.get(i), hashMap.get(A.get(i)), B.get(i));
                } else {
                    // if the map doesn't contain the key, then the oldValue = 0 is passed to the method call
                    // so that trie nodes are updated with the new value.
                    updateTrieNode(A.get(i), 0, B.get(i));
                }
                // we insert the new value for the string of type 1 in the map
                hashMap.put(A.get(i), B.get(i));
            }
        }
        return result;
    }

    private int returnCountFromTrie(String key) {
        int n = key.length();
        TrieNode trieNode = trieRoot;
        for (int i = 0; i < n; i++) {
            if (trieNode.children.containsKey(key.charAt(i))) {
                trieNode = trieNode.children.get(key.charAt(i));
            } else {
                return 0;
            }
        }
        return trieNode.count;
    }

    private void updateTrieNode(String key, int oldValue, int newValue) {
        int n = key.length();
        TrieNode trieNode = trieRoot;
        for (int i = 0; i < n; i++) {
            if (!trieNode.children.containsKey(key.charAt(i))) {
                trieNode.children.put(key.charAt(i), new TrieNode());
            }
            trieNode = trieNode.children.get(key.charAt(i));
            trieNode.count = trieNode.count - oldValue + newValue;
        }
    }

    static class TrieNode {
        Map<Character, TrieNode> children;
        int count;

        public TrieNode() {
            children = new HashMap<>();
        }
    }

    public static void main(String[] args) {
        MapSumPairs m = new MapSumPairs();
        ArrayList<String> a1 = new ArrayList<>(Arrays.asList("apple", "ap", "app", "ap"));
        ArrayList<Integer> b1 = new ArrayList<>(Arrays.asList(3, -1, 2, -1));
        System.out.println(m.solve(a1, b1));
        ArrayList<String> a2 = new ArrayList<>(Arrays.asList("ban", "banana", "ba"));
        ArrayList<Integer> b2 = new ArrayList<>(Arrays.asList(10, -1, -1));
        System.out.println(m.solve(a2, b2));
        ArrayList<String> a3 = new ArrayList<>(Arrays.asList("apple", "ap", "apple", "app", "ap"));
        ArrayList<Integer> b3 = new ArrayList<>(Arrays.asList(3, -1, 5, 6, -1));
        System.out.println(m.solve(a3, b3));
    }
}

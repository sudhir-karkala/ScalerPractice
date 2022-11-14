package com.scaler.tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of unique words A, find all pairs of distinct indices (i, j) in the given list
 * such that concatenation of the two words, i.e. A[i] + A[j] is a palindrome.
 * <p>
 * Note: A string is a palindrome if it reads the same backward as forward.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of the list A <= 1000
 * <p>
 * 1 <= length of words in list A <= 100
 * <p>
 * Input Format
 * <p>
 * The only argument given is the string array A.
 * <p>
 * Output Format
 * <p>
 * Return the list of all pairs of distinct indices such that concatenation of the two words,
 * i.e. A[i] + A[j] is a palindrome.
 * <p>
 * You can return the list in any order.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = ["abcd", "dcba", "lls", "s", "sssll"]
 * <p>
 * Input 2:
 * <p>
 * A =  ["bat", "tab", "cat"]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * [ [0,1], [1,0], [3,2], [2,4] ]
 * <p>
 * Output 2:
 * <p>
 * [[0,1],[1,0]]
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * <p>
 * Explanation 2:
 * <p>
 * The palindromes are ["battab","tabbat"]
 *
 * @author sudhir on 29-Aug-2020
 */
public class PalindromePairs {
    static class TrieNode {
        TrieNode[] child;
        int index;
        List<Integer> list;

        public TrieNode() {
            child = new TrieNode[26];
            list = new ArrayList<>();
            index = -1;
        }
    }

    /**
     * This solution uses trie to solve the problem in O(n*(k^2)) time complexity
     * where k = average length of each word.
     * This is better than brute force solution that takes O((n^2)*k) time complexity.
     *
     * @param A
     * @return
     */
    public ArrayList<ArrayList<Integer>> solve(ArrayList<String> A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int wordCount = A.size();
        TrieNode root = new TrieNode();
        for (int i = 0; i < wordCount; i++) {
            addWord(root, A.get(i), i);
        }
        for (int i = 0; i < wordCount; i++) {
            search(root, A, i, result);
        }
        return result;
    }

    private void addWord(TrieNode root, String word, int index) {
        for (int i = word.length() - 1; i >= 0; i--) {
            int j = word.charAt(i) - 'a';
            if (root.child[j] == null) {
                root.child[j] = new TrieNode();
            }
            if (isPalindrome(word, 0, i)) {
                root.list.add(index);
            }
            root = root.child[j];
        }
        root.list.add(index);
        root.index = index;
    }

    private void search(TrieNode root, ArrayList<String> words, int i,
                        ArrayList<ArrayList<Integer>> result) {
        int length = words.get(i).length();
        for (int j = 0; j < length; j++) {
            if (root.index >= 0 && root.index != i && isPalindrome(words.get(i), j, length - 1)) {
                result.add(new ArrayList<>(Arrays.asList(i, root.index)));
            }
            root = root.child[words.get(i).charAt(j) - 'a'];
            if (root == null) return;
        }
        for (int j : root.list) {
            if (i == j) {
                continue;
            }
            result.add(new ArrayList<>(Arrays.asList(i, j)));
        }
    }

    private boolean isPalindrome(String word, int start, int end) {
        while (start < end) {
            if (word.charAt(start) != word.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePairs p = new PalindromePairs();
        ArrayList<String> words1 = new ArrayList<>(Arrays.asList(
                "abcd", "dcba", "lls", "s", "sssll"));
        ArrayList<String> words2 = new ArrayList<>(Arrays.asList("bat", "tab", "cat"));
        System.out.println(p.solve(words1));
        System.out.println(p.solve(words2));
    }
}

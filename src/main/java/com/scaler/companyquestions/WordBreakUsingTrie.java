package com.scaler.companyquestions;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string A and a dictionary of words B, determine if A can be segmented into a space-separated
 * sequence of one or more dictionary words.
 * <p>
 * Input Format:
 * <p>
 * The first argument is a string, A.
 * <p>
 * The second argument is an array of strings, B.
 * <p>
 * Output Format:
 * <p>
 * Return 0 / 1 ( 0 for false, 1 for true ) for this problem.
 * <p>
 * Constraints:
 * <p>
 * 1 <= len(A) <= 6500
 * <p>
 * 1 <= len(B) <= 10000
 * <p>
 * 1 <= len(B[i]) <= 20
 * <p>
 * Examples:
 * <p>
 * Input 1:
 * <p>
 * A = "myinterviewtrainer",
 * <p>
 * B = ["trainer", "my", "interview"]
 * <p>
 * Output 1:
 * <p>
 * 1
 * <p>
 * Explanation 1:
 * <p>
 * Return 1 ( corresponding to true ) because "myinterviewtrainer" can be segmented as "my interview trainer".
 * <p>
 * Input 2:
 * <p>
 * A = "a"
 * <p>
 * B = ["aaa"]
 * <p>
 * Output 2:
 * <p>
 * 0
 * <p>
 * Explanation 2:
 * <p>
 * Return 0 ( corresponding to false ) because "a" cannot be segmented as "aaa".
 *
 * @author sudhir on 26-Sep-2020
 */
public class WordBreakUsingTrie {
    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
        }
    }

    private void insertWord(TrieNode root, String word) {
        if (root == null) {
            root = new TrieNode();
        }
        int len = word.length();
        TrieNode trieNode = root;
        for (int i = 0; i < len; i++) {
            if (!trieNode.children.containsKey(word.charAt(i))) {
                trieNode.children.put(word.charAt(i), new TrieNode());
            }
            trieNode = trieNode.children.get(word.charAt(i));
        }
        trieNode.isEndOfWord = true;
    }

    private boolean searchInTrie(TrieNode root, String word) {
        if (root == null) {
            return false;
        }
        int len = word.length();
        TrieNode trieNode = root;
        for (int i = 0; i < len; i++) {
            if (!trieNode.children.containsKey(word.charAt(i))) {
                return false;
            }
            trieNode = trieNode.children.get(word.charAt(i));
        }
        return trieNode.isEndOfWord;
    }

    public int wordBreak(String A, String[] B) {
        TrieNode root = new TrieNode();
        // insert words into a trie structure.
        for (String word : B) {
            insertWord(root, word);
        }
        return isWordBreakPossible(A, root) ? 1 : 0;
    }

    // time complexity will be O(length_of_word * length_of_word * search_time_in_trie)
    // which will be O(len * len * length_of_longest_word_in_dictionary) in the worst case.
    // search time in trie will be the length of the longest word present in the trie.
    private boolean isWordBreakPossible(String word, TrieNode root) {
        int len = word.length();
        // The value dp[i] will be true if str[0..i-1] can be segmented into dictionary words,
        // otherwise false.
        boolean[] dp = new boolean[len + 1];
        for (int i = 1; i <= len; i++) {
            // if dp[i] is false, then check if current prefix can make it true.
            // Current prefix is word.substring(0, i)
            if (!dp[i] && searchInTrie(root, word.substring(0, i))) {
                dp[i] = true;
            }
            if (dp[i] == true) {
                // If we reached the last prefix
                if (i == len) {
                    return true;
                }
                // then check for remaining prefixes
                for (int j = i + 1; j <= len; j++) {
                    // Update dp[j] if it is false and can be updated
                    // Note the substring passed to search() is
                    // starting from index 'i' to index 'j - 1'
                    if (!dp[j] && searchInTrie(root, word.substring(i, j))) {
                        dp[j] = true;
                    }
                    if (j == len && dp[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordBreakUsingTrie wB = new WordBreakUsingTrie();
        String[] dict1 = {"trainer", "my", "interview"};
        String word1 = "myinterviewtrainer";
        System.out.println(wB.wordBreak(word1, dict1));

        String[] dict2 = {"aaa"};
        String word2 = "a";
        System.out.println(wB.wordBreak(word2, dict2));
    }
}

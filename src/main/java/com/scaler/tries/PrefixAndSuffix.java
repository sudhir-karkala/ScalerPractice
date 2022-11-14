package com.scaler.tries;

/**
 * Given a list of N words denoted by string array A of size N.
 * <p>
 * You have to answer Q queries denoted by string array B, for each query you have a string S of size M,
 * find the number of words in the list A which have string S as a prefix and suffix.
 * <p>
 * NOTE: The size M for all strings in the queries remains same.
 * <p>
 * 1 <= N <= 10^5
 * <p>
 * 1 <= |A[i]| <= 1000
 * <p>
 * 1 <= Q, M <= 1000
 * <p>
 * Sum of length of all N words <= 106
 *
 * @author sudhir on 25-Jul-2020
 */
public class PrefixAndSuffix {
    private TrieNode root;

    private void insertKey(String key) {
        int n = key.length();
        TrieNode trieNode = root;
        for (int i = 0; i < n; i++) {
            if (!trieNode.getChildren().containsKey(key.charAt(i))) {
                trieNode.getChildren().put(key.charAt(i), new TrieNode());
            }
            trieNode = trieNode.getChildren().get(key.charAt(i));
        }
    }

    private void insertQueries(String[] queries) {
        for (int i = 0; i < queries.length; i++) {
            insertKey(queries[i]);
        }
    }

    private void updateFreq(String word, int len) {
        TrieNode trieNode = root;
        for (int i = 0; i < len; i++) {
            if (!trieNode.getChildren().containsKey(word.charAt(i))) {
                return;
            }
            trieNode = trieNode.getChildren().get(word.charAt(i));
        }
        trieNode.setCount(trieNode.getCount() + 1); // it is enough to update the freq of the last character.
    }

    private void updateFreqInTrie(String[] words, int len) {
        for (String word : words) {
            int i = 0;
            int n = word.length();
            int j = n - len;
            boolean valid = true;
            if (j < 0) {
                valid = false;
            }
            while (i >= 0 && i < len && j >= 0 && j < n) {
                if (word.charAt(i) != word.charAt(j)) {
                    valid = false;
                    break;
                }
                i++;
                j++;
            }
            if (valid) {
                updateFreq(word, len);
            }
        }
    }

    private int[] getFreqOutput(String[] words) {
        int[] result = new int[words.length];
        int k = 0;
        for (String word : words) {
            TrieNode trieNode = root;
            int n = word.length();
            for (int i = 0; i < n; i++) {
                trieNode = trieNode.getChildren().get(word.charAt(i));
            }
            result[k++] = trieNode.getCount();
        }
        return result;
    }

    /**
     * Entry point of the solution
     * Algorithm:
     * <p>
     * 1. Construct a trie for Q queries. <br/>
     * 2. Go through the list of words in A and filter out only valid words(valid means that prefix and suffix of given
     * length(length is the length of the word in Q queries) should be equal. <br/>
     * 3. For all the valid words obtained in step 2, update the frequency of the prefix in the trie. <br/>
     * 4. For every query in the trie, record its frequency in the result array and finally return the array.
     * </p>
     *
     * @param A
     * @param B
     * @return
     */
    public int[] solve(String[] A, String[] B) {
        root = new TrieNode();
        int m = B[0].length();
        insertQueries(B);
        updateFreqInTrie(A, m);
        return getFreqOutput(B);
    }

    public static void main(String[] args) {
        PrefixAndSuffix p = new PrefixAndSuffix();
        String[] words1 = {"ababa", "aabbvaab", "aecdsaaec", "abaaxbqaba"};
        String[] queries1 = {"aba", "aec", "abb", "aab"};
        String[] words2 = {"cazqzqcaz", "cadssac", "caz"};
        String[] queries2 = {"caz", "cad"};
        System.out.println(p.solve(words1, queries1));
        System.out.println(p.solve(words2, queries2));
    }
}

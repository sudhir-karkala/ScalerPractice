package com.scaler.companyquestions;

import java.util.HashSet;
import java.util.Set;

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
 * @author sudhir on 25-Sep-2020
 */
public class WordBreakUsingDP {
    public int wordBreakDP(String A, String[] B) {
        Set<String> dictionary = new HashSet<>();
        for (String word : B) {
            dictionary.add(word);
        }
        return isWordBreakPossibleDP(A, dictionary) ? 1 : 0;
    }

    // time complexity will be O(length_of_word * length_of_word * hashmap_lookup_time)
    // which will be O(len * len * B.length) in tht worst case.
    private boolean isWordBreakPossibleDP(String word, Set<String> dictionary) {
        int size = word.length();
        if (size == 0) {
            return true;
        }
        // The value dp[i] will be true if str[0..i-1] can be segmented into dictionary words,
        // otherwise false.
        boolean[] dp = new boolean[size + 1];
        for (int i = 1; i <= size; i++) {
            // if dp[i] is false, then check if current prefix can make it true.
            // Current prefix is word.substring(0, i)
            if (!dp[i] && dictionary.contains(word.substring(0, i))) {
                dp[i] = true;
            }
            if (dp[i] == true) {
                // If we reached the last prefix
                if (i == size) {
                    return true;
                }
                // then check for remaining prefixes
                for (int j = i + 1; j <= size; j++) {
                    // Update dp[j] if it is false and can be updated
                    // Note the parameter passed to dictionary.contains() is
                    // substring starting from index 'i' to index 'j - 1'

                    // search time in the hashmap will take O(number of words) in the worst case
                    // due to collisions since the hash function would most likely ignore
                    // the prefix property within the dictionary and hence store repeated occurrences
                    // of each prefix.
                    if (!dp[j] && dictionary.contains(word.substring(i, j))) {
                        dp[j] = true;
                    }
                    if (j == size && dp[j]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordBreakUsingDP wordBreak = new WordBreakUsingDP();
        String[] dict1 = {"trainer", "my", "interview"};
        String word1 = "myinterviewtrainer";
        System.out.println(wordBreak.wordBreakDP(word1, dict1));

        String[] dict2 = {"aaa"};
        String word2 = "a";
        System.out.println(wordBreak.wordBreakDP(word2, dict2));
    }
}

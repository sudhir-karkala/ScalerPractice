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
public class WordBreakUsingRecursion {
    public int wordBreakRecursive(String A, String[] B) {
        Set<String> dictionary = new HashSet<>();
        for (String word : B) {
            dictionary.add(word);
        }
        return isWordBreakPossibleRecursive(A, dictionary) ? 1 : 0;
    }

    private boolean isWordBreakPossibleRecursive(String word, Set<String> dictionary) {
        int size = word.length();
        if (size == 0) {
            return true;
        }
        for (int i = 1; i <= size; i++) {
            // if the current prefix is present in the dictionary, then check for remaining part of the word.
            if (dictionary.contains(word.substring(0, i)) &&
                    isWordBreakPossibleRecursive(word.substring(i), dictionary)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordBreakUsingRecursion wordBreak = new WordBreakUsingRecursion();
        String[] dict1 = {"trainer", "my", "interview"};
        String word1 = "myinterviewtrainer";
        System.out.println(wordBreak.wordBreakRecursive(word1, dict1));

        String[] dict2 = {"aaa"};
        String word2 = "a";
        System.out.println(wordBreak.wordBreakRecursive(word2, dict2));
    }
}

package com.scaler.companyquestions;

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
public class WordBreak {
    public int wordBreak(String A, String[] B) {
        return isWordBreakPossible(A, B) ? 1 : 0;
    }

    // time complexity is O(len * B.length)
    private boolean isWordBreakPossible(String word, String[] dictionary) {
        int len = word.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 0; i <= len; i++) {
            if (!dp[i]) {
                continue;
            }
            // iterate in the dictionary to check if the prefix is found in the dictionary
            for (String str : dictionary) {
                int size = str.length();
                int end = i + size;
                // here we check if the computed size is within the size of the given word.
                // if not, then continue with the next dictionary word
                if (end > len) {
                    continue;
                }
                if (dp[end]) {
                    continue;
                }
                if (word.substring(i, end).equals(str)) {
                    dp[end] = true;
                }
            }
        }
        return dp[len];
    }

    public static void main(String[] args) {
        WordBreak wB = new WordBreak();
        String[] dict1 = {"trainer", "my", "interview"};
        String word1 = "myinterviewtrainer";
        System.out.println(wB.wordBreak(word1, dict1));

        String[] dict2 = {"aaa"};
        String word2 = "a";
        System.out.println(wB.wordBreak(word2, dict2));
    }
}

package com.scaler.dynamicprogramming;

/**
 * Given two sequences A and B, count number of unique ways in sequence A,
 * to form a subsequence that is identical to the sequence B.
 * <p>
 * Subsequence : A subsequence of a string is a new string which is formed from the original string by deleting
 * some (can be none) of the characters without disturbing the relative positions of the remaining characters.
 * (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = "abc"
 * B = "abc"
 * <p>
 * Input 2:
 * <p>
 * A = "rabbbit"
 * B = "rabbit"
 * <p>
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 1
 * <p>
 * Output 2:
 * <p>
 * 3
 * <p>
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Both the strings are equal.
 * <p>
 * Explanation 2:
 * <p>
 * These are the possible removals of characters:
 * => A = "ra_bbit"
 * => A = "rab_bit"
 * => A = "rabb_it"
 * <p>
 * Note: "_" marks the removed character.
 * <p>
 * 1 <= length(A), length(B) <= 700
 *
 * @author sudhir on 19-Jul-2020
 */
public class DistinctSubsequences {
    public int numDistinct(String A, String B) {
        int m = A.length();
        int n = B.length();
        // dp[i][j] represents the number of unique ways in sequence A[0..(i-1)], to form a subsequence that is identical
        // to the sequence B[0..(j-1)]
        int[][] dp = new int[m + 1][n + 1];
        // dp[0][0] means number of ways in empty sequence to form empty sequence B which is 1(do nothing).
        dp[0][0] = 1;
        // for every row i, dp[i][0] is number of ways in sequence A[0..(i-1)] to form empty sequence B which is 1.
        for (int i = 1; i <= m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // There are two cases when A[i-1] matches with B[i-1].
                // 1. dp[i - 1][j - 1] => We need to include number of ways excluding (i-1)th character in A
                // and (j-1)th character in B since we match those two characters.
                // 2. dp[i - 1][j] => We need to include number of ways excluding (i-1)th character in A
                // since we don't match those two characters, which means that it still has original number of distinct subsequences.

                // example: consider A(rabbb) and B(rabb).
                // A(....b) matches with B(...b).
                // total ways will include number of ways wrt A(rabbb) and B(rab) which are "ra_bb" and "rab_b" totalling 2.
                // and number of ways wrt A(rabb) and B(rabb) which are "rabb_" totalling 1.
                // so total number of ways = 3.
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                } else {
                    // dp[i - 1][j] => We need to include number of ways excluding (i-1)th character in A when
                    // there is no match
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        DistinctSubsequences d = new DistinctSubsequences();
        String a1 = "rabbbit";
        String b1 = "rabbit";
        String a2 = "abc";
        String b2 = "abc";
        String a3 = "babgbag";
        String b3 = "bag";
        System.out.println(d.numDistinct(a1, b1));
        System.out.println(d.numDistinct(a2, b2));
        System.out.println(d.numDistinct(a3, b3));
    }
}

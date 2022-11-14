package com.scaler.dynamicprogramming;

/**
 * Given a string A. Find the longest palindromic subsequence (A subsequence which does not need to be contiguous and is a palindrome).
 * <p>
 * You need to return the length of longest palindromic subsequence.
 * <p>
 * 1 <= length of(A) <= 10^3
 *
 * @author sudhir on 05-Jul-2020
 */
public class LongestPalindromicSubsequence {
    public int solve(String A) {
        int n = A.length();
        // dp[i][j] represents longest palindromic subsequence in the index range (i..j)
        int[][] dp = new int[n][n];
        // sub sequences of length 1 are always palindrome
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // check of sub sequences of length 2 or greater
        // This fills the dp array in the diagonal and upper diagonal region as we don't need lower diagonal region.
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (A.charAt(i) == A.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
        String a1 = "bebeeed";
        System.out.println(lps.solve(a1));
    }
}

package com.scaler.dynamicprogramming;

import java.util.Arrays;

/**
 * Given a string A, partition A such that every substring of the partition is a palindrome.
 * <p>
 * Return the minimum cuts needed for a palindrome partitioning of A.
 * <p>
 * Problem Constraints<br/>
 * 1 <= length(A) <= 501
 *
 * @author sudhir on 05-Jul-2020
 */
public class PalindromePartitioningII {
    public int minCut(String A) {
        int n = A.length();
        // pal[i][j] represents whether the substring(i, j) (j is inclusive) is a palindrome or not
        boolean[][] pal = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            pal[i][i] = true;
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (A.charAt(i) == A.charAt(j)) {
                    if ((j - i) == 1) {
                        pal[i][j] = true;
                    } else {
                        pal[i][j] = pal[i + 1][j - 1];
                    }
                }
            }
        }

        // dp[i] represents min cuts required for substring starting at index i.
        // we need to return dp[0] which represents min cuts for the given string.
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (pal[i][n - 1]) {
                dp[i] = 0;
            } else {
                for (int k = i; k <= n - 2; k++) {
                    if (pal[i][k]) {
                        dp[i] = Math.min(dp[i], 1 + dp[k + 1]);
                    }
                }
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        PalindromePartitioningII ppII = new PalindromePartitioningII();
        String s1 = "aab";
        String s2 = "abcbd";
        String s3 = "aba";
        System.out.println(ppII.minCut(s1));
        System.out.println(ppII.minCut(s2));
        System.out.println(ppII.minCut(s3));
    }
}

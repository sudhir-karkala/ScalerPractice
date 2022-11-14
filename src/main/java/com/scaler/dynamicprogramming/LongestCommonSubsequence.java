package com.scaler.dynamicprogramming;

/**
 * Given two strings A and B. Find the longest common subsequence ( A sequence which does not need to be contiguous),
 * which is common in both the strings.
 * <p>
 * You need to return the length of such longest common subsequence.
 * <p>
 * 1 <= Length of A, B <= 1005
 *
 * @author sudhir on 05-Jul-2020
 */
public class LongestCommonSubsequence {
    public int solve(String A, String B) {
        int m = A.length();
        int n = B.length();
        int[][] lcs = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }
        return lcs[m][n];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String a1 = "abbcdgf";
        String b1 = "bbabcdf";
        System.out.println(lcs.solve(a1, b1));
    }
}

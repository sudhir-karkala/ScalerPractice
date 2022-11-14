package com.scaler.dynamicprogramming;

/**
 * Implement wildcard pattern matching with support for ' . ' and ' * ' for strings A and B.
 * <p>
 * ' . ' : Matches any single character.
 * ' * ' : Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * <p>
 * 1 <= length(A), length(B) <= 10^4
 *
 * @author sudhir on 02-Jul-2020
 */
public class RegularExpressionII {
    public int isMatch(final String s, final String p) {
        int m = s.length();
        int n = p.length();
        // dp[i][j] represents the state where pattern is from 0th to (j-1)th index and text is from 0th to (i-1)th index
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                // if the pattern doesn't begin with '*', then it is a valid case.
                if ((j - 2) >= 0) {
                    dp[0][j] = dp[0][j - 2];
                }
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    // if the pattern doesn't begin with '*', then it is a valid case.
                    if ((j - 2) >= 0) {
                        // consider 0th occurrence of (j-1)th character of s
                        dp[i][j] = dp[i][j - 2];
                        // consider 1 or more occurrence of (j-1)th character of s
                        if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                            dp[i][j] = dp[i][j] || dp[i - 1][j];
                        }
                    }
                }
            }
        }
        return dp[m][n] ? 1 : 0;
    }

    public static void main(String[] args) {
        RegularExpressionII r = new RegularExpressionII();
        String s1 = "acz";
        String p1 = "a.a";
        System.out.println(r.isMatch(s1, p1));
        String s2 = "aab";
        String p2 = "c*a*b";
        System.out.println(r.isMatch(s2, p2));
    }
}

package com.scaler.dynamicprogramming;

/**
 * Implement wildcard pattern matching with support for ' ? ' and ' * ' for strings A and B.
 * <p>
 * ' ? ' : Matches any single character.<br/>
 * ' * ' : Matches any sequence of characters (including the empty sequence).<br/>
 * The matching should cover the entire input string (not partial).
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length(A), length(B) <= 10^4
 *
 * @author sudhir on 02-Jul-2020
 */
public class RegularExpressionMatch {
    public int isMatch(final String s, final String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[m][n] ? 1 : 0;
    }

    public static void main(String[] args) {
        RegularExpressionMatch r = new RegularExpressionMatch();
        String s1 = "xaylmz";
        String p1 = "x?y*z";
        System.out.println(r.isMatch(s1, p1));
        String s2 = "adceb";
        String p2 = "*a*b";
        System.out.println(r.isMatch(s2, p2));
        String s3 = "acdcb";
        String p3 = "a*c?b";
        System.out.println(r.isMatch(s2, p2));
    }
}

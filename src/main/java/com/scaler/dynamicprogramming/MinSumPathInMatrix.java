package com.scaler.dynamicprogramming;

/**
 * Given a M x N grid A of integers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * <p>
 * Return the minimum sum of the path.
 * <p>
 * NOTE: You can only move either down or right at any point in time.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= M, N <= 2000
 * <p>
 * -1000 <= A[i][j] <= 1000
 *
 * @author sudhir on 03-Jul-2020
 */
public class MinSumPathInMatrix {
    public int minPathSum(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        // dp[i][j] represents minimum sum path from (0,0)th cell to (i,j)th cell
        int[][] dp = new int[m][n];
        dp[0][0] = A[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j > 0) {
                    dp[i][j] = dp[i][j - 1] + A[i][j];
                } else if (j == 0 && i > 0) {
                    dp[i][j] = dp[i - 1][j] + A[i][j];
                } else if (i > 0 && j > 0) {
                    // dp[i][j] = min(value in current cell + sum obtained in top cell, value in current cell + sum obtained in left cell)
                    dp[i][j] = Math.min(dp[i - 1][j] + A[i][j], dp[i][j - 1] + A[i][j]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        MinSumPathInMatrix m = new MinSumPathInMatrix();
        int[][] a1 = {{1, 3, 1}, {4, 3, 1}, {5, 6, 1}};
        System.out.println(m.minPathSum(a1));
        int[][] a2 = {{1, -3, 2}, {2, 5, 10}, {5, -5, 1}};
        System.out.println(m.minPathSum(a2));
    }
}

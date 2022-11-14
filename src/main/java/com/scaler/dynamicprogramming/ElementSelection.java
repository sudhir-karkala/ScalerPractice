package com.scaler.dynamicprogramming;

/**
 * Given a matrix A of size N*N. You have to choose N elements from the matrix
 * such that their sum is maximum but there is a catch.
 * <p>
 * You have to choose exactly one element from every row and every column.
 * Return the maximum possible sum you can achieve by choosing N elements
 * with the given condition.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 18
 * <p>
 * -10^8 <= A[i][j] <= 10^8
 * <p>
 * Input Format
 * <p>
 * First and only argument is an integer matrix A.
 * <p>
 * Output Format
 * <p>
 * Return an integer denoting the maximum possible sum.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [ [5, 50],
 * [100, 10] ]
 * <p>
 * Input 2:
 * <p>
 * A = [ [1, 1],
 * [10, 20] ]
 * <p>
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 150
 * <p>
 * Output 2:
 * <p>
 * 21
 * <p>
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Choose 50 from 1st row and 100 from 2nd row.
 * <p>
 * Explanation 2:
 * <p>
 * Choose first 1 from 1st row and 20 from 2nd row.
 *
 * @author sudhir on 22-Oct-2020
 */
public class ElementSelection {
    // dp[i][j] represents the maximum sum till row i and mask j.
    private int[][] dp;

    public int solve(int[][] A) {
        int n = A.length;
        int dpColSize = (int) (Math.pow(2, n));
        dp = new int[n][dpColSize];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < dpColSize; j++) {
                dp[i][j] = -1;
            }
        }
        int result = selectElements(A, 0, 0, n);
        return result;
    }

    private int selectElements(int[][] arr, int currentRow, int mask, int n) {
        if (currentRow == n) {
            return 0;
        }
        if (dp[currentRow][mask] != -1) {
            return dp[currentRow][mask];
        }
        int ans = Integer.MIN_VALUE;
        for (int col = 0; col < n; col++) {
            if (((mask >> col) & 1) == 1) {
                continue;
            }
            ans = Math.max(ans, selectElements(arr, currentRow + 1, mask | (1 << col), n) + arr[currentRow][col]);
        }
        dp[currentRow][mask] = ans;
        return dp[currentRow][mask];
    }

    public static void main(String[] args) {
        ElementSelection elementSelection = new ElementSelection();
        int[][] arr = {{5, 50}, {100, 10}};
        System.out.println(elementSelection.solve(arr));
    }
}

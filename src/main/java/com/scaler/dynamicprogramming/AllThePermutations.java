package com.scaler.dynamicprogramming;

/**
 * You are given two integer array A and B of size N.
 * You are allowed to shuffle array A such that sum of "A[i] xor B[i]" for all i in [1, N] is minimized.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 16
 * <p>
 * 0 <= A[i],B[i] <= 10^8
 * <p>
 * <p>
 * Input Format
 * <p>
 * First argument of input contains a integer array A. Second argument of input contains a integer array B.
 * <p>
 * <p>
 * Output Format
 * <p>
 * Return a single integer denoting the minimized sum.
 * <p>
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [1, 2], B = [2, 3]
 * <p>
 * Input 2:
 * <p>
 * A = [1, 0, 3], B = [5, 3, 4]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 2
 * <p>
 * Output 1:
 * <p>
 * 8
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * if A = [1, 2] then ( 1 ^ 2 ) + ( 2 ^ 3 ) = 4
 * <p>
 * if A = [2, 1] then ( 2 ^ 2 ) + ( 1 ^ 3 ) = 2
 * <p>
 * clearly, second option is better.
 *
 * @author sudhir on 27-Oct-2020
 */
public class AllThePermutations {
    private int[][] dp;

    public int solve(int[] A, int[] B) {
        int n = A.length;
        int colsize = (int) Math.pow(2, n);
        dp = new int[n][colsize];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < colsize; j++) {
                dp[i][j] = -1;
            }
        }
        return minXORSum(A, B, 0, n, 0);
    }

    private int minXORSum(int[] a, int[] b, int index, int n, int mask) {
        if (index == n) {
            return 0;
        }
        if (dp[index][mask] != -1) {
            return dp[index][mask];
        }
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            if (((mask >> j) & 1) == 1) {
                continue;
            }
            ans = Math.min(ans, minXORSum(a, b, index + 1, n, mask | (1 << j)) + (a[index] ^ b[j]));
        }
        dp[index][mask] = ans;
        return dp[index][mask];
    }

    public static void main(String[] args) {
        AllThePermutations allThePermutations = new AllThePermutations();
        int[] a1 = {1, 0, 3};
        int[] b1 = {5, 3, 4};
        System.out.println(allThePermutations.solve(a1, b1));
    }
}

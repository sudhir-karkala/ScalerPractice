package com.scaler.arrays;

import java.util.Arrays;

/**
 * Given a matrix of integers A of size N x M and multiple queries Q,
 * for each query find and return the submatrix sum. <br/>
 * Inputs to queries are top left (b, c) and bottom right (d, e) indexes of submatrix whose sum is to find out. <br/>
 * Note: Rows are numbered from top to bottom and columns are numbered from left to right. <br/>
 * Sum may be large so return the answer mod 10^9 + 7.
 *
 * @author sudhir on 04-Apr-2020
 */
public class SubMatrixSumQueries {
    public int[] solve(int[][] A, int[] B, int[] C, int[] D, int[] E) {
        int MOD = 1000000007;
        int m = A.length;
        int n = A[0].length;
        int prefixsum[][] = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefixsum[i][j] = A[i][j];
                if (prefixsum[i][j] < 0) {
                    prefixsum[i][j] += MOD;
                }
                if ((j - 1) >= 0) {
                    if (prefixsum[i][j - 1] < 0) {
                        prefixsum[i][j - 1] += MOD;
                    }
                    prefixsum[i][j] = (prefixsum[i][j] % MOD + prefixsum[i][j - 1] % MOD) % MOD;
                }
                if ((i - 1) >= 0) {
                    if (prefixsum[i - 1][j] < 0) {
                        prefixsum[i - 1][j] += MOD;
                    }
                    prefixsum[i][j] = (prefixsum[i][j] % MOD + prefixsum[i - 1][j] % MOD) % MOD;
                }
                if ((i - 1) >= 0 && (j - 1) >= 0) {
                    if (prefixsum[i - 1][j - 1] < 0) {
                        prefixsum[i - 1][j - 1] += MOD;
                    }
                    prefixsum[i][j] = (prefixsum[i][j] - prefixsum[i - 1][j - 1]);
                    if (prefixsum[i][j] < 0) {
                        prefixsum[i][j] += MOD;
                    } else {
                        prefixsum[i][j] = prefixsum[i][j] % MOD;
                    }
                }
            }
        }
        int[] result = new int[D.length];
        for (int i = 0; i < D.length; i++) {
            result[i] = prefixsum[D[i] - 1][E[i] - 1] % MOD;
            if ((C[i] - 2) >= 0) {
                result[i] = result[i] % MOD - prefixsum[D[i] - 1][C[i] - 2] % MOD;
                if (result[i] < 0) {
                    result[i] += MOD;
                } else {
                    result[i] = result[i] % MOD;
                }
            }
            if ((B[i] - 2) >= 0) {
                result[i] = result[i] % MOD - prefixsum[B[i] - 2][E[i] - 1] % MOD;
                if (result[i] < 0) {
                    result[i] += MOD;
                } else {
                    result[i] = result[i] % MOD;
                }
            }
            if ((B[i] - 2) >= 0 && (C[i] - 2) >= 0) {
                result[i] = (result[i] % MOD + (prefixsum[B[i] - 2][C[i] - 2] % MOD)) % MOD;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SubMatrixSumQueries s = new SubMatrixSumQueries();
        int[][] a1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] b1 = {1, 2};
        int[] c1 = {1, 2};
        int[] d1 = {2, 3};
        int[] e1 = {2, 3};
        System.out.println(Arrays.toString(s.solve(a1, b1, c1, d1, e1)));

        int[][] a2 = {{5, 17, 100, 11}, {0, 0, 2, 8}};
        int[] b2 = {1, 1};
        int[] c2 = {1, 4};
        int[] d2 = {2, 2};
        int[] e2 = {2, 4};
        System.out.println(Arrays.toString(s.solve(a2, b2, c2, d2, e2)));
    }
}

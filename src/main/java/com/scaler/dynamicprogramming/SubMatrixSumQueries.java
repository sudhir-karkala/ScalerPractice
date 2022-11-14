package com.scaler.dynamicprogramming;

import java.util.Arrays;

/**
 * Given a matrix of integers A of size N x M and multiple queries Q, for each query find and return the submatrix sum.
 * <p>
 * Inputs to queries are top left (b, c) and bottom right (d, e) indexes of submatrix whose sum is to find out.
 * <p>
 * NOTE:
 * <p>
 * Rows are numbered from top to bottom and columns are numbered from left to right.
 * <p>
 * Sum may be large so return the answer mod 10^9 + 7.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N, M <= 1000
 * <p>
 * -100000 <= A[i] <= 100000
 * <p>
 * 1 <= Q <= 100000
 * <p>
 * 1 <= B[i] <= D[i] <= N
 * <p>
 * 1 <= C[i] <= E[i] <= M
 * <p>
 * Input Format
 * <p>
 * The first argument given is the integer matrix A.
 * <p>
 * The second argument given is the integer array B.
 * <p>
 * The third argument given is the integer array C.
 * <p>
 * The fourth argument given is the integer array D.
 * <p>
 * The fifth argument given is the integer array E.
 * <p>
 * (B[i], C[i]) represents the top left corner of the i'th query.
 * <p>
 * (D[i], E[i]) represents the bottom right corner of the i'th query.
 * <p>
 * Output Format
 * <p>
 * Return an integer array containing the submatrix sum for each query.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [   [1, 2, 3]
 * [4, 5, 6]
 * [7, 8, 9]   ]
 * <p>
 * B = [1, 2]
 * <p>
 * C = [1, 2]
 * <p>
 * D = [2, 3]
 * <p>
 * E = [2, 3]
 * <p>
 * Input 2:
 * <p>
 * A = [   [5, 17, 100, 11]
 * [0, 0,  2,   8]    ]
 * <p>
 * B = [1, 1]
 * <p>
 * C = [1, 4]
 * <p>
 * D = [2, 2]
 * <p>
 * E = [2, 4]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * [12, 28]
 * <p>
 * Output 2:
 * <p>
 * [22, 19]
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * For query 1: Submatrix contains elements: 1, 2, 4 and 5. So, their sum is 12.
 * <p>
 * For query 2: Submatrix contains elements: 5, 6, 8 and 9. So, their sum is 28.
 * <p>
 * Explanation 2:
 * <p>
 * For query 1: Submatrix contains elements: 5, 17, 0 and 0. So, their sum is 22.
 * <p>
 * For query 2: Submatrix contains elements: 11 and 8. So, their sum is 19.
 *
 * @author sudhir on 19-Sep-2020
 */
public class SubMatrixSumQueries {
    public int[] solve(int[][] A, int[] B, int[] C, int[] D, int[] E) {
        // A=>input matrix of integers
        // B=>row value of top-left corner
        // C=>column value of top-left corner
        // D=>row value of bottom-right corner
        // E=>column value of bottom-right corner
        // size of B/C/D/E is the number of queries
        // compute the prefix sums for the matrix
        int mod = (int) 1e9 + 7;
        int rows = A.length;
        int cols = A[0].length;
        int[][] prefixSum = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                prefixSum[i][j] = A[i][j];
                if (prefixSum[i][j] < 0) {
                    prefixSum[i][j] += mod;
                }
                if (i - 1 >= 0) {
                    if (prefixSum[i - 1][j] < 0) {
                        prefixSum[i - 1][j] += mod;
                    }
                    prefixSum[i][j] = (prefixSum[i][j] % mod + prefixSum[i - 1][j] % mod) % mod;
                }
                if (j - 1 >= 0) {
                    if (prefixSum[i][j - 1] < 0) {
                        prefixSum[i][j - 1] += mod;
                    }
                    prefixSum[i][j] = (prefixSum[i][j] % mod + prefixSum[i][j - 1] % mod) % mod;
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    if (prefixSum[i - 1][j - 1] < 0) {
                        prefixSum[i - 1][j - 1] += mod;
                    }
                    prefixSum[i][j] = prefixSum[i][j] - prefixSum[i - 1][j - 1];
                    if (prefixSum[i][j] < 0) {
                        prefixSum[i][j] += mod;
                    } else {
                        prefixSum[i][j] = prefixSum[i][j] % mod;
                    }
                }
            }
        }
        int[] result = new int[B.length];
        for (int i = 0; i < B.length; i++) {
            result[i] = prefixSum[D[i] - 1][E[i] - 1] % mod;
            if (C[i] - 2 >= 0) {
                result[i] = result[i] - prefixSum[D[i] - 1][C[i] - 2];
                if (result[i] < 0) {
                    result[i] += mod;
                } else {
                    result[i] = result[i] % mod;
                }
            }
            if (B[i] - 2 >= 0) {
                result[i] = result[i] - prefixSum[B[i] - 2][E[i] - 1];
                if (result[i] < 0) {
                    result[i] += mod;
                } else {
                    result[i] = result[i] % mod;
                }
            }
            if (B[i] - 2 >= 0 && C[i] - 2 >= 0) {
                result[i] = (result[i] % mod + prefixSum[B[i] - 2][C[i] - 2] % mod) % mod;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SubMatrixSumQueries subMatrixSumQueries = new SubMatrixSumQueries();
        int[][] a1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[] b1 = {1, 2};
        int[] c1 = {1, 2};
        int[] d1 = {2, 3};
        int[] e1 = {2, 3};
        System.out.println(Arrays.toString(subMatrixSumQueries.solve(a1, b1, c1, d1, e1)));

        int[][] a2 = {{5, 17, 100, 11}, {0, 0, 2, 8}};
        int[] b2 = {1, 1};
        int[] c2 = {1, 4};
        int[] d2 = {2, 2};
        int[] e2 = {2, 4};
        System.out.println(Arrays.toString(subMatrixSumQueries.solve(a2, b2, c2, d2, e2)));
    }
}

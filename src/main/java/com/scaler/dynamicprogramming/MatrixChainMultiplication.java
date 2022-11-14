package com.scaler.dynamicprogramming;

/**
 * Given an array of integers A representing chain of 2-D matrices such that the dimensions of
 * ith matrix is A[i-1] x A[i].
 * <p>
 * Find the most efficient way to multiply these matrices together.
 * The problem is not actually to perform the multiplications, but merely to decide in which order
 * to perform the multiplications.
 * <p>
 * Return the minimum number of multiplications needed to multiply the chain.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of the array <= 1000
 * <p>
 * 1 <= A[i] <= 100
 * <p>
 * Input Format
 * <p>
 * The only argument given is the integer array A.
 * <p>
 * Output Format
 * <p>
 * Return an integer denoting the minimum number of multiplications needed to multiply the chain.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [40, 20, 30, 10, 30]
 * <p>
 * Input 2:
 * <p>
 * A = [10, 20, 30]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 26000
 * <p>
 * Output 2:
 * <p>
 * 6000
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Dimensions of A1 = 40 x 20
 * <p>
 * Dimensions of A2 = 20 x 30
 * <p>
 * Dimensions of A3 = 30 x 10
 * <p>
 * Dimensions of A4 = 10 x 30
 * <p>
 * First, multiply A2 and A3 ,cost = 20*30*10 = 6000
 * <p>
 * Second, multiply A1 and (Matrix obtained after multiplying A2 and A3) =  40 * 20 * 10 = 8000
 * <p>
 * Third, multiply (Matrix obtained after multiplying A1, A2 and A3) and A4 =  40 * 10 * 30 = 12000
 * <p>
 * Total Cost = 12000 + 8000 + 6000 = 26000
 * <p>
 * Explanation 2:
 * <p>
 * Cost to multiply two matrices with dimensions 10 x 20 and 20 x 30 = 10 * 20 * 30 = 6000.
 *
 * @author sudhir on 21-Sep-2020
 */
public class MatrixChainMultiplication {
    public int solve(int[] A) {
        int n = A.length;
        if (n == 1) {
            return 0;
        }
        // for a given N, we will have N-1 matrices and we name it from 1 to N-1 i.e. matrix 1 to matrix N-1
        // cost[i][j] represents min cost of multiplying from matrix i to matrix j.
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i != j) {
                    cost[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for (int diff = 1; diff <= n - 2; diff++) {
            for (int i = 1; i <= n - diff - 1; i++) {
                int j = i + diff;
                for (int k = i; k < j; k++) {
                    if (cost[i][k] != Integer.MAX_VALUE && cost[k + 1][j] != Integer.MAX_VALUE) {
                        cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k + 1][j] + (A[i - 1] * A[k] * A[j]));
                    } else {
                        cost[i][j] = A[i - 1] * A[i] * A[j];
                    }
                }
            }
        }
        return cost[1][n - 1];
    }

    public static void main(String[] args) {
        MatrixChainMultiplication m = new MatrixChainMultiplication();
        int[] a1 = {5, 4, 6, 2, 7};
        System.out.println(m.solve(a1));
    }
}

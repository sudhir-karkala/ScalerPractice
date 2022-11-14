package com.scaler.dynamicprogramming;

/**
 * Given two integer arrays A and B of size N each which represent values and weights associated with N items respectively.
 * <p>
 * Also given an integer C which represents knapsack capacity.
 * <p>
 * Find out the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.
 * <p>
 * NOTE:
 * <p>
 * You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
 * <p>
 * <p>
 * Problem Constraints
 * 1 <= N <= 10^3
 * <p>
 * 1 <= C <= 10^3
 * <p>
 * 1 <= A[i], B[i] <= 10^3
 * <p>
 * Input Format
 * <p>
 * First argument is an integer array A of size N denoting the values on N items.
 * <p>
 * Second argument is an integer array B of size N denoting the weights on N items.
 * <p>
 * Third argument is an integer C denoting the knapsack capacity.
 * <p>
 * Output Format
 * <p>
 * Return a single integer denoting the maximum value subset of A such that sum of the weights of this subset
 * is smaller than or equal to C.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [60, 100, 120]
 * <p>
 * B = [10, 20, 30]
 * <p>
 * C = 50
 * <p>
 * Input 2:
 * <p>
 * A = [10, 20, 30, 40]
 * <p>
 * B = [12, 13, 15, 19]
 * <p>
 * C = 10
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 220
 * Output 2:
 * <p>
 * 0
 * <p>
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Taking items with weight 20 and 30 will give us the maximum value i.e 100 + 120 = 220
 * <p>
 * Explanation 2:
 * <p>
 * Knapsack capacity is 10 but each item has weight greater than 10 so no items can be
 * considered in the knapsack therefore answer is 0.
 *
 * @author sudhir on 08-Sep-2020
 */
public class Knapsack01 {
    /**
     * DP solution with O(N*C) time and space where N = number of items and C = max capacity
     * @param A value/profit of individual items.
     * @param B weights of the items.
     * @param C max capacity.
     * @return
     */
    public int solve(int[] A, int[] B, int C) {
        // dp[i][j] => represents the max value of knapsack considering items till ith index(1-based index)
        // and capacity j
        int[][] dp = new int[A.length + 1][C + 1];
        for (int i = 1; i <= A.length; i++) {
            for (int j = 0; j <= C; j++) {
                if (B[i - 1] <= j) {
                    // if the weight of the current item is less than capacity j, then 2 possibilities exist.
                    // 1. we can consider the item and update the current state as dp[i - 1][j - B[i - 1]] + A[i - 1].
                    // dp[i - 1][j - B[i - 1]] represents the state considering items till (i - 1) and
                    // capacity (j - B[i - 1]) which is the capacity of the knapsack before adding the current item.
                    // since we are selecting the current item, we had the value/profit to the knapsack which is A[i - 1].
                    // 2. we can discard the item and retain the previous state.
                    // we take max of those 2 possibilities.
                    dp[i][j] = Math.max(dp[i - 1][j - B[i - 1]] + A[i - 1], dp[i - 1][j]);
                } else {
                    // if the weight of (i-1)th item exceeds capacity j, then we discard that item
                    // and take the previous dp state as the current state.
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[A.length][C];
    }

    public static void main(String[] args) {
        Knapsack01 knapsack01 = new Knapsack01();
        int[] values1 = {60, 100, 120};
        int[] weights1 = {10, 20, 30};
        int capacity1 = 50;
        System.out.println(knapsack01.solve(values1, weights1, capacity1));

        int[] values2 = {10, 20, 30, 40};
        int[] weights2 = {12, 13, 15, 19};
        int capacity2 = 10;
        System.out.println(knapsack01.solve(values2, weights2, capacity2));
    }
}

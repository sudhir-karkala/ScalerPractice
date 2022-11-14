package com.scaler.dynamicprogramming;

/**
 * Given a knapsack weight A and a set of items with certain value B[i] and weight C[i],
 * we need to calculate maximum amount that could make up this quantity exactly.
 * <p>
 * This is different from classical Knapsack problem, here we are allowed to use unlimited number of instances of an item.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= A <= 1000
 * <p>
 * 1 <= |B| <= 1000
 * <p>
 * 1 <= B[i] <= 1000
 * <p>
 * 1 <= C[i] <= 1000
 * <p>
 * Input Format
 * <p>
 * First argument is the Weight of knapsack A
 * <p>
 * Second argument is the vector of values B
 * <p>
 * Third argument is the vector of weights C
 * <p>
 * Output Format
 * <p>
 * Return the maximum value that fills the knapsack completely.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = 10,
 * <p>
 * B = [5],
 * <p>
 * C = [10]
 * <p>
 * Input 2:
 * <p>
 * A = 10
 * <p>
 * B = [6, 7]
 * <p>
 * C = [5, 5]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 5
 * <p>
 * Output 2:
 * <p>
 * 14
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Only valid possibility is to take the given item.
 * <p>
 * Explanation 2:
 * <p>
 * Take the second item twice.
 *
 * @author sudhir on 12-Sep-2020
 */
public class UnboundedKnapsack {
    public int solve(int A, int[] B, int[] C) {
        // dp[i][j] => represents the max value of knapsack considering items till ith index(1-based index)
        // and capacity j
        // A=>weight of knapsack
        // B=>value/profit array of items
        // C=>weight of items
        int[][] dp = new int[C.length + 1][A + 1];
        for (int i = 1; i <= C.length; i++) {
            for (int j = 0; j <= A; j++) {
                if (C[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i][j - C[i - 1]] + B[i - 1], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[C.length][A];
    }

    public static void main(String[] args) {
        UnboundedKnapsack unboundedKnapsack = new UnboundedKnapsack();
        int W1 = 10;
        int[] p1 = {6, 7};
        int[] w1 = {5, 5};
        System.out.println(unboundedKnapsack.solve(W1, p1, w1));
    }
}

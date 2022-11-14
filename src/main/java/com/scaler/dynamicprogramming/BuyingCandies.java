package com.scaler.dynamicprogramming;

/**
 * Rishik likes candies a lot. So, he went to a candy-shop to buy candies.
 * <p>
 * The shopkeeper showed him N packets each containg A[i] candies for cost of C[i] nibbles,
 * each candy in that packet has a sweetness B[i].
 * The shopkeeper puts the condition that Rishik can buy as many complete candy-packets as he wants
 * but he can't buy a part of the packet.
 * <p>
 * Rishik has D nibbles, can you tell him the maximum amount of sweetness he can get from candy-packets he will buy?
 * Problem Constraints
 * <p>
 * 1 <= N <= 700
 * <p>
 * 1 <= A[i] <= 1000
 * <p>
 * 1 <= B[i] <= 1000
 * <p>
 * 1 <= C[i],D <= 1000
 * <p>
 * Input Format
 * <p>
 * First argument of input is an integer array A
 * <p>
 * Second argument of input is an integer array B
 * <p>
 * Third argument of input is an integer array C
 * <p>
 * Fourth argument of input is an integer D
 * <p>
 * Output Format
 * <p>
 * Return a single integer denoting maximum sweetness of the candies that he can buy
 * <p>
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [1, 2, 3]
 * <p>
 * B = [2, 2, 10]
 * <p>
 * C = [2, 3, 9]
 * <p>
 * D = 8
 * <p>
 * Input 2:
 * <p>
 * A = [2]
 * <p>
 * B = [5]
 * <p>
 * C = [10]
 * <p>
 * D = 99
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 10
 * Output 2:
 * <p>
 * 90
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Choose 1 Packet of kind 1 = 1 Candy of 2 Sweetness = 2 Sweetness
 * <p>
 * Choose 2 Packet of kind 2 = 2 Candy of 2 Sweetness = 8 Sweetness
 * <p>
 * Explanation 2:
 * <p>
 * Buy 9 Packet of kind 1. 18 Candy each of 5 Sweetness = 90 Sweetness
 *
 * @author sudhir on 12-Sep-2020
 */
public class BuyingCandies {
    public int solve(int[] A, int[] B, int[] C, int D) {
        // A=>number of candies in packet i is A[i]
        // B=>sweetness of A[i]
        // C=>nibbles
        // D=>max nibble=>capacity
        int[][] dp = new int[C.length + 1][D + 1];
        for (int i = 1; i <= C.length; i++) {
            for (int j = 0; j <= D; j++) {
                if (C[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i][j - C[i - 1]] + B[i - 1] * A[i - 1], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[C.length][D];
    }

    public static void main(String[] args) {
        BuyingCandies buyingCandies = new BuyingCandies();
        int[] candies = {1, 2, 3};
        int[] sweetness = {2, 2, 10};
        int[] cost = {2, 3, 9};
        int maxCost = 8;
        System.out.println(buyingCandies.solve(candies, sweetness, cost, maxCost));
    }
}

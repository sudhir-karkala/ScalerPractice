package com.scaler.dynamicprogramming;

/**
 * You are given a set of coins A. In how many ways can you make sum B assuming you have infinite amount of each coin in the set.
 * <p>
 * NOTE:
 * <p>
 * Coins in set A will be unique. Expected space complexity of this problem is O(B).
 * The answer can overflow. So, return the answer % (106 + 7).
 *
 * @author sudhir on 11-Jul-2020
 */
public class CoinSumInfinite {
    public int coinchange2(int[] A, int B) {
        int mod = (int) 1e6 + 7;
        // dp[i] represents number of ways to get to an amount i. order of coin selection doesn't matter.
        // i.e. for amount=4, 1+3 or 3+1 is considered as 1 way(select 1 first and then 3 or vice versa)
        long[] dp = new long[B + 1];
        dp[0] = 1;
        for (int i = 0; i < A.length; i++) {//coins
            for (int j = A[i]; j <= B; j++) {//amount
                dp[j] = (dp[j] % mod + dp[j - A[i]] % mod) % mod;
            }
        }
        return (int) dp[B];
    }

    public static void main(String[] args) {
        CoinSumInfinite c = new CoinSumInfinite();
        int[] coins = {1, 2, 3};
        int amount = 4;
        System.out.println(c.coinchange2(coins, amount));
    }
}

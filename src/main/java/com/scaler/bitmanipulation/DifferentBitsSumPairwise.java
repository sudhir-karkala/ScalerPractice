package com.scaler.bitmanipulation;

/**
 * We define f(X, Y) as number of different corresponding bits in binary representation of X and Y. <br/>
 * For example, f(2, 7) = 2, since binary representation of 2 and 7 are 010 and 111,respectively. <br/>
 * The first and the third bit differ, so f(2, 7) = 2. <br/>
 * You are given an array of N positive integers, A1, A2 ,..., AN. <br/>
 * Find sum of f(Ai, Aj) for all pairs (i, j) such that 1 ? i, j ? N. Return the answer modulo 10^9+7.
 *
 * @author sudhir on 04-Apr-2020
 */
public class DifferentBitsSumPairwise {
    public int cntBits(int[] A) {
        long sum = 0;
        int MOD = 1000000007;
        for (int i = 0; i < 32; i++) {
            long count = 0;
            for (int j = 0; j < A.length; j++) {
                //check if ith bit is set
                if (((A[j] >> i) & 1) == 1) {
                    count++;
                }
            }
            sum = (sum + ((2 * count % MOD) * ((A.length - count) % MOD)) % MOD) % MOD;
        }
        return (int) sum;
    }

    public static void main(String[] args) {
        DifferentBitsSumPairwise d = new DifferentBitsSumPairwise();
        int[] a = {1, 3, 5};
        System.out.println(d.cntBits(a));
    }
}

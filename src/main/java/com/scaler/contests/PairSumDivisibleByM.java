package com.scaler.contests;

/**
 * Given an array of integers A and an integer B, find and return the number of pairs in A whose sum is divisible by B.<br/>
 * Since the answer may be large, return the answer modulo (10^9 + 7).
 * @see com.geeksforgeeks.ds.arrays.CountPairsWhoseSumIsDivisibleBy4 for intuition.
 * @author sudhir on 16-May-2020
 */
public class PairSumDivisibleByM {
    public int solve(int[] A, int B) {
        int MOD = 1000000007;
        int[] freq = new int[B];
        for (int i = 0; i < A.length; i++) {
            freq[A[i] % B]++;
        }
        long pairs = 0L;
        if (freq[0] > 0) {
            // If both pairs are divisible by B
            pairs += ((freq[0] % MOD) * 1L * ((freq[0] - 1) % MOD) / 2) % MOD;
        }
        // count for all i and (B-i) freq pairs
        for (int k = 1; k <= B / 2 && k != (B - k); k++) {
            pairs += ((freq[k] % MOD) * 1L * (freq[B - k] % MOD)) % MOD;
        }
        // if B is even, then we have to consider the freq[B/2] element
        // for example: if B = 6, we have to consider those cases where modulo gives 3 as the answer, so that summing up
        // gives 6 modulo 6 which is 0.
        // When B is odd, this case won't arise and other frequency array elements are considered in the calculation above.
        if (B > 0 && B % 2 == 0) {
            pairs += ((freq[B / 2] % MOD) * 1L * ((freq[B / 2] - 1) % MOD) / 2) % MOD;
        }
        return (int) (pairs % MOD);
    }

    public static void main(String[] args) {
        PairSumDivisibleByM p = new PairSumDivisibleByM();
        int[] a1 = {5, 17, 100, 11};
        int b1 = 28;
        int[] a2 = {1, 2, 3, 4, 5};
        int b2 = 2;
        System.out.println(p.solve(a1, b1));
        System.out.println(p.solve(a2, b2));
    }
}

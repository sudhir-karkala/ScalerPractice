package com.scaler.contests;

import java.util.Arrays;

/**
 * Given an integer array A of size N. In one operation, you can remove any element from the array and
 * cost of this operation is sum of all elements in the array present before this operation. <br/>
 * Find the minimum cost to remove all elements from the array. <br/>
 * NOTE: Return the answer modulo 10^9 + 7 as the answer can be large.
 *
 * @author sudhir on 12-May-2020
 */
public class RemoveElements {
    public int solve(int[] A) {
        int MOD = (int) 1e9 + 7;
        Arrays.sort(A);
        long cost = 0L;
        for (int i = 0; i < A.length; i++) {
            cost += ((A[i] % MOD) * 1L * ((A.length - i) % MOD)) % MOD;
        }
        return (int) (cost % MOD);
    }

    public static void main(String[] args) {
        RemoveElements r = new RemoveElements();
        int[] nums = {2, 1, 4, 5, 3};
        System.out.println(r.solve(nums));
    }
}

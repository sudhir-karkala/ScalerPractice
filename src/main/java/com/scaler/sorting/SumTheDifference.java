package com.scaler.sorting;

import java.util.Arrays;

/**
 * Given an integer array A of size N.
 * You have to find all possible non-empty subsequence of the array of numbers and then,
 * for each subsequence, find the difference between the largest and smallest numbers in that subsequence.
 * Then add up all the differences to get the number. <br/>
 * As the number may be large, output the number modulo 1e9 + 7 (1000000007). <br/>
 * NOTE: Subsequence can be non-contiguous.
 *
 * @author sudhir on 20-May-2020
 */
public class SumTheDifference {
    /**
     * The expected answer can be computed as follows:
     * a. Find the max element of each subset in the array. Sum their values, say maxsum
     * b. Find the min element of each subset in the array. Sum their values, say minsum
     * c. Return (maxsum - minsum) as the answer.
     * <p>
     * Following is the observation we make to find minsum. Similar observation can be made for maxsum.
     * We need to consider the number of times an element appears as minimum element. i.e. number of subsets for each element.
     * Consider the array of n elements to be sorted in ascending order[a1,a2,a3,....an].
     * if we exclude a1, then from [a2,a3,..an] we can have 2^(n-1) subsets. If we add a1 to each subset,
     * then 2^(n-1) subsets will have a1 as the minimum element.
     * Similarly 2^(n-2) subsets for a2, 2^(n-3) subsets for a3 and so on.
     * minsum = a1*2^(n-1) + a2*2^(n-2) + a3*2^(n-3) +....+ an
     * i.e. minsum = an + 2(an-1 + 2(an-2 + ...+2(a1))) using Horner's rule.
     * Similarly maxsum can be written as maxsum = a1 + 2(a2 + 2(a3 + ...+2(an))) using Horner's rule.
     * </p>
     * <p>
     * We can observe that we can compute maxsum by picking elements from the end and traversing right to left
     * i.e. a[n-1] to a[0] and doing 2 * maxsum + a[n-1-i] for all i from 0 to n-1
     * We can compute minsum by picking elements from left to right i.e. a[0] to a[n-1]
     * and doing 2 * minsum + a[i] for all i from 0 to n-1
     * </p>
     *
     * @param A
     * @return
     */
    public int solve(int[] A) {
        int MOD = (int) (1e9 + 7);
        Arrays.sort(A);
        long maxsum = 0L;
        long minsum = 0L;
        for (int i = 0; i < A.length; i++) {
            maxsum = 2L * maxsum + A[A.length - 1 - i];
            maxsum %= MOD;
            minsum = 2L * minsum + A[i];
            minsum %= MOD;
        }
        long result = maxsum - minsum;
        while (result < 0) {
            result += MOD;
        }
        return (int) (result % MOD);
    }
}

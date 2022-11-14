package com.scaler.hashing;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers A, find and return whether the given array contains a
 * subarray with a sum equal to 0.<br/>
 * If the given array contains a sub-array with sum zero return 1 else return 0. <br/>
 * Note: Length of sub array should be at least one.
 *
 * @author sudhir on 29-Mar-2020
 */
public class FindZeroSumSubArrayExists {
    public int solve(int[] A) {
        long sum = 0;
        sum = A[0];
        Set<Long> set = new HashSet<>();
        for (int i = 1; i < A.length; i++) {
            // case 1: A[i] is zero, example: [1 2 3 0 4], here A[3] is the subarray with zero sum
            // case 2: (sum + A[i]) is zero, example: [4 -2 -2 3 4], here A[0..2] is the subarray with zero sum
            // case 3: if the set already contains (sum + A[i]), example: [[5 -4 3 1 4 -2 -2], here A[0..3]
            // has sum = 9 and A[0..6] has sum = 9, so A[4..6] is the subarray with zero sum.
            // i.e. if A[i..j] and A[i..k] has same sums, then A[j+1..k] has zero sum, i<j<k
            if (A[i] == 0 || sum + A[i] == 0 || set.contains(sum + A[i])) {
                return 1;
            }
            sum += A[i];
            set.add(sum);
        }
        return 0;
    }

    public static void main(String[] args) {
        FindZeroSumSubArrayExists f = new FindZeroSumSubArrayExists();
        int[] a1 = {1, 2, 3, 4, 5};
        int[] a2 = {5, 17, -22, 11};
        System.out.println(f.solve(a1));
        System.out.println(f.solve(a2));
    }
}

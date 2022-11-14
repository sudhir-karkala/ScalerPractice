package com.scaler.dynamicprogramming;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a strictly increasing array A of positive integers forming a sequence.
 * <p>
 * A sequence X1, X2, X3, ..., XN is fibonacci like if
 * <p>
 * <p>
 * N > =3
 * Xi + Xi+1 = Xi+2 for all i+2 <= N
 * Find and return the length of the longest Fibonacci-like subsequence of A.
 * <p>
 * If one does not exist, return 0.
 * <p>
 * NOTE: A subsequence is derived from another sequence A by deleting any number of elements (including none) from A,
 * without changing the order of the remaining elements.
 *
 * @author sudhir on 13-Jul-2020
 */
public class LengthOfLongestFibonacciSubsequence {
    // Brute force solution that takes O((n^2)log m) time, where m is the maximum value in A.
    public int solve(int[] A) {
        int n = A.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(A[i]);
        }
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int f1 = A[i];
                int f2 = A[j];
                int len = 2;
                while (set.contains(f1 + f2)) {
                    int f3 = f1 + f2;
                    f1 = f2;
                    f2 = f3;
                    len++;
                    maxLen = Math.max(len, maxLen);
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LengthOfLongestFibonacciSubsequence l = new LengthOfLongestFibonacciSubsequence();
        int[] a1 = {2, 8, 11, 14, 19, 26, 34, 37, 43, 52};
        System.out.println(l.solve(a1));
    }
}

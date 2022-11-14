package com.scaler.sorting;

import java.util.Arrays;

/**
 * Given an array of integers A and an integer B, find and return the difference of B'th max element and B'th min element of the array A.
 *
 * @author sudhir on 10-Apr-2020
 */
public class MaxMinusMin {
    public int solve(int[] A, int B) {
        Arrays.sort(A);
        return A[A.length - B] - A[B - 1];
    }

    public static void main(String[] args) {
        MaxMinusMin m = new MaxMinusMin();
        int[] a = {5, 17, 100, 11};
        int b = 1;
        System.out.println(m.solve(a, b));
    }
}

package com.scaler.sorting;

/**
 * Given an array of integers A of size N that is a permutation of [0, 1, 2, ..., (N-1)]. <br/>
 * It is allowed to swap any two elements (not necessarily consecutive). <br/>
 * Find the minimum number of swaps required to sort the array in ascending order.
 *
 * @author sudhir on 10-Apr-2020
 */
public class MinimumSwaps2 {
    public int solve(int[] A) {
        int swaps = 0;
        for (int i = 0; i < A.length; i++) {
            while (A[i] != i) {
                int temp = A[A[i]];
                A[A[i]] = A[i];
                A[i] = temp;
                swaps++;
            }
        }
        return swaps;
    }

    public static void main(String[] args) {
        MinimumSwaps2 m = new MinimumSwaps2();
        int[] a1 = {1, 2, 3, 4, 0};
        int[] a2 = {2, 0, 1, 3};
        System.out.println(m.solve(a1));
        System.out.println(m.solve(a2));
    }
}

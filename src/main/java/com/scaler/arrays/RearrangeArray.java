package com.scaler.arrays;

import java.util.Arrays;

/**
 * Given an array of integers A of size N that is a permutation of [0, 1, 2, ..., (N-1)]. <br/>
 * Rearrange the array such that A[i] = j is changed to A[j] = i for all i and j form 0 to N-1. <br/>
 * Note: Try solving this with O(1) extra space.
 *
 * @author sudhir on 04-Apr-2020
 */
public class RearrangeArray {
    public int[] solve(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0) {
                rearrangeUtil(A, i);
            }
        }
        for (int i = 0; i < A.length; i++) {
            A[i] = -(A[i] + 1);
        }
        return A;
    }

    private void rearrangeUtil(int[] arr, int i) {
        int value = -(i + 1);
        int index = arr[i];
        while (arr[index] >= 0) {
            int tempIndex = arr[index];
            arr[index] = value;
            value = -(index + 1);
            index = tempIndex;
        }
    }

    // Approach 2: Using extra space
    public int[] solve2(int[] A) {
        int[] temp = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            temp[A[i]] = i;
        }
        return temp;
    }

    public static void main(String[] args) {
        RearrangeArray r = new RearrangeArray();
        int[] a = {1, 2, 3, 4, 0};
        int[] b = {2, 0, 1, 3};
        System.out.println(Arrays.toString(r.solve(a)));
        System.out.println(Arrays.toString(r.solve(b)));

    }
}

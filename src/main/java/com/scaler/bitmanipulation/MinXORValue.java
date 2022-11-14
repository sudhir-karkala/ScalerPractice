package com.scaler.bitmanipulation;

import java.util.Arrays;

/**
 * Given an integer array A of N integers, find the pair of integers in the array which have minimum XOR value. Report the minimum XOR value.
 *
 * @author sudhir on 04-Apr-2020
 */
public class MinXORValue {
    public int findMinXor(int[] A) {
        Arrays.sort(A);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length - 1; i++) {
            if (min > (A[i] ^ A[i + 1])) {
                min = A[i] ^ A[i + 1];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        MinXORValue m = new MinXORValue();
        int[] a1 = {0, 2, 5, 7};
        int[] a2 = {0, 4, 7, 9};
        System.out.println(m.findMinXor(a1));
        System.out.println(m.findMinXor(a2));
    }
}

package com.scaler.arrays;

import java.util.Arrays;

/**
 * Given an array of integers A, sort the array into a wave like array and return it, <br/>
 * In other words, arrange the elements into a sequence such that a1 >= a2 <= a3 >= a4 <= a5..... <br/>
 * NOTE : If there are multiple answers possible, return the one that's lexicographically smallest.
 *
 * @author sudhir on 04-Apr-2020
 */
public class WaveArray {
    public int[] wave(int[] A) {
        Arrays.sort(A);
        for (int i = 0; i < A.length - 1; i = i + 2) {
            swap(A, i, i + 1);
        }
        return A;
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void main(String[] args) {
        WaveArray w = new WaveArray();
        int[] a = {1, 2, 3, 4};
        System.out.println(Arrays.toString(w.wave(a)));
    }
}

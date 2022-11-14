package com.scaler.sorting;

import java.util.Arrays;

/**
 * Given an array of positive integers A, check and return whether the array elements are consecutive or not.<br/>
 * NOTE: Try this with O(1) extra space.<br/>
 * Return 1 if the array elements are consecutive else return 0.
 *
 * @author sudhir on 10-Apr-2020
 */
public class ArrayWithConsecutiveElements {
    public int solve(int[] A) {
        Arrays.sort(A);
        for (int i = 1; i < A.length; i++) {
            if (A[i] - A[i - 1] != 1) {
                return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        ArrayWithConsecutiveElements arrayWithConsecutiveElements = new ArrayWithConsecutiveElements();
        int[] a = {3, 2, 1, 4, 5};
        int[] b = {3, 6, 1, 4, 5};
        System.out.println(arrayWithConsecutiveElements.solve(a));
        System.out.println(arrayWithConsecutiveElements.solve(b));
    }
}

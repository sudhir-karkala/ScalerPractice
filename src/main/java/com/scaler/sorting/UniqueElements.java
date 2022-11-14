package com.scaler.sorting;

import java.util.Arrays;

/**
 * You are given an array A of N elements. You have to make all elements unique,
 * to do so in one step you can increase any number by one. <br/>
 * Find the minimum number of steps.
 *
 * @author sudhir on 10-Apr-2020
 */
public class UniqueElements {
    public int solve(int[] A) {
        Arrays.sort(A);
        int steps = 0;
        for (int i = 1; i < A.length; i++) {
            while (A[i] <= A[i - 1]) {
                steps++;
                A[i]++;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        UniqueElements uniqueElements = new UniqueElements();
        int[] a = {1, 1, 3};
        System.out.println(uniqueElements.solve(a));
    }
}

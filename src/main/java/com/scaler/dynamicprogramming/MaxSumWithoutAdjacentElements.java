package com.scaler.dynamicprogramming;

/**
 * Given a 2 x N grid of integer, A, choose numbers such that the sum of the numbers is maximum
 * and no two chosen numbers are adjacent horizontally, vertically or diagonally, and return it.
 *
 * @author sudhir on 25-Apr-2020
 */
public class MaxSumWithoutAdjacentElements {
    public int adjacent(int[][] A) {
        // the idea is to transform 2-d array into 1-d array and work on 1-d array to get the required answer.
        // 1-d array is formed by picking the max(top element, bottom element) in the same column for all columns in the grid.
        int[] transform = new int[A[0].length];
        for (int c = 0; c < A[0].length; c++) {
            transform[c] = Math.max(A[0][c], A[1][c]);
        }
        int n = transform.length;
        int[] maxsum = new int[n];
        maxsum[0] = transform[0];
        if (n > 1) {
            maxsum[1] = Math.max(maxsum[0], transform[1]);
        }
        for (int i = 2; i < maxsum.length; i++) {
            maxsum[i] = Math.max(maxsum[i - 1], transform[i] + maxsum[i - 2]);
        }
        return maxsum[maxsum.length - 1];
    }

    public static void main(String[] args) {
        MaxSumWithoutAdjacentElements m = new MaxSumWithoutAdjacentElements();
        int[][] nums1 = {{1}, {2}};
        int[][] nums2 = {{1, 2, 3, 4}, {2, 3, 4, 5}};
        System.out.println(m.adjacent(nums1));
        System.out.println(m.adjacent(nums2));

    }
}

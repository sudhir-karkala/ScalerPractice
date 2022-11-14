package com.scaler.searching;

import java.util.Arrays;

/**
 * Given a matrix of integers A of size N x M in which each row is sorted.
 * Find an return the overall median of the matrix A.
 * <li>
 * Note: No extra memory is allowed.
 * </li>
 * <li>
 * Note: Rows are numbered from top to bottom and columns are numbered from left to right.
 * </li>
 *
 * @author sudhir on 01-Mar-2020
 */
public class MatrixMedian {
    public static int findMedian(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int desired = ((m * n) + 1) / 2;
        int place = 0;
        int get = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            if (min > A[i][0]) {
                min = A[i][0];
            }
            if (max < A[i][n - 1]) {
                max = A[i][n - 1];
            }
        }
        while (min < max) {
            place = 0;
            int mid = min + (max - min) / 2;
            for (int i = 0; i < m; i++) {
                get = Arrays.binarySearch(A[i], mid);
                if (get < 0) {
                    get = Math.abs(get) - 1;
                } else {
                    while (get < A[i].length && A[i][get] == mid) {
                        get += 1;
                    }
                }
                place += get;
            }
            if (place < desired) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[][] a = {{1, 3, 5}, {2, 6, 9}, {3, 6, 9}};
        System.out.println(findMedian(a));
    }
}

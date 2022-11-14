package com.scaler.sorting;

import java.util.Arrays;

/**
 * Find the kth smallest element in an unsorted array of non-negative integers.
 * <p>
 * kth smallest element is the minimum possible n such that
 * there are at least k elements in the array <= n.
 * <p>
 * In other words, if the array A was sorted, then A[k - 1]
 * ( k is 1 based, while the arrays are 0 based )
 * <p>
 * NOTE: You are not allowed to modify the array ( The array is read only ).
 * Try to do it using constant extra space.
 *
 * @author sudhir on 10-Apr-2020
 */
public class KthSmallestInArray {
    // Approach: Using binary search
    public int kthsmallest(final int[] A, int K) {
        int min = Arrays.stream(A).min().getAsInt();
        int max = Arrays.stream(A).max().getAsInt();
        while (min <= max) {
            int mid = min + (max - min) / 2;
            int countless = 0;
            int countequal = 0;
            for (int i = 0; i < A.length; i++) {
                if (A[i] < mid) {
                    countless++;
                }
                if (A[i] == mid) {
                    countequal++;
                }
            }
            if (countless < K && (countless + countequal) >= K) {
                return mid;
            } else if (countless < K && (countless + countequal) < K) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        KthSmallestInArray kth = new KthSmallestInArray();
        int[] a = {2, 1, 4, 3, 2};
        int k = 3;
        System.out.println(kth.kthsmallest(a, k));
    }
}

package com.scaler.arrays;

import java.util.Arrays;

/**
 * Given an unsorted integer array A of size N.<br/>
 * Find the maximum difference between the successive elements in its sorted form. Try to solve it in linear time/space.<br/>
 * You may assume that all the elements in the array are non-negative integers and fit in the 32-bit signed integer range.<br/>
 * You may also assume that the difference will not overflow.<br/>
 * Return 0 if the array contains less than 2 elements.
 *
 * @author sudhir on 04-Apr-2020
 */
public class MaximumConsecutiveGap {
    public int maximumGap(final int[] A) {
        if (A.length < 2) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            max = Math.max(max, A[i]);
            min = Math.min(min, A[i]);
        }
        float min_gap = (float) (max - min) / (float) (A.length - 1);
        int max_gap = 0;
        int[] bucketmax = new int[A.length - 1];
        int[] bucketmin = new int[A.length - 1];
        Arrays.fill(bucketmax, Integer.MIN_VALUE);
        Arrays.fill(bucketmin, Integer.MAX_VALUE);
        for (int i = 0; i < A.length; i++) {
            if (A[i] == min || A[i] == max) {
                continue;
            }
            int index = (int) (Math.floor((A[i] - min) / min_gap));
            if (bucketmax[index] == Integer.MIN_VALUE) {
                bucketmax[index] = A[i];
            } else {
                bucketmax[index] = Math.max(bucketmax[index], A[i]);
            }
            if (bucketmin[index] == Integer.MAX_VALUE) {
                bucketmin[index] = A[i];
            } else {
                bucketmin[index] = Math.min(bucketmin[index], A[i]);
            }
        }
        int prevbucket = min;
        for (int i = 0; i < A.length - 1; i++) {
            if (bucketmin[i] != Integer.MAX_VALUE) {
                max_gap = Math.max(max_gap, bucketmin[i] - prevbucket);
                prevbucket = bucketmax[i];
            }
        }
        max_gap = Math.max(max_gap, max - prevbucket);
        return max_gap;
    }

    public static void main(String[] args) {
        MaximumConsecutiveGap m = new MaximumConsecutiveGap();
        int[] a = {1, 10, 5};
        System.out.println(m.maximumGap(a));
    }
}

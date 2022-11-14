package com.scaler.arrays;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * @author sudhir on 04-Apr-2020
 */
public class RainWaterTrapped {
    public int trap(final int[] A) {
        int prefix[] = new int[A.length];
        int suffix[] = new int[A.length];
        int maxleft = prefix[0];
        for (int i = 1; i < A.length; i++) {
            prefix[i] = Math.max(maxleft, A[i - 1]);
            maxleft = prefix[i];
        }
        int maxright = suffix[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--) {
            suffix[i] = Math.max(maxright, A[i + 1]);
            maxright = suffix[i];
        }
        int sum = 0;
        for (int i = 1; i < A.length - 1; i++) {
            int s = Math.min(prefix[i], suffix[i]);
            if (s > A[i]) {
                sum += (s - A[i]);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        RainWaterTrapped r = new RainWaterTrapped();
        int[] a = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(r.trap(a));

    }
}

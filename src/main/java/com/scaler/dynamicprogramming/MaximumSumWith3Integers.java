package com.scaler.dynamicprogramming;

/**
 * You are given an array A of N integers and three integers X, Y, and Z. <br/>
 * You have to find the maximum value of A[i]*X + A[j]*Y + A[k]*Z, where 1 <= i <= j <= k <= N.<br/>
 * Return an Integer S, i.e maximum value of (A[i]*X + A[j]*Y + A[k]*Z), where  1 <= i <= j <= k <= N.
 * <p>
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 10^5
 * <p>
 * -10000 <= A[i], B, C, D <= 10000
 *
 * @author sudhir on 26-Apr-2020
 */
public class MaximumSumWith3Integers {
    public int solve(int[] A, int B, int C, int D) {
        // since i <= j <= k, we can precompute A[i] * B and A[k] * D which are basically
        // maximum value from the left and right side respectively.
        int[] left = new int[A.length];
        int[] right = new int[A.length];
        left[0] = A[0] * B;
        right[A.length - 1] = A[A.length - 1] * D;
        for (int i = 1; i < A.length; i++) {
            left[i] = Math.max(left[i - 1], A[i] * B);
        }
        for (int i = A.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], A[i] * D);
        }

        // now we compute A[i] * X + A[j] * Y + A[k] * Z for every index in the array using the following:
        // left[i] + A[i] * C + right[i]
        // We do this for every index and keep track of maximum sum and return the maximum value as the answer.
        int maxsum = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            int cursum = left[i] + right[i] + A[i] * C;
            maxsum = Math.max(maxsum, cursum);
        }
        return maxsum;
    }

    public static void main(String[] args) {
        MaximumSumWith3Integers m = new MaximumSumWith3Integers();
        int[] A = {1, 5, -3, 4, -2};
        int x = 2;
        int y = 1;
        int z = -1;
        System.out.println(m.solve(A, x, y, z));
    }
}

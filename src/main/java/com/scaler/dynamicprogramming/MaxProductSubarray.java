package com.scaler.dynamicprogramming;

/**
 * Find the contiguous subarray within an array (containing at least one number)
 * which has the largest product.
 * Return an integer corresponding to the maximum product possible.
 *
 * @author sudhir on 26-Apr-2020
 */
public class MaxProductSubarray {
    public int maxProduct(final int[] A) {
        // curMaxTillIndex[i] denotes max product of the subarray denoted by subarray
        // indexed from 0 to i including A[i]
        int[] curMaxTillIndex = new int[A.length];
        // curMinTillIndex[i] denotes min product of the subarray denoted by subarray
        // indexed from 0 to i including A[i]
        int[] curMinTillIndex = new int[A.length];
        curMaxTillIndex[0] = curMinTillIndex[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            curMaxTillIndex[i] = Math.max(A[i], Math.max(curMaxTillIndex[i - 1] * A[i],
                    curMinTillIndex[i - 1] * A[i]));
            curMinTillIndex[i] = Math.min(A[i], Math.min(curMaxTillIndex[i - 1] * A[i],
                    curMinTillIndex[i - 1] * A[i]));
        }
        // now the maximum value in curMaxTillIndex[] array will be our answer
        int maxProd = Integer.MIN_VALUE;
        for (int i = 0; i < curMaxTillIndex.length; i++) {
            if (curMaxTillIndex[i] > maxProd) {
                maxProd = curMaxTillIndex[i];
            }
        }
        return maxProd;
    }

    // Space optimized version of the same solution
    public int maxProductSpaceOptimized(final int[] A) {
        int curMax = A[0];
        int curMin = A[0];
        int maxProd = A[0];
        for (int i = 1; i < A.length; i++) {
            int temp = curMax;
            curMax = Math.max(A[i], Math.max(curMax * A[i], curMin * A[i]));
            curMin = Math.min(A[i], Math.min(curMin * A[i], temp * A[i]));
            if (maxProd < curMax) {
                maxProd = curMax;
            }
        }
        return maxProd;
    }


    public static void main(String[] args) {
        MaxProductSubarray m = new MaxProductSubarray();
        int[] a = {2, 3, -2, 4};
        int[] b = {5, 1, -6, -5, 3, -7};
        System.out.println(m.maxProduct(a));
        System.out.println(m.maxProductSpaceOptimized(a));
        System.out.println(m.maxProduct(b));
        System.out.println(m.maxProductSpaceOptimized(b));
    }
}

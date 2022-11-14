package com.scaler.contests;

/**
 * Given two array of integers A, B of equal size N. <br/>
 * Power of an array is defined as the product of all the elements of the array. <br/>
 * If the power of array A >= power of array B return 1 else return 0.<br/>
 * 1 <= N <= 100000<br/>
 * 1 <= A[i], B[i] <= 10^9
 *
 * @author sudhir on 21-May-2020
 */
public class PowerfulArray {
    public int solve(int[] A, int[] B) {
        // Since the numbers can be in the range of 10^9, multiplying the numbers is not a good idea as
        // it can cause overflows.
        // Instead, we can perform (A[i] / B[i]) for every i and multiply them.
        // If the final result is < 0, then it means B is powerful, else A is powerful.
        double value = 1.0;
        for (int i = 0; i < A.length; i++) {
            value *= (double) A[i] / B[i];
        }
        if (Double.compare(value, 1.0) >= 0) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        PowerfulArray p = new PowerfulArray();
        int[] a1 = {9, 6, 8, 5, 1, 2};
        int[] b1 = {1, 1, 4, 6, 1, 9};
        System.out.println(p.solve(a1, b1));
        int[] a2 = {1, 2, 3, 4};
        int[] b2 = {2, 4, 3, 2};
        System.out.println(p.solve(a2, b2));
    }
}

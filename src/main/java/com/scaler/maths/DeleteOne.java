package com.scaler.maths;

/**
 * Given an integer array A of size N. <br/>
 * You have to delete one element such that the GCD(Greatest common divisor) of the remaining array is maximum.<br/>
 * Find the maximum value of GCD.
 *
 * @author sudhir on 04-Apr-2020
 */
public class DeleteOne {
    private int findGCD(int a, int b) {
        int m = Math.max(a, b);
        int n = Math.min(a, b);
        while (n != 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        return m;
    }

    public int solve(int[] A) {
        int[] prefixGCD = new int[A.length];
        prefixGCD[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixGCD[i] = findGCD(prefixGCD[i - 1], A[i]);
        }
        int[] suffixGCD = new int[A.length];
        suffixGCD[A.length - 1] = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--) {
            suffixGCD[i] = findGCD(suffixGCD[i + 1], A[i]);
        }
        int maxgcd = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            int gcd1, gcd2;
            if ((i - 1) < 0) {
                gcd1 = 0;
            } else {
                gcd1 = prefixGCD[i - 1];
            }
            if ((i + 1) > A.length - 1) {
                gcd2 = 0;
            } else {
                gcd2 = suffixGCD[i + 1];
            }
            int gcd = findGCD(gcd1, gcd2);
            if (gcd > maxgcd) {
                maxgcd = gcd;
            }
        }
        return maxgcd;
    }

    public static void main(String[] args) {
        DeleteOne d = new DeleteOne();
        int[] a = {12, 15, 18};
        System.out.println(d.solve(a));
    }
}

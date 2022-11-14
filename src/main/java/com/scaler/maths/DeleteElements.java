package com.scaler.maths;

/**
 * Given an integer array A of size N.<br/>
 * Find the minimum number of elements that need to be removed such that the GCD of the resulting array becomes 1.<br/>
 * If not possible then return -1.
 *
 * @author sudhir on 04-Apr-2020
 */
public class DeleteElements {
    private int findGcd(int a, int b) {
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
        int gcd = A[0];
        for (int i = 1; i < A.length; i++) {
            gcd = findGcd(gcd, A[i]);
        }
        if (gcd == 1) {
            return 0;
        }
        return -1;
    }

    public static void main(String[] args) {
        DeleteElements d = new DeleteElements();
        int[] a = {7, 2, 5};
        System.out.println(d.solve(a));
    }
}

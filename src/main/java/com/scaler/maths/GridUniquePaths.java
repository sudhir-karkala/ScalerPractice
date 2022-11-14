package com.scaler.maths;

/**
 * A robot is located at the top-left corner of an A x B grid
 * <p>
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid
 * <p>
 * How many possible unique paths are there?
 * <p>
 * Problem Constraints
 * <p>
 * A and B will be such that the resulting answer fits in a 32 bit signed integer.
 *
 * @author sudhir on 26-May-2020
 */
public class GridUniquePaths {
    /**
     * Note that we have to take m + n - 2 steps in total. We have to take (m - 1) steps going down and (n-1) steps going right.
     * In Combinatorics, we can write it as (m + n - 2)C(n - 1) = (m + n - 2)! / ((m - 1)! * (n - 1)!)
     *
     * @param A
     * @param B
     * @return
     */
    public int uniquePaths(int A, int B) {
        if (A == 0 || A == 1 || B == 0) {
            return 1;
        }
        int result = computeNcR(A, B);
        return result;
    }

    private int computeNcR(int a, int b) {
        // nCr can be expanded as (n*(n-1)*(n-2)....*(n-r+1)*(n-r)*(n-r-1)*....*(r+1)*r*(r-1)*(r-2)*....*2*1) in the numerator
        // and (n-r)*(n-r-1)*....*(r+1)*r*(r-1)*(r-2)*....*2*1) * (r*(r-1)*(r-2)*....*2*1) in the denominator
        // We can either simplify numerator and denominator as the following:
        // numerator = n*(n-1)*(n-2)....*(n-r+1) which has r terms, denominator = r*(r-1)*(r-2)*....*2*1 which has r terms
        // or the numerator as (n*(n-1)*(n-2)....*(n-r+1)*(n-r)*(n-r-1)*....*(r+1) and
        // denominator as (n-r)*(n-r-1)*....*(r+1)*r*(r-1)*(r-2)*....*2*1) which have (n-r) terms
        // depending on max(n-r+1, r+1) value so that we get lesser terms in numerator and denominator.
        long ans = 1;
        int denominator = 1;
        int n = a + b - 2;
        int r = b - 1;
        int start = Math.max(n - r + 1, r + 1);
        for (int i = start; i <= n; i++) {
            ans *= i;
            ans /= denominator;
            denominator++;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        GridUniquePaths g = new GridUniquePaths();
        int A = 15;
        int B = 9;
        System.out.println(g.uniquePaths(A, B));
    }
}

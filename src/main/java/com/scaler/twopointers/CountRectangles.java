package com.scaler.twopointers;

/**
 * Given a sorted array of distinct integers A and an integer B,
 * find and return how many rectangles with distinct configurations can be created using elements of this array
 * as length and breadth whose area is lesser than B.
 * <p>
 * (Note that a rectangle of 2 x 3 is different from 3 x 2 if we take configuration into view)
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= |A| <= 100000
 * <p>
 * 1 <= A[i] <= 10^9
 * <p>
 * 1 <= B <= 10^9
 * <p>
 * Input Format
 * <p>
 * The first argument given is the integer array A.
 * <p>
 * The second argument given is integer B.
 * <p>
 * Output Format
 * <p>
 * Return the number of rectangles with distinct configurations with area less than B modulo (109 + 7).
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [1, 2]
 * <p>
 * B = 5
 * <p>
 * Input 2:
 * <p>
 * A = [1, 2]
 * <p>
 * B = 1
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 4
 * <p>
 * Output 2:
 * <p>
 * 0
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * All 1X1, 2X2, 1X2 and 2X1 have area less than 5.
 * <p>
 * Explanation 2:
 * <p>
 * No Rectangle is valid.
 *
 * @author sudhir on 09-Mar-2020
 */
public class CountRectangles {
    private int MOD = 1000000007;

    public int solve(int[] A, int B) {
        int j = A.length - 1;
        int n = A.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            // we are looking for rectangles which are not squares, so i != j and i < j
            while (j >= 0 && ((long) A[i]) * A[j] >= B) {
                j--;
            }
            if (j <= i) {
                break;
            }
            // (j - i) will represent all rectangles satisfying the area condition
            ans += (j - i);
            ans %= MOD;
        }
        // we multiply by 2 since for example 2 * 3 and 3 * 2 are different configurations
        ans *= 2;
        ans = ans % MOD;
        // we are looking for rectangles where both sides are equal => squares
        for (int i = 0; i < n; i++) {
            if (((long) A[i]) * A[i] < B) {
                ans += 1;
                ans %= MOD;
            }
        }
        return (int) (ans % MOD);
    }

    public static void main(String[] args) {
        int[] a1 = {2, 3, 5};
        int b1 = 15;
        int[] a2 = {1, 2, 3, 4, 5};
        int b2 = 5;
        CountRectangles countRectangles = new CountRectangles();
        System.out.println(countRectangles.solve(a1, b1));
        System.out.println(countRectangles.solve(a2, b2));
    }
}

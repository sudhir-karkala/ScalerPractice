package com.scaler.twopointers;

import java.util.Arrays;

/**
 * Given two sorted arrays of distinct integers, A and B, and an integer C,
 * find and return the pair whose sum is closest to C and the pair has one element from each array.
 * More formally, find A[i] and B[j] such that abs((A[i] + B[j]) - C) has minimum value.
 * <p>
 * If there are multiple solutions find the one with minimum i and even if there are multiple values of j
 * for the same i then return the one with minimum j.
 * <p>
 * Return an array with two elements {A[i], B[j]}.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of both the arrays <= 100000
 * <p>
 * 1 <= A[i], B[i] <= 10^9
 * <p>
 * 1 <= C <= 10^9
 * <p>
 * Input Format
 * <p>
 * The first argument given is the integer array A.
 * <p>
 * The second argument given is the integer array B.
 * <p>
 * The third argument given is integer C.
 * <p>
 * Output Format
 * <p>
 * Return an array of size 2 denoting the pair which has sum closest to C.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [1, 2, 3, 4, 5]
 * <p>
 * B = [2, 4, 6, 8]
 * <p>
 * C = 9
 * <p>
 * Input 2:
 * <p>
 * A = [5, 10, 20]
 * <p>
 * B = [1, 2, 30]
 * <p>
 * C = 13
 * <p>
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * [1, 8]
 * <p>
 * Output 2:
 * <p>
 * [10, 2]
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * There are three pairs: (1, 8), (3, 6), (5, 4), that gives the minimum value.
 * Since we have to return the value with minimum i and then with minimum j. We will return [1, 8].
 * <p>
 * Explanation 2:
 * <p>
 * [10, 2] is the only pair such abs(10+2-13) is minimum.
 *
 * @author sudhir on 11-Mar-2020
 */
public class FindClosestPairFromTwoSortedArrays {
    public int[] solve(int[] A, int[] B, int C) {
        int m = A.length;
        int n = B.length;
        int l = 0;
        int r = n - 1;
        int min_l = 0;
        int min_r = 0;
        int diff = Integer.MAX_VALUE;
        while (l < m && r >= 0) {
            if (A[l] + B[r] == C) {
                return new int[]{A[l], B[r]};
            }
            if (Math.abs(A[l] + B[r] - C) < diff || (Math.abs(A[l] + B[r] - C) == diff && (min_l > A[l] || (min_l == A[l] && min_r > B[r])))) {
                diff = Math.abs(A[l] + B[r] - C);
                min_l = A[l];
                min_r = B[r];
            }
            if (A[l] + B[r] < C) {
                l++;
            } else {
                r--;
            }
        }
        return new int[]{min_l, min_r};
    }

    public static void main(String[] args) {
        FindClosestPairFromTwoSortedArrays fcpftsr = new FindClosestPairFromTwoSortedArrays();
        int[] a1 = {1, 2, 3, 4, 5};
        int[] b1 = {2, 4, 6, 8};
        int c1 = 9;
        int[] a2 = {5, 10, 20};
        int[] b2 = {1, 2, 30};
        int c2 = 13;
        int[] a3 = {1, 3, 5, 7, 9};
        int[] b3 = {2, 4, 6, 8, 10};
        int c3 = 10;
        System.out.println(Arrays.toString(fcpftsr.solve(a1, b1, c1)));
        System.out.println(Arrays.toString(fcpftsr.solve(a2, b2, c2)));
        System.out.println(Arrays.toString(fcpftsr.solve(a3, b3, c3)));
    }
}

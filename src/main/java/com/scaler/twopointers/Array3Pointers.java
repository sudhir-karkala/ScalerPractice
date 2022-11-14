package com.scaler.twopointers;

/**
 * You are given 3 sorted arrays A, B and C.
 * Find i, j, k such that : max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])) is minimized.
 * Return the minimum max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])).
 * <p>
 * Problem Constraints
 * <p>
 * 0 <= len(A), len(B), len(c) <= 10^6
 * <p>
 * 0 <= A[i], B[i], C[i] <= 10^7
 * <p>
 * Input Format
 * <p>
 * First argument is an integer array A.
 * <p>
 * Second argument is an integer array B.
 * <p>
 * Third argument is an integer array C.
 * <p>
 * Output Format
 * <p>
 * Return an single integer denoting the minimum max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])).
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [1, 4, 10]
 * <p>
 * B = [2, 15, 20]
 * <p>
 * C = [10, 12]
 * <p>
 * Input 2:
 * <p>
 * A = [3, 5, 6]
 * <p>
 * B = [2]
 * <p>
 * C = [3, 4]
 * <p>
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 5
 * <p>
 * Output 2:
 * <p>
 * 1
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * With 10 from A, 15 from B and 10 from C.
 * <p>
 * Explanation 2:
 * <p>
 * With 3 from A, 2 from B and 3 from C.
 *
 * @author sudhir on 12-Mar-2020
 */
public class Array3Pointers {
    public int minimize(final int[] A, final int[] B, final int[] C) {
        int minimum;
        int maximum;
        int diff = Integer.MAX_VALUE;
        for (int i = 0, j = 0, k = 0; i < A.length && j < B.length && k < C.length; ) {
            maximum = Math.max(A[i], Math.max(B[j], C[k]));
            minimum = Math.min(A[i], Math.min(B[j], C[k]));
            diff = Math.min(diff, maximum - minimum);
            if (diff == 0) {
                break;
            }
            if (minimum == A[i]) {
                i++;
            } else if (minimum == B[j]) {
                j++;
            } else {
                k++;
            }
        }
        return diff;
    }

    public static void main(String[] args) {
        Array3Pointers p = new Array3Pointers();
        int[] a = {1, 4, 10};
        int[] b = {2, 15, 20};
        int[] c = {10, 12};
        System.out.println(p.minimize(a, b, c));
    }
}

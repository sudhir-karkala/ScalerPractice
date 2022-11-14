package com.scaler.hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two arrays of integers A and B of size N each, where each pair (A[i], B[i]) for 0 <= i < N
 * represents a unique point (x, y) in 2D Cartesian plane.
 * Find and return the number of unordered triplets (i, j, k) such that (A[i], B[i]), (A[j], B[j]) and (A[k], B[k])
 * form a right angled triangle with the triangle having one side parallel to the x-axis and one side parallel to the y-axis.
 * <p>
 * Note: The answer may be large so return the answer modulo (10^9 + 7)
 * </p>
 * <p>
 * Input: A = [1, 1, 2, 3, 3], B = [1, 2, 1, 2, 1] Output: 6
 * </p>
 *
 * @author sudhir on 16-Mar-2020
 */
public class CountRightTriangles {
    private int MOD = (int) 1e9 + 7;

    public int solve(int[] A, int[] B) {
        //create a map of x-coordinate and the frequency
        //create a map of y-coordinate and the frequency
        Map<Integer, Integer> x_map = new HashMap<>();
        Map<Integer, Integer> y_map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            x_map.put(A[i], x_map.getOrDefault(A[i], 0) + 1);
        }
        for (int i = 0; i < B.length; i++) {
            y_map.put(B[i], y_map.getOrDefault(B[i], 0) + 1);
        }
        long ans = 0;
        for (int i = 0; i < A.length; i++) {
            ans += ((x_map.get(A[i]) - 1) * (y_map.get(B[i]) - 1)) % MOD;
        }
        return (int) ans;

    }

    public static void main(String[] args) {
        CountRightTriangles c = new CountRightTriangles();
        int[] a1 = {1, 1, 2, 3, 3};
        int[] b1 = {1, 2, 1, 2, 1};
        System.out.println(c.solve(a1, b1));
    }
}

package com.scaler.hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two array of integers A and B describing a pair of (A[i], B[i]) coordinates in 2-D plane.
 * A[i] describe x coordinates of the ith point in 2D plane whereas B[i] describes the y-coordinate of the ith point in 2D plane.
 * Find and return the maximum number of points which lie on the same line.
 * <p>
 * Input 1: A = [-1, 0, 1, 2, 3, 3], B = [1, 0, 1, 2, 3, 4]
 * </p>
 * <p>
 * Output 1: 4. The maximum number of point which lie on same line are 4, those points are {0, 0}, {1, 1}, {2, 2}, {3, 3}
 * </p>
 *
 * @author sudhir on 16-Mar-2020
 */
public class MaximumPointsOnTheSameLine {
    public int solve(int[] A, int[] B) {
        Map<Float, Integer> map = new HashMap<>();
        int maxPoints = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            map.clear();
            int same = 0;
            for (int j = 0; j < A.length; j++) {
                if (i == j) {
                    continue;
                }
                if (isSame(A, B, i, j)) {
                    same++;
                    continue;
                }
                float key = getSlope(A, B, i, j);
                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }
            }
            maxPoints = Math.max(maxPoints, same + map.values().stream().max(Integer::compare).get() + 1);
        }
        return maxPoints;
    }

    private boolean isSame(int[] A, int[] B, int i, int j) {
        return (A[i] == A[j]) && (B[i] == B[j]);
    }

    private float getSlope(int[] A, int[] B, int i, int j) {
        if (A[i] == A[j]) {
            return Float.MAX_VALUE;
        } else {
            return (((float) B[i] - B[j]) / ((float) A[i] - A[j]));
        }
    }

    public static void main(String[] args) {
        MaximumPointsOnTheSameLine m = new MaximumPointsOnTheSameLine();
        int[] a1 = {-1, 0, 1, 2, 3, 3};
        int[] b1 = {1, 0, 1, 2, 3, 4};
        System.out.println(m.solve(a1, b1));
        int[] a2 = {3, 1, 4, 5, 7, -9, -8, 6};
        int[] b2 = {4, -8, -3, -2, -1, 5, 7, -4};
        System.out.println(m.solve(a2, b2));
    }
}

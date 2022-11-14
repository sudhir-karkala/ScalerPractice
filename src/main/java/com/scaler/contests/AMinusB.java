package com.scaler.contests;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array A of integers of size N. <br/>
 * Find the size of the largest subset of the array in which every pair satisfy the condition: x^2 + y^2 + x*y = B mod C,
 * where x and y are two different elements of the subset and C is a prime number. <br/>
 * Note: All elements in the array are different. ('=' stands for congruent to in the equation)
 *
 * @author sudhir on 22-Apr-2020
 */
public class AMinusB {
    // we know that: x^3 - y^3 = (x - y)(x^2 + y^2 + x*y). This equation can be used to simplify the given equation with
    // two unknowns x, y into finding only x. This is done as follows:
    // multiply by (x - y) on both sides of the given equation resulting in the following equation.
    // x^3 - y^3 = (x - y) B mod C. Rearranging x and y terms,
    // (x^3 - x * B) = (y^3 - y * B) mod C, i.e. (x^3 - x * B) mod C needs to be calculated for every x. Let's call this z.
    // we can store all values in the map as <z, freq>. freq counts how many x's output same answer using the equation.
    // The one with the largest freq means that it is the largest subset.
    public int solve(int[] A, int B, int C) {
        long K = B;
        long M = C;
        long ans = 0L;
        Map<Long, Long> result = new HashMap<>();
        long max = 0L;
        for (int i = 0; i < A.length; i++) {
            ans = ((((A[i] * 1L * A[i] % M) * (A[i] % M)) % M) - ((A[i] * 1L * K) % M)) % M;
            if (ans < 0) {
                ans += M;
            }
            result.put(ans, result.getOrDefault(ans, 0L) + 1);
            max = Math.max(max, result.get(ans));
        }
        return (int) max;
    }

    public static void main(String[] args) {
        AMinusB aMinusB = new AMinusB();
        int[] A = {9, 17, 10, 16};
        int B = 1;
        int C = 19;
        System.out.println(aMinusB.solve(A, B, C));
    }
}

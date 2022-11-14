package com.scaler.sorting;

import java.util.Arrays;

/**
 * Given an array of integers A of size N where N is even. Divide the array into two subsets such that<br/>
 * 1.Length of both subset is equal.<br/>
 * 2.Each element of A occurs in exactly one of these subset.<br/>
 * Magic number = sum of absolute difference of corresponding elements of subset. <br/>
 * Note: You can reorder the position of elements within the subset to find the value of magic number.<br/>
 * Return an array B of size 2, where B[0] = maximum possible value of Magic number % 10^9 + 7,
 * B[1] = minimum possible value of a Magic number % 10^9 + 7.
 *
 * @author sudhir on 10-Apr-2020
 */
public class MaximumAndMinimum {
    public int[] solve(int[] A) {
        Arrays.sort(A);
        int mod = 1000000007;
        long max = 0;
        long min = 0;
        for (int i = 0; i < (A.length / 2); i++) {
            max += (Math.abs(A[i] - A[A.length - i - 1])) % mod;
        }
        for (int i = 1; i < A.length; i += 2) {
            min += (Math.abs(A[i] - A[i - 1])) % mod;
        }
        return new int[]{(int) (max % mod), (int) (min % mod)};

    }

    public static void main(String[] args) {
        MaximumAndMinimum m = new MaximumAndMinimum();
        int[] a = {-98, 54, -52, 15, 23, -97, 12, -64, 52, 85};
        System.out.println(Arrays.toString(m.solve(a)));
    }
}

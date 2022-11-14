package com.scaler.bitmanipulation;

import java.util.Arrays;

/**
 * You are given an array A (containing only 0 and 1) as element of N length.<br/>
 * Given L and R, you need to determine value of XOR of all elements from L to R (both inclusive)
 * and number of unset bits (0's) in the given range of the array.<br/>
 * Return an 2D array of Q rows and 2 columns i.e the xor value and number of unset bits in that range respectively for each query.
 *
 * @author sudhir on 04-Apr-2020
 */
public class XorQueries {
    public int[][] solve(int[] A, int[][] B) {
        int[] prefixCountOnes = new int[A.length];
        int[] prefixCountZeros = new int[A.length];
        prefixCountOnes[0] = A[0];
        prefixCountZeros[0] = A[0] ^ 1;
        for (int i = 1; i < A.length; i++) {
            prefixCountOnes[i] = prefixCountOnes[i - 1] + A[i];
            prefixCountZeros[i] = prefixCountZeros[i - 1] + (A[i] ^ 1);
        }
        int[][] result = new int[B.length][2];
        for (int i = 0; i < B.length; i++) {
            int prefix1 = B[i][0] - 1;
            int prefix2 = B[i][1] - 1;
            result[i][0] = prefixCountOnes[prefix2];
            if (prefix1 - 1 >= 0) {
                result[i][0] -= prefixCountOnes[prefix1 - 1];
            }
            result[i][0] = result[i][0] % 2;
            result[i][1] = prefixCountZeros[prefix2];
            if (prefix1 - 1 >= 0) {
                result[i][1] -= prefixCountZeros[prefix1 - 1];
            }
        }
        return result;

    }

    public static void main(String[] args) {
        XorQueries x = new XorQueries();
        int[] a = {1, 0, 0, 0, 1};
        int[][] b = {{2, 4}, {1, 5}, {3, 5}};
        int[][] result = x.solve(a, b);
        for (int i = 0; i < result.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }
    }
}

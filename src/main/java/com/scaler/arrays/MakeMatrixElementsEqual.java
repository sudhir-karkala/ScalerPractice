package com.scaler.arrays;

import java.util.Arrays;

/**
 * <b>Minimum operations of given type to make all elements of a matrix equal</b><br/>
 * Given a matrix of integers A of size N x M and an integer B. <br/>
 * In a single operation, B can be added to or subtracted from any element of the matrix. <br/>
 * Find and return the minimum number of operations required to make all the elements of the matrix equal
 * and if it impossible return -1 instead. <br/>
 * NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.
 * <p>
 * 1 <= N, M <= 1000
 * <p>
 * -1000 <= A[i] <= 1000
 * <p>
 * 1 <= B <= 1000
 *
 * @author sudhir on 04-Apr-2020
 */
public class MakeMatrixElementsEqual {
    public int solve(int[][] A, int B) {
        int minOperations = 0;
        int m = A.length;
        int n = A[0].length;
        // Notice that we always decrease or increase by B. that is if answer exist then all number must have same mod B.
        int mod = makePositive(A[0][0], B) % B;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // if any number doesn't have same mod B, then return -1
                if (makePositive(A[i][j], B) % B != mod) {
                    return -1;
                }
            }
        }

        // transform matrix into 1-d array and sort the array.
        int[] arr = new int[m * n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i * n + j] = A[i][j];
            }
        }
        Arrays.sort(arr);
        int size = arr.length;
        // if the array size is even, then we will have median1 and median2, else we will have only median1.
        int median1 = arr[size / 2];
        for (int i = 0; i < size; i++) {
            minOperations += Math.abs(arr[i] - median1) / B;
        }
        int result = minOperations;
        minOperations = 0;
        if (size % 2 == 0) {
            int median2 = arr[(size / 2) - 1];
            for (int i = 0; i < size; i++) {
                minOperations += Math.abs(arr[i] - median2) / B;
            }
            result = Math.min(result, minOperations);
        }
        return result;
    }

    // this is to make the array elements positive if they are not, so that we can apply mod B later.
    private int makePositive(int a, int b) {
        while (a < 0) {
            a += b;
        }
        return a;
    }

    public static void main(String[] args) {
        MakeMatrixElementsEqual m = new MakeMatrixElementsEqual();
        int[][] A = {{0, 2, 8}, {8, 2, 0}, {0, 2, 8}};
        int B = 2;
        System.out.println(m.solve(A, B));
    }
}

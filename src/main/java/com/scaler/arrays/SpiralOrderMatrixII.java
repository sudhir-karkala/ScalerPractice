package com.scaler.arrays;

import java.util.Arrays;

/**
 * Given an integer A, generate a square matrix filled with elements from 1 to A^2 in spiral order.
 *
 * @author sudhir on 04-Apr-2020
 */
public class SpiralOrderMatrixII {
    public int[][] generateMatrix(int A) {
        int[][] matrix = new int[A][A];
        int topleftrow = 0;
        int topleftcol = 0;
        int toprightrow = 0;
        int toprightcol = A - 1;
        int bottomleftrow = A - 1;
        int bottomleftcol = 0;
        int bottomrightrow = A - 1;
        int bottomrightcol = A - 1;
        int val = 1;
        int target = A * A;
        while (val <= target) {
            for (int i = topleftcol; i <= toprightcol; i++) {
                matrix[topleftrow][i] = val++;
            }
            topleftrow++;
            toprightrow++;
            for (int i = toprightrow; i <= bottomrightrow; i++) {
                matrix[i][toprightcol] = val++;
            }
            bottomrightcol--;
            toprightcol--;
            for (int i = bottomrightcol; i >= bottomleftcol; i--) {
                matrix[bottomrightrow][i] = val++;
            }
            bottomleftrow--;
            bottomrightrow--;
            for (int i = bottomleftrow; i >= topleftrow; i--) {
                matrix[i][bottomleftcol] = val++;
            }
            topleftcol++;
            bottomleftcol++;
        }
        return matrix;
    }

    public static void main(String[] args) {
        SpiralOrderMatrixII s = new SpiralOrderMatrixII();
        int A = 3;
        int[][] matrix = s.generateMatrix(A);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println();
        A = 4;
        matrix = s.generateMatrix(A);
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}

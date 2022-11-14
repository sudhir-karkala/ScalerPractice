package com.scaler.searching;

/**
 * Given a matrix of integers A of size N x M and an integer B. Write an efficient algorithm that searches for integar B in matrix A.
 * This matrix A has the following properties:
 * <li>
 * 1. Integers in each row are sorted from left to right.
 * </li>
 * <li>
 * 2. The first integer of each row is greater than or equal to the last integer of the previous row.
 * </li>
 * Return 1 if B is present in A, else return 0.
 * <p>
 * NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.
 * </p>
 *
 * @author sudhir on 01-Mar-2020
 */
public class MatrixSearch {
    public static int searchMatrix(int[][] A, int B) {
        int m = A.length;
        int n = A[0].length;
        int l = 0;
        int r = m * n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (A[mid / n][mid % n] == B) {
                return 1;
            } else if (A[mid / n][mid % n] > B) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] a1 = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int b1 = 3;
        int[][] a2 = {{5, 17, 100, 111}, {119, 120, 127, 131}};
        int b2 = 3;
        System.out.println(searchMatrix(a1, b1));
        System.out.println(searchMatrix(a2, b2));
    }
}

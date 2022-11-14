package com.scaler.arrays;

/**
 * Given a matrix of integers A of size N x M and an integer B. <br/>
 * In the given matrix every row and column is sorted in increasing order. <br/>
 * Find and return the position of B in the matrix in the given form:<br/>
 * If A[i][j] = B then return (i * 1009 + j)<br/>
 * If B is not present return -1.<br/>
 * Note 1: Rows are numbered from top to bottom and columns are numbered from left to right.<br/>
 * Note 2: If there are multiple B in A then return the smallest value of i*1009 +j such that A[i][j]=B.
 *
 * @author sudhir on 04-Apr-2020
 */
public class SearchInRowWiseAndColumnWiseSortedMatrix {
    public int solve(int[][] A, int B) {
        int m = A.length;
        int n = A[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (A[i][j] == B) {
                return ((i + 1) * 1009 + (j + 1));
            } else if (A[i][j] > B) {
                j--;
            } else {
                i++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchInRowWiseAndColumnWiseSortedMatrix s = new SearchInRowWiseAndColumnWiseSortedMatrix();
        int[][] A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int B = 2;
        System.out.println(s.solve(A, B));
    }
}

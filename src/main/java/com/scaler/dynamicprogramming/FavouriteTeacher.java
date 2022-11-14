package com.scaler.dynamicprogramming;

import java.util.Arrays;

/**
 * There are N teachers and N students both numbered from 1 to N.
 * We know that each student has a preference for teachers.
 * We have a matrix A. A[i][j] is 1 if ith student likes teacher j. It is zero otherwise.
 * <p>
 * We want to find the number of ways to assign a teacher to each student
 * so that each student only gets one of his preferred teachers and
 * each teacher is allowed to only one student.
 * Since the answer can be large return it modulo 10^9 + 7.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 20
 * <p>
 * 0 <= A[i][j] <= 1
 * <p>
 * Input Format
 * <p>
 * First line contains a 2-D matrix A of size NxN.
 * <p>
 * Output Format
 * <p>
 * Return the number of ways to allot teachers to students.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [
 * [0, 1, 1]
 * [1, 0, 1]
 * [1, 1, 1]
 * ]
 * <p>
 * Input 2:
 * <p>
 * A = [
 * [0]
 * ]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 3
 * <p>
 * Output 2:
 * <p>
 * 0
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * There are 3 ways to form pairs (i,j) denotes pair of i-th student and j-th teacher.
 * <p>
 * (1,2), (2,1), (3,3)
 * (1,2), (2,3), (3,1)
 * (1,3), (2,1), (3,2)
 * <p>
 * Explanation 2:
 * <p>
 * We can't form a pair.
 *
 * @author sudhir on 02-Nov-2020
 */
public class FavouriteTeacher {
    private int[] dp;
    private int n;
    private int mod = (int) 1e9 + 7;

    public int solve(int[][] A) {
        n = A.length;
        int mask = (int) Math.pow(2, n);
        dp = new int[mask];
        Arrays.fill(dp, -1);
        return noOfWays(A, 0, 0);
    }

    private int noOfWays(int[][] A, int row, int mask) {
        if (row == n) {
            return 1;
        }
        if (dp[mask] != -1) {
            return dp[mask];
        }
        int ans = 0;
        for (int col = 0; col < n; col++) {
            if (((mask >> col) & 1) == 1 || A[row][col] == 0) {
                continue;
            }
            ans = (ans % mod + noOfWays(A, row + 1, mask | (1 << col)) % mod) % mod;
        }
        dp[mask] = ans;
        return dp[mask];
    }

    public static void main(String[] args) {
        FavouriteTeacher favouriteTeacher = new FavouriteTeacher();
        int[][] a1 = {{0, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        System.out.println(favouriteTeacher.solve(a1));
    }
}

package com.scaler.arrays;

/**
 * Given a 2D Matrix A of dimensions N*N, we need to return sum of all possible submatrices.
 * @author sudhir on 04-Apr-2020
 */
public class SumOfAllSubmatrices {
    public int solve(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans += (i + 1) * (j + 1) * (m - i) * (n - j) * A[i][j];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SumOfAllSubmatrices s = new SumOfAllSubmatrices();
        int[][] A = {{1, 1}, {1, 1}};
        System.out.println(s.solve(A));
    }
}

package com.scaler.graphs;

import java.util.Arrays;

/**
 * Given a matrix of integers A of size N x N, where A[i][j] represents
 * the weight of directed edge from i to j (i ---> j).
 * <p>
 * If i == j, A[i][j] = 0, and if there is no directed edge from vertex i to vertex j, A[i][j] = -1.
 * <p>
 * Return a matrix B of size N x N where B[i][j] = shortest path from vertex i to vertex j.
 * <p>
 * If there is no possible path from vertex i to vertex j , B[i][j] = -1
 * <p>
 * Note: Rows are numbered from top to bottom and columns are numbered from left to right.
 * <p>
 * Problem Constraints
 * 1 <= N <= 200
 * -1 <= A[i][j] <= 1000000
 * <p>
 * Input Format
 * <p>
 * The first and only argument given is the integer matrix A.
 * <p>
 * Output Format
 * <p>
 * Return a matrix B of size N x N where B[i][j] = shortest path from vertex i to vertex j
 * If there is no possible path from vertex i to vertex j, B[i][j] = -1.
 *
 * @author sudhir on 27-Aug-2020
 */
public class FloydWarshallAlgorithm {
    public int[][] solve(int[][] A) {
        int n = A.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // means there is no path from i to j initially.
                    if (A[i][j] == -1) {
                        // if there is a path via k, then store the path as the path from i to j via k.
                        if (A[i][k] != -1 && A[k][j] != -1) {
                            A[i][j] = A[i][k] + A[k][j];
                        }
                    } else {
                        // means there is already a path from i to j.
                        // In that case, we need to find min path distance.
                        if (A[i][k] != -1 && A[k][j] != -1) {
                            A[i][j] = Math.min(A[i][k] + A[k][j], A[i][j]);
                        }
                    }
                }
            }
        }
        return A;
    }

    public static void main(String[] args) {
        FloydWarshallAlgorithm floydWarshallAlgorithm = new FloydWarshallAlgorithm();
        int[][] a1 = {{0, 50, 39}, {-1, 0, 1}, {-1, 10, 0}};
        int[][] a2 = {{0, 49, 39}, {-1, 0, -1}, {-1, 10, 0}};
        System.out.println(Arrays.toString(floydWarshallAlgorithm.solve(a1)));
        System.out.println(Arrays.toString(floydWarshallAlgorithm.solve(a2)));
    }
}

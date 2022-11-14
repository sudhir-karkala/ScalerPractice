package com.scaler.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * <p>
 * Adjacent numbers for jth number of row i is jth and (j+1)th numbers of row i+1
 * <p>
 * Example:
 * A = [
 * [2],
 * [3, 4],
 * [6, 5, 7],
 * [4, 1, 8, 3]
 * ]
 * <p>
 * Output: 11
 *
 * @author sudhir on 11-Jul-2020
 */
public class MinSumPathInTriangle {
    public int minimumTotal(ArrayList<ArrayList<Integer>> a) {
        int rows = a.size();
        int cols = a.get(rows - 1).size();
        int[][] dp = new int[rows][cols];
        dp[0][0] = a.get(0).get(0);

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = a.get(i).get(j) + dp[i - 1][j];
                } else if (i == j) {
                    dp[i][j] = a.get(i).get(j) + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(a.get(i).get(j) + dp[i - 1][j - 1], a.get(i).get(j) + dp[i - 1][j]);
                }
            }
        }
        int minSum = dp[rows - 1][0];
        for (int j = 1; j < cols; j++) {
            if (minSum > dp[rows - 1][j]) {
                minSum = dp[rows - 1][j];
            }
        }
        return minSum;
    }

    public static void main(String[] args) {
        MinSumPathInTriangle m = new MinSumPathInTriangle();
        ArrayList<ArrayList<Integer>> triangle = new ArrayList<>();
        triangle.add(new ArrayList<>(Arrays.asList(2)));
        triangle.add(new ArrayList<>(Arrays.asList(3, 4)));
        triangle.add(new ArrayList<>(Arrays.asList(6, 5, 7)));
        triangle.add(new ArrayList<>(Arrays.asList(4, 1, 8, 3)));
        System.out.println(m.minimumTotal(triangle));
    }
}

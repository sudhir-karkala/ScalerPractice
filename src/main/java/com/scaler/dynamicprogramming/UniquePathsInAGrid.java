package com.scaler.dynamicprogramming;

/**
 * Given a grid of size n * m, lets assume you are starting at (1,1) and your goal is to reach (n, m). <br/>
 * At any instance, if you are on (x, y), you can either go to (x, y + 1) or (x + 1, y). <br/>
 * Now consider if some obstacles are added to the grids. How many unique paths would there be? <br/>
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.<br/>
 * 1 <= n, m <= 100, A[i][j] = 0 or 1
 *
 * @author sudhir on 30-Apr-2020
 */
public class UniquePathsInAGrid {
    public int uniquePathsWithObstacles(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[][] ways = new int[n + 1][m + 1];
        ways[1][1] = (A[0][0] == 0) ? 1 : 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // if the cell has the value of 1, it means we can't reach that cell, so ways = 0
                if (A[i - 1][j - 1] == 1) {
                    ways[i][j] = 0;
                } else {
                    // else number of ways will be summation of number of ways coming from the top and coming from the left
                    ways[i][j] += ways[i - 1][j] + ways[i][j - 1];
                }
            }
        }
        return ways[n][m];
    }

    public static void main(String[] args) {
        UniquePathsInAGrid u = new UniquePathsInAGrid();
        int[][] a1 = {{0}};
        int[][] a2 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] a3 = {{0, 0, 0}, {1, 1, 1}, {0, 0, 0}};
        System.out.println(u.uniquePathsWithObstacles(a1));
        System.out.println(u.uniquePathsWithObstacles(a2));
        System.out.println(u.uniquePathsWithObstacles(a3));
    }
}

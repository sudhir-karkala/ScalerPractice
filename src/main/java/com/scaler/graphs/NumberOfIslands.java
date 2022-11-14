package com.scaler.graphs;

/**
 * Given a matrix of integers A of size N x M consisting of 0 and 1. <br/>
 * A group of connected 1's forms an island. From a cell (i, j) such that A[i][j] = 1<br/>
 * you can visit any cell that shares a corner with (i, j) and value in that cell is 1.<br/>
 * More formally, from any cell (i, j) if A[i][j] = 1 you can visit:<br/>
 * (i-1, j) if (i-1, j) is inside the matrix and A[i-1][j] = 1.<br/>
 * (i, j-1) if (i, j-1) is inside the matrix and A[i][j-1] = 1.<br/>
 * (i+1, j) if (i+1, j) is inside the matrix and A[i+1][j] = 1.<br/>
 * (i, j+1) if (i, j+1) is inside the matrix and A[i][j+1] = 1.<br/>
 * (i-1, j-1) if (i-1, j-1) is inside the matrix and A[i-1][j-1] = 1.<br/>
 * (i+1, j+1) if (i+1, j+1) is inside the matrix and A[i+1][j+1] = 1.<br/>
 * (i-1, j+1) if (i-1, j+1) is inside the matrix and A[i-1][j+1] = 1.<br/>
 * (i+1, j-1) if (i+1, j-1) is inside the matrix and A[i+1][j-1] = 1.<br/>
 * Return the number of islands. NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.<br/>
 * 1 <= N, M <= 100, 0 <= A[i] <= 1
 *
 * @author sudhir on 17-May-2020
 */
public class NumberOfIslands {
    private int ROW = 0;
    private int COL = 0;
    private int directions = 8;

    public int solve(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        ROW = grid.length;
        COL = grid[0].length;
        // maintain visited array for every cell
        boolean[][] visited = new boolean[ROW][COL];
        int islands = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    DFS(grid, visited, i, j);
                    // once a DFS traversal is done, we update island count and start with next island
                    islands++;
                }
            }
        }
        return islands;
    }

    private boolean isPossible(int[][] grid, int row, int col, boolean[][] visited) {
        return (row >= 0 && row < ROW) && (col >= 0 && col < COL) && (grid[row][col] == 1 && !visited[row][col]);
    }

    private void DFS(int[][] grid, boolean[][] visited, int row, int col) {
        // initialize row numbers for 8 directions
        // i.e. top of current, left of current, right of current, bottom of current,
        // top-left of current, top-right of current, bottom-left of current, bottom-right of current
        int[] rowNumbers = {-1, 0, 0, 1, -1, -1, 1, 1};
        // initialize col numbers for 4 directions
        int[] colNumbers = {0, -1, 1, 0, -1, 1, -1, 1};
        visited[row][col] = true;
        for (int k = 0; k < directions; k++) {
            if (isPossible(grid, row + rowNumbers[k], col + colNumbers[k], visited)) {
                DFS(grid, visited, row + rowNumbers[k], col + colNumbers[k]);
            }
        }
    }
}

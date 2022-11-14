package com.scaler.recursionandbacktracking;

import java.util.ArrayList;

/**
 * Given a matrix of integers A of size N x M . There are 4 types of squares in it:<br/>
 * 1. 1 represents the starting square.  There is exactly one starting square.<br/>
 * 2. 2 represents the ending square.  There is exactly one ending square.<br/>
 * 3. 0 represents empty squares we can walk over.<br/>
 * 4. -1 represents obstacles that we cannot walk over.<br/>
 * Find and return the number of 4-directional walks from the starting square to the ending square,
 * that walk over every non-obstacle square exactly once.<br/>
 * Note: Rows are numbered from top to bottom and columns are numbered from left to right.<br/>
 * 2 <= N * M <= 20 and -1 <= A[i] <= 2
 *
 * @author sudhir on 30-Mar-2020
 */
public class UniquePathsIII {
    private int paths = 0;
    private int m = 0;
    private int n = 0;

    public int solve(ArrayList<ArrayList<Integer>> A) {
        int startX = 0, startY = 0, endX = 0, endY = 0, empty = 0;
        paths = 0;
        m = A.size();
        n = A.get(0).size();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A.get(i).get(j) == 1) {
                    startX = i;
                    startY = j;
                } else if (A.get(i).get(j) == 2) {
                    endX = i;
                    endY = j;
                } else if (A.get(i).get(j) == 0) {
                    empty++;
                }
            }
        }
        solve(A, startX, startY, endX, endY, empty);
        return paths;
    }

    private void solve(ArrayList<ArrayList<Integer>> A, int startX, int startY, int endX, int endY, int empty) {
        if (startX == endX && startY == endY) {
            if (empty == -1) {
                paths++;
            }
            return;
        }
        A.get(startX).set(startY, -1);

        if (startX + 1 < m && A.get(startX + 1).get(startY) != -1) {
            solve(A, startX + 1, startY, endX, endY, empty - 1);
        }
        if (startY + 1 < n && A.get(startX).get(startY + 1) != -1) {
            solve(A, startX, startY + 1, endX, endY, empty - 1);
        }
        if (startX - 1 >= 0 && A.get(startX - 1).get(startY) != -1) {
            solve(A, startX - 1, startY, endX, endY, empty - 1);
        }
        if (startY - 1 >= 0 && A.get(startX).get(startY - 1) != -1) {
            solve(A, startX, startY - 1, endX, endY, empty - 1);
        }
        A.get(startX).set(startY, 0);
    }

    public static void main(String[] args) {
        UniquePathsIII uniquePathsIII = new UniquePathsIII();
        ArrayList<ArrayList<Integer>> grid1 = new ArrayList<>();
        grid1.add(new ArrayList<>());
        grid1.add(new ArrayList<>());
        grid1.add(new ArrayList<>());
        grid1.get(0).add(1);
        grid1.get(0).add(0);
        grid1.get(0).add(0);
        grid1.get(0).add(0);
        grid1.get(1).add(0);
        grid1.get(1).add(0);
        grid1.get(1).add(0);
        grid1.get(1).add(0);
        grid1.get(2).add(0);
        grid1.get(2).add(0);
        grid1.get(2).add(2);
        grid1.get(2).add(-1);

        ArrayList<ArrayList<Integer>> grid2 = new ArrayList<>();
        grid2.add(new ArrayList<>());
        grid2.add(new ArrayList<>());
        grid2.get(0).add(0);
        grid2.get(0).add(1);
        grid2.get(1).add(2);
        grid2.get(1).add(0);

        System.out.println(uniquePathsIII.solve(grid1));
        System.out.println(uniquePathsIII.solve(grid2));

    }
}

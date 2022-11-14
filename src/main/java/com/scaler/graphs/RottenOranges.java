package com.scaler.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a matrix of integers A of size N x M consisting of 0, 1 or 2.
 * <p>
 * Each cell can have three values:
 * <p>
 * The value 0 representing an empty cell.
 * <p>
 * The value 1 representing a fresh orange.
 * <p>
 * The value 2 representing a rotten orange.
 * <p>
 * Every minute, any fresh orange that is adjacent (Left, Right, Top, or Bottom) to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1 instead.
 * <p>
 * Note: Your solution will run on multiple test cases. If you are using global variables, make sure to clear them.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N, M <= 1000
 * <p>
 * 0 <= A[i][j] <= 2
 * <p>
 * Input Format
 * <p>
 * The first argument given is the integer matrix A.
 * <p>
 * Output Format
 * <p>
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange.
 * <p>
 * If this is impossible, return -1 instead.
 *
 * @author sudhir on 17-Aug-2020
 */
public class RottenOranges {
    public int solve(ArrayList<ArrayList<Integer>> A) {
        int m = A.size();
        int n = A.get(0).size();
        Queue<Pair> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        // add all those elements to the queue which are marked as 2.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A.get(i).get(j) == 2) {
                    queue.offer(new Pair(i, j));
                    visited[i][j] = true;
                }
            }
        }
        int minTimeNeeded = -1;
        while (!queue.isEmpty()) {
            minTimeNeeded++;
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Pair p = queue.poll();
                // we need to check in 4 directions: left, top, right and bottom
                int[] rowNumbers = {0, -1, 0, 1};
                int[] colNumbers = {-1, 0, 1, 0};
                for (int dir = 0; dir < 4; dir++) {
                    int new_row = p.row + rowNumbers[dir];
                    int new_col = p.col + colNumbers[dir];
                    // if (new_row, new_col) is within the boundary of the matrix and the cell value is 1 and unvisited,
                    // mark it as visited, mark the cell value as 2 and add it to the queue.
                    if (new_row >= 0 && new_col >= 0 && new_row < m && new_col < n && A.get(new_row).get(new_col) == 1 && !visited[new_row][new_col]) {
                        visited[new_row][new_col] = true;
                        A.get(new_row).set(new_col, 2);
                        queue.offer(new Pair(new_row, new_col));
                    }
                }
            }
        }
        // if there are any cell values with the value 1, then it means it is impossible, so return -1.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A.get(i).get(j) == 1) {
                    return -1;
                }
            }
        }
        return minTimeNeeded;
    }

    static class Pair {
        int row;
        int col;

        public Pair(int m, int n) {
            row = m;
            col = n;
        }
    }

    public static void main(String[] args) {
        RottenOranges r = new RottenOranges();
        ArrayList<ArrayList<Integer>> a1 = new ArrayList<>();
        a1.add(new ArrayList<>(Arrays.asList(2, 1, 1)));
        a1.add(new ArrayList<>(Arrays.asList(1, 1, 0)));
        a1.add(new ArrayList<>(Arrays.asList(0, 1, 1)));
        System.out.println(r.solve(a1));

        ArrayList<ArrayList<Integer>> a2 = new ArrayList<>();
        a2.add(new ArrayList<>(Arrays.asList(2, 1, 1)));
        a2.add(new ArrayList<>(Arrays.asList(0, 1, 1)));
        a2.add(new ArrayList<>(Arrays.asList(1, 0, 1)));
        System.out.println(r.solve(a2));
    }
}

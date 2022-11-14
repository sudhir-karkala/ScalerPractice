package com.scaler.recursionandbacktracking;

import java.util.ArrayList;

/**
 * Given an integer n, return all distinct solutions to the n-queens puzzle.<br/>
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.'
 * both indicate a queen and an empty space respectively.
 *
 * @author sudhir on 29-Mar-2020
 */
public class NQueens {
    public ArrayList<ArrayList<String>> solveNQueens(int a) {
        int[] column = new int[a];
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        int i = 0;
        while (i < a) {
            column[0] = i;
            nqueens(1, column, a, result);
            i++;
        }
        return result;
    }

    private void store(int[] column, ArrayList<ArrayList<String>> result, int N) {
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < N; j++) {
                if (column[i] == j) {
                    builder.append("Q");
                } else {
                    builder.append(".");
                }
            }
            temp.add(builder.toString());
        }
        result.add(temp);
    }

    private void nqueens(int row, int[] column, int N, ArrayList<ArrayList<String>> result) {
        if (row == N) {
            store(column, result, N);
        }
        for (int i = 0; i < N; i++) {
            if (isValid(row, i, column)) {
                column[row] = i;
                nqueens(row + 1, column, N, result);
            }
        }
    }

    private boolean isValid(int r, int c, int[] column) {
        for (int i = 0; i < r; i++) {
            if (c == column[i]) {
                return false;
            }
            if ((r - c) == (i - column[i]) || (r + c) == (i + column[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        int a = 4;
        int b = 5;
        System.out.println(nQueens.solveNQueens(a));
        System.out.println(nQueens.solveNQueens(b));
    }
}

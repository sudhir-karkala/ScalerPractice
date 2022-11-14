package com.scaler.recursionandbacktracking;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * Empty cells are indicated by the character '.' You may assume that there will be only one unique solution.
 * <p>
 * Problem Constraints
 * <p>
 * N = 9
 * Input Format
 * First argument is an array of array of characters representing the Sudoku puzzle.
 * Output Format
 * Modify the given input to the required answer.
 * Example Input
 * Input 1:
 * A = [[53..7....], [6..195...], [.98....6.], [8...6...3], [4..8.3..1], [7...2...6], [.6....28.], [...419..5], [....8..79]]
 * Example Output
 * Output 1:
 * [[534678912], [672195348], [198342567], [859761423], [426853791], [713924856], [961537284], [287419635], [345286179]]
 *
 * @author sudhir on 13-Aug-2020
 */
public class Sudoku {
    private boolean isDone;
    private boolean[][] rowPosition;
    private boolean[][] colPosition;
    private boolean[][][] boxPosition;

    public void solveSudoku(ArrayList<ArrayList<Character>> a) {
        // isDone = true indicates filling sudoku grid is possible or not
        isDone = false;
        // rowPosition[i][num] indicates num is present in ith row if true, false otherwise
        rowPosition = new boolean[9][10];
        // colPosition[j][num] indicates num is present in jth column if true, false otherwise
        colPosition = new boolean[9][10];
        // boxPosition[i][j][num] indicates num is present in ith row and jth column box if true, false otherwise
        // there are a total of 9 3*3 boxes. each box has 3 rows and 3 columns.
        boxPosition = new boolean[3][3][10];
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (a.get(i).get(j) != '.') {
                    rowPosition[i][a.get(i).get(j) - '0'] = true;
                    colPosition[j][a.get(i).get(j) - '0'] = true;
                    boxPosition[i / 3][j / 3][a.get(i).get(j) - '0'] = true;
                }
            }
        }
        // start filling from index 0 which refers to topleft position
        fillSudokuGrid(0, a);
    }

    private void fillSudokuGrid(int index, ArrayList<ArrayList<Character>> a) {
        if (index == 81) {
            isDone = true;
            return;
        }
        int row = index / 9;
        int col = index % 9;
        // if the current position is not filled, then check for numbers from 1 to 9 and fill accordingly
        if (a.get(row).get(col) == '.') {
            for (int num = 1; num <= 9; num++) {
                if (!(rowPosition[row][num] || colPosition[col][num] || boxPosition[row / 3][col / 3][num])) {
                    a.get(row).set(col, (char) (num + '0'));
                    rowPosition[row][num] = true;
                    colPosition[col][num] = true;
                    boxPosition[row / 3][col / 3][num] = true;
                    fillSudokuGrid(index + 1, a);
                    if (!isDone) {
                        // if the current num filled at this position doesn't lead to a valid filling, then undo the operations
                        // and continue with the next num.
                        rowPosition[row][num] = false;
                        colPosition[col][num] = false;
                        boxPosition[row / 3][col / 3][num] = false;
                        a.get(row).set(col, '.');
                    }
                }
            }
        } else {
            // if the position is already filled, then consider next index and call the function
            fillSudokuGrid(index + 1, a);
        }
    }

    public static void main(String[] args) {
        Sudoku s = new Sudoku();
        ArrayList<ArrayList<Character>> input = new ArrayList<>();
        ArrayList<Character> one = new ArrayList<>(Arrays.asList('5', '3', '.', '.', '7', '.', '.', '.', '.'));
        ArrayList<Character> two = new ArrayList<>(Arrays.asList('6', '.', '.', '1', '9', '5', '.', '.', '.'));
        ArrayList<Character> three = new ArrayList<>(Arrays.asList('.', '9', '8', '.', '.', '.', '.', '6', '.'));
        ArrayList<Character> four = new ArrayList<>(Arrays.asList('8', '.', '.', '.', '6', '.', '.', '.', '3'));
        ArrayList<Character> five = new ArrayList<>(Arrays.asList('4', '.', '.', '8', '.', '3', '.', '.', '1'));
        ArrayList<Character> six = new ArrayList<>(Arrays.asList('7', '.', '.', '.', '2', '.', '.', '.', '6'));
        ArrayList<Character> seven = new ArrayList<>(Arrays.asList('.', '6', '.', '.', '.', '.', '2', '8', '.'));
        ArrayList<Character> eight = new ArrayList<>(Arrays.asList('.', '.', '.', '4', '1', '9', '.', '.', '5'));
        ArrayList<Character> nine = new ArrayList<>(Arrays.asList('.', '.', '.', '.', '8', '.', '.', '7', '9'));
        input.add(one);
        input.add(two);
        input.add(three);
        input.add(four);
        input.add(five);
        input.add(six);
        input.add(seven);
        input.add(eight);
        input.add(nine);
        s.solveSudoku(input);
        System.out.println(input);
    }
}

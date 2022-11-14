package com.scaler.graphs;

/**
 * Given character matrix A of O's and X's, where O = white, X = black.
 * <p>
 * Return the number of black shapes. A black shape consists of one or more adjacent X's (diagonals not included)
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= |A|,|A[0]| <= 1000
 * <p>
 * A[i][j] = 'X' or 'O'
 * <p>
 * Input Format
 * <p>
 * The First and only argument is character matrix A.
 * <p>
 * Output Format
 * <p>
 * Return a single integer denoting number of black shapes.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [ [X, X, X], [X, X, X], [X, X, X] ]
 * <p>
 * Input 2:
 * <p>
 * A = [ [X, O], [O, X] ]
 * <p>
 * Example Output
 * Output 1: 1
 * Output 2: 2
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * All X's belong to single shapes
 * <p>
 * Explanation 2:
 * <p>
 * Both X's belong to different shapes
 *
 * @author sudhir on 19-Aug-2020
 */
public class BlackShapes {
    private int directions = 4;
    private int ROW;
    private int COL;

    public int black(String[] A) {
        ROW = A.length;
        COL = A[0].length();
        boolean[][] visited = new boolean[ROW][COL];
        int shapes = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (A[i].charAt(j) == 'X' && !visited[i][j]) {
                    dfs(A, visited, i, j);
                    shapes++;
                }
            }
        }
        return shapes;
    }

    private void dfs(String[] strings, boolean[][] visited, int row, int col) {
        int[] rowNumbers = {0, -1, 0, 1};
        int[] colNumbers = {-1, 0, 1, 0};
        visited[row][col] = true;
        for (int dir = 0; dir < directions; dir++) {
            if (isPossible(strings, row + rowNumbers[dir], col + colNumbers[dir], visited)) {
                dfs(strings, visited, row + rowNumbers[dir], col + colNumbers[dir]);
            }
        }
    }

    private boolean isPossible(String[] strings, int row, int col, boolean[][] visited) {
        return (row >= 0 && row < ROW && col >= 0 && col < COL && !visited[row][col] && strings[row].charAt(col) == 'X');
    }

    public static void main(String[] args) {
        BlackShapes blackShapes = new BlackShapes();
        String[] strings1 = {"XXX", "XXX", "XXX"};
        String[] strings2 = {"X0", "0X"};
        System.out.println(blackShapes.black(strings1));
        System.out.println(blackShapes.black(strings2));
    }
}

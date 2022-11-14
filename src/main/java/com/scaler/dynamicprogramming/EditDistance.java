package com.scaler.dynamicprogramming;

/**
 * Given two strings A and B, find the minimum number of steps required to convert A to B. (each operation is counted as 1 step.)
 * <p>
 * You have the following 3 operations permitted on a word:
 * <p>
 * Insert a character<br/>
 * Delete a character<br/>
 * Replace a character<br/>
 * <p>
 * Return an integer, representing the minimum number of steps required.
 * <p>
 * 1 <= length(A), length(B) <= 450
 *
 * @author sudhir on 03-Jul-2020
 */
public class EditDistance {
    public int minDistance(String A, String B) {
        int m = A.length();
        int n = B.length();
        int[][] steps = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    steps[i][j] = j;
                } else if (j == 0) {
                    steps[i][j] = i;
                } else if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    steps[i][j] = steps[i - 1][j - 1];
                } else {
                    steps[i][j] = 1 + Math.min(steps[i - 1][j], Math.min(steps[i][j - 1], steps[i - 1][j - 1]));
                }
            }
        }
        return steps[m][n];
    }

    public static void main(String[] args) {
        EditDistance e = new EditDistance();
        String a1 = "Anshuman";
        String b1 = "Antihuman";
        System.out.println(e.minDistance(a1, b1));
    }
}

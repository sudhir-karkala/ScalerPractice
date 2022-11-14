package com.scaler.dynamicprogramming;

/**
 * The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon.
 * The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight was initially positioned
 * in the top-left room and must fight his way through the dungeon to rescue the princess.
 * <p>
 * The knight has an initial health point represented by a positive integer.
 * If at any point his health point drops to 0 or below, he dies immediately.
 * <p>
 * Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms;
 * other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
 * <p>
 * In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 * <p>
 * Given a 2D array of integers A of size M x N. Find and return the knight's minimum initial health
 * so that he is able to rescue the princess.
 *
 * @author sudhir on 11-Jul-2020
 */
public class DungeonPrincess {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        // dp[i][j] represents minimum power needed to enter cell (i,j) so that health point does not drop to 0.
        int[][] dp = new int[m][n];
        dp[m - 1][n - 1] = 1 - dungeon[m - 1][n - 1];
        // say if the cell had a value -4, then health of 5 is enough to enter that call.
        // In case, the value in the cell is 4, then health needed to enter that cell is 1.
        if (dp[m - 1][n - 1] <= 0) {
            dp[m - 1][n - 1] = 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    continue;
                }
                int HP_down = (i + 1 == m) ? Integer.MAX_VALUE : dp[i + 1][j] - dungeon[i][j];
                int HP_right = (j + 1 == n) ? Integer.MAX_VALUE : dp[i][j + 1] - dungeon[i][j];
                int HP = Math.min(HP_down, HP_right);
                dp[i][j] = HP <= 0 ? 1 : HP;
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        DungeonPrincess d = new DungeonPrincess();
        int[][] a1 = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        System.out.println(d.calculateMinimumHP(a1));
    }
}

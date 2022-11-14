package com.scaler.maths;

/**
 * Dipen has a chocolate of N by M pieces. He and Damini started playing with this chocolate. <br/>
 * First Dipen takes the chocolate and divides it into two parts by making either a horizontal or a vertical cut. <br/>
 * Then, Damini takes one of the available pieces and divides it into two parts by making either a horizontal or a vertical cut. <br/>
 * Then Dipen do the same thing again and so on. The player who cannot make a turn loses. <br/>
 * When all pieces are of size 1 * 1 player can not make a turn. Find who will win if both of them play optimally.<br/>
 * 1 <= N <= 10^9, 1 <= M <= 10^9
 *
 * @author sudhir on 24-May-2020
 */
public class CutTheChocolate {
    public int solve(int A, int B) {
        // In order th generate n*m pieces after all the moves are completed, we need moves = ((n * m) - 1).
        // if moves is odd, the person who starts will end the game, else the other person ends the game.
        return (((A * 1L * B) - 1) % 2L == 0L) ? 0 : 1;
    }

    public static void main(String[] args) {
        CutTheChocolate c = new CutTheChocolate();
        int a = 4;
        int b = 3;
        System.out.println(c.solve(a, b));
    }
}

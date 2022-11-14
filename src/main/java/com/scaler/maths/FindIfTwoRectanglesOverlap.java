package com.scaler.maths;

/**
 * Given eight integers A, B, C, D, E, F, G and H which represent two rectangles in a 2D plane. <br/>
 * For the first rectangle it's bottom left corner is (A, B) and top right corner is (C, D) and
 * for the second rectangle it's bottom left corner is (E, F) and top right corner is (G, H). <br/>
 * Find and return whether the two rectangles overlap or not.<br/>
 * Return 1 if the two rectangles overlap else return 0.
 *
 * @author sudhir on 04-Apr-2020
 */
public class FindIfTwoRectanglesOverlap {
    public int solve(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (!(C <= E || G <= A || D <= F || H <= B)) {
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        FindIfTwoRectanglesOverlap f = new FindIfTwoRectanglesOverlap();
        int A = 0, B = 0, C = 4, D = 4, E = 2, F = 2, G = 6, H = 6;
        int A1 = 0, B1 = 0, C1 = 4, D1 = 4, E1 = 2, F1 = 2, G1 = 3, H1 = 3;
        System.out.println(f.solve(A, B, C, D, E, F, G, H));
        System.out.println(f.solve(A1, B1, C1, D1, E1, F1, G1, H1));
    }
}

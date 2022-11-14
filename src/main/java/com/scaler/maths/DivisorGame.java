package com.scaler.maths;

/**
 * Scooby has 3 three integers A, B and C. <br/>
 * Scooby calls a positive integer special if it is divisible by B and it is divisible by C. <br/>
 * You need to tell number of special integers less than or equal to A.
 *
 * @author sudhir on 04-Apr-2020
 */
public class DivisorGame {
    public int solve(int A, int B, int C) {
        int lcm = findLcm(B, C);
        return A / lcm;
    }

    private int findLcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    private int gcd(int a, int b) {
        int m = Math.max(a, b);
        int n = Math.min(a, b);
        while (n != 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        return m;
    }

    public static void main(String[] args) {
        DivisorGame d = new DivisorGame();
        int a = 12, b = 3, c = 2;
        System.out.println(d.solve(a, b, c));
    }
}

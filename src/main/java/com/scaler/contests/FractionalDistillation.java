package com.scaler.contests;

/**
 * Given an integer A, find two numbers x and y such that:
 * <p>
 * x<y
 * GCD(x,y) = 1
 * x+y = A
 * <p>
 * </p>
 * <p>
 * For all values of x, y that satisfy the criteria, x/y should be the greatest.
 * </p>
 * <p>
 * Return y-x as the answer.
 * </p>
 *
 * @author sudhir on 12-Jul-2020
 */
public class FractionalDistillation {
    public int getDiff(int A) {
        int x = 0;
        int y = 0;
        if (A % 2 == 0) {
            x = A / 2 - 1;
            y = A / 2 + 1;
        } else {
            x = A / 2;
            y = A - x;
        }
        while (gcd(x, y) != 1) {
            x--;
            y++;
        }
        return y - x;
    }

    private int gcd(int a, int b) {
        int m = a;
        int n = b;
        if (a < b) {
            m = b;
            n = a;
        }
        while (n > 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        return m;
    }

    public static void main(String[] args) {
        FractionalDistillation f = new FractionalDistillation();
        int n1 = 11;
        int n2 = 24;
        System.out.println(f.getDiff(n1));
        System.out.println(f.getDiff(n2));
    }
}

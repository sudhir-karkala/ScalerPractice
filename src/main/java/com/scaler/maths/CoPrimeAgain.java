package com.scaler.maths;

/**
 * You are given 2 arrays A and B of size N and M respectively and a number K. <br/>
 * You have to tell that how many pairs (A[i], B[j]) (1 <= i <= N and 1 <= j <= m) exists such that product of them is not CoPrime with K.
 *
 * @author sudhir on 04-Apr-2020
 */
public class CoPrimeAgain {
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

    public int solve(int[] A, int[] B, int C) {
        int m = A.length;
        int n = B.length;
        int total = m * n;//total pairs possible.
        //if we get pairs (xi,yi) such that gcd(xi*yi, C) = 1, then (m * n) - (x * y) is the required answer.
        //note that if gcd(a, C) = 1 and gcd(b, C) = 1, then gcd(ab, C) = 1
        int x = 0;
        int y = 0;
        for (int i = 0; i < A.length; i++) {
            if (gcd(A[i], C) == 1) {
                x++;
            }
        }
        for (int i = 0; i < B.length; i++) {
            if (gcd(B[i], C) == 1) {
                y++;
            }
        }
        return total - (x * y);
    }

    public static void main(String[] args) {
        CoPrimeAgain c = new CoPrimeAgain();
        //There are total 12 pairs, and 6 pairs are there such that their product is not coprime with k, i.e 6.
        //These pairs are :(1, 3), (2, 3), (3, 3), (3, 2), (3, 4), (3, 5)
        int[] A = {1, 2, 3};
        int[] B = {2, 3, 4, 5};
        int K = 3;
        System.out.println(c.solve(A, B, K));
    }
}

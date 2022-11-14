package com.scaler.maths;

/**
 * Given 2 non negative integers A and B, find gcd(A, B).<br/>
 * GCD of 2 integers A and B is defined as the greatest integer g such that g is a divisor of both A and B. <br/>
 * Both A and B fit in a 32 bit signed integer.
 *
 * @author sudhir on 04-Apr-2020
 */
public class GreatestCommonDivisor {
    public int gcd(int A, int B) {
        int m = Math.max(A, B);
        int n = Math.min(A, B);
        while (n != 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        return m;
    }

    public static void main(String[] args) {
        GreatestCommonDivisor g = new GreatestCommonDivisor();
        System.out.println(g.gcd(6, 3));
    }
}

package com.scaler.maths;

/**
 * You are given two positive numbers A and B . You need to find the maximum valued integer X such that:<br/>
 * 1. X divides A i.e. A % X = 0<br/>
 * 2. X and B are co-prime i.e. gcd(X, B) = 1
 *
 * @author sudhir on 04-Apr-2020
 */
public class LargestCoprimeDivisor {
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

    public int cpFact(int A, int B) {
        while (gcd(A, B) != 1) {
            A /= gcd(A, B);
        }
        return A;
    }

    public static void main(String[] args) {
        LargestCoprimeDivisor l = new LargestCoprimeDivisor();
        int a = 30;
        int b = 12;
        System.out.println(l.cpFact(a, b));
    }
}

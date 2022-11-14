package com.scaler.maths;

/**
 * You are given 2 numbers P and Q. An operation on these 2 numbers is defined as follows: <br/>
 * Take the smaller number of the 2 numbers and subtract it from the bigger number. <br/>
 * Keep performing this operation till either of the following criterion is met:Both numbers become equal.<br/>
 * Either of the number becomes 0.<br/>
 * Find the sum of the final values of P and Q
 *
 * @author sudhir on 04-Apr-2020
 */
public class RepeatedSubtraction {
    private int findgcd(int a, int b) {
        int m = Math.max(a, b);
        int n = Math.min(a, b);
        while (n != 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        return m;
    }

    public int getFinal(int A, int B) {
        if (A == B) {
            return 2 * A;
        }
        if (A == 0) {
            return B;
        }
        if (B == 0) {
            return A;
        }
        return 2 * findgcd(A, B);
    }

    public static void main(String[] args) {
        RepeatedSubtraction r = new RepeatedSubtraction();
        int a = 5;
        int b = 15;
        System.out.println(r.getFinal(a, b));
    }
}

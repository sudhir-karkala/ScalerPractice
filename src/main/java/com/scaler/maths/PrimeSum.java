package com.scaler.maths;

import java.util.Arrays;

/**
 * Given an even number A ( greater than 2 ), return two prime numbers whose sum will be equal to given number. <br/>
 * If there are more than one solutions possible, return the lexicographically smaller solution.<br/>
 * If [a, b] is one solution with a <= b, and [c,d] is another solution with c <= d, then [a, b] < [c, d], <br/>
 * If a < c OR a==c AND b < d.<br/>
 * NOTE: A solution will always exist. Read Goldbach's conjecture.
 *
 * @author sudhir on 04-Apr-2020
 */
public class PrimeSum {
    public int[] primesum(int A) {
        //construct sieve
        boolean isprime[] = new boolean[A + 1];
        Arrays.fill(isprime, true);
        isprime[0] = false;
        isprime[1] = false;
        for (int p = 2; p * p <= A; p++) {
            if (isprime[p] == true) {
                for (int i = p * p; i <= A; i += p) {
                    isprime[i] = false;
                }
            }
        }
        for (int i = 2; i <= A; i++) {
            if (isprime[i] && isprime[A - i]) {
                return new int[]{i, A - i};
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        PrimeSum p = new PrimeSum();
        int a = 4;
        System.out.println(Arrays.toString(p.primesum(a)));
    }
}

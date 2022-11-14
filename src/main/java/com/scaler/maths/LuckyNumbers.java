package com.scaler.maths;

import java.util.Arrays;

/**
 * A lucky number is a number which has exactly 2 distinct prime divisors. <br/>
 * You are given a number N and you need to determine the count of lucky numbers between the range 1 to N (both inclusive).
 *
 * @author sudhir on 04-Apr-2020
 */
public class LuckyNumbers {
    private int MAXNUM = 5000;
    //npf[i] = number of prime factors of i
    private int[] npf = new int[MAXNUM + 1];

    private void modifiedSieve() {
        Arrays.fill(npf, 0);
        for (int p = 2; p <= MAXNUM; p++) {
            if (npf[p] == 0) {
                for (int i = p; i <= MAXNUM; i += p) {
                    npf[i]++;
                }
            }
        }
    }

    private int countOf2PrimeFactors(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (npf[i] == 2) {
                count++;
            }
        }
        return count;
    }

    public int solve(int A) {
        modifiedSieve();
        return countOf2PrimeFactors(A);
    }

    public static void main(String[] args) {
        LuckyNumbers l = new LuckyNumbers();
        int a = 8;
        System.out.println(l.solve(a));
    }
}

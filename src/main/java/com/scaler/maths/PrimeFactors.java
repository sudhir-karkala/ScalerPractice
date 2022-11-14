package com.scaler.maths;

import java.util.Arrays;

/**
 * You are given a Task as described below :<br/>
 * You are given A queries. For each query (1<=i<=A) you are given a prime number B[i],
 * you need to give the count of all numbers in range 1 to 10^6 inclusive which have minimum prime factor B[i] for each query.
 *
 * @author sudhir on 04-Apr-2020
 */
public class PrimeFactors {
    //spf = smallest prime factor
    //count[i] = count of numbers with smallest prime factor as i
    private int MAXNUM = 1000000;
    private int[] spf = new int[MAXNUM + 1];
    private int[] count = new int[MAXNUM + 1];

    private void sieve() {
        for (int i = 0; i < spf.length; i++) {
            spf[i] = i;
        }

        for (int p = 2; p * p <= MAXNUM; p++) {
            if (spf[p] == p) {
                for (int i = p * p; i <= MAXNUM; i += p) {
                    if (spf[i] == i) {
                        spf[i] = p;
                        count[spf[i]]++;
                    }
                }
            }
        }
    }

    public int[] solve(int A, int[] B) {
        sieve();
        int[] result = new int[A];
        for (int i = 0; i < A; i++) {
            result[i] = count[B[i]] + 1;//count[i] doesn't include the number i itself. so we add 1 here to the count.
        }
        return result;
    }

    public static void main(String[] args) {
        PrimeFactors p = new PrimeFactors();
        int a = 1;
        int[] b = {11};
        System.out.println(Arrays.toString(p.solve(a, b)));
    }
}

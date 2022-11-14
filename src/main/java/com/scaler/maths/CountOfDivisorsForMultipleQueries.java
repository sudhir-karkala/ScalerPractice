package com.scaler.maths;

import java.util.Arrays;

/**
 * Given an array of integers A, find and return the count of divisors of each element of the array. <br/>
 * NOTE: Order of the resultant array should be same as the input array.
 *
 * @author sudhir on 04-Apr-2020
 */
public class CountOfDivisorsForMultipleQueries {
    // spf[i] = smallest prime factor of i
    private int MAXNUM = 1000000;
    private int[] spf = new int[MAXNUM + 1];

    private void modifiedSieve() {
        for (int i = 0; i <= MAXNUM; i++) {
            spf[i] = i;
        }
        for (int p = 2; p * p <= MAXNUM; p++) {
            if (spf[p] == p) {
                for (int i = p * p; i <= MAXNUM; i += p) {
                    if (spf[i] == i) {
                        spf[i] = p;
                    }
                }
            }
        }
    }

    private int countDivisors(int num) {
        int ans = 1;
        while (spf[num] > 1) {
            int count = 1;
            int b = spf[num];
            while (spf[num] == b) {
                count++;
                num = num / b;
            }
            ans = ans * count;
        }
        return ans;
    }

    public int[] solve(int[] A) {
        int[] result = new int[A.length];
        modifiedSieve();
        for (int i = 0; i < A.length; i++) {
            result[i] = countDivisors(A[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        CountOfDivisorsForMultipleQueries c = new CountOfDivisorsForMultipleQueries();
        int[] a = {2, 3, 4, 20};
        System.out.println(Arrays.toString(c.solve(a)));
    }
}

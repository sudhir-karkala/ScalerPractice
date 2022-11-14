package com.scaler.searching;

import java.math.BigInteger;

/**
 * Given an integer A, we call k >= 2 a good base of A,
 * if all digits of A base k are 1.
 * Now given a string representing A, you should return the smallest good base of A in string format.
 * Constraint: 3 <= A <= 10^18
 *
 * @author sudhir on 01-Mar-2020
 */
public class SmallestGoodBase {
    public static String solve(String A) {
        long N = Long.parseLong(A);
        int bits = 1 + (int) (Math.log(N) / Math.log(2));
        for (int i = bits; i >= 2; i--) {
            long result = binarySearch(i, 2, N - 1, N);
            if (result != -1) {
                return String.valueOf(result);
            }
        }
        return String.valueOf(N - 1);
    }

    static long binarySearch(int i, long l, long r, long N) {
        if (l > r) {
            return -1;
        }
        long k = l + (r - l) / 2;
        BigInteger t1 = BigInteger.valueOf(k).pow(i).subtract(BigInteger.ONE);
        BigInteger t2 = BigInteger.valueOf(N).multiply(BigInteger.valueOf(k).subtract(BigInteger.ONE));
        int compare = t1.compareTo(t2);
        if (compare == 0) {
            return k;
        } else if (compare < 0) {
            return binarySearch(i, k + 1, r, N);
        } else {
            return binarySearch(i, l, k - 1, N);
        }
    }

    public static void main(String[] args) {
        String a = "4681";
        String b = "13";
        String c = "1000000000000000000";
        System.out.println(solve(a));
        System.out.println(solve(b));
        System.out.println(solve(c));
    }
}

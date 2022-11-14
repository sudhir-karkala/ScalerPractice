package com.scaler.companyquestions;

/**
 * Given an array A having N positive numbers. You have to find the number of Prime subsequences of A.
 * <p>
 * A Prime subsequence is one that has only prime numbers, for example [2, 3], [5] are the Prime subsequences
 * where [2, 4] and [1, 2, 3, 4] are not.
 * <p>
 * Return an integer X, i.e number of Prime subsequences.
 * As X can be very large print X % (1000000007), here % is modulus operator.
 *
 * @author sudhir on 17-Jul-2020
 */
public class PrimeSubsequences {
    private int mod = (int) 1e9 + 7;

    private boolean[] constructSieve(int max) {
        boolean isPrime[] = new boolean[max + 1];
        isPrime[0] = false;
        isPrime[1] = false;
        int n = max;
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    private int power(int x, int y, int mod) {
        if (y == 0) {
            return 1;
        }
        x = x % mod;
        long res = 1;
        while (y > 0) {
            if (y % 2 == 1) {
                res = (res * x) % mod;
            }
            y = y >> 1;
            x = (int) ((1L * x * x) % mod);
        }
        return (int) res;
    }

    public int solve(int[] A) {
        // find max number in the array
        int max = Integer.MIN_VALUE;
        int n = A.length;
        for (int i = 0; i < n; i++) {
            if (max < A[i]) {
                max = A[i];
            }
        }
        // using Sieve of Eratosthenes, generate prime numbers upto max number in the array
        boolean[] isPrime = constructSieve(max);
        int count = 0;
        for (int i = 0; i < n; i++) {
            // find the count of prime numbers in the given array.
            if (isPrime[A[i]]) {
                count++;
            }
        }
        // number of prime subsequences = (2^(count_of_prime_numbers) - 1)
        return (power(2, count, mod) - 1);
    }
}

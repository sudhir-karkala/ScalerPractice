package com.scaler.maths;

/**
 * Given three integers A, B and C, where A represents n, B represents r and C represents p and p is a prime number
 * greater than equal to n, find and return the value of nCr % p where nCr % p = (n! / ((n-r)! * r!)) % p. <br/>
 * x! means factorial of x i.e. x! = 1 * 2 * 3... * x. <br/>
 * NOTE: For this problem, we are considering 1 as a prime.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= A <= 10^6
 * <p>
 * 1 <= B <= A
 * <p>
 * A <= C <= 10^9+7
 *
 * @author sudhir on 26-May-2020
 */
public class ComputeNcRModP {
    /**
     * We need to find fact[n] * (fact[n-r])^-1 * (fact[r])^-1
     * i.e. we need to compute modulo inverse using Fermat's Little theorem: a^(p-2)=a^-1(mod p).
     * Hence, to find a^-1, we need to compute a^(p-2) mod p.
     *
     * @param A
     * @param B
     * @param C
     * @return
     */
    public int solve(int A, int B, int C) {
        int n = A;
        int r = B;
        int p = C;
        long[] fact = computeFactorial(n, p);
        return (int) ((((fact[n] % p) * (modInverse(fact[r], p) % p) % p) * (modInverse(fact[n - r], p) % p)) % p);
    }

    private long[] computeFactorial(int n, int p) {
        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = (fact[i - 1] * i) % p;
        }
        return fact;
    }

    private long modInverse(long n, int p) {
        return power(n, p - 2, p);
    }

    private long power(long x, int y, int p) {
        if (y == 0) {
            return 1;
        }
        long res = 1;
        x = x % p;
        while (y > 0) {
            // if y is odd
            if (y % 2 == 1) {
                res = (res * x) % p;
            }
            // now y will be even
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }

    public static void main(String[] args) {
        ComputeNcRModP c = new ComputeNcRModP();
        int n1 = 5;
        int r1 = 2;
        int p1 = 13;
        int n2 = 778;
        int r2 = 578;
        int p2 = 10007;
        System.out.println(c.solve(n1, r1, p1));
        System.out.println(c.solve(n2, r2, p2));
    }
}

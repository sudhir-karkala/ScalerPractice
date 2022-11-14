package com.scaler.dynamicprogramming;

import java.util.Scanner;

/**
 * Given a positive integer A, write a program to find the Ath Fibonacci number. <br/>
 * In a Fibonacci series, each term is the sum of the previous two terms and the first two terms of the series are 0 and 1.
 * i.e. f(0) = 0 and f(1) = 1. <br/>
 * Hence, f(2) = 1, f(3) = 2, f(4) = 3 and so on. NOTE: 0th term is 0. 1th term is 1 and so on.
 *
 * @author sudhir on 25-Apr-2020
 */
public class FibonacciNumber {
    public static void main(String[] args) {
        // YOUR CODE GOES HERE
        // Please take input and print output to standard input/output (stdin/stdout)
        // DO NOT USE ARGUMENTS FOR INPUTS
        // E.g. 'Scanner' for input & 'System.out' for output
        FibonacciNumber f = new FibonacciNumber();
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int result = f.fib(n);
        System.out.println(result);
        scan.close();
    }

    private int fib(int n) {
        int[] fib = new int[n + 1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }
}

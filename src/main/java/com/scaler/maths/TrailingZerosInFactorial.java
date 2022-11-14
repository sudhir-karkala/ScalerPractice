package com.scaler.maths;

/**
 * Given an integer A, return the number of trailing zeroes in A! i.e. factorial of A. <br/>
 * Note: Your solution should run in logarithmic time complexity.
 *
 * @author sudhir on 04-Apr-2020
 */
public class TrailingZerosInFactorial {
    public int trailingZeroes(int A) {
        int i = 5;
        int count = 0;
        while ((A / i) > 0) {
            count += (A / i);
            i *= 5;
        }
        return count;
    }

    public static void main(String[] args) {
        TrailingZerosInFactorial t = new TrailingZerosInFactorial();
        int a = 5;
        System.out.println(t.trailingZeroes(a));
    }

}

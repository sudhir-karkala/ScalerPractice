package com.scaler.bitmanipulation;

/**
 * Divide two integers without using multiplication, division and mod operator. Return the floor of the result of the division.<br/>
 * Also, consider if there can be overflow cases. For overflow case, return INT_MAX. Note: INT_MAX = 2^31 - 1
 *
 * @author sudhir on 04-Apr-2020
 */
public class DivideIntegers {
    public int divide(int A, int B) {
        long a = (long) A;
        long b = (long) B;
        long quotient = 0;
        long temp = 0;
        int sign = (a < 0 ^ b < 0) ? -1 : 1;
        a = Math.abs(a);
        b = Math.abs(b);
        for (int i = 31; i >= 0; i--) {
            if (temp + (b << i) <= a) {
                temp += (b << i);
                quotient = quotient | (1L << i);
            }
        }
        quotient = sign * quotient;
        if (quotient > Integer.MAX_VALUE || quotient < Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) quotient;
    }

    public static void main(String[] args) {
        DivideIntegers d = new DivideIntegers();
        int a = 5;
        int b = 2;
        System.out.println(d.divide(a, b));

    }
}

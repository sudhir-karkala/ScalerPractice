package com.scaler.maths;

/**
 * You are given an integer N and the task is to reverse the digits of the given integer. <br/>
 * Return 0 if the result overflows and does not fit in a 32 bit signed integer.<br/>
 * N belongs to the Integer limits.
 *
 * @author sudhir on 24-May-2020
 */
public class ReverseInteger {
    public int reverse(int A) {
        int rev = 0;
        int sign = 1;
        // if given number is -ve, then make it positive and perform operations. later we can change the sign again to make it negative.
        if (A < 0) {
            sign = -1;
            A *= -1;
        }

        while (A > 0) {
            int digit = A % 10;
            // return 0 if one of the two overflow conditions occur.
            // a. if rev * 10 overflows i.e. it becomes greater than INT_MAX.
            // b. if rev * 10 equals INT_MAX and the digit is greater then INT_MAX % 10
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return 0;
            }
            rev = rev * 10 + digit;
            A /= 10;
        }
        // change the sign to discard the modification done earlier to the sign.
        if (sign == -1) {
            rev *= -1;
        }
        return rev;
    }

    public static void main(String[] args) {
        ReverseInteger r = new ReverseInteger();
        int num1 = -123;
        System.out.println(r.reverse(num1));
        int num2 = 2147483647;
        System.out.println(r.reverse(num2));
    }
}

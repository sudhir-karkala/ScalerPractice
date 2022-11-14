package com.scaler.strings;

/**
 * Given a number A in the form of string. Check if the number is divisible by 8 or not. <br/>
 * Return 1 if it is divisible by 8 else return 0.
 *
 * @author sudhir on 09-Apr-2020
 */
public class DivisibilityBy8 {
    public int solve(String A) {
        int base = 1;
        int digits = 3;
        int length = A.length();
        int num = 0;
        int i = length - 1;
        while (i >= 0 && (length - i) <= 3) {
            num += ((A.charAt(i) - '0') * base);
            base *= 10;
            i--;
        }
        return num % 8 == 0 ? 1 : 0;
    }

    public static void main(String[] args) {
        DivisibilityBy8 d = new DivisibilityBy8();
        String A = "4758310052942341036336679074241759053154797537802772";
        System.out.println(d.solve(A));
    }
}

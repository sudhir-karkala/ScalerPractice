package com.scaler.strings;

/**
 * Given two binary strings, return their sum (also a binary string).
 *
 * @author sudhir on 24-Apr-2020
 */
public class AddBinaryStrings {
    public String addBinary(String A, String B) {
        int m = A.length();
        int n = B.length();
        int i = m - 1;
        int j = n - 1;
        int sum = 0;
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        while (i >= 0 || j >= 0 || carry == 1) {
            if (i >= 0) {
                sum += (A.charAt(i) - '0');
            }
            if (j >= 0) {
                sum += (B.charAt(j) - '0');
            }
            sum += carry;
            builder.insert(0, (char) ((sum % 2) + '0'));
            carry = sum / 2;
            sum = 0;
            i--;
            j--;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        AddBinaryStrings a = new AddBinaryStrings();
        String s1 = "1111";
        String s2 = "111";
        System.out.println(a.addBinary(s1, s2));
    }
}

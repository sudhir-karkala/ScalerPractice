package com.scaler.strings;

import java.math.BigInteger;

/**
 * Compare two version numbers version1 and version2.
 * <p>
 * If version1 > version2 return 1, If version1 < version2 return -1, otherwise return 0.
 * </p>
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three",
 * it is the fifth second-level revision of the second first-level revision.
 * <p>
 * Example: 0.1 < 1.1 < 1.2 < 1.13 < 1.13.4
 * </p>
 *
 * @author sudhir on 25-Mar-2020
 */
public class CompareVersionNumbers {
    public int compareVersion(String A, String B) {
        int m = A.length();
        int n = B.length();
        for (int i = 0, j = 0; i < m || j < n; ) {
            BigInteger num1 = BigInteger.ZERO;
            BigInteger num2 = BigInteger.ZERO;
            while (i < m && A.charAt(i) != '.') {
                num1 = num1.multiply(BigInteger.TEN).add(BigInteger.valueOf(A.charAt(i) - '0'));
                i++;
            }
            while (j < n && B.charAt(j) != '.') {
                num2 = num2.multiply(BigInteger.TEN).add(BigInteger.valueOf(B.charAt(j) - '0'));
                j++;
            }
            int value = num1.compareTo(num2);
            if (value == 1) {
                return 1;
            }
            if (value == -1) {
                return -1;
            }
            //if num1==num2, then check for subsequent parts of the string
            i++;
            j++;
        }
        return 0;
    }

    public static void main(String[] args) {
        CompareVersionNumbers c = new CompareVersionNumbers();
        String a1 = "1.13.20";
        String a2 = "1.13.13";
        String b1 = "2.06";
        String b2 = "2.6";
        String c1 = "4444371174137455";
        String c2 = "5.168";
        System.out.println(c.compareVersion(a1, a2));
        System.out.println(c.compareVersion(b1, b2));
        System.out.println(c.compareVersion(c1, c2));
    }
}

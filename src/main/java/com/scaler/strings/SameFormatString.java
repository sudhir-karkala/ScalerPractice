package com.scaler.strings;


/**
 * Given two strings A and B. Check if B contains same characters as that of given string A and in the same order.
 * <p>
 * Note<br/>
 * 1: A and B contain only UPPERCASE Letters.<br/>
 * 2: No two consecutive characters are same in A.<br/>
 * 3: You can only use constant amount of extra memory.
 * </p>
 *
 * @author sudhir on 27-Mar-2020
 */
public class SameFormatString {
    public int solve(final String A, final String B) {
        int i = 0;
        int j = 0;
        int m = A.length();
        int n = B.length();
        while (i < m) {
            while (j < n) {
                if (A.charAt(i) != B.charAt(j)) {
                    return 0;
                } else {
                    while (j < n && A.charAt(i) == B.charAt(j)) {
                        j++;
                    }
                    break;
                }
            }
            i++;
            if (i == m && j == n) {
                return 1;
            }
            if (i == m || j == n) {
                break;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        SameFormatString s = new SameFormatString();
        String a1 = "HIRED";
        String b1 = "HHHIIIRRRRREEEEEDDDDD";
        String a2 = "HIRED";
        String b2 = "DDHHHHIIIIRRRRREEEEDDD";
        System.out.println(s.solve(a1, b1));
        System.out.println(s.solve(a2, b2));
    }
}

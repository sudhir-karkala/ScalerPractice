package com.scaler.hashing;

import java.util.HashSet;
import java.util.Set;

/**
 * For Given Number A find if its COLORFUL number or not. If number A is a COLORFUL number return 1 else return 0.
 * What is a COLORFUL Number:<br/>
 * A number can be broken into different contiguous sub-subsequence parts.<br/>
 * Suppose, a number 3245 can be broken into parts like 3 2 4 5 32 24 45 324 245.<br/>
 * And this number is a COLORFUL number, since product of every digit of a contiguous subsequence is different.
 *
 * @author sudhir on 10-Apr-2020
 */
public class ColorfulNumber {
    public int colorful(int A) {
        Set<Integer> set = new HashSet<>();
        String str = Integer.toString(A);
        int prod = 1;
        int n = str.length();
        for (int i = 0; i < n; i++) {
            prod = 1;
            for (int j = i; j < n; j++) {
                prod *= (str.charAt(j) - '0');
                if (set.contains(prod)) {
                    return 0;
                }
                set.add(prod);
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        ColorfulNumber c = new ColorfulNumber();
        int a = 23;
        System.out.println(c.colorful(a));
    }
}

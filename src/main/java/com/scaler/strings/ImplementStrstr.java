package com.scaler.strings;

/**
 * Search pattern in text using O(n) algorithm. This uses Z-algorithm.
 *
 * @author sudhir on 25-Apr-2020
 */
public class ImplementStrstr {
    public int strStr(final String text, final String pattern) {
        String concat = pattern + "$" + text;
        int l = concat.length();
        int Z[] = new int[l];
        int patternLength = pattern.length();
        // construct z-array
        getZarr(concat, Z);
        for (int i = 0; i < l; ++i) {
            if (Z[i] == patternLength) {
                return (i - patternLength - 1);
            }
        }
        return -1;
    }

    private void getZarr(String str, int[] Z) {
        int n = str.length();
        int L = 0, R = 0;
        for (int i = 1; i < n; ++i) {
            if (i > R) {
                L = R = i;
                while (R < n && str.charAt(R - L) == str.charAt(R))
                    R++;
                Z[i] = R - L;
                R--;
            } else {
                int k = i - L;
                if (Z[k] < R - i + 1)
                    Z[i] = Z[k];
                else {
                    L = i;
                    while (R < n && str.charAt(R - L) == str.charAt(R)) {
                        R++;
                    }
                    Z[i] = R - L;
                    R--;
                }
            }
        }
    }

    public static void main(String[] args) {
        ImplementStrstr implementStrstr = new ImplementStrstr();
        String text = "abcjsdhsjsdfgh";
        String pattern = "jsdh";
        System.out.println(implementStrstr.strStr(text, pattern));
    }
}

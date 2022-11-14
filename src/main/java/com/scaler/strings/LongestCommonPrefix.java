package com.scaler.strings;

/**
 * Given the array of strings A, you need to find the longest string S
 * which is the prefix of ALL the strings in the array.
 * Longest common prefix for a pair of strings S1 and S2 is the longest string S
 * which is the prefix of both S1 and S2.
 * <p>
 * For Example: longest common prefix of "abcdefgh" and "abcefgh" is "abc".
 * </p>
 *
 * @author sudhir on 18-Mar-2020
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] A) {
        //find min length to iterate character by character for all strings
        int minLength = findMinLength(A);
        String prefix = "";
        for (int i = 0; i < minLength; i++) {
            char c = A[0].charAt(i);
            for (int j = 1; j < A.length; j++) {
                if (c != A[j].charAt(i)) {
                    return prefix;
                }
            }
            prefix = prefix + c;
        }
        return prefix;
    }

    private int findMinLength(String[] A) {
        int min = A[0].length();
        for (int i = 1; i < A.length; i++) {
            if (A[i].length() < min) {
                min = A[i].length();
            }
        }
        return min;
    }

    public static void main(String[] args) {
        LongestCommonPrefix l = new LongestCommonPrefix();
        String[] A = {"abcdefgh", "aefghijk", "abcefgh"};
        String[] B = {"abab", "ab", "abcd"};
        System.out.println(l.longestCommonPrefix(A));
        System.out.println(l.longestCommonPrefix(B));
    }
}

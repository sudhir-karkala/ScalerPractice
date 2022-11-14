package com.scaler.strings;

/**
 * <b>Title: </b>
 * Minimum Characters required to make a String Palindromic
 * <p>
 * Given a string A of size N consisting only of uppercase alphabets.
 * The only operation allowed is to insert characters in the beginning of the string.
 * Find and return how many minimum characters are needed to be inserted to make the string a palindrome string.
 * </p>
 *
 * @author sudhir on 25-Mar-2020
 */
public class MakeStringPalindromic {
    public int solve(String A) {
        int n = A.length();
        int i = 0;
        int j = n - 1;
        while (i <= j) {
            if (isPalindrome(A, i, j)) {
                break;
            } else {
                j--;
            }
        }
        return n - j - 1;
    }

    private boolean isPalindrome(String A, int start, int end) {
        while (start < end) {
            if (A.charAt(start) != A.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        MakeStringPalindromic m = new MakeStringPalindromic();
        String a1 = "ABC";
        String a2 = "ABCDBA";
        System.out.println(m.solve(a1));
        System.out.println(m.solve(a2));

    }
}

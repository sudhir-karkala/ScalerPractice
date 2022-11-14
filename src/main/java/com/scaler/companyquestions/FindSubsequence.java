package com.scaler.companyquestions;

/**
 * Given two strings A and B, find if A is a subsequence of B.
 * <p>
 * Return "YES" if A is a subsequence of B, else return "NO".
 * <p>
 * 1 <= length(A), length(B) <= 100000
 * <p>
 * 'a' <= A[i], B[i] <= 'z'
 *
 * @author sudhir on 16-Jul-2020
 */
public class FindSubsequence {
    public String solve(String A, String B) {
        int m = A.length();
        int n = B.length();
        int start = n - 1;
        int match = 0;
        // for every character in A, check if it is present in B and
        // keep track of the position matched in B using start variable so that sequence is maintained.
        // i.e. say if ith character A is matched at index j in B, then (i-1)th character can be matched only before
        // jth character in B which is (j-1)th character.
        boolean matched = false;
        for (int i = m - 1; i >= 0; i--) {
            matched = false;
            for (int j = start; j >= 0; j--) {
                if (A.charAt(i) == B.charAt(j)) {
                    match++;
                    start = j - 1;
                    matched = true;
                    break;
                }
            }
            // this check is done so that if any character in A is not found in B, then there is no need to
            // check for remaining characters from A in B.
            if (!matched) {
                break;
            }
        }
        return match == m ? "YES" : "NO";
    }

    public static void main(String[] args) {
        FindSubsequence f = new FindSubsequence();
        String a1 = "bit";
        String b1 = "dfbkjijgbbiihbmmt";
        String a2 = "apple";
        String b2 = "appel";
        System.out.println(f.solve(a1, b1));
        System.out.println(f.solve(a2, b2));
    }
}

package com.scaler.strings;

import java.util.ArrayList;

/**
 * Given a string A and a string B. Find and return the starting indices of the substrings of A which matches any of the anagrams of B. <br/>
 * Input 1: A = "bacdgabcda", B = "abcd" <br/>
 * Output 1: [0, 5, 6] <br/>
 *
 * @author sudhir on 22-Apr-2020
 */
public class AnagramSubstringSearch {
    public ArrayList<Integer> solve(String A, String B) {
        int[] hash1 = new int[26];
        int[] hash2 = new int[26];
        int m = A.length();
        int n = B.length();
        // initially construct hash array for string A of length n which are indices from 0..n-1 which is the length of string B
        // hash1 and hash2 contain counts of each character in that window of characters present in indices 0..n-1
        for (int i = 0; i < n; i++) {
            hash2[B.charAt(i) - 'a']++;
            hash1[A.charAt(i) - 'a']++;
        }
        ArrayList<Integer> result = new ArrayList<>();
        // check if constructed hash1 is same as hash2, meaning check if set of characters in hash1 is same as that in hash2
        // i.e. check if string represented by hash1 is anagram of string represented by hash2
        // If yes, then we add the starting index of substring in A which is index = 0
        if (isSame(hash1, hash2)) {
            result.add(0);
        }
        // use sliding window based approach to find the indices of substrings of A which are anagrams in B
        for (int i = n, j = 0; j < m && i < m; j++, i++) {
            hash1[A.charAt(j) - 'a']--;
            hash1[A.charAt(i) - 'a']++;
            if (isSame(hash1, hash2)) {
                result.add(j + 1);
            }
        }
        return result;
    }

    private boolean isSame(int[] hash1, int[] hash2) {
        for (int i = 0; i < hash2.length; i++) {
            if (hash1[i] != hash2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        AnagramSubstringSearch a = new AnagramSubstringSearch();
        String s1 = "bacdgabcda";
        String s2 = "abcd";
        String s3 = "aaababaa";
        String s4 = "aaba";
        System.out.println(a.solve(s1, s2));
        System.out.println(a.solve(s3, s4));
    }
}

package com.scaler.contests;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings A and B each of size N consisting of lowercase alphabets. You can perform the following operations any number of time:<br/>
 * Select any two characters in string A and swap the characters.<br/>
 * Select any two characters in string B and swap the characters.<br/>
 * Select a character in string A and select a character in string B. Swap the characters from both strings.<br/>
 * Return 1 if it possible to make strings equal else return 0.
 *
 * @author sudhir on 10-May-2020
 */
public class EqualStrings {
    public int solve(String A, String B) {
        int m = A.length();
        int n = B.length();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            map.put(A.charAt(i) - 'a', map.getOrDefault(A.charAt(i) - 'a', 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            map.put(B.charAt(i) - 'a', map.getOrDefault(B.charAt(i) - 'a', 0) + 1);
        }
        // if the frequency of characters is even, then it's possible to make equal strings, else it's not possible.
        for (Integer num : map.values()) {
            if (num % 2 == 1) {
                return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        EqualStrings e = new EqualStrings();
        String str1 = "adbc";
        String str2 = "abcd";
        System.out.println(e.solve(str1, str2));
    }
}

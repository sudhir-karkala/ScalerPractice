package com.scaler.companyquestions;

import java.util.HashMap;
import java.util.Map;

/**
 * Rick and Morty are best friends. Rick and Morty will change their name to strings A and B respectively.
 * <p>
 * They want to know if it is possible to transform A into B by doing zero or more conversions.
 * <p>
 * In one conversion you can convert all occurrences of one character in A to any other lowercase English character.
 * <p>
 * 1 <= len(A) <= 10^5
 * <p>
 * len(A) == len(B)
 *
 * @author sudhir on 06-Aug-2020
 */
public class StrongTransformation {
    public int solve(String A, String B) {
        // if the strings are same, then return true.
        if (A.equals(B)) {
            return 1;
        }
        int m = A.length();
        int n = B.length();
        // this check is not ideally not needed as it is given in the constraints that lengths are same
        if (m != n) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            // if the character is mapped to some other character, then transformation is not possible, return false.
            if (map.containsKey(A.charAt(i) - 'a')) {
                if (map.get(A.charAt(i) - 'a') != (B.charAt(i) - 'a')) {
                    return 0;
                }
            } else {
                map.put(A.charAt(i) - 'a', B.charAt(i) - 'a');
            }
        }
        // check if there is at least one character that is left out from the mapping, then even if there is cyclic mapping from
        // A to B (ex: A = acb, B = abc) we can still do the transformation from A to B in this way:
        // acb => acd => abd => abc. Note that we have used 'd' to perform mappings.
        // If there is no free character in the sample space i.e. all have been used in the map, then transformation is not possible.
        // In that case, return false.
        if (map.size() == 26) {
            return 0;
        }
        return 1;
    }

    public static void main(String[] args) {
        StrongTransformation s = new StrongTransformation();
        String a1 = "acb";
        String b1 = "abc";
        String a2 = "ababc";
        String b2 = "adadg";
        String a3 = "abcab";
        String b3 = "abcdb";
        System.out.println(s.solve(a1, b1));
        System.out.println(s.solve(a2, b2));
        System.out.println(s.solve(a3, b3));
    }
}

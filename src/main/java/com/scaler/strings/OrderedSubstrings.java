package com.scaler.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given an array A consisting of strings. You are supposed to order the given strings in such a way
 * that for a particular string, all strings ordered before it exist as its substrings. <br/>
 * Each string is made up of lowercase English letters.
 * Return an array consisting of the correct order of strings. <br/>
 * If it is not possible to do so, return a vector consisting of one element, which is the string "NO".
 *
 * @author sudhir on 23-Apr-2020
 */
public class OrderedSubstrings {
    static class Pair {
        String str;
        int length;

        public Pair(String str, int length) {
            this.str = str;
            this.length = length;
        }
    }

    public ArrayList<String> solve(ArrayList<String> A) {
        int n = A.size();
        Pair pairs[] = new Pair[n];
        // store pairs containing given string and its length
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(A.get(i), A.get(i).length());
        }
        // sort the array of pairs based on the length of the strings
        Arrays.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.valueOf(o1.length).compareTo(Integer.valueOf(o2.length));
            }
        });
        for (int i = 1; i < n; i++) {
            // check if string in pairs[i-1] is substring of string in pairs[i] using Z-algorithm
            // At any point, if the method isSubstring() returns false, we return "NO" as the answer
            if (!isSubstring(pairs[i].str, pairs[i - 1].str)) {
                return new ArrayList<>(Arrays.asList("NO"));
            }
        }
        ArrayList<String> result = new ArrayList<>();
        for (Pair p : pairs) {
            result.add(p.str);
        }
        return result;
    }

    // check if string b is a substring of string a or not
    private boolean isSubstring(String text, String pattern) {
        String concat = pattern + "$" + text;
        int l = concat.length();
        int Z[] = new int[l];
        // construct z-array
        getZarr(concat, Z);
        for (int i = 0; i < l; ++i) {
            if (Z[i] == pattern.length()) {
                return true;
            }
        }
        return false;
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
        OrderedSubstrings o = new OrderedSubstrings();
        String[] strings1 = {"abc", "abcd", "a", "abc"};
        String[] strings2 = {"xyz", "xz", "xyzzy"};
        System.out.println(o.solve(new ArrayList<>(Arrays.asList(strings1))));
        System.out.println(o.solve(new ArrayList<>(Arrays.asList(strings2))));
    }
}

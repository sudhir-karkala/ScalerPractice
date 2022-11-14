package com.scaler.companyquestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * In an alien language, surprisingly they also use english lowercase letters,
 * but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 * <p>
 * Given an array of words A of size N written in the alien language, and the order of the
 * alphabet denoted by string B of size 26, return 1 if and only if the given words
 * are sorted lexicographically in this alien language else return 0.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N, length of each word <= 10^5
 * <p>
 * Sum of length of all words <= 2 * 10^6
 * <p>
 * Input Format
 * <p>
 * First argument is a string array A of size N.
 * <p>
 * Second argument is a string B of size 26 denoting the order.
 * <p>
 * Output Format
 * <p>
 * Return 1 if and only if the given words are sorted lexicographically in this alien language
 * else return 0.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = ["hello", "scaler", "interviewbit"]
 * <p>
 * B = "adhbcfegskjlponmirqtxwuvzy"
 * <p>
 * Input 2:
 * <p>
 * A = ["fine", "none", "no"]
 * <p>
 * B = "qwertyuiopasdfghjklzxcvbnm"
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 1
 * <p>
 * Output 2:
 * <p>
 * 0
 * <p>
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * The order shown in string B is: h < s < i for the given words. So return 1.
 * <p>
 * Explanation 2:
 * <p>
 * "none" should be present after "no". Return 0.
 *
 * @author sudhir on 06-Oct-2020
 */
public class IsDictionary {
    public int solve(ArrayList<String> A, String B) {
        Map<Character, Integer> charMap = new HashMap<>();
        int len = B.length();
        if (len == 0) {
            return 0;
        }
        for (int i = 0; i < len; i++) {
            charMap.put(B.charAt(i), i);
        }
        int size = A.size();
        int m = 0;
        int n = A.get(0).length();
        for (int i = 1; i < size; i++) {
            m = n;
            n = A.get(i).length();
            if (m > n) {
                return 0;
            }
            int k = 0;
            while (k < m && k < n) {
                while (k < m && k < n) {
                    if (A.get(i - 1).charAt(k) == A.get(i).charAt(k)) {
                        k++;
                    } else {
                        break;
                    }
                }
                if (k < m && k < n && A.get(i - 1).charAt(k) != A.get(i).charAt(k)) {
                    if (charMap.get(A.get(i - 1).charAt(k)) > charMap.get(A.get(i).charAt(k))) {
                        return 0;
                    } else {
                        break;
                    }
                }
                k++;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        IsDictionary isDictionary = new IsDictionary();
        ArrayList<String> a1 = new ArrayList<>(Arrays.asList("hello", "scaler", "interviewbit"));
        String b1 = "adhbcfegskjlponmirqtxwuvzy";
        System.out.println(isDictionary.solve(a1, b1));

        ArrayList<String> a2 = new ArrayList<>(Arrays.asList("fine", "none", "no"));
        String b2 = "qwertyuiopasdfghjklzxcvbnm";
        System.out.println(isDictionary.solve(a2, b2));
    }
}

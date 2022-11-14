package com.scaler.hashing;

/**
 * Given a string S, find if it is possible to re-order the characters of the string S
 * so that it can be represented as a concatenation of K similar strings.
 * <p>
 * Eg if S=aabb and K=2, then it is possible to re-arrange the string as "abab" which is a concatenation of
 * 2 similar strings "ab". If it is possible, return 1, else return -1.
 * </p>
 *
 * @author sudhir on 16-Mar-2020
 */
public class ReplicatingSubstring {
    public int solve(int A, String B) {
        int[] ch = new int[26];
        int n = B.length();
        for (int i = 0; i < n; i++) {
            ch[B.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (ch[i] % A != 0) {
                return -1;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        ReplicatingSubstring rs = new ReplicatingSubstring();
        int k1 = 2;
        String s1 = "bbaabb";
        int k2 = 2;
        String s2 = "bba";
        System.out.println(rs.solve(k1, s1));
        System.out.println(rs.solve(k2, s2));
    }
}

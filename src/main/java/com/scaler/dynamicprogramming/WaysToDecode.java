package com.scaler.dynamicprogramming;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * A->1, B->2,...Z->26<br/>
 * Given an encoded message containing digits, determine the total number of ways to decode it.<br/>
 * Input: A = "12", Output: 2<br/>
 * Given encoded message "12", it could be decoded as "AB" (1, 2) or "L" (12).
 * <p>
 * 1 <= length(A) <= 10^5
 *
 * @author sudhir on 26-Apr-2020
 */
public class WaysToDecode {
    public int numDecodings(String A) {
        int n = A.length();
        // ways[i] represents number of ways to decode considering (i - 1)th indexed character in the string.
        // Number of ways will include ways to decode considering (i - 1)th character as single character
        // and (i - 2)(i - 1)th character as a pair. so ways[i] = ways[i-1] + ways[i-2]
        int[] ways = new int[n + 1];
        ways[0] = 1;
        ways[1] = 1;
        if (A.charAt(0) == '0') {
            ways[1] = 0;
        }
        for (int i = 2; i <= n; i++) {
            // considering 2 character pair=> (i-2):(i-1)
            if (A.charAt(i - 2) != '0' && ((A.charAt(i - 2) == '1' || (A.charAt(i - 2) == '2') && A.charAt(i - 1) <= '6'))) {
                ways[i] += ways[i - 2];
            }
            // consider one character (i-1)
            if (A.charAt(i - 1) != '0') {
                ways[i] += ways[i - 1];
            }
        }
        return ways[n];
    }

    public static void main(String[] args) {
        WaysToDecode w = new WaysToDecode();
        String s1 = "12";
        String s2 = "2611055971756562";
        String s3 = "1504";
        System.out.println(w.numDecodings(s1));
        System.out.println(w.numDecodings(s2));
        System.out.println(w.numDecodings(s3));
    }
}

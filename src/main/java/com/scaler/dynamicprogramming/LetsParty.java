package com.scaler.dynamicprogramming;

/**
 * In Danceland, one person can party either alone or can pair up with another person.
 * Can you find in how many ways they can party if there are N people in Danceland?
 * <p>
 * 1 <= A <= 10^5
 *
 * @author sudhir on 25-Apr-2020
 */
public class LetsParty {
    public int solve(int A) {
        // if i party alone, i need to find number of ways for remaining i-1 people.
        // if i pair with someone, i can pair with someone in (i-1) ways. so remaining people will be (i-2).
        int mod = 10003;
        int[] ways = new int[A + 1];
        if (A == 0) {
            return 0;
        } else if (A == 1) {
            return 1;
        } else {
            ways[0] = 0;
            ways[1] = 1;
            ways[2] = 2;
            for (int i = 3; i <= A; i++) {
                ways[i] = (ways[i - 1] + (i - 1) * ways[i - 2]) % mod;
            }
        }
        return ways[A] % mod;
    }

    public static void main(String[] args) {
        LetsParty letsParty = new LetsParty();
        int n1 = 1;
        int n2 = 5;
        System.out.println(letsParty.solve(n1));
        System.out.println(letsParty.solve(n2));
    }
}

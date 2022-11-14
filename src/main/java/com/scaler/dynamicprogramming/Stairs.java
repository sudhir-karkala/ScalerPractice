package com.scaler.dynamicprogramming;

/**
 * You are climbing a stair case and it takes A steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * @author sudhir on 25-Apr-2020
 */
public class Stairs {
    public int climbStairs(int A) {
        int ways[] = new int[A + 1];
        ways[0] = 1;
        ways[1] = 1;
        for (int i = 2; i <= A; i++) {
            ways[i] = ways[i - 1] + ways[i - 2];
        }
        return ways[A];
    }

    public static void main(String[] args) {
        Stairs s = new Stairs();
        int n = 5;
        System.out.println(s.climbStairs(n));
    }
}

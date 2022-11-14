package com.scaler.strings;

/**
 * Given a string S, count minimum no. of swaps needed to regroup 0's and 1's. <br/>
 * After all swaps final string will look like all 0's followed by all 1's or all 1's followed by all 0's. <br/>
 * Swap operation swaps two adjacent characters (01 -> 10, 10 -> 01, 00 -> 00 and 11 -> 11).<br/>
 * Try to solve the problem using constant extra space. Expected time complexity is worst case O(length of S).<br/>
 * Examples: S: 000111 Answer: 0, S: 1110101 Answer: 3, Explanation: 1110101 -> 1111001 -> 1111010 -> 1111100
 *
 * @author sudhir on 23-Apr-2020
 */
public class Regroup0sAnd1sUsingMinimumSwaps {
    public int solve(final String A) {
        // there are two ways to rearrange the array.
        // one way is to move all 1's to the left side and other way is to move all 1's to the right side.
        // Perform these two ways and count the number of swaps needed and return the minimum of those two ways.
        // Note that this solution uses O(1) extra space.

        // way1: move 1's to the right
        int zerosTotheRight = 0;
        int n = A.length();
        int i = n - 1;
        int ansRight = 0;
        while (i >= 0) {
            if (A.charAt(i) == '0') {
                zerosTotheRight++;
            } else {
                ansRight += zerosTotheRight;
            }
            i--;
        }

        // way2: move 1's to the left
        i = 0;
        int zerosToTheLeft = 0;
        int ansLeft = 0;
        while (i < n) {
            if (A.charAt(i) == '0') {
                zerosToTheLeft++;
            } else {
                ansLeft += zerosToTheLeft;
            }
            i++;
        }
        // return minimum of two values
        return Math.min(ansRight, ansLeft);
    }

    public static void main(String[] args) {
        Regroup0sAnd1sUsingMinimumSwaps r = new Regroup0sAnd1sUsingMinimumSwaps();
        String s1 = "1110101";
        System.out.println(r.solve(s1));
    }
}

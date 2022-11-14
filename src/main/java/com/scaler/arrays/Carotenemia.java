package com.scaler.arrays;

/**
 * You are given an Array of boxes A, where each box consists of oranges. <br/>
 * You really love oranges, and you want to eat atleast B oranges. <br/>
 * You start from the 0th index of the array, and keep eating oranges until you eat B oranges. <br/>
 * If oranges from a box at ith index get depleted, you start eating from the (i+1)th box. <br/>
 * Determine index of the box where you finish eating B number of oranges. <br/>
 * If you don't eat B oranges even after eating from all the boxes, return -1.
 * <p>
 * 1 <= size(A) <= 10^5
 * <p>
 * 1 <= Integers in A <= 10^4
 * <p>
 * 1 <= B <= 10^9
 *
 * @author sudhir on 04-Apr-2020
 */
public class Carotenemia {
    public int solve(int[] A, int B) {
        int remaining = B;
        int i = 0;
        while (remaining > 0 && i < A.length) {
            remaining = remaining - A[i];
            i++;
        }
        if (remaining > 0) {
            return -1;
        }
        return (i - 1);
    }

    public static void main(String[] args) {
        Carotenemia c = new Carotenemia();
        int[] a1 = {1, 1, 1, 4};
        int b1 = 10;
        int[] a2 = {1, 3, 2, 4};
        int b2 = 5;
        System.out.println(c.solve(a1, b1));
        System.out.println(c.solve(a2, b2));
    }
}

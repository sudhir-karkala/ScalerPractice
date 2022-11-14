package com.scaler.contests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * There is a rectangular brick wall consisting of several rows of bricks.<br/>
 * The wall has A rows and length of each row is B units.<br/>
 * The bricks have the same height that can be considered as 1 unit but has different length. <br/>
 * You are given an integer array C denoting length of N bricks.The bricks are chosen one by one from the left of the array
 * and each row of the wall is build from left to right.<br/>
 * While building the wall, if the sum of length of bricks in a row is equal to B then start building another row again
 * from left to right. <br/>
 * Input is such that, you will end up building the wall consisting of A rows and length of each row will be equal to B. <br/>
 * You need to find a vertical line going from top to bottom of the wall that crossed through the fewest number of bricks.<br/>
 * Return the least number of bricks through which the vertical line crossed. <br/>
 * NOTE: If your line go through the edge of a brick, then the brick is not considered as crossed.<br/>
 * You cannot draw a vertical line just along one of the two vertical edges of the wall,
 * in which case the line will obviously cross no bricks.<br/>
 * 1 <= N <= 10^5 1 <= A x B <= 10^9 1 <= C[i] <= 10^9
 *
 * @author sudhir on 20-May-2020
 */
public class CrossTheWall {
    public int solve(int A, int B, ArrayList<Integer> C) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int size = C.size();
        for (int i = 0; i < size; i++) {
            sum += C.get(i);
            if (sum < B) {
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            } else if (sum == B) {
                sum = 0;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int num : map.values()) {
            max = Math.max(max, num);
        }
        return max == Integer.MIN_VALUE ? A : A - max;
    }

    public static void main(String[] args) {
        CrossTheWall c = new CrossTheWall();
        ArrayList<Integer> C1 = new ArrayList<>(Arrays.asList(3, 4, 2, 2, 3));
        int A1 = 2;
        int B1 = 7;
        ArrayList<Integer> C2 = new ArrayList<>(Arrays.asList(1, 2, 2, 5, 3, 2));
        int A2 = 3;
        int B2 = 5;
        System.out.println(c.solve(A1, B1, C1));
        System.out.println(c.solve(A2, B2, C2));
    }
}

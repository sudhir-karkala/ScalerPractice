package com.scaler.sorting;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers A of size N which denotes N cylindrical empty bottles. <br/>
 * The radius of the ith bottle is A[i]. You can put the ith bottle into the jth bottle if the following conditions are met:<br/>
 * ith bottle is not put into another bottle.<br/>
 * jth bottle doesn't contain any other bottle.<br/>
 * The radius of bottle i is smaller than bottle j (A[i] < A[j]).<br/>
 * You can put bottles into each other any number of times.
 * You want to MINIMIZE the number of visible bottles. A bottle is called visible if it is not put into any other bottle. <br/>
 * Find and return the minimum number of visible bottles.
 *
 * @author sudhir on 10-Apr-2020
 */
public class GameOfBottles {
    public int solve(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }
        int max = Integer.MIN_VALUE;
        for (Integer num : map.values()) {
            max = Math.max(max, num);
        }
        return max;
    }

    public static void main(String[] args) {
        GameOfBottles g = new GameOfBottles();
        int[] a = {1, 2, 3};
        System.out.println(g.solve(a));
    }
}

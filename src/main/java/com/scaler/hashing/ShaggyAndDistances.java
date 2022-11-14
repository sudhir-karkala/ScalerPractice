package com.scaler.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Shaggy has an array A consisting of N elements.
 * We call a pair of distinct indices in that array as a special pair if elements at that index in the array are equal.
 * Shaggy wants you to find a special pair such that distance between that pair is minimum.
 * Distance between two indices is defined as |i-j|. If there is no special pair in the array then return -1.
 *
 * @author sudhir on 27-Mar-2020
 */
public class ShaggyAndDistances {
    public int solve(int[] A) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.computeIfAbsent(A[i], z -> new ArrayList<>()).add(i);
        }
        int minDistance = Integer.MAX_VALUE;
        for (Integer num : map.keySet()) {
            if (map.get(num).size() > 1) {
                if (Math.abs(map.get(num).get(0) - map.get(num).get(1)) < minDistance) {
                    minDistance = Math.abs(map.get(num).get(0) - map.get(num).get(1));
                }
            }
        }
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    public static void main(String[] args) {
        ShaggyAndDistances s = new ShaggyAndDistances();
        int[] a1 = {7, 1, 3, 4, 1, 7};
        System.out.println(s.solve(a1));
    }
}

package com.scaler.maths;

import java.util.*;

/**
 * Given an array of integers A of size N containing GCD of every possible pair of elements of another array. <br/>
 * Find and return the original numbers which are used to calculate the GCD array in any order. <br/>
 * For example, if original numbers are {2, 8, 10} then the given array will be {2, 2, 2, 2, 8, 2, 2, 2, 10}.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 10000
 * <p>
 * 1 <= A[i] <= 10^9
 *
 * @author sudhir on 25-May-2020
 */
public class AllGCDPair {
    public ArrayList<Integer> solve(ArrayList<Integer> A) {
        // The size of the resultant array will be sqrt(given_array_length)
        ArrayList<Integer> result = new ArrayList<>();
        // sort in descending order.
        int size = A.size();
        // Largest element will always be one of the original numbers. Hence we sort in descending order and start with that number.
        Collections.sort(A, Collections.reverseOrder());
        // get the frequency counts of every element in the given array
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map.put(A.get(i), map.getOrDefault(A.get(i), 0) + 1);
        }
        // variable to keep track of size of the resultant array.
        // The final array will have resultSize = sqrt(given_array_length).
        int resultSize = 0;
        for (int i = 0; i < size; i++) {
            if (map.containsKey(A.get(i)) && map.get(A.get(i)) > 0) {
                // decrement the freq count
                map.put(A.get(i), map.get(A.get(i)) - 1);
                result.add(A.get(i));
                resultSize++;
                // compute GCD of the elements taken in the previous steps with the current element starting from the greatest
                // and discard the GCD value from the given array by decrementing the freq count by 2.
                for (int j = 0; j < resultSize; j++) {
                    if (i != j) {
                        int x = gcd(result.get(j), A.get(i));
                        // decrement the freq of the gcd element by 2 because number of possible pairs are 2 with 2 numbers
                        map.put(x, map.get(x) - 2);
                    }
                }
            }
        }
        return result;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        AllGCDPair allGCDPair = new AllGCDPair();
        ArrayList<Integer> a1 = new ArrayList<>(Arrays.asList(2, 2, 2, 2, 8, 2, 2, 2, 10));
        ArrayList<Integer> a2 = new ArrayList<>(Arrays.asList(5, 5, 5, 15));
        ArrayList<Integer> a3 = new ArrayList<>(Arrays.asList(1, 31, 1, 1, 1, 1, 14, 2, 1, 1, 1, 2, 22, 1, 11,
                1, 1, 1, 1, 23, 1, 1, 11, 1, 11));
        System.out.println(allGCDPair.solve(a1));
        System.out.println(allGCDPair.solve(a2));
        System.out.println(allGCDPair.solve(a3));
    }
}

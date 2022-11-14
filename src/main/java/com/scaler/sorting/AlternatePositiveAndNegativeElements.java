package com.scaler.sorting;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an array of integers A, arrange them in an alternate fashion such that
 * every non-negative number is followed by negative and vice-versa, starting from a negative number,
 * maintaining the order of appearance. <br/>
 * The number of non-negative and negative numbers need not be equal. <br/>
 * If there are more non-negative numbers they appear at the end of the array. <br/>
 * If there are more negative numbers, they too appear at the end of the array. <br/>
 * Note: Try solving with O(1) extra space.
 *
 * @author sudhir on 10-Apr-2020
 */
public class AlternatePositiveAndNegativeElements {
    // This approach uses O(n) space to store positive and negative elements and then place them in the result array
    // in alternating fashion.
    public ArrayList<Integer> solve(ArrayList<Integer> A) {
        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();
        int size = A.size();
        for (int i = 0; i < size; i++) {
            if (A.get(i) < 0) {
                negative.add(A.get(i));
            } else {
                positive.add(A.get(i));
            }
        }
        int m = negative.size();
        int n = positive.size();
        int i = 0, j = 0;
        ArrayList<Integer> result = new ArrayList<>();
        while (i < m && j < n) {
            result.add(negative.get(i++));
            result.add(positive.get(j++));
        }
        while (i < m) {
            result.add(negative.get(i++));
        }
        while (j < n) {
            result.add(positive.get(j++));
        }
        return result;
    }

    public static void main(String[] args) {
        AlternatePositiveAndNegativeElements a = new AlternatePositiveAndNegativeElements();
        ArrayList<Integer> a1 = new ArrayList<>(Arrays.asList(-1, -2, -3, 4, 5));
        ArrayList<Integer> a2 = new ArrayList<>(Arrays.asList(5, -17, -100, -11));
        System.out.println(a.solve(a1));
        System.out.println(a.solve(a2));
    }
}

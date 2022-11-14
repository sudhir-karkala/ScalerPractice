package com.scaler.arrays;

import java.util.ArrayList;

/**
 * You are given an array of N integers, A1, A2, .... AN.<br/>
 * Return the maximum value of f(i, j) for all 1 ? i, j ? N. <br/>
 * f(i, j) is defined as |A[i] - A[j]| + |i - j|, where |x| denotes absolute value of x.
 *
 * @author sudhir on 04-Apr-2020
 */
public class MaximumAbsoluteDifference {
    public int maxArr(ArrayList<Integer> A) {
        int size = A.size();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sum1[] = new int[size];
        int sum2[] = new int[size];
        for (int i = 0; i < size; i++) {
            sum1[i] = A.get(i) + i;
            sum2[i] = A.get(i) - i;
        }
        for (int i = 0; i < size; i++) {
            if (max < sum1[i]) {
                max = sum1[i];
            }
            if (min > sum1[i]) {
                min = sum1[i];
            }
        }
        int result1 = max - min;
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            if (max < sum2[i]) {
                max = sum2[i];
            }
            if (min > sum2[i]) {
                min = sum2[i];
            }
        }
        int result2 = max - min;
        return Math.max(result1, result2);
    }

    public static void main(String[] args) {
        MaximumAbsoluteDifference m = new MaximumAbsoluteDifference();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(-1);
        System.out.println(m.maxArr(list));
    }
}

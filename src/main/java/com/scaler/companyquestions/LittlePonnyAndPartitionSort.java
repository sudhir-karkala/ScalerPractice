package com.scaler.companyquestions;

/**
 * You are given an array of integers A.
 * <p>
 * You need to divide it in the maximum number of partitions such that after sorting each partition individually,
 * The array becomes sorted.
 * <p>
 * Return the maximum number of partitions in which you can divide the array.
 * <p>
 * <p>
 * <p>
 * Problem Constraints
 * 1 <= |A| <= 100000
 * <p>
 * Each element of the array is between 1 and 109 (inclusive)
 *
 * @author sudhir on 01-Aug-2020
 */
public class LittlePonnyAndPartitionSort {
    public int solve(int[] A) {
        int n = A.length;
        int[] suffix = new int[n];
        suffix[n - 1] = A[n - 1];
        // suffix array to maintain min element so far
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.min(suffix[i + 1], A[i]);
        }
        int i = 0;
        // This will hold number of partitions
        int partitions = 0;
        int curmax = Integer.MIN_VALUE;
        // To iterate over all the elements. every iteration of this loop represents the start of a new partition
        while (i < n) {
            curmax = Math.max(curmax, A[i]);
            i++;
            // if the max in the current partition(curmax) is greater than the min of rest of the elements(suffix[i]),
            // then the current element is included in the current partition, so we just increment variable i.
            // else the loop breaks and we increment the partition count.
            // Note that curmax is updated in every iteration.
            while (i < n && curmax > suffix[i]) {
                curmax = Math.max(curmax, A[i]);
                i++;
            }
            partitions++;
        }
        return partitions;
    }

    public static void main(String[] args) {
        LittlePonnyAndPartitionSort l = new LittlePonnyAndPartitionSort();
        int[] a1 = {3, 4, 1, 2, 5, 7, 6};
        int[] a2 = {3, 2, 2};
        int[] a3 = {2, 0, 1, 2};
        int[] a4 = {6, 4, 5, 4, 8, 10, 8, 8, 11, 11, 12, 12, 12, 15, 17};
        int[] a5 = {5, 1, 1, 8, 1, 6, 5, 9, 7, 8};
        System.out.println(l.solve(a1));
        System.out.println(l.solve(a2));
        System.out.println(l.solve(a3));
        System.out.println(l.solve(a4));
        System.out.println(l.solve(a5));
    }
}

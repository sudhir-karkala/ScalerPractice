package com.scaler.sorting;

/**
 * Given an array of integers A of size N that is a permutation of [0, 1, 2, ..., (N-1)],
 * if we split the array into some number of "chunks" (partitions), and individually sort each chunk. <br/>
 * After concatenating them, the result equals the sorted array. What is the most number of chunks we could have made?
 *
 * @author sudhir on 10-Apr-2020
 */
public class MaxChunksToMakeSorted {
    /**
     * This is the simplest way to solve this problem. Check another method to solve this problem.
     *
     * @param A
     * @return chunks
     * @see com.leetcode.arrays.MaxChunksToMakeSorted
     */
    public int solve(int[] A) {
        int max = Integer.MIN_VALUE;
        int chunks = 0;
        for (int i = 0; i < A.length; i++) {
            max = Math.max(max, A[i]);
            // if max becomes equal to the current index i, it means the elements encountered so far
            // were less than this max.
            // And these will be part of current chunk. so we increment chunks count.
            if (max == i) {
                chunks++;
            }
        }
        return chunks;
    }

    public static void main(String[] args) {
        MaxChunksToMakeSorted m = new MaxChunksToMakeSorted();
        int[] a1 = {1, 2, 3, 4, 0};
        int[] a2 = {2, 0, 1, 3};
        System.out.println(m.solve(a1));
        System.out.println(m.solve(a2));
    }
}

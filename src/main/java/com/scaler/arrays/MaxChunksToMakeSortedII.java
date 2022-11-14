package com.scaler.arrays;

/**
 * Given an array of integers (not necessarily distinct) A,
 * if we split the array into some number of "chunks" (partitions), and individually sort each chunk. <br/>
 * After concatenating them, the result equals the sorted array. <br/>
 * What is the most number of chunks we could have made?
 *
 * @author sudhir on 04-Apr-2020
 */
public class MaxChunksToMakeSortedII {
    /**
     * This method uses a single array suffix[] to maintain min obtained so far from the right
     *
     * @param A
     * @return chunks
     */
    public int solve(int[] A) {
        int n = A.length;
        int[] suffix = new int[n];
        suffix[n - 1] = A[n - 1];
        // suffix array to maintain min element so far
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.min(suffix[i + 1], A[i]);
        }
        int i = 0;
        // This will hold number of chunks
        int chunks = 0;
        int curmax = Integer.MIN_VALUE;
        // To iterate over all the elements. every iteration of this loop represents the start of a new chunk
        while (i < n) {
            curmax = Math.max(curmax, A[i]);
            i++;
            // if the max in the current chunk(curmax) is greater than the min of rest of the elements(suffix[i]),
            // then the current element is included in the current chunk, so we just increment variable i.
            // else the loop breaks and we increment the chunks count.
            // Note that curmax is updated in every iteration.
            while (i < n && curmax > suffix[i]) {
                curmax = Math.max(curmax, A[i]);
                i++;
            }
            chunks++;
        }
        return chunks;
    }

    /**
     * This method uses two arrays:
     * left[] to maintain max obtained so far from the left
     * right[] to maintain min obtained so far from the right
     *
     * @param A
     * @return chunks
     */
    public int solve2(int[] A) {
        int[] left = new int[A.length];
        int[] right = new int[A.length];
        left[0] = A[0];
        right[A.length - 1] = A[A.length - 1];
        for (int i = 1; i < A.length; i++) {
            left[i] = Math.max(left[i - 1], A[i]);
        }
        for (int i = A.length - 2; i >= 0; i--) {
            right[i] = Math.min(right[i + 1], A[i]);
        }
        int chunks = 0;
        for (int i = 0; i < A.length; i++) {
            int l = left[i];
            int r = Integer.MAX_VALUE;
            if ((i + 1) < A.length) {
                r = right[i + 1];
            }
            if (l <= r) {
                chunks++;
            }
        }
        return chunks;
    }

    public static void main(String[] args) {
        MaxChunksToMakeSortedII m = new MaxChunksToMakeSortedII();
        int[] a1 = {2, 0, 1, 2};
        int[] a2 = {6, 4, 5, 4, 8, 10, 8, 8, 11, 11, 12, 12, 12, 15, 17};
        int[] a3 = {5, 1, 1, 8, 1, 6, 5, 9, 7, 8};
        System.out.println(m.solve(a1));
        System.out.println(m.solve(a2));
        System.out.println(m.solve(a3));
        System.out.println(m.solve2(a1));
        System.out.println(m.solve2(a2));
        System.out.println(m.solve2(a3));
    }
}

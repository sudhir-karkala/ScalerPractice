package com.scaler.contests;

import java.util.Arrays;

/**
 * Given an integer array A of size N. In the array, you can swap (A[i],A[j]) if the following conditions are satisfied:<br/>
 * i < j, i is a divisor of j<br/>
 * You are also provided M independent queries. In each query, you are given two integers idx and K. <br/>
 * For each query, you are required to output the maximum number you can retrieve at index idx after performing at most K swaps. <br/>
 * NOTE 1: Queries are given by a 2-D integer array B of size M * 2 where B[i][0] denotes idx and B[i][1] denotes K. <br/>
 * NOTE 2: Consider array A as 1-based indexed.
 *
 * @author sudhir on 22-Apr-2020
 */
public class SwapElements {
    public int[] solve(int[] A, int[][] B) {
        // Assume that we are talking in terms of 1-based indexing
        // We can observe that if k>=2 then the maximum element of the array is the answer for any index.
        // Since in one swap we can send the maximum element to index 1 as 1 divides all other indices(1,2,...etc)
        // and in another swap to the desired index asked in query.
        // Also, for k=0, we cannot do any swap so the element at that index will be the answer.
        // Now, we have to find out the answer when k=1.
        // We can think that if we are at any ith index then we can swap it with any divisor of i or with any multiple of i.
        // We can do that in O(N sqrt(N)).
        int n = A.length;
        int[] val = new int[n]; // val[] holds the answer for k=1 case
        int maxval = 0;
        for (int i = n - 1; i >= 0; i--) {
            maxval = Math.max(maxval, A[i]);
            for (int j = 1; (j * j) <= (i + 1); j++) {
                if ((i + 1) % j == 0) {
                    // swapping with divisor of i which is jth index(in the array we point to (j-1)th index).
                    val[j - 1] = Math.max(val[j - 1], A[i]);
                    val[i] = Math.max(val[i], A[j - 1]);
                    // swapping with another divisor of i which is (i/j).
                    // Here, in the array it is denoted by the variable temp = (i + 1) / j.
                    if ((i + 1) / j != j) {
                        int temp = (i + 1) / j;
                        val[temp - 1] = Math.max(val[temp - 1], A[i]);
                        val[i] = Math.max(val[i], A[temp - 1]);
                    }
                }
            }
        }
        int[] ans = new int[B.length];
        for (int i = 0; i < B.length; i++) {
            int idx = B[i][0];
            int k = B[i][1];
            if (k >= 2) {
                ans[i] = maxval;
            } else if (k == 0) {
                ans[i] = A[idx - 1];
            } else { // case: k=1
                ans[i] = val[idx - 1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        SwapElements s = new SwapElements();
        int[] a = {2, 4, 6, 3, 2};
        int[][] b = {{5, 1}, {4, 1}, {4, 0}};
        System.out.println(Arrays.toString(s.solve(a, b)));
    }
}

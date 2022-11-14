package com.scaler.heaps;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * You are given an array A that has N integers. <br/>
 * You have to find the product of the 3 largest integers in array A from range 1 to i,
 * where i goes from 1 to N.<br/>
 * Return an array where the integer at index i of the array is the product of the largest
 * 3 integers in range 1 to i in array A. <br/>
 * If i<3, then the integer at index i is -1.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 10^5
 * <p>
 * 0 <= A[i] <= 10^3
 * <p>
 * Input Format
 * <p>
 * First and only argument is an integer array A.
 * <p>
 * Output Format
 * <p>
 * Return an integer array B. B[i] denotes the product of the largest 3 integers in range 1 to i in array A.
 *
 * @author sudhir on 16-Apr-2020
 */
public class ProductOf3 {
    public int[] solve(int[] A) {
        int n = A.length;
        int[] result = new int[n];
        if (n >= 1) {
            result[0] = -1;
        }
        if (n >= 2) {
            result[1] = -1;
        }
        if (n >= 3) {
            result[2] = A[0] * A[1] * A[2];
        }
        if (n <= 3) {
            return result;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int i;
        minHeap.add(A[0]);
        minHeap.add(A[1]);
        minHeap.add(A[2]);
        maxHeap.add(A[0]);
        maxHeap.add(A[1]);
        maxHeap.add(A[2]);
        for (i = 3; i < n; i++) {
            maxHeap.add(A[i]);
            minHeap.add(A[i]);
            int max1 = maxHeap.poll();
            int max2 = maxHeap.poll();
            int max3 = maxHeap.poll();
            int min1 = minHeap.poll();
            int min2 = minHeap.poll();
            result[i] = Math.max(max1 * max2 * max3, max1 * min1 * min2);
            maxHeap.add(max1);
            maxHeap.add(max2);
            maxHeap.add(max3);
            minHeap.add(min1);
            minHeap.add(min2);
        }
        return result;
    }

    public static void main(String[] args) {
        ProductOf3 p = new ProductOf3();
        int[] a = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(p.solve(a)));
    }
}

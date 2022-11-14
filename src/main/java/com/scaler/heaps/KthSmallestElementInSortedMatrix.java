package com.scaler.heaps;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Given a sorted matrix of integers A of size N x M and an integer B. <br/>
 * Each of the rows and columns of matrix A are sorted in ascending order,
 * find the Bth smallest element in the matrix.
 * <p>
 * NOTE: Return The Bth smallest element in the sorted order, not the Bth distinct element.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N, M <= 500
 * <p>
 * 1 <= A[i] <= 10^9
 * <p>
 * 1 <= B <= N * M
 * <p>
 * Input Format
 * <p>
 * The first argument given is the integer matrix A.
 * <p>
 * The second argument given is an integer B.
 * <p>
 * Output Format
 * <p>
 * Return the B-th smallest element in the matrix.
 *
 * @author sudhir on 16-Apr-2020
 */
public class KthSmallestElementInSortedMatrix {
    public int solve(int[][] A, int B) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                //if heap contains less than B elements, then add incoming element
                if (maxHeap.size() < B) {
                    maxHeap.add(A[i][j]);
                } else {
                    //add incoming element only if it is less than the peek() element of maxheap
                    if (A[i][j] < maxHeap.peek()) {
                        maxHeap.poll();
                        maxHeap.add(A[i][j]);
                    }
                }
            }
        }
        return maxHeap.poll();
    }

    public static void main(String[] args) {
        KthSmallestElementInSortedMatrix k = new KthSmallestElementInSortedMatrix();
        int[][] a1 = {{9, 11, 15}, {10, 15, 17}};
        int b1 = 6;
        int[][] a2 = {{5, 9, 11}, {9, 11, 13}, {10, 12, 15}, {13, 14, 16}, {16, 20, 21}};
        int b2 = 12;
        System.out.println(k.solve(a1, b1));
        System.out.println(k.solve(a2, b2));
    }
}

package com.scaler.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Given an array of integers A denoting a stream of integers.
 * New arrays of integer B and C are formed.
 * Each time an integer is encountered in a stream, append it at the end of B and
 * append median of array B at the C.
 * <p>
 * Find and return the array C.
 * <p>
 * NOTE:
 * <p>
 * If the number of elements are N in B and N is odd then consider median as B[N/2]
 * (B must be in sorted order).
 * <p>
 * If the number of elements are N in B and N is even then consider median as B[N/2-1].
 * (B must be in sorted order).
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of the array <= 100000
 * <p>
 * 1 <= A[i] <= 10^9
 * <p>
 * Input Format
 * <p>
 * The only argument given is the integer array A.
 * <p>
 * Output Format
 * <p>
 * Return an integer array C, C[i] denotes the median of first i elements.
 *
 * @author sudhir on 22-May-2020
 */
public class RunningMedian {
    public ArrayList<Integer> solve(ArrayList<Integer> A) {
        // The peek() element of the maxHeap is always the median.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        ArrayList<Integer> result = new ArrayList<>();
        int n = A.size();
        for (int i = 0; i < n; i++) {
            // insert first element into maxHeap
            if (maxHeap.isEmpty() && minHeap.isEmpty()) {
                maxHeap.offer(A.get(i));
                result.add(A.get(i));
                continue;
            } else if (A.get(i) > maxHeap.peek()) {
                minHeap.offer(A.get(i));
            } else {
                maxHeap.offer(A.get(i));
            }
            // balance the heaps if needed
            int size1 = maxHeap.size();
            int size2 = minHeap.size();
            // maxHeap can contain minHeap.size() + 1 elements at maximum
            if (size1 > size2 + 1) {// minHeap has less elements. so move elements from maxHeap to minHeap.
                while (size1 > size2 + 1) {
                    minHeap.offer(maxHeap.poll());
                    size1--;
                    size2++;
                }
            } else if (size2 > size1) {// minHeap has more elements. so move elements from minHeap to maxHeap.
                // Size of minHeap can't exceed size of maxHeap.
                while (size2 > size1) {
                    maxHeap.offer(minHeap.poll());
                    size2--;
                    size1++;
                }
            }
            result.add(maxHeap.peek());
        }
        return result;
    }

    public static void main(String[] args) {
        RunningMedian r = new RunningMedian();
        ArrayList<Integer> a1 = new ArrayList<>(Arrays.asList(1, 2, 5, 4, 3));
        ArrayList<Integer> a2 = new ArrayList<>(Arrays.asList(5, 17, 100, 11));
        System.out.println(r.solve(a1));
        System.out.println(r.solve(a2));
    }
}

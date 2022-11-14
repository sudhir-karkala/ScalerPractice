package com.scaler.heaps;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * You are given an array A containing N numbers. This array is called special
 * if it satisfies one of the following properties:
 * <p>
 * There exists an element A[i] in the array such that A[i] is equal to the
 * median of elements [A[0], A[1], ...., A[i-1]]
 * There exists an element A[i] in the array such that A[i] is equal to the
 * median of elements [A[i+1], A[i+2], ...., A[N-1]]
 * Median is the middle element in the sorted list of elements.
 * If the number of elements are even then median will be (sum of both middle elements)/2.
 * <p>
 * Return 1 if the array is special else return 0.
 * <p>
 * NOTE:
 * <p>
 * For A[0] consider only the median of elements [A[1], A[2], ..., A[N-1]]
 * (as there are no elements to the left of it)
 * For A[N-1] consider only the median of elements [A[0], A[1], ...., A[N-2]]
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 1000000.
 * <p>
 * A[i] is in the range of a signed 32-bit integer.
 * <p>
 * Input Format
 * <p>
 * First and only argument is an integer array A.
 * <p>
 * Output Format
 * <p>
 * Return 1 if the given array is special else return 0.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [4, 6, 8, 4]
 * <p>
 * Input 2:
 * <p>
 * A = [2, 7, 3, 1]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 1
 * <p>
 * Output 2:
 * <p>
 * 0
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Here, 6 is equal to the median of [8, 4].
 * <p>
 * Explanation 2:
 * <p>
 * No element satisfies any condition.
 *
 * @author sudhir on 28-Nov-2020
 */
public class SpecialMedian {
    private PriorityQueue<Long> minHeap = new PriorityQueue<>();
    private PriorityQueue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    private int minHeapSize = 0;
    private int maxHeapSize = 0;

    private void init() {
        minHeap.clear();
        maxHeap.clear();
        maxHeapSize = 0;
        minHeapSize = 0;
    }

    private void addToHeap(long num) {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            maxHeap.offer(num);
            maxHeapSize++;
        } else if (num > maxHeap.peek()) {
            minHeap.offer(num);
            minHeapSize++;
        } else {
            maxHeap.offer(num);
            maxHeapSize++;
        }
        while (maxHeapSize > minHeapSize + 1) {
            minHeap.offer(maxHeap.poll());
            maxHeapSize--;
            minHeapSize++;
        }
        while (maxHeapSize < minHeapSize) {
            maxHeap.offer(minHeap.poll());
            maxHeapSize++;
            minHeapSize--;
        }
    }

    private long findMedian() {
        int totalSize = maxHeapSize + minHeapSize;
        if (totalSize % 2 == 0) {
            // even size
            if ((maxHeap.peek() + minHeap.peek()) % 2 == 0) {
                return (maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                // since median is a fraction, it's of no use as it wont be present
                // in the array. so return Long.MAX_VALUE
                return Long.MAX_VALUE;
            }
        } else {
            return maxHeap.peek();
        }
    }

    public int solve(int[] A) {
        int n = A.length;
        init();
        for (int i = 0; i < n - 1; i++) {
            // keep adding numbers to maxHeap/minHeap and check if A[i+1] becomes
            // median of numbers added so far i.e. from A[0] to A[i]. If yes, then return 1.
            addToHeap(A[i]);
            if (A[i + 1] == findMedian()) {
                return 1;
            }
        }
        init();
        for (int i = n - 1; i > 0; i--) {
            // keep adding numbers to maxHeap/minHeap and check if A[i-1] becomes
            // median of numbers added so far i.e. from A[i] to A[n-1]. If yes, then return 1.
            addToHeap(A[i]);
            if (A[i - 1] == findMedian()) {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        SpecialMedian s = new SpecialMedian();
        int[] a1 = {4, 6, 8, 4};
        int[] a2 = {2, 7, 3, 1};
        int[] a3 = {2147483647, -2147483648, 0};
        System.out.println(s.solve(a1));
        System.out.println(s.solve(a2));
        System.out.println(s.solve(a3));
    }
}

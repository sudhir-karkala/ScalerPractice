package com.scaler.heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an integer array B of size N.
 * <p>
 * You need to find the Ath largest element in the subarray [1 to i] where i varies from 1 to N.
 * In other words, find the Ath largest element in the sub-arrays [1 : 1], [1 : 2], [1 : 3], ...., [1 : N].
 * <p>
 * NOTE: If any subarray [1 : i] has less than A elements then output array should be -1 at the ith index.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 100000
 * <p>
 * 1 <= A <= N
 * <p>
 * 1 <= B[i] <= INT_MAX
 * <p>
 * <p>
 * <p>
 * Input Format
 * <p>
 * First argument is an integer A.
 * <p>
 * Second argument is an integer array B of size N.
 * <p>
 * Output Format
 * <p>
 * Return an integer array C, where C[i] (1 <= i <= N) will have the Ath largest element
 * in the subarray [1 : i].
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = 4, B = [1 2 3 4 5 6]
 * Input 2:
 * <p>
 * A = 2, B = [15, 20, 99, 1]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * [-1, -1, -1, 1, 2, 3]
 * <p>
 * Output 2:
 * <p>
 * [-1, 15, 20, 20]
 * <p>
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * for i <= 3 output should be -1.
 * <p>
 * for i = 4 , Subarray [1:4] has 4 elements 1, 2, 3 and 4. So, 4th maximum element is 1.
 * <p>
 * for i = 5 , Subarray [1:5] has 5 elements 1, 2, 3, 4 and 5. So, 4th maximum element is 2.
 * <p>
 * for i = 6 , Subarray [1:6] has 6 elements 1, 2, 3, 4, 5 and 6. So, 4th maximum element is 3.
 * <p>
 * So, output array is [-1, -1, -1, 1, 2, 3].
 * <p>
 * Explanation 2:
 * <p>
 * for i <= 1 output should be -1.
 * <p>
 * for i = 2 , Subarray [1:2] has 2 elements 15 and 20. So, 2th maximum element is 15.
 * <p>
 * for i = 3 , Subarray [1:3] has 3 elements 15, 20 and 99. So, 2th maximum element is 20.
 * <p>
 * for i = 4 , Subarray [1:4] has 4 elements 15, 20, 99 and 1. So, 2th maximum element is 20.
 * <p>
 * So, output array is [-1, 15, 20, 20].
 *
 * @author sudhir on 18-Apr-2020
 */
public class AthLargestElement {
    public int[] solve(int A, int[] B) {
        int[] result = new int[B.length];
        for (int i = 0; i < A - 1; i++) {
            result[i] = -1;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < A; i++) {
            minHeap.add(B[i]);
        }
        result[A - 1] = minHeap.peek();
        for (int i = A; i < B.length; i++) {
            if (minHeap.peek() < B[i]) {
                minHeap.poll();
                minHeap.add(B[i]);
            }
            result[i] = minHeap.peek();
        }
        return result;
    }

    public static void main(String[] args) {
        AthLargestElement athLargestElement = new AthLargestElement();
        int a1 = 4;
        int[] b1 = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(athLargestElement.solve(a1, b1)));
        int a2 = 2;
        int[] b2 = {15, 20, 99, 1};
        System.out.println(Arrays.toString(athLargestElement.solve(a2, b2)));
    }
}

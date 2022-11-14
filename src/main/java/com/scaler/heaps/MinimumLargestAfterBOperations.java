package com.scaler.heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array A of N numbers, you have to perform B operations. <br/>
 * In each operation, you have to pick any one of the N elements and add original value
 * (value stored at index before we did any operations) to it's current value. <br/>
 * You can choose any of the N elements in each operation. <br/>
 * Perform B operations in such a way that the largest element of the
 * modified array(after B operations) is minimised. <br/>
 * Find the minimum possible largest element after B operations.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 10^6
 * <p>
 * 0 <= B <= 10^5
 * <p>
 * -10^5 <= A[i] <= 10^5
 * <p>
 * Input Format
 * <p>
 * First argument is an integer array A.
 * <p>
 * Second argument is an integer B.
 * <p>
 * Output Format
 * <p>
 * Return an integer denoting the minimum possible largest element after B operations.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [1, 2, 3, 4]
 * <p>
 * B = 3
 * <p>
 * Input 2:
 * <p>
 * A = [5, 1, 4, 2]
 * <p>
 * B = 5
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 4
 * <p>
 * Output 2:
 * <p>
 * 5
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Apply operation on element at index 0, the array would change to [2, 2, 3, 4]
 * <p>
 * Apply operation on element at index 0, the array would change to [3, 2, 3, 4]
 * <p>
 * Apply operation on element at index 0, the array would change to [4, 2, 3, 4]
 * <p>
 * Minimum possible largest element after 3 operations is 4.
 * <p>
 * Explanation 2:
 * <p>
 * Apply operation on element at index 1, the array would change to [5, 2, 4, 2]
 * <p>
 * Apply operation on element at index 1, the array would change to [5, 3, 4, 2]
 * <p>
 * Apply operation on element at index 1, the array would change to [5, 4, 4, 2]
 * <p>
 * Apply operation on element at index 1, the array would change to [5, 5, 4, 2]
 * <p>
 * Apply operation on element at index 3, the array would change to [5, 5, 4, 4]
 * <p>
 * Minimum possible largest element after 5 operations is 5.
 *
 * @author sudhir on 18-Apr-2020
 */
public class MinimumLargestAfterBOperations {
    static class Pair {
        int value;
        int index;

        public Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public int solve(int[] A, int B) {
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.valueOf(o1.value).compareTo(Integer.valueOf(o2.value));
            }
        });
        int[] cur = Arrays.copyOf(A, A.length);
        // add the next state of every element to the heap
        for (int i = 0; i < A.length; i++) {
            priorityQueue.offer(new Pair(2 * A[i], i));
        }
        // do this for B times
        while (B > 0) {
            Pair p = priorityQueue.poll();
            int i = p.index;
            cur[i] += A[i];
            // push the next state of current element to the heap
            priorityQueue.offer(new Pair(cur[i] + A[i], i));
            B--;
        }

        // pick the max element in the array after B operations
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < cur.length; i++) {
            max = Math.max(max, cur[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        MinimumLargestAfterBOperations m = new MinimumLargestAfterBOperations();
        int[] a1 = {1, 2, 3, 4};
        int b1 = 3;
        int[] a2 = {5, 1, 4, 2};
        int b2 = 5;
        System.out.println(m.solve(a1, b1));
        System.out.println(m.solve(a2, b2));
    }
}

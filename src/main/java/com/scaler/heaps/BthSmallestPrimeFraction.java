package com.scaler.heaps;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * A sorted array of integers, A contains 1, plus some number of primes. <br/>
 * Then, for every p < q in the list, we consider the fraction p/q. <br/>
 * What is the B-th smallest fraction considered? Return your answer as an array of integers,
 * where answer[0] = p and answer[1] = q.
 * <p>
 * Problem Constraints
 * 1 <= length(A) <= 2000
 * <p>
 * 1 <= A[i] <= 30000
 * <p>
 * 1 <= B <= length(A)*(length(A) - 1)/2
 * <p>
 * Input Format
 * <p>
 * The first argument of input contains the integer array, A.
 * <p>
 * The second argument of input contains an integer B.
 * <p>
 * Output Format
 * <p>
 * Return an array of two integers, where answer[0] = p and answer[1] = q.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [1, 2, 3, 5]
 * <p>
 * B = 3
 * <p>
 * Input 2:
 * <p>
 * A = [1, 7]
 * <p>
 * B = 1
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * [2, 5]
 * Output 2:
 * <p>
 * [1, 7]
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * The fractions to be considered in sorted order are:
 * [1/5, 1/3, 2/5, 1/2, 3/5, 2/3]
 * <p>
 * The third fraction is 2/5.
 * <p>
 * Explanation 2:
 * <p>
 * The fractions to be considered in sorted order are:
 * [1/7]
 * <p>
 * The first fraction is 1/7.
 *
 * @author sudhir on 16-Apr-2020
 */
public class BthSmallestPrimeFraction {
    // Approach1: Using min heap
    public int[] solve(int[] A, int B) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                Double d1 = Double.valueOf(A[o1[0]] * 1.0 / A[o1[1]]);
                Double d2 = Double.valueOf(A[o2[0]] * 1.0 / A[o2[1]]);
                return d1.compareTo(d2);
            }
        });
        // initially we add fractions formed with numerator iterating over the array and denominator as the largest number
        for (int i = 0; i < A.length - 1; i++) {
            priorityQueue.offer(new int[]{i, A.length - 1});
        }
        // we keep polling element one by one replacing it with the next fraction formed.
        // The way we add the next fraction is in the increasing order
        // which when done B-1 times results in Bth smallest element in the top of the heap
        for (int i = 0; i < B - 1; i++) {
            int[] element = priorityQueue.poll();
            if (element[0] < element[1] - 1) {
                priorityQueue.offer(new int[]{element[0], element[1] - 1});
            }
        }
        int[] indices = priorityQueue.poll();
        return new int[]{A[indices[0]], A[indices[1]]};
    }

    // Approach2: Using max heap
    public int[] solve2(int[] A, int B) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                Double d1 = Double.valueOf(A[o1[0]] * 1.0 / A[o1[1]]);
                Double d2 = Double.valueOf(A[o2[0]] * 1.0 / A[o2[1]]);
                return d1.compareTo(d2);
            }
        });
        // Here we maintain top B elements in the heap. Hence, we add new element only when the incoming element
        // is less than the peek() element. So we replace the peek() element with the incoming element.
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (priorityQueue.size() < B) {
                    priorityQueue.offer(new int[]{i, j});
                } else {
                    int[] element = priorityQueue.peek();
                    Double d1 = Double.valueOf(A[element[0]] * 1.0 / A[element[1]]);
                    Double d2 = Double.valueOf(A[i] * 1.0 / A[j]);
                    if (d1.compareTo(d2) > 0) {
                        priorityQueue.poll();
                        priorityQueue.offer(new int[]{i, j});
                    }
                }

            }
        }
        int[] indices = priorityQueue.poll();
        return new int[]{A[indices[0]], A[indices[1]]};
    }

    // Approach 3: Using binary search(todo)

    public static void main(String[] args) {
        BthSmallestPrimeFraction b = new BthSmallestPrimeFraction();
        int[] a1 = {1, 2, 3, 5};
        int b1 = 3;
        System.out.println(b.solve(a1, b1));
    }
}

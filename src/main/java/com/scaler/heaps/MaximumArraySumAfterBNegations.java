package com.scaler.heaps;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Given an array of integers A and an integer B. <br/>
 * You must modify the array exactly B number of times. <br/>
 * In single modification we can replace any one array element A[i] by -A[i]. <br/>
 * You need to perform these modifications in such a way that after exactly B modifications,
 * sum of the array must be maximum.
 * <p>
 * Problem Constraints
 * 1 <= length of the array <= 5 * 10^5
 * 1 <= B <= 5 * 10^6
 * -100 <= A[i] <= 100
 * <p>
 * Input Format
 * <p>
 * First argument given is an integer array A.
 * <p>
 * Second argument given is an integer B.
 * <p>
 * Output Format
 * <p>
 * Return an integer denoting the maximum array sum after B modifications.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [24, -68, -29, -9, 84]
 * <p>
 * B = 4
 * <p>
 * Input 2:
 * <p>
 * A = [57, 3, -14, -87, 42, 38, 31, -7, -28, -61]
 * <p>
 * B = 10
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 196
 * Output 2:
 * <p>
 * 362
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Final array after B modifications = [24, 68, 29, -9, 84]
 * <p>
 * Explanation 2:
 * <p>
 * Final array after B modifications = [57, -3, 14, 87, 42, 38, 31, 7, 28, 61]
 *
 * @author sudhir on 16-Apr-2020
 */
public class MaximumArraySumAfterBNegations {
    //Approach1: Using priority queue
    public int solve(int[] A, int B) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (Integer num : A) {
            priorityQueue.add(num);
        }
        int op = 0;
        //as long as we have negative elements to choose from,
        // we directly change the sign to positive for all those numbers.
        while (priorityQueue.peek() < 0 && op < B) {
            priorityQueue.add(-1 * priorityQueue.poll());
            op++;
        }
        // now we just pick the min element from the heap and change the sign
        // if the number of remaining operations is odd, else we do nothing.
        // this is because if remaining operations is even, the number will retain the
        // original sign no matter how many times we repeat the same process.
        if ((B - op) % 2 == 1) {
            priorityQueue.add(-1 * priorityQueue.poll());
        }
        int sum = 0;
        while (priorityQueue.peek() != null) {
            sum += priorityQueue.poll();
        }
        return sum;
    }

    //Approach2: Using sorting approach
    public int solve2(int[] A, int B) {
        Arrays.sort(A);
        int op = 0;
        int i = 0;
        while (op < B && A[i] < 0) {
            A[i] = -A[i];
            i++;
            op++;
        }
        if ((B - op) > 0) {//we have pending operations to be performed.
            if ((B - op) % 2 == 1) {
                if (A[i] < A[i - 1]) {
                    A[i] = -A[i];
                } else {
                    A[i - 1] = -A[i - 1];
                }
            }
        }
        int sum = 0;
        for (i = 0; i < A.length; i++) {
            sum += A[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        MaximumArraySumAfterBNegations m = new MaximumArraySumAfterBNegations();
        int[] a1 = {24, -68, -29, -9, 84};
        int b1 = 4;
        int[] a2 = {57, 3, -14, -87, 42, 38, 31, -7, -28, -61};
        int b2 = 10;
        System.out.println(m.solve(a1, b1));
        System.out.println(m.solve(a2, b2));
        System.out.println(m.solve2(a1, b1));
        System.out.println(m.solve2(a2, b2));
    }
}

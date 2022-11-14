package com.scaler.queues;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array A of both positive and negative integers.
 * Your task is to compute sum of minimum and maximum elements of all sub-array of size B.<br/>
 * Note: Since the answer can be very large, you are required to return the sum module 1000000007
 *
 * @author sudhir on 11-May-2020
 */
public class SumOfMinimumAndMaximumInSubarraysOfSizeK {
    public int solve(int[] A, int B) {
        int MOD = 1000000007;
        // deque whose first element is the maximum in the current window
        Deque<Integer> dequeMaximum = new LinkedList<>();
        dequeMaximum.offer(0);
        int[] max = new int[A.length - B + 1];
        for (int i = 1; i < B; i++) {
            while (!dequeMaximum.isEmpty() && A[dequeMaximum.peekLast()] < A[i]) {
                dequeMaximum.pollLast();
            }
            dequeMaximum.offerLast(i);
        }
        max[0] = A[dequeMaximum.peekFirst()];

        for (int i = B; i < A.length; i++) {
            // check if the front element is part of the current window and remove if it's not
            if (!dequeMaximum.isEmpty() && dequeMaximum.peekFirst() <= (i - B)) {
                dequeMaximum.pollFirst();
            }
            // check if rear element is smaller than the incoming element and poll them accordingly.
            while (!dequeMaximum.isEmpty() && A[dequeMaximum.peekLast()] < A[i]) {
                dequeMaximum.pollLast();
            }
            dequeMaximum.offerLast(i);
            max[i - B + 1] = A[dequeMaximum.peekFirst()];
        }

        // deque whose first element is the minimum in the current window
        Deque<Integer> dequeMinimum = new LinkedList<>();
        dequeMinimum.offer(0);
        int[] min = new int[A.length - B + 1];
        for (int i = 1; i < B; i++) {
            while (!dequeMinimum.isEmpty() && A[dequeMinimum.peekLast()] > A[i]) {
                dequeMinimum.pollLast();
            }
            dequeMinimum.offerLast(i);
        }
        min[0] = A[dequeMinimum.peekFirst()];

        for (int i = B; i < A.length; i++) {
            // check if the front element is part of the current window and remove if it's not
            if (!dequeMinimum.isEmpty() && dequeMinimum.peekFirst() <= (i - B)) {
                dequeMinimum.pollFirst();
            }
            // check if rear element is greater than the incoming element and poll them accordingly.
            while (!dequeMinimum.isEmpty() && A[dequeMinimum.peekLast()] > A[i]) {
                dequeMinimum.pollLast();
            }
            dequeMinimum.offerLast(i);
            min[i - B + 1] = A[dequeMinimum.peekFirst()];
        }
        long sum = 0L;
        for (int i = 0; i < A.length - B + 1; i++) {
            int temp = max[i] + min[i];
            while (temp < 0) {
                temp += MOD;
            }
            sum += temp % MOD;
        }
        return (int) (sum % MOD);
    }

    public static void main(String[] args) {
        SumOfMinimumAndMaximumInSubarraysOfSizeK s = new SumOfMinimumAndMaximumInSubarraysOfSizeK();
        int[] nums = {2, 5, -1, 7, -3, -1, -2};
        int k = 4;
        System.out.println(s.solve(nums, k));
    }
}

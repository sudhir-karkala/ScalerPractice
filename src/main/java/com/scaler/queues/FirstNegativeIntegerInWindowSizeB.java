package com.scaler.queues;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an array of integers A of size N and an integer B. <br/>
 * Find the first negative integer for each and every window(contiguous subarray) of size B.<br/>
 * If a window does not contain a negative integer, then return 0 for that window.
 *
 * @author sudhir on 11-May-2020
 */
public class FirstNegativeIntegerInWindowSizeB {
    public int[] solve(int[] A, int B) {
        int[] result = new int[A.length - B + 1];
        Queue<Integer> queue = new LinkedList<>();
        // enqueuing first B elements into the queue(here elements refer to indices)
        for (int i = 0; i < B; i++) {
            queue.offer(i);
        }
        // we remove useless elements from the queue: useless are the ones for which A[index] >= 0
        while (!queue.isEmpty() && A[queue.peek()] >= 0) {
            queue.poll();
        }
        // if useful element is not found then set 0, else set the index accordingly
        if (queue.isEmpty()) {
            result[0] = 0;
        } else {
            result[0] = A[queue.peek()];
        }
        // repeat the same process for the rest of the elements
        for (int i = B; i < A.length; i++) {
            queue.offer(i);
            if (!queue.isEmpty() && queue.peek() <= (i - B)) {
                queue.poll();
            }

            while (!queue.isEmpty() && A[queue.peek()] >= 0) {
                queue.poll();
            }

            if (queue.isEmpty()) {
                result[i - B + 1] = 0;
            } else {
                result[i - B + 1] = A[queue.peek()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FirstNegativeIntegerInWindowSizeB f = new FirstNegativeIntegerInWindowSizeB();
        int[] A1 = {-1, 2, 3, -4, 5};
        int B1 = 2;
        int[] A2 = {-8, 2, 3, -6, 10};
        int B2 = 2;
        System.out.println(Arrays.toString(f.solve(A1, B1)));
        System.out.println(Arrays.toString(f.solve(A2, B2)));
    }
}

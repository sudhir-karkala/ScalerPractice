package com.scaler.queues;

import java.util.*;

/**
 * Given an array of integers A and an integer B. we need to reverse the order of the first B elements of the array,
 * leaving the other elements in the same relative order. <br/>
 * Note: You are required to first insert elements into an auxiliary queue then perform Reversal of first B elements.
 *
 * @author sudhir on 12-May-2020
 */
public class ReversingTheFirstBElementsOfQueue {
    public ArrayList<Integer> solve(ArrayList<Integer> A, int B) {
        Queue<Integer> queue = new LinkedList<>();
        queue.addAll(A);
        int count = B;
        Stack<Integer> st = new Stack<>();
        // push first B elements onto the stack
        while (count > 0) {
            st.push(queue.poll());
            count--;
        }
        // pop B elements from the stack and enqueue them into the queue
        while (!st.isEmpty()) {
            queue.offer(st.pop());
        }
        count = A.size() - B;
        // now dequeue (A.size() - B) elements from the queue and enqueue them onto the queue so that queue is reversed
        while (count > 0) {
            queue.offer(queue.poll());
            count--;
        }
        return new ArrayList<>(queue);
    }

    public static void main(String[] args) {
        ReversingTheFirstBElementsOfQueue r = new ReversingTheFirstBElementsOfQueue();
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int B = 3;
        System.out.println(r.solve(A, B));
    }
}

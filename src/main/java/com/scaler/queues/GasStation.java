package com.scaler.queues;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given two integer arrays A and B of size N. <br/>
 * There are N gas stations along a circular route, where the amount of gas at station i is A[i]. <br/>
 * You have a car with an unlimited gas tank and it costs B[i] of gas to travel from station i to its next station (i+1). <br/>
 * You begin the journey with an empty tank at one of the gas stations. <br/>
 * Return the minimum starting gas station's index if you can travel around the circuit once, otherwise return -1. <br/>
 * You can only travel in one direction. i to i+1, i+2, ... n-1, 0, 1, 2.. <br/>
 * Completing the circuit means starting at i and ending up at i again.<br/>
 * Return the minimum starting gas station's index if you can travel around the circuit once, otherwise return -1.
 *
 * @author sudhir on 13-May-2020
 */
public class GasStation {
    /**
     * This approach uses queue. Any time in the queue, queue's peek() points to the starting station index.
     * Run time in leetcode: 2 ms
     */
    public int canCompleteCircuit2(final int[] A, final int[] B) {
        int cursum = 0;
        Queue<Integer> queue = new LinkedList<>();
        int queueSize = 0;
        int i = 0;
        int j = 0;
        // The maximum index value variable i can reach is (2 * array_length - 1)
        // which can happen in the case where start station is the last station
        // which is of index (array_length - 1)
        while (i < 2 * A.length) {
            while (!queue.isEmpty() && cursum < 0) {
                int index = queue.poll();
                cursum -= (A[index] - B[index]);
                queueSize--;
            }
            cursum += (A[j] - B[j]);
            queueSize++;
            queue.offer(j);
            j = (i + 1) % A.length;
            if (queueSize == A.length) {
                break;
            }
            i++;
        }
        if (cursum >= 0 && queueSize == A.length) {
            return queue.peek();
        } else {
            return -1;
        }
    }

    /**
     * This approach uses two pointers start and end and no extra data structure.
     * Run time in leetcode: 0 ms
     */
    public int canCompleteCircuit(final int[] A, final int[] B) {
        int cursum = 0;
        int start = 0;
        int end = 0;
        int stationCount = 0;
        // when we get all the stations we break out of the loop and return the index of start station.
        while (stationCount < A.length) {
            cursum += (A[end] - B[end]);
            end = (end + 1) % A.length;
            stationCount++;
            while (cursum < 0) {
                cursum -= (A[start] - B[start]);
                start++;
                // if start reaches A.length, then it means no solution exists, so we can return -1.
                if (start == A.length) {
                    return -1;
                }
                stationCount--;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        GasStation g = new GasStation();
        int[] a1 = {1, 2};
        int[] b1 = {2, 1};
        int[] a2 = {1, 2, 3, 4, 5};
        int[] b2 = {3, 4, 5, 1, 2};
        int[] a3 = {4, 6, 7, 4};
        int[] b3 = {6, 5, 3, 5};
        int[] a4 = {2, 3, 4};
        int[] b4 = {3, 4, 3};
        int[] a5 = {5};
        int[] b5 = {4};
        System.out.println(g.canCompleteCircuit(a1, b1));
        System.out.println(g.canCompleteCircuit2(a1, b1));
        System.out.println(g.canCompleteCircuit(a2, b2));
        System.out.println(g.canCompleteCircuit2(a2, b2));
        System.out.println(g.canCompleteCircuit(a3, b3));
        System.out.println(g.canCompleteCircuit2(a3, b3));
        System.out.println(g.canCompleteCircuit(a4, b4));
        System.out.println(g.canCompleteCircuit2(a4, b4));
        System.out.println(g.canCompleteCircuit(a5, b5));
        System.out.println(g.canCompleteCircuit2(a5, b5));
    }
}

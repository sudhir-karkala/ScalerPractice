package com.scaler.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * The local ship renting service has a special rate plan:<br/>
 * It is up to a passenger to choose a ship.<br/>
 * If the chosen ship has X (X > 0) vacant places at the given moment, then the ticket for such a ship costs X.<br/>
 * The passengers buy tickets in turn, the first person in the queue goes first,
 * then goes the second one, and so on up to A-th person.<br/>
 * You need to tell the maximum and the minimum money that the ship company can earn if all A passengers buy tickets.<br/>
 * Problem Constraints
 * <p>
 * 1 ? A ? 3000, 1 ? B ? 1000, 1 ? C[i] ? 1000
 * <p>
 * It is guaranteed that there are at least A empty seats in total.
 *
 * @author sudhir on 8/6/20
 */
public class TheShipCompany {
    public ArrayList<Integer> solve(int A, int B, ArrayList<Integer> C) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap.addAll(C);
        maxHeap.addAll(C);
        int count = A;
        ArrayList<Integer> result = new ArrayList<>();
        int minCost = 0;
        int maxCost = 0;
        // To get the minimum money earned, the passenger has to choose the ship which has least number of empty seats.
        // so minHeap can be used for this purpose.
        while (count > 0) {
            if (!minHeap.isEmpty()) {
                int temp = minHeap.poll();
                minCost += temp;
                if (temp - 1 > 0) {
                    minHeap.offer(temp - 1);
                }
                count--;
            }
        }
        // To get the maximum money earned, the passenger has to choose the ship which has most number of empty seats.
        // so maxHeap can be used for this purpose.
        count = A;
        while (count > 0) {
            if (!maxHeap.isEmpty()) {
                int temp = maxHeap.poll();
                maxCost += temp;
                if (temp - 1 > 0) {
                    maxHeap.offer(temp - 1);
                }
                count--;
            }
        }
        result.add(maxCost);
        result.add(minCost);
        return result;
    }

    public static void main(String[] args) {
        TheShipCompany theShipCompany = new TheShipCompany();
        int a1 = 4;
        int b1 = 3;
        ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 1, 1));
        int a2 = 4;
        int b2 = 3;
        ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(2, 2, 2));
        System.out.println(theShipCompany.solve(a1, b1, c1));
        System.out.println(theShipCompany.solve(a2, b2, c2));
    }
}

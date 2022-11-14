package com.scaler.heaps;

import java.util.PriorityQueue;

/**
 * Given an array of integers A representing the length of ropes. <br/>
 * You need to connect these ropes into one rope. <br/>
 * The cost of connecting two ropes is equal to the sum of their lengths.<br/>
 * You need to connect the ropes with minimum cost. <br/>
 * Find and return the minimum cost to connect these ropes into one rope.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of the array <= 100000
 * <p>
 * 1 <= A[i] <= 1000
 * <p>
 * Input Format
 * <p>
 * The only argument given is the integer array A.
 * <p>
 * Output Format
 * <p>
 * Return an integer denoting the minimum cost to connect these ropes into one rope.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [1, 2, 3, 4, 5]
 * <p>
 * Input 2:
 * <p>
 * A = [5, 17, 100, 11]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 33
 * <p>
 * Output 2:
 * <p>
 * 182
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Given array A = [1, 2, 3, 4, 5].
 * <p>
 * Connect the ropes in the following manner:
 * 1 + 2 = 3
 * <p>
 * 3 + 3 = 6
 * <p>
 * 4 + 5 = 9
 * <p>
 * 6 + 9 = 15
 * <p>
 * So, total cost  to connect the ropes into one is 3 + 6 + 9 + 15 = 33.
 * <p>
 * Explanation 2:
 * <p>
 * Given array A = [5, 17, 100, 11].
 * <p>
 * Connect the ropes in the following manner:
 * <p>
 * 5 + 11 = 16
 * <p>
 * 16 + 17 = 33
 * <p>
 * 33 + 100 = 133
 * <p>
 * So, total cost  to connect the ropes into one is 16 + 33 + 133 = 182.
 *
 * @author sudhir on 16-Apr-2020
 */
public class ConnectRopesWithMinimumLength {
    public int solve(int[] A) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (Integer num : A) {
            priorityQueue.add(num);
        }
        int cost = 0;
        while (priorityQueue.size() > 1) {
            int rope1 = priorityQueue.poll();
            int rope2 = priorityQueue.poll();
            cost += rope1 + rope2;
            priorityQueue.add(rope1 + rope2);
        }
        return cost;
    }

    public static void main(String[] args) {
        ConnectRopesWithMinimumLength c = new ConnectRopesWithMinimumLength();
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {5, 17, 100, 11};
        System.out.println(c.solve(a));
        System.out.println(c.solve(b));
    }
}

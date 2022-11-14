package com.scaler.heaps;

import java.util.PriorityQueue;

/**
 * Misha loves eating candies.
 * She has given N boxes of Candies.
 * <p>
 * She decides, every time she will choose a box having the minimum number of candies,
 * eat half of the candies and put the remaining candies in the other box
 * that has the minimum number of candies.
 * <p>
 * Misha does not like a box if it has the number of candies greater than K so
 * she won't eat from that box.
 * <p>
 * Can you find how many candies she will eat?
 * <p>
 * Note 1: If a box has an odd number of candies then Misha will eat floor(odd/2)
 * <p>
 * Note 2: A same box will not be chosen again.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 10^5
 * <p>
 * 1 <= A[i] <= 10^5
 * <p>
 * 1 <= B <= 10^6
 * <p>
 * Input Format
 * <p>
 * The first argument is A an Array of Integers, where A[i] is the number of candies in the ith box.
 * <p>
 * The second argument is B, the maximum number of candies Misha like in a box.
 * <p>
 * Output Format
 * <p>
 * Return an Integer denoting number of candies Misha will eat.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [3, 2, 3]
 * <p>
 * B = 4
 * <p>
 * Input 2:
 * <p>
 * A = [1,2,1]
 * <p>
 * B = 2
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 2
 * Output 2:
 * <p>
 * 1
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * 1st time Misha will eat from 2nd box, i.e 1 candy she'll eat and will put the remaining
 * 1 candy in the 1st box.
 * <p>
 * 2nd time she will eat from the 3rd box, i.e 1 candy she'll eat and will put the remaining
 * 2 candies in the 1st box.
 * <p>
 * She will not eat from the 3rd box as now it has candies greater than B.
 * <p>
 * So the number of candies Misha eat is 2.
 * <p>
 * Explanation 2:
 * <p>
 * 1st time Misha will eat from 1st box, i.e she can't eat any and will put the remaining
 * 1 candy in the 3rd box.
 * <p>
 * 2nd time she will eat from the 3rd box, i.e 1 candy she'll eat and will put the
 * remaining 1 candies in the 2nd box.
 * <p>
 * She will not eat from the 2nd box as now it has candies greater than B.
 * So the number of candies Misha eat is 1.
 *
 * @author sudhir on 16-Apr-2020
 */
public class MishaAndCandies {
    public int solve(int[] A, int B) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : A) {
            minHeap.offer(num);
        }
        int total = 0;
        while (minHeap.peek() != null && minHeap.peek() <= B) {
            int poll = minHeap.poll();
            total += poll / 2;
            int remaining = poll - (poll / 2);
            if (minHeap.peek() != null) {//if there is no more box in the heap, we can't put remaining candies into it.
                minHeap.offer(minHeap.poll() + remaining);
            }
        }
        return total;
    }

    public static void main(String[] args) {
        MishaAndCandies m = new MishaAndCandies();
        int[] a1 = {3, 2, 3};
        int b1 = 4;
        int[] a2 = {705};
        int b2 = 895;
        System.out.println(m.solve(a1, b1));
        System.out.println(m.solve(a2, b2));
    }
}

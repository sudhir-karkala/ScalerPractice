package com.scaler.heaps;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Given N bags, each bag contains Ai chocolates. <br/>
 * There is a kid and a magician. In one unit of time, kid chooses a random bag i, eats Ai chocolates,
 * then the magician fills the ith bag with floor(Ai/2) chocolates. <br/>
 * Given Ai for 1 <= i <= N, find the maximum number of chocolates kid can eat in K units of time.<br/>
 * Note:
 * <p>
 * floor() function returns the largest integer less than or equal to a given number.
 * <p>
 * Return your answer modulo 10^9+7
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 100000
 * 0 <= B[i] <= INT_MAX
 * 0 <= A <= 10^5
 * <p>
 * Input Format
 * <p>
 * First argument is an integer A.
 * <p>
 * Second argument is an integer array B of size N.
 * <p>
 * Output Format
 * <p>
 * Return an integer denoting the maximum number of chocolates that kid can eat in A units of time.
 *
 * @author sudhir on 16-Apr-2020
 */
public class MagicianAndChocolates {
    public int nchoc(int A, int[] B) {
        int MOD = 1000000007;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        for (Integer num : B) {
            priorityQueue.add(num);
        }
        int i = 1;
        long chocolates = 0L;
        while (i <= A) {
            int element = priorityQueue.poll();
            chocolates += element % MOD;
            priorityQueue.add(element / 2);
            i++;
        }
        return (int) (chocolates % MOD);
    }

    public static void main(String[] args) {
        MagicianAndChocolates m = new MagicianAndChocolates();
        int k = 3;
        int[] a = {6, 5};
        System.out.println(m.nchoc(k, a));
    }
}

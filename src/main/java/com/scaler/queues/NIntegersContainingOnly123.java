package com.scaler.queues;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an integer A. Find and Return first positive A integers in ascending order containing only digits 1,2 and 3. <br/>
 * NOTE: all the A integers will fit in 32 bit integer.
 *
 * @author sudhir on 13-May-2020
 */
public class NIntegersContainingOnly123 {
    public ArrayList<Integer> solve(int A) {
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        ArrayList<Integer> result = new ArrayList<>();
        while (count < A) {
            int temp = queue.poll();
            result.add(temp);
            // for example: number 1 can generate 11, 12 and 13. so we do 1*10+1, 1*10+2 and 1*10+3 to get 11,12 and 13 respectively
            queue.offer(temp * 10 + 1);
            queue.offer(temp * 10 + 2);
            queue.offer(temp * 10 + 3);
            count++;
        }
        return result;
    }

    public static void main(String[] args) {
        NIntegersContainingOnly123 n = new NIntegersContainingOnly123();
        int a1 = 7;
        int a2 = 10;
        System.out.println(n.solve(a1));
        System.out.println(n.solve(a2));
    }
}

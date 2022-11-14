package com.scaler.bitmanipulation;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.<br/>
 * NOTE: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * @author sudhir on 04-Apr-2020
 */
public class SingleNumber {
    public int singleNumber(final int[] A) {
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            ans ^= A[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        SingleNumber s = new SingleNumber();
        int[] a1 = {1, 2, 2, 3, 1};
        int[] a2 = {1, 2, 2};
        System.out.println(s.singleNumber(a1));
        System.out.println(s.singleNumber(a2));
    }
}

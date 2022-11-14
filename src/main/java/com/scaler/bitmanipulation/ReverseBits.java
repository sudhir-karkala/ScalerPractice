package com.scaler.bitmanipulation;

/**
 * Reverse the bits of an 32 bit unsigned integer A.
 *
 * @author sudhir on 04-Apr-2020
 */
public class ReverseBits {
    public long reverse(long a) {
        long ans = 0;
        for (int i = 31; i >= 0; i--) {
            ans = ans | ((a & 1) << i);
            a = a >> 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        ReverseBits r = new ReverseBits();
        long a1 = 0;
        long a2 = 3;
        System.out.println(r.reverse(a1));
        System.out.println(r.reverse(a2));
    }
}

package com.scaler.bitmanipulation;

/**
 * Given an array of integers, every element appears thrice except for one which occurs once.<br/>
 * Find that element which does not appear thrice.<br/>
 * NOTE: Your algorithm should have a linear runtime complexity.<br/>
 * Could you implement it without using extra memory?
 *
 * @author sudhir on 04-Apr-2020
 */
public class SingleNumberII {
    // Approach 1
    public int singleNumber(final int[] A) {
        // Every number that occurs thrice will contribute either 3 '1's or 3 '0's to the position.
        // The number that occurs once say x, will contribute exactly one 0 or 1 to the position
        // depending on whether it has 0 or 1 in that position.
        // when we do (mod 3) to the count of 1's in that position,we get remaining 1 if the number we want has 1 in that position.
        // so we get that bit in that position appropriately.
        int res = 0;
        int countBitOnes[] = new int[32];
        for (int i = 0; i <= 31; i++) {
            for (int j = 0; j < A.length; j++) {
                if (((A[j] >> i) & 1) == 1) {
                    countBitOnes[i]++;
                }
            }
            res |= ((countBitOnes[i] % 3) << i);
        }
        return res;
    }

    // Approach 2
    public int singleNumber2(final int[] A) {
        int ones = 0, twos = 0, not_threes = 0;
        for (int i = 0; i < A.length; i++) {
            twos |= (ones & A[i]);
            ones ^= A[i];
            not_threes = ~(ones & twos);
            ones &= not_threes;
            twos &= not_threes;
        }
        return ones;
    }

    public static void main(String[] args) {
        SingleNumberII s = new SingleNumberII();
        int[] a1 = {1, 2, 4, 3, 3, 2, 2, 3, 1, 1};
        int[] a2 = {0, 0, 0, 1};
        System.out.println(s.singleNumber(a1));
        System.out.println(s.singleNumber(a2));
    }
}

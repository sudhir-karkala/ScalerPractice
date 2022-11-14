package com.scaler.hashing;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * Example: Given [100, 4, 200, 1, 3, 2], The longest consecutive elements sequence is [1, 2, 3, 4]. <br/>
 * Return its length: 4. <br/>
 * Your algorithm should run in O(n) complexity.
 * </p>
 *
 * @author sudhir on 27-Mar-2020
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(final int[] A) {
        Set<Integer> set = new HashSet<>();
        for (Integer num : A) {
            set.add(num);
        }
        int maxLength = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            if (!set.contains(A[i] - 1)) {//means start of the sequence
                int startNum = A[i];
                int count = 0;
                while (set.contains(startNum)) {
                    count++;
                    startNum++;
                }
                if (count > maxLength) {
                    maxLength = count;
                }
                if (maxLength == A.length) {
                    break;
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence l = new LongestConsecutiveSequence();
        int[] a = {100, 4, 200, 1, 3, 2};
        System.out.println(l.longestConsecutive(a));
    }
}

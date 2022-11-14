package com.scaler.companyquestions;

import java.util.Arrays;

/**
 * Akash bought a sequence [1, 2, ..., N-1, N] but unfortunately he had jumbled this sequence.
 * You are asked to help him and the only clue you have for its order is an array representing
 * whether each number is larger or smaller than all the previous elements.
 * <p>
 * For example, given ['.', '+', '+', '-', '+'], you should return [2, 3, 4, 1, 5].
 * <p>
 * '+' at ith index denotes that the ith number is larger than all the number indexed less than i.
 * '-' at ith index denotes that the ith number is smaller than all the number indexed less than i.
 * Given this information, return an array denoting the original sequence.
 * <p>
 * 1 <= N <= 105
 * <p>
 * A[i] = '.' or '+' or '-'
 * <p>
 * Only A[0] = '.'
 *
 * @author sudhir on 07-Aug-2020
 */
public class MissingArray {
    /**
     * Algorithm:
     * 1. consider min to be 1 (since sequence starts from 1) and max to be n (since sequence ends at n).
     * <p>
     * 2. Start filling the result array from right to left doing the following steps.<br/>
     * a. if the character is '-', then it means the character chosen must be less than prev character to the left.
     * so we choose min element to fill this position and increment min.<br/>
     * b. if the character is '+', then it means the character chosen must be greater than prev character to the left.
     * so we choose max element to fill this position and decrement max.<br/>
     * c. if the character is '.', then by this time, max and min will be equal, so we can choose any of min and max
     * to fill the first position.
     * <p>
     * 3. Return the result array.
     *
     * @param A
     * @return result
     */
    public int[] solve(char[] A) {
        int n = A.length;
        int[] result = new int[n];
        int max = n;
        int min = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (A[i] == '-') {
                result[i] = min;
                min++;
            } else if (A[i] == '+') {
                result[i] = max;
                max--;
            } else {
                result[i] = max;
            }
        }
        return result;
    }

    /**
     * Algorithm:
     * 1. Initialize min = 1 and max = 1 (since sequence starts from 1).
     * <p>
     * 2. Start filling the result array from left to right doing the following steps.<br/>
     * a. Fill the first position with min(=1).
     * b. if the character is '-', then it means the character chosen must be less than prev character to the left.
     * so we fill this position with min - 1 and update min<br/>
     * c. if the character is '+', then it means the character chosen must be greater than prev character to the left.
     * so we fill this position with max + 1 and update max.<br/>
     * <p>
     * 3. if min <= 0 then it means, we have filled some position(s) with negative values. So we need to make them positive
     * by adding abs(min) + 1 to all array elements. (or add (min * -1) + 1 to all array elements)
     * 3. Return the result array.
     *
     * @param A
     * @return result
     */
    public int[] solve2(char[] A) {
        int n = A.length;
        int[] result = new int[n];
        int max = 1;
        int min = 1;
        for (int i = 0; i < n; i++) {
            if (A[i] == '-') {
                result[i] = min - 1;
                min = Math.min(min, result[i]);
            } else if (A[i] == '+') {
                result[i] = max + 1;
                max = Math.max(max, result[i]);
            } else {
                result[i] = min;
            }
        }
        if (min <= 0) {
            for (int i = 0; i < n; i++) {
                result[i] += (min * -1) + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MissingArray m = new MissingArray();
        char[] c1 = {'.', '-', '-', '+', '+'};
        char[] c2 = {'.', '-', '+', '-'};
        char[] c3 = {'.', '+', '+', '-', '-'};
        System.out.println(Arrays.toString(m.solve(c1)));
        System.out.println(Arrays.toString(m.solve(c2)));
        System.out.println(Arrays.toString(m.solve(c3)));
        System.out.println(Arrays.toString(m.solve2(c1)));
        System.out.println(Arrays.toString(m.solve2(c2)));
        System.out.println(Arrays.toString(m.solve2(c3)));
    }
}

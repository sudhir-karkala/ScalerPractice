package com.scaler.twopointers;

import java.util.Arrays;

/**
 * Given a binary array A, find the maximum sequence of continuous 1's that can be formed by replacing at-most B ones.
 * For this problem, return the indices of maximum continuous series of 1s in order.
 * If there are multiple possible solutions, return the sequence which has the minimum start index.
 * <p>
 * Problem Constraints
 * <p>
 * 0 <= B <= 10^5
 * <p>
 * 1 <= size(A) <= 10^5
 * <p>
 * A[i]==0 or A[i]==1
 * <p>
 * Input Format
 * <p>
 * First argument is an binary array A.
 * <p>
 * Second argument is an integer B.
 * <p>
 * Output Format
 * <p>
 * Return an array of integers denoting the indices(0-based) of 1's in the maximum continuous series.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [1 1 0 1 1 0 0 1 1 1 ]
 * <p>
 * B = 1
 * <p>
 * Input 2:
 * <p>
 * A = [1, 0, 0, 0, 1, 0, 1]
 * <p>
 * B = 2
 * <p>
 * Example Output
 * Output 1:
 * <p>
 * [0, 1, 2, 3, 4]
 * <p>
 * Output 2:
 * <p>
 * [3, 4, 5, 6]
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Flipping 0 present at index 2 gives us the longest continous series of 1's i.e subarray [0:4].
 * <p>
 * Explanation 2:
 * <p>
 * Flipping 0 present at index 3 and index 5 gives us the longest continous series of 1's i.e subarray [3:6].
 *
 * @author sudhir on 13-Mar-2020
 */
public class MaximumContinuousSeriesOf1s {
    public int[] maxone(int[] A, int B) {
        int left = 0;
        int right = 0;
        int bestLeft = 0;
        int bestRight = 0;
        int zeroCount = 0;
        int bestRange = Integer.MIN_VALUE;

        while (right < A.length) {
            if (A[right] == 0) {
                zeroCount++;
            }
            while (zeroCount > B) {
                if (A[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            if ((right - left > bestRange) && (zeroCount <= B)) {
                bestRange = right - left;
                bestLeft = left;
                bestRight = right;
            }
            right++;
        }
        int[] result = new int[bestRight - bestLeft + 1];
        int k = 0;
        for (int i = bestLeft; i <= bestRight; i++) {
            result[k++] = i;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a1 = {1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1};
        int b1 = 1;
        int[] a2 = {0, 1, 1, 1};
        int b2 = 0;
        MaximumContinuousSeriesOf1s m = new MaximumContinuousSeriesOf1s();
        System.out.println(Arrays.toString(m.maxone(a1, b1)));
        System.out.println(Arrays.toString(m.maxone(a2, b2)));
    }
}

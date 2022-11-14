package com.scaler.twopointers;

import java.util.Arrays;

/**
 * Given an array A of N integers, find three integers in A such that the sum is closest to a given number B.
 * Return the sum of those three integers.
 * <p>
 * Assume that there will only be one solution.
 * <p>
 * Problem Constraints
 * <p>
 * -10^8 <= B <= 10^8
 * <p>
 * 1 <= N <= 10^4
 * <p>
 * -10^8 <= A[i] <= 10^8
 * <p>
 * <p>
 * Input Format
 * <p>
 * First argument is an integer array A of size N.
 * <p>
 * Second argument is an integer B denoting the sum you need to get close to.
 * <p>
 * Output Format
 * <p>
 * Return a single integer denoting the sum of three integers which is closest to B.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [-1, 2, 1, -4]
 * <p>
 * B = 1
 * <p>
 * Input 2:
 * <p>
 * A = [1, 2, 3]
 * <p>
 * B = 6
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 2
 * <p>
 * Output 2:
 * <p>
 * 6
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2)
 * <p>
 * Explanation 2:
 * <p>
 * Take all elements to get exactly 6.
 *
 * @author sudhir on 09-Mar-2020
 */
public class ThreeSum {
    public int threeSumClosest(int[] A, int B) {
        int n = A.length;
        Arrays.sort(A);
        int curSum = 0;
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                curSum = A[i] + A[j] + A[k];
                if (minSum == Integer.MAX_VALUE) {
                    minSum = curSum;
                }
                if (curSum == B) {
                    minSum = curSum;
                    break;
                } else if (curSum > B) {
                    k--;
                } else {
                    j++;
                }
                if (Math.abs(B - curSum) < Math.abs(B - minSum)) {
                    minSum = curSum;
                }
            }
        }
        return minSum;
    }

    public static void main(String[] args) {
        int[] a1 = {-1, 2, 1, -4};
        int b1 = 1;
        int[] a2 = {2, 1, -9, -7, -8, 2, -8, 2, 3, -8};
        int b2 = -1;
        ThreeSum threeSum = new ThreeSum();
        System.out.println(threeSum.threeSumClosest(a1, b1));
        System.out.println(threeSum.threeSumClosest(a2, b2));
    }
}

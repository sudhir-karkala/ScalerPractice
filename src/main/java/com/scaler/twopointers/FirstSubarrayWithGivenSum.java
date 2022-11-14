package com.scaler.twopointers;

import java.util.Arrays;

/**
 * Given an array of positive integers A and an integer B, find and return first continuous subarray which adds to B.
 * <p>
 * If the answer does not exist return an array with a single element "-1".
 * <p>
 * First sub-array means the sub-array for which starting index in minimum.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of the array <= 100000
 * <p>
 * 1 <= A[i] <= 10^9
 * <p>
 * 1 <= B <= 10^9
 * <p>
 * <p>
 * <p>
 * Input Format
 * <p>
 * The first argument given is the integer array A.
 * <p>
 * The second argument given is integer B.
 * <p>
 * Output Format
 * Return the first continuous sub-array which adds to B and if the answer does not exist return
 * an array with a single element "-1".
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [1, 2, 3, 4, 5]
 * <p>
 * B = 5
 * <p>
 * Input 2:
 * <p>
 * A = [5, 10, 20, 100, 105]
 * <p>
 * B = 110
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * [2, 3]
 * <p>
 * Output 2:
 * <p>
 * -1
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * [2, 3] sums up to 5.
 * <p>
 * Explanation 2:
 * <p>
 * No subarray sums up to required number.
 *
 * @author sudhir on 10-Mar-2020
 */
public class FirstSubarrayWithGivenSum {
    public int[] solve(int[] A, int B) {
        int n = A.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + A[i - 1];
        }
        int i = 0;
        int j = i + 1;
        while (i < j && j <= n) {
            if (prefixSum[j] - prefixSum[i] == B) {
                return getArrayElements(A, i, j - 1);
            } else if (prefixSum[j] - prefixSum[i] < B) {
                j++;
            } else {
                i++;
            }
        }
        return new int[]{-1};
    }

    private int[] getArrayElements(int[] A, int i, int j) {
        int[] arr = new int[j - i + 1];
        int k = 0;
        for (int start = i; start <= j; start++) {
            arr[k++] = A[start];
        }
        return arr;
    }

    public static void main(String[] args) {
        FirstSubarrayWithGivenSum firstSubarrayWithGivenSum = new FirstSubarrayWithGivenSum();
        int[] a1 = {1, 2, 3, 4, 5};
        int b1 = 5;
        int[] a2 = {5, 10, 20, 100, 105};
        int b2 = 110;
        System.out.println(Arrays.toString(firstSubarrayWithGivenSum.solve(a1, b1)));
        System.out.println(Arrays.toString(firstSubarrayWithGivenSum.solve(a2, b2)));
    }
}

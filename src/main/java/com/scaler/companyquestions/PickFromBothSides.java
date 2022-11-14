package com.scaler.companyquestions;

/**
 * Given an integer array A of size N.
 * <p>
 * You can pick B elements from either left or right end of the array A to get maximum sum.
 * <p>
 * Find and return this maximum possible sum.
 * <p>
 * NOTE: Suppose B = 4 and array A contains 10 elements then:
 * <p>
 * You can pick first four elements or can pick last four elements or can pick 1 from front and 3 from back etc .
 * you need to return the maximum possible sum of elements you can pick.
 * <p>
 * 1 <= N <= 10^5
 * <p>
 * 1 <= B <= N
 * <p>
 * -10^3 <= A[i] <= 10^3
 *
 * @author sudhir on 18-Jul-2020
 */
public class PickFromBothSides {
    /**
     * This method uses O(n) extra space.
     *
     * @param A
     * @param B
     * @return
     */
    public int solve(int[] A, int B) {
        int n = A.length;
        // Maintain two arrays prefixSum[i] and suffixSum[i] where prefixSum[i] denotes sum of elements from index [0,i]
        // and suffixSum[i] denotes sum of elements from index [i,N-1].
        int[] prefixSum = new int[n];
        int[] suffixSum = new int[n];
        prefixSum[0] = A[0];
        suffixSum[n - 1] = A[n - 1];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = A[i] + prefixSum[i - 1];
        }
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = A[i] + suffixSum[i + 1];
        }
        // Now iterate from left and pick elements one by one from left
        // for example: if you pick ‘a’ elements from left and remaining ‘k-a’ elements from right.
        // So the sum in this case will be prefixSum[a-1] + suffixSum[n-(k-a)]
        int maxSum = suffixSum[n - B];
        for (int i = 0; i < B; i++) {
            int sum = prefixSum[i];
            if (n - (B - (i + 1)) < n) {
                sum += suffixSum[n - (B - (i + 1))];
            }
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    /**
     * This method uses O(1) extra space.
     *
     * @param A
     * @param B
     * @return
     */
    public int solve2(int[] A, int B) {
        int n = A.length;
        int sum = 0;
        for (int i = n - B; i < n; i++) {
            sum += A[i];
        }
        int maxsum = sum;
        for (int i = 0; i < B; i++) {
            sum = sum + A[i] - A[n - B + i];
            maxsum = Math.max(maxsum, sum);
        }
        return maxsum;
    }

    public static void main(String[] args) {
        PickFromBothSides p = new PickFromBothSides();
        int[] a1 = {5, -2, 3, 1, 2};
        int b1 = 3;
        System.out.println(p.solve(a1, b1));
        System.out.println(p.solve2(a1, b1));
    }
}

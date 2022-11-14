package com.scaler.dynamicprogramming;

/**
 * Given an array A of positive elements, you have to flip the sign of some of its elements
 * such that the resultant sum of the elements of array should be minimum non-negative(as close to zero as possible).
 * <p>
 * Return the minimum number of elements whose sign needs to be flipped such that the resultant sum is minimum non-negative.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of(A) <= 100
 * <p>
 * Sum of all the elements will not exceed 10,000.
 * <p>
 * Input Format
 * <p>
 * First and only argument is an integer array A.
 * <p>
 * Output Format
 * <p>
 * Return an integer denoting the minimum number of elements whose sign needs to be flipped.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [15, 10, 6]
 * <p>
 * Input 2:
 * <p>
 * A = [14, 10, 4]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 1
 * <p>
 * Output 2:
 * <p>
 * 1
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Here, we will flip the sign of 15 and the resultant sum will be 1.
 * <p>
 * Explanation 2:
 * <p>
 * Here, we will flip the sign of 14 and the resultant sum will be 0.
 * <p>
 * Note that flipping the sign of 10 and 4 also gives the resultant sum 0 but flippings there sign are not minimum.
 *
 * @author sudhir on 15-Sep-2020
 */
public class FlipArray {
    public int solve(final int[] A) {
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        int capacity = sum / 2;
        int[][] dp = new int[A.length + 1][capacity + 1];
        for (int i = 0; i <= A.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j == 0) {
                    // this also takes care of base case dp[0][0] = 0.
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 1; i <= A.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (A[i - 1] <= j && dp[i - 1][j - A[i - 1]] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - A[i - 1]] + 1);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        // we traverse from the right to pick the number of flips which will result in minimum positive sum.
        int maxCapacityPossible = capacity;
        for (int i = capacity; i >= 0; i--) {
            if (dp[A.length][i] != Integer.MAX_VALUE) {
                maxCapacityPossible = i;
                break;
            }
        }
        // maxCapacityPossible is the capacity for which we have valid value != INT_MAX for number of flips needed.
        return dp[A.length][maxCapacityPossible];
    }

    public static void main(String[] args) {
        FlipArray f = new FlipArray();
        int[] a1 = {8, 4, 5, 7, 6, 2};
        System.out.println(f.solve(a1));

        int[] a2 = {9, 6};
        System.out.println(f.solve(a2));
    }
}

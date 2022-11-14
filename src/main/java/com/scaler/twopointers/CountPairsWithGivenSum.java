package com.scaler.twopointers;

/**
 * Given a sorted array of distinct integers A and an integer B,
 * find and return how many pair of integers ( A[i], A[j] ) such that i != j have sum equal to B.
 * <p>
 * Input Format
 * <p>
 * The first argument given is the integer array A.
 * <p>
 * The second argument given is integer B.
 * <p>
 * Output Format
 * <p>
 * Return the number of pairs for which sum is equal to B.
 * <p>
 * Constraints
 * <p>
 * 1 <= length of the array <= 100000
 * <p>
 * 1 <= A[i] <= 10^9
 * <p>
 * 1 <= B <= 10^9
 * <p>
 * For Example
 * <p>
 * Input 1:
 * <p>
 * A = [1, 2, 3, 4, 5]
 * <p>
 * B = 5
 * <p>
 * Output 1:
 * <p>
 * 2
 * <p>
 * Input 2:
 * <p>
 * A = [5, 10, 20, 100, 105]
 * <p>
 * B = 110
 * <p>
 * Output 2:
 * <p>
 * 2
 *
 * @author sudhir on 09-Mar-2020
 */
public class CountPairsWithGivenSum {
    public int solve(int[] A, int B) {
        int n = A.length;
        int i = 0;
        int j = n - 1;
        int count = 0;
        while (i < j) {
            if (A[i] + A[j] == B) {
                count++;
                i++;
                j--;
            } else if (A[i] + A[j] > B) {
                j--;
            } else {
                i++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 4, 5};
        int b1 = 5;
        int[] a2 = {5, 10, 20, 100, 105};
        int b2 = 110;
        CountPairsWithGivenSum countPairsWithGivenSum = new CountPairsWithGivenSum();
        System.out.println(countPairsWithGivenSum.solve(a1, b1));
        System.out.println(countPairsWithGivenSum.solve(a2, b2));
    }
}

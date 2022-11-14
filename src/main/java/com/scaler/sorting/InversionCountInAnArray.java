package com.scaler.sorting;

/**
 * Given an array of integers A. If i < j and A[i] > A[j] then the pair (i, j) is called an inversion of A.
 * Find the total number of inversions of A modulo (10^9 + 7).
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of the array <= 100000
 * <p>
 * 1 <= A[i] <= 10^9
 * <p>
 * Input Format
 * The only argument given is the integer array A.
 * <p>
 * Output Format
 * <p>
 * Return the number of inversions of A modulo (10^9 + 7).
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [3, 2, 1]
 * <p>
 * Input 2:
 * <p>
 * A = [1, 2, 3]
 * <p>
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 3
 * <p>
 * Output 2:
 * <p>
 * 0
 *
 * @author sudhir on 14-Aug-2020
 */
public class InversionCountInAnArray {
    private long inversionCount = 0;
    private int mod = (int) 1e9 + 7;

    private void merge(int[] arr, int start, int mid, int end) {
        int n1 = mid - start + 1;
        int n2 = end - mid;
        int[] left = new int[n1 + 1];
        int[] right = new int[n2 + 1];
        // The use of sentinel in merge sort prevents us from needing to check
        // to see if we have reached the end of either of the arrays being sorted.
        left[n1] = right[n2] = Integer.MAX_VALUE;
        for (int i = 0; i < n1; i++) {
            left[i] = arr[start + i];
        }
        for (int j = 0; j < n2; j++) {
            right[j] = arr[mid + j + 1];
        }
        int i = 0, j = 0;
        for (int k = start; k <= end; k++) {
            if (left[i] <= right[j]) {
                arr[k] = left[i++];
            } else {
                inversionCount = (inversionCount % mod + (n1 - i) % mod) % mod;
                arr[k] = right[j++];
            }
        }
    }

    private void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    public int solve(int[] A) {
        inversionCount = 0;
        int n = A.length;
        mergeSort(A, 0, n - 1);
        return (int) inversionCount % mod;
    }

    public static void main(String[] args) {
        InversionCountInAnArray invCount = new InversionCountInAnArray();
        int[] a1 = {1, 20, 6, 7, 5, 8, 11, 3};
        System.out.println(invCount.solve(a1));
    }
}

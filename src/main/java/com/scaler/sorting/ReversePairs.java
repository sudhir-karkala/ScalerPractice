package com.scaler.sorting;

/**
 * Given an array of integers A, we call (i, j) an important reverse pair if i < j and A[i] > 2 * A[j].
 * Return the number of important reverse pairs in the given array A.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of the array <= 100000
 * <p>
 * 1 <= A[i] <= 10^9
 * <p>
 * Input Format
 * <p>
 * The only argument given is the integer array A.
 * <p>
 * <p>
 * <p>
 * Output Format
 * <p>
 * Return the number of important reverse pairs in the given array A.
 * <p>
 * Example Input
 * Input 1: A = [1, 3, 2, 3, 1]
 * <p>
 * Input 2: A = [4, 1, 2]
 * <p>
 * Example Output
 * <p>
 * Output 1: 2
 * <p>
 * Output 2: 1
 *
 * @author sudhir on 14-Aug-2020
 */
public class ReversePairs {
    private int reversePairs = 0;

    private void merge(int[] arr, int start, int mid, int end) {
        int n1 = mid - start + 1;
        int n2 = end - mid;
        int[] left = new int[n1];
        int[] right = new int[n2];
        for (int i = 0; i < n1; i++) {
            left[i] = arr[start + i];
        }
        for (int j = 0; j < n2; j++) {
            right[j] = arr[mid + j + 1];
        }
        int i = 0, j = 0;
        // begin: logic to count reverse pairs
        while (i < n1 && j < n2) {
            // if the condition is satisfied, then we know that since left[] is sorted,
            // the condition will be satisfied for all elements after left[i] too.
            // so we can increment the count by (n1 - i) and do next comparisons
            // with (j+1)th element. so we increment j.
            if (left[i] > 2 * right[j]) {
                reversePairs += (n1 - i);
                j++;
            } else {
                // if the condition is not satisfied, then check for (i+1)th element.
                // so we increment i.
                i++;
            }
        }
        // end: logic to count reverse pairs
        // begin: logic to merge two sorted arrays
        i = 0;
        j = 0;
        int k = 0;
        for (k = start; k <= end; k++) {
            if (i < n1 && j < n2) {
                if (left[i] <= right[j]) {
                    arr[k] = left[i++];
                } else {
                    arr[k] = right[j++];
                }
            } else {
                break;
            }
        }
        // at the end of for loop, if there are elements in either of the arrays,
        // we will add them to the array.
        // if the sentinel element is used, there is no need for this step
        // as there won't be any left over elements.
        while (i < n1) {
            arr[k++] = left[i++];
        }
        while (j < n2) {
            arr[k++] = right[j++];
        }
        // end: logic to merge two sorted arrays
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
        reversePairs = 0;
        int n = A.length;
        mergeSort(A, 0, n - 1);
        return reversePairs;
    }

    public static void main(String[] args) {
        ReversePairs rp = new ReversePairs();
        int[] a1 = {1, 3, 2, 3, 1};
        int[] a2 = {4, 1, 2};
        System.out.println(rp.solve(a1));
        System.out.println(rp.solve(a2));
    }
}

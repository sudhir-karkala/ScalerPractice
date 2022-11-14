package com.scaler.sorting;

/**
 * Given an array of integers A, find and return new integer array B.
 * <p>
 * Array B has the property where B[i] is the number of smaller elements to the right of A[i].
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
 * Output Format
 * <p>
 * Return the new integer array B.
 * <p>
 * Example Input
 * <p>
 * A = [1, 5, 4, 2, 1]
 * <p>
 * Example Output
 * <p>
 * [0, 3, 2, 1, 0]
 * <p>
 * Example Explanation
 * <p>
 * Number of smaller elements to the right of 1 are 0.
 * <p>
 * Number of smaller elements to the right of 5 are 3 (4, 2, 1).
 * <p>
 * Number of smaller elements to the right of 4 are 2 (2, 1).
 * <p>
 * Number of smaller elements to the right of 2 are 1 (1).
 * <p>
 * Number of smaller elements to the right of 1 are 0.
 *
 * @author sudhir on 24-Aug-2020
 */
public class CountOfSmallerNumbersAfterSelf {
    public int[] solve(int[] A) {
        int n = A.length;
        int[] result = new int[n];
        // maintain index of every element using Pair object.
        Pair[] newNums = new Pair[n];
        for (int i = 0; i < n; i++) {
            newNums[i] = new Pair(A[i], i);
        }
        mergeSort(newNums, result, 0, n - 1);
        return result;
    }

    private void mergeSort(Pair[] newNums, int[] result, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(newNums, result, start, mid);
            mergeSort(newNums, result, mid + 1, end);
            merge(newNums, result, start, mid, end);
        }
    }

    private void merge(Pair[] newNums, int[] result, int start, int mid, int end) {
        int n1 = mid - start + 1;
        int n2 = end - mid;
        Pair[] left = new Pair[n1];
        Pair[] right = new Pair[n2];
        for (int i = 0; i < n1; i++) {
            left[i] = newNums[start + i];
        }
        for (int i = 0; i < n2; i++) {
            right[i] = newNums[mid + 1 + i];
        }
        int i = 0, j = 0, k = 0;
        for (k = start; k <= end; k++) {
            if (i < n1 && j < n2) {
                // if this condition is met, then it means, there were j number of elements
                // that were smaller than ith element, so we add this count to ith element.
                if (left[i].value <= right[j].value) {
                    result[left[i].index] += j;
                    newNums[k] = left[i];
                    i++;
                } else {
                    newNums[k] = right[j];
                    j++;
                }
            } else {
                break;
            }
        }
        // if there are pending elements in left array, then it means all of those are greater than
        // j elements on the right that are already consumed. so we add this count to the
        // ith element.
        while (i < n1) {
            result[left[i].index] += j;
            newNums[k] = left[i];
            i++;
            k++;
        }
        while (j < n2) {
            newNums[k] = right[j];
            j++;
            k++;
        }
    }

    /**
     * We need this class object to store the corresponding index of the element
     * so that we can update the count at that particular index in the result list
     */
    static class Pair {
        int value;
        int index;

        public Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        CountOfSmallerNumbersAfterSelf c = new CountOfSmallerNumbersAfterSelf();
        int[] nums1 = {1, 5, 4, 2, 1};
        System.out.println(c.solve(nums1));
    }
}

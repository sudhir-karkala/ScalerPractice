package com.scaler.contests;

/**
 * Given an array of integers A and two integers B and C.
 * Find the number of range sums that lie in [B, C ] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in A between indices i and j (i <= j), inclusive.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of the array <= 50000
 * <p>
 * -10^9 <= A[i] <= 10^9
 * <p>
 * Input Format
 * <p>
 * First argument is an integer array A.
 * <p>
 * Second argument is an integer B.
 * <p>
 * Third argument is an integer C.
 * <p>
 * Output Format
 * <p>
 * Find the number of range sums that lie in [B, C ] inclusive.
 *
 * @author sudhir on 22-May-2020
 */
public class CountOfRangeSum {
    private int countOfRangeSum;

    /**
     * This is the brute force method which takes O(n^2) time.
     *
     * @param A
     * @param B
     * @param C
     * @return
     */
    public int solve(int[] A, int B, int C) {
        int n = A.length;
        int count = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                if (sum >= B && sum <= C) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * This uses the idea of merge sort and counts range sum before every merge operation.
     * This gives better time complexity of O(nlogn).
     *
     * @param A
     * @param B
     * @param C
     * @return
     */
    public int solve2(int[] A, int B, int C) {
        int n = A.length;
        countOfRangeSum = 0;
        // prefixSum[] might overflow integer range. so we use long[] type
        long[] prefixSum = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + A[i];
        }
        mergeSort(prefixSum, 0, n, B, C);
        return countOfRangeSum;
    }

    private void mergeSort(long[] nums, int start, int end, int lower, int upper) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(nums, start, mid, lower, upper);
            mergeSort(nums, mid + 1, end, lower, upper);
            int rangeStart = mid + 1;
            int rangeEnd = mid + 1;
            for (int i = start; i <= mid; i++) {
                // rangeEnd will be the first index that satisfies nums[rangeEnd] - nums[i] > upper
                while (rangeEnd <= end && nums[rangeEnd] - nums[i] <= upper) {
                    rangeEnd++;
                }
                // rangeStart will be the first index that satisfies nums[rangeStart] - nums[i] >= upper
                // Then the number of sums in [lower, upper] is (rangeEnd - rangeStart)
                while (rangeStart <= end && nums[rangeStart] - nums[i] < lower) {
                    rangeStart++;
                }
                countOfRangeSum += (rangeEnd - rangeStart);
            }
            merge(nums, start, mid, end);
        }
    }

    private void merge(long[] nums, int start, int mid, int end) {
        int n1 = mid - start + 1;
        int n2 = end - mid;
        long[] left = new long[n1];
        long[] right = new long[n2];
        for (int i = 0; i < n1; i++) {
            left[i] = nums[i + start];
        }
        for (int i = 0; i < n2; i++) {
            right[i] = nums[i + mid + 1];
        }
        // begin: logic to merge two sorted arrays
        int i = 0;
        int j = 0;
        int k = 0;
        for (k = start; k <= end; k++) {
            if (i < n1 && j < n2) {
                if (left[i] <= right[j]) {
                    nums[k] = left[i++];
                } else {
                    nums[k] = right[j++];
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
            nums[k++] = left[i++];
        }
        while (j < n2) {
            nums[k++] = right[j++];
        }
        // end: logic to merge two sorted arrays
    }

    public static void main(String[] args) {
        CountOfRangeSum c = new CountOfRangeSum();
        int[] a1 = {1, 2, 3};
        int b1 = 4;
        int c1 = 6;
        System.out.println(c.solve(a1, b1, c1));
        System.out.println(c.solve2(a1, b1, c1));
        int[] a2 = {1, 1, 1};
        int b2 = 1;
        int c2 = 3;
        System.out.println(c.solve(a2, b2, c2));
        System.out.println(c.solve2(a2, b2, c2));
    }
}

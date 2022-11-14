package com.scaler.twopointers;

import java.util.HashSet;
import java.util.Set;

/**
 * Misha likes finding all Subarrays of an Array. Now she gives you an array A of N elements and
 * told you to find the number of subarrays of A, that have unique elements.
 * Since the number of subarrays could be large, return value % 10^9 +7.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 10^5
 * <p>
 * 1 <= A[i] <= 10^6
 * <p>
 * Input Format
 * <p>
 * The only argument given is an Array A, having N integers.
 * <p>
 * Output Format
 * <p>
 * Return the number of subarrays of A, that have unique elements.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [1, 1, 3]
 * <p>
 * Input 2:
 * <p>
 * A = [2, 1, 2]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 4
 * <p>
 * Output 1:
 * <p>
 * 5
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Subarrays of A that have unique elements only:
 * <p>
 * [1], [1], [1, 3], [3]
 * <p>
 * Explanation 2:
 * <p>
 * Subarrays of A that have unique elements only:
 * <p>
 * [2], [1], [2, 1], [1, 2], [2]
 *
 * @author sudhir on 14-Mar-2020
 */
public class CountSubarrays {
    public int solve(int[] A) {
        int mod = (int) (1e9 + 7);
        // Initialize start and end pointers
        int start = 0;
        int end = 0;
        int n = A.length;
        // create a set to store unique elements
        Set<Integer> set = new HashSet<>();
        long subarrays = 0;
        // stores the previous value of end
        int prev_end = 0;
        while (end < n) {
            if (!set.contains(A[end])) {
                set.add(A[end]);
                end++;
            } else {
                // all segments in (start to end) will make unique elements

                subarrays += (1L * (end - start) * (end - start + 1) / 2) % mod;
                // remove already considered elements from subarray count
                // already considered elements will be from start to prev_end-1 whose count is given by (prev_end - start)
                subarrays -= (1L * (prev_end - start) * (prev_end - start + 1) / 2) % mod;
                prev_end = end;

                // as long as we don't encounter the duplicate element to be removed which is indicated by end pointer,
                // we keep removing elements one by one from start and advance start pointer
                while (set.contains(A[end])) {
                    set.remove(A[start]);
                    start++;
                }

            }
        }
        // add last segment
        subarrays += (1L * (end - start) * (end - start + 1) / 2) % mod;
        subarrays -= (1L * (prev_end - start) * (prev_end - start + 1) / 2) % mod;
        // return the answer
        return (int) subarrays % mod;
    }

    public static void main(String[] args) {
        int[] a1 = {1, 2, 2, 1, 4, 2};
        int[] a2 = {1, 2, 1, 4, 2};
        CountSubarrays c = new CountSubarrays();
        System.out.println(c.solve(a1));
        System.out.println(c.solve(a2));
    }
}

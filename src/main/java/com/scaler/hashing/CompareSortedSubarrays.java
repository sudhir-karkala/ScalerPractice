package com.scaler.hashing;

import java.util.*;

/**
 * Given an array A of length N. You have to answer Q queries. <br/>
 * Each query will contain 4 integers l1, r1, l2 and r2.
 * If sorted segment from [l1, r1] is same as sorted segment from [l2 r2] then answer is 1 else 0.<br/>
 * NOTE - The queries are 0-indexed.<br/>
 * Input: 1st will be array A. 2nd will be 2-D array denoting queries with dimension Q * 4.<br/>
 * Consider ith query as Arr[i][0], Arr[i][1], Arr[i][2] and Arr[i][3].<br/>
 * Output: Return an array of length Q with answer of the queries in the same order in input.
 *
 * @author sudhir on 19-Apr-2020
 */
public class CompareSortedSubarrays {
    public int[] solve(int[] A, int[][] B) {
        int[] result = new int[B.length];
        Map<Integer, Long> hashCodeMap = new HashMap<>();
        Random random = new Random();
        // one way to compare two subarrays is to sum all in the range and compare the sums, but this won't work for cases like these.
        // 3 + 3 = 6 and 4 + 2 = 6, so if one subarray contains [3, 3] and other subarray contains [4, 2]
        // this will return as 1 which is incorrect
        // Another solution to keep track of one subarray elements in hashmap and comparing it with another subarray
        // which is correct but result in TLE.
        // Optimal approach is to map given elements to unique hash value so that sum results in unique value.
        // We compute cumulative sum so that we can easily compute range sum for given query and return the answer.
        long[] cumulativeSum = new long[A.length + 1];
        Set<Long> usedHashCode = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            if (!hashCodeMap.containsKey(A[i])) {
                long hashCode = random.nextLong();
                while (usedHashCode.contains(hashCode)) {
                    hashCode = random.nextLong();
                }
                hashCodeMap.put(A[i], hashCode);
                usedHashCode.add(hashCode);
            }
            cumulativeSum[i + 1] = cumulativeSum[i] + hashCodeMap.get(A[i]);
        }

        for (int i = 0; i < B.length; i++) {
            long value1 = cumulativeSum[B[i][1] + 1] - cumulativeSum[B[i][0]];
            long value2 = cumulativeSum[B[i][3] + 1] - cumulativeSum[B[i][2]];
            if (value1 == value2) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CompareSortedSubarrays compareSortedSubarrays = new CompareSortedSubarrays();
        int[] a1 = {1, 7, 11, 8, 11, 7, 1};
        int[][] b1 = {{0, 2, 4, 6}};
        System.out.println(Arrays.toString(compareSortedSubarrays.solve(a1, b1)));
    }
}

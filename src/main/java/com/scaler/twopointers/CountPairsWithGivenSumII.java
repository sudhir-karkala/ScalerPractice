package com.scaler.twopointers;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a sorted array of integers (not necessarily distinct) A and an integer B,
 * find and return how many pair of integers ( A[i], A[j] ) such that i != j have sum equal to B.
 * <p>
 * Since the number of such pairs can be very large, return number of such pairs modulo (10^9 + 7).
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= |A| <= 100000
 * <p>
 * 1 <= A[i] <= 10^9
 * <p>
 * 1 <= B <= 10^9
 * <p>
 * Input Format
 * <p>
 * The first argument given is the integer array A.
 * <p>
 * The second argument given is integer B.
 * <p>
 * Output Format
 * <p>
 * Return the number of pairs for which sum is equal to B modulo (10^9+7).
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [1, 1, 1]
 * <p>
 * B = 2
 * <p>
 * Input 2:
 * <p>
 * A = [1, 1]
 * <p>
 * B = 2
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 3
 * <p>
 * Output 2:
 * <p>
 * 1a
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Any two pairs sum up to 2.
 * <p>
 * Explanation 2:
 * <p>
 * only pair (1, 2) sums up to 2.
 *
 * @author sudhir on 09-Mar-2020
 */
public class CountPairsWithGivenSumII {
    public int solve(int[] A, int B) {
        int n = A.length;
        int MOD = (int) 1e9 + 7;
        int i = 0;
        int j = n - 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : A) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        long count = 0;
        while (i < j) {
            if (A[i] + A[j] == B) {
                if (A[i] == A[j]) {
                    count = (count % MOD + nc2(map.get(A[i])) % MOD) % MOD;
                    break;
                }
                count = (count % MOD + ((map.get(A[i]) % MOD * map.get(A[j]) % MOD)) % MOD) % MOD;
                i += map.get(A[i]);
                j -= map.get(A[j]);
            } else if (A[i] + A[j] > B) {
                j -= map.get(A[j]);
            } else {
                i += map.get(A[i]);
            }
        }
        return (int) count;
    }

    private long nc2(int n) {
        return 1L * n * (n - 1) / 2;
    }

    public int solve2(int[] A, int B) {
        Map<Integer, Integer> mapOfCounts = new HashMap<>();
        long count = 0;
        int mod = (int) 1e9 + 7;
        for (int i = 0; i < A.length; i++) {
            // if hashmap contains B - A[i] as the key, then we know that we can pair
            // hashmap.get(B-A[i]) count of numbers with A[i]
            if (mapOfCounts.containsKey(B - A[i])) {
                count = ((count % mod) + (mapOfCounts.get(B - A[i]) % mod)) % mod;
            }
            mapOfCounts.put(A[i], mapOfCounts.getOrDefault(A[i], 0) + 1);
        }
        return (int) count;
    }

    public static void main(String[] args) {
        int[] a1 = {1, 1, 1, 2, 3};
        int b1 = 4;
        int[] a2 = {1, 2, 2, 3, 4};
        int b2 = 5;
        CountPairsWithGivenSumII countPairsWithGivenSum = new CountPairsWithGivenSumII();
        System.out.println(countPairsWithGivenSum.solve(a1, b1));
        System.out.println(countPairsWithGivenSum.solve(a2, b2));
        System.out.println(countPairsWithGivenSum.solve2(a1, b1));
        System.out.println(countPairsWithGivenSum.solve2(a2, b2));
    }
}

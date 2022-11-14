package com.scaler.searching;

/**
 * Given 2 integers A and B and an array of integers C of size N. Element C[i] represents length of ith board.
 * You have to paint all N boards [C0, C1, C2, C3 … CN-1]. There are A painters available and each of them takes B units of time to paint 1 unit of board.
 * Calculate and return minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of board. NOTE:
 * <li>
 * 1. 2 painters cannot share a board to paint. That is to say, a board cannot be painted partially by one painter, and partially by another.
 * </li>
 * <li>
 * 2. A painter will only paint contiguous boards. Which means a configuration where painter 1 paints board 1 and 3 but not 2 is invalid.
 * </li>
 * Return the ans MOD 10000003.
 *
 * @author sudhir on 04-Mar-2020
 */
public class PaintersPartition {
    private int MOD = 10000003;

    public int paint(int A, int B, int[] C) {
        long min = 0;
        long max = 0;
        for (int i = 0; i < C.length; i++) {
            max += C[i];
        }
        return binarySearch(min, max, A, B, C);
    }

    int binarySearch(long l, long r, int painters, int cost, int[] C) {
        long result = Long.MAX_VALUE;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (isPossibleToAllocatePainters(C, mid, painters)) {
                result = Math.min(result, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) (result * cost % MOD);
    }

    boolean isPossibleToAllocatePainters(int[] C, long maxLength, int painters) {
        long total = 0;
        int count = 1;
        for (int i = 0; i < C.length; i++) {
            long temp = C[i];
            if (temp > maxLength) {
                return false;
            }
            if ((total + temp) <= maxLength) {
                total += temp;
            } else {
                total = temp;
                count++;
                if (count > painters) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        PaintersPartition p = new PaintersPartition();
        int[] c1 = {1, 10};
        int a1 = 2;
        int b1 = 5;
        int[] c2 = {1, 8, 11, 3};
        int a2 = 10;
        int b2 = 1;
        System.out.println(p.paint(a1, b1, c1));
        System.out.println(p.paint(a2, b2, c2));
    }
}


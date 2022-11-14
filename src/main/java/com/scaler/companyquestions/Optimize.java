package com.scaler.companyquestions;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers A of size N.
 * <p>
 * A triplet (i, j, k) is valid if a[i] < a[j] < a[k] and i < j < k.
 * <p>
 * Value of a valid triplet = a[i] + a[j] * a[k].
 * <p>
 * Return maximum value among values of all valid triplets, If there is no valid triplet in the entire array return -1.
 * <p>
 * Problem Constraints
 * <p>
 * 3 <= N <= 10^5
 * <p>
 * 1 <= A[i] <= 10^3
 *
 * @author sudhir on 08-Aug-2020
 */
public class Optimize {

    /**
     * Brute force solution which gives TLE.
     *
     * @param A
     * @return
     */
    public int solve2(int[] A) {
        int n = A.length;
        int result = Integer.MIN_VALUE;
        for (int j = 1; j < n - 1; j++) {
            int max1 = Integer.MIN_VALUE;
            for (int i = 0; i < j; i++) {
                if (A[i] < A[j]) {
                    max1 = Math.max(max1, A[i]);
                }
            }
            int max2 = Integer.MIN_VALUE;
            for (int k = j + 1; k < n; k++) {
                if (A[j] < A[k]) {
                    max2 = Math.max(max2, A[k]);
                }
            }
            if (max1 != Integer.MIN_VALUE && max2 != Integer.MIN_VALUE) {
                result = Math.max(result, max1 + A[j] * max2);
            }
        }
        return result == Integer.MIN_VALUE ? -1 : result;
    }

    class Pair {
        int aj;//this is A[j] such that A[j] < A[k] and j < k
        int prod;// product of a[j]*a[k]

        public Pair(int aj, int prod) {
            this.aj = aj;
            this.prod = prod;
        }
    }

    /**
     * An efficient solution to solve Optimize problem.
     *
     * @param A
     * @return
     */
    public int solve(int[] A) {
        int n = A.length;
        int[] suffix = new int[n];
        suffix[n - 1] = A[n - 1];
        // maintain max element from the right
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.max(A[i], suffix[i + 1]);
        }
        // In order to maintain O(1) complexity of element insertion into the arraylist, we insert elements into the arraylist
        // from 0th index even though we iterate elements from the end of the given array.
        List<Pair> list = new ArrayList<>();
        // for the last element, we can't pair with kth element, we put -1 as prod.
        list.add(new Pair(A[n - 1], -1));
        for (int i = n - 2; i >= 0; i--) {
            Pair p = list.get(n - i - 2);
            // it means that the current element A[i] can form a product with suffix[i], but we need to maintain
            // the maximum product obtained so far, i.e. max(A[i] * suffix[i], prev_prod)
            if (A[i] != suffix[i]) {
                if (p.prod > A[i] * suffix[i]) {
                    list.add(new Pair(p.aj, p.prod));
                } else {
                    list.add(new Pair(A[i], A[i] * suffix[i]));
                }
            } else {
                // it means that there is no element greater than the current element so that we can form A[j] * A[k].
                // so we retain the prev product if prev product  != -1, else, we insert -1 as the product
                if (p.prod != -1) {
                    list.add(new Pair(p.aj, p.prod));
                } else {
                    list.add(new Pair(A[i], -1));
                }
            }
        }
        int maxValue = -1;
        for (int i = 0; i < n - 1; i++) {
            if (A[i] < list.get(n - i - 2).aj) {
                maxValue = Math.max(maxValue, A[i] + list.get(n - i - 2).prod);
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        Optimize o = new Optimize();
        int[] a1 = {7, 9, 3, 8, 11, 10};
        int[] a2 = {5, 4, 3, 2, 1};
        int[] a3 = {507, 31, 414, 169, 901, 592, 763, 656, 411, 360, 625, 538, 549, 484, 596, 42};
        int[] a4 = {2, 15, 14, 13, 12, 5, 6};
        System.out.println(o.solve(a1));
        System.out.println(o.solve(a2));
        System.out.println(o.solve(a3));
        System.out.println(o.solve(a4));
        System.out.println(o.solve2(a1));
        System.out.println(o.solve2(a2));
        System.out.println(o.solve2(a3));
        System.out.println(o.solve2(a4));
    }
}

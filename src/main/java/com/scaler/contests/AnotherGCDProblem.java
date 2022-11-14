package com.scaler.contests;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array A of size N. Find the maximum length of a subarray Al, Al+1 ... Ar
 * such that gcd(A[l], A[l+1], ... A[r]) > 1.
 * <p>
 * NOTE: If no such subarray exists, return -1.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 10^5
 * <p>
 * 0 <= A[i] <= 10^6
 * <p>
 * Input Format
 * <p>
 * First and only argument is an integer array A of size N.
 * <p>
 * Output Format
 * <p>
 * Return an integer denoting the maximum length of a subarray.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [7, 1, 2, 3, 4]
 * <p>
 * Input 2:
 * <p>
 * A = [2, 4, 6, 8, 20]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 1
 * <p>
 * Output 2:
 * <p>
 * 5
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Gcd of every two consecutive element is 1. So, we can not take more than 1 element
 * in any subarray. So, the answer is 1.
 * <p>
 * Explanation 2:
 * <p>
 * Gcd of all elements in the array is greater than 1 which is 2.
 * So, the maximum length of the subarray is 5.
 *
 * @author sudhir on 27-Nov-2020
 */
public class AnotherGCDProblem {
    private int MAXN = (int) 1e6;
    private int[] spf = new int[MAXN + 1];

    private void modifiedSieve() {
        // initial values
        for (int i = 0; i <= MAXN; i++) {
            spf[i] = i;
        }

        for (int p = 2; p * p <= MAXN; p++) {
            if (spf[p] == p) {
                for (int i = p * p; i <= MAXN; i += p) {
                    if (spf[i] == i) {
                        spf[i] = p;
                    }
                }
            }
        }
    }

    public int solve(int[] A) {
        modifiedSieve();
        List<List<Integer>> list = new ArrayList<>();
        int n = A.length;
        for (int i = 0; i <= MAXN; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            while (A[i] > 1) {
                int b = spf[A[i]];
                // make an entry in arraylist saying that b is factor of ith element.
                list.get(b).add(i);
                // remove all factors with value b from A[i] as the entry is already made.
                while (A[i] % b == 0) {
                    A[i] /= b;
                }
            }
        }
        // If no such subarray exists with gcd > 1, return -1. so set ans = -1 initially
        int ans = -1;
        for (int i = 2; i <= MAXN; i++) {
            int curlen = 1;
            int sizeOfList = list.get(i).size();
            for (int j = 0; j < sizeOfList; j++) {
                // if the consecutive values which represent indices of the numbers differ by 1,
                // then we increment the length of the subarray, else start with a new subarray
                // with curlen = 1.
                if (j != 0 && ((list.get(i).get(j) - list.get(i).get(j - 1)) == 1)) {
                    curlen++;
                } else {
                    curlen = 1;
                }
                // keep track of max subarray length in ans and return the same after checking
                // for all possible factors from 2 to MAXN.
                ans = Math.max(ans, curlen);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        AnotherGCDProblem anotherGCDProblem = new AnotherGCDProblem();
        int[] a1 = {7, 1, 2, 3, 4};
        int[] a2 = {2, 4, 6, 8, 20};
        System.out.println(anotherGCDProblem.solve(a1));
        System.out.println(anotherGCDProblem.solve(a2));
    }
}

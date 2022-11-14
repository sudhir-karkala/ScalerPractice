package com.scaler.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Given a matrix of integers A of size N x 2 describing dimensions of N envelopes,
 * where A[i][0] denotes the height of the ith envelope and A[i][1] denotes the width of the ith envelope.
 * <p>
 * One envelope can fit into another if and only if both the width and height of one envelope
 * is greater than the width and height of the other envelope.
 * <p>
 * Find the maximum number of envelopes you can put one inside other.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 1000
 * <p>
 * 1 <= A[i][0], A[i][1] <= 10^9
 * <p>
 * Input Format
 * <p>
 * The only argument given is the integer matrix A.
 * <p>
 * Output Format
 * <p>
 * Return an integer denoting the maximum number of envelopes you can put one inside other.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [
 * [5, 4]
 * [6, 4]
 * [6, 7]
 * [2, 3]
 * ]
 * <p>
 * Input 2:
 * <p>
 * A = [     '
 * [8, 9]
 * [8, 18]
 * ]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 3
 * <p>
 * Output 2:
 * <p>
 * 1
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Step 1: put [2, 3] inside [5, 4]
 * <p>
 * Step 2: put [5, 4] inside [6, 7]
 * <p>
 * 3 envelopes can be put one inside other.
 * <p>
 * Explanation 2:
 * <p>
 * No envelopes can be put inside any other so answer is 1.
 *
 * @author sudhir on 07-Sep-2020
 */
public class RussianDollEnvelopes {
    public int solve(ArrayList<ArrayList<Integer>> A) {
        // we sort the input based on height. if height is same, then we sort based on weight in descending order.
        // why descending order for weight?
        // example: A = (5*3), (6*4), (6*7), (2*2), when we sort based on height alone,
        // there are 2 possibilities for sorted array
        // 1: (2*2), (5*3), (6*4), (6*7) => LIS = 4
        // 2: (2*2), (5*3), (6*7), (6*4) => LIS = 3
        // Here the correct answer = 3 and not 4. but there is a possibility of returning answer as 4 if we only sort
        // based on height.
        Collections.sort(A, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if (o1.get(0).equals(o2.get(0))) {
                    return o2.get(1).compareTo(o1.get(1));
                }
                return o1.get(0).compareTo(o2.get(0));
            }
        });

        // apply LIS (Longest Increasing Subsequence) logic for weight.
        int n = A.size();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (A.get(j).get(1) < A.get(i).get(1)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int ans = dp[0];
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        RussianDollEnvelopes r = new RussianDollEnvelopes();
        ArrayList<ArrayList<Integer>> a1 = new ArrayList<>();
        a1.add(new ArrayList<>(Arrays.asList(5, 4)));
        a1.add(new ArrayList<>(Arrays.asList(6, 4)));
        a1.add(new ArrayList<>(Arrays.asList(6, 7)));
        a1.add(new ArrayList<>(Arrays.asList(2, 3)));
        System.out.println(r.solve(a1));
    }
}

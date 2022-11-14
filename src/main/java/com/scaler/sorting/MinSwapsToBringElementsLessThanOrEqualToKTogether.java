package com.scaler.sorting;

/**
 * Given an array of integers A and an integer B,
 * find and return the minimum number of swaps required to bring all the numbers less than or equal to B together. <br/>
 * Note: It is possible to swap any two elements, not necessarily consecutive.
 *
 * @author sudhir on 11-Apr-2020
 */
public class MinSwapsToBringElementsLessThanOrEqualToKTogether {
    public int solve(int[] A, int B) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] <= B) {
                count++;
            }
        }
        int l = 0, r = count - 1;
        int ans = count;
        int bad = 0;
        for (int i = 0; i < count; i++) {
            if (A[i] > B) {
                bad++;
            }
        }
        l++;
        r++;
        ans = Math.min(bad, ans);
        while (r < A.length) {
            if (A[l - 1] > B) {
                bad--;
            }
            if (A[r] > B) {
                bad++;
            }
            ans = Math.min(bad, ans);
            l++;
            r++;
        }
        return ans;
    }

    public static void main(String[] args) {
        MinSwapsToBringElementsLessThanOrEqualToKTogether m = new MinSwapsToBringElementsLessThanOrEqualToKTogether();
        int[] a = {1, 12, 10, 3, 14, 10, 5};
        int b = 8;
        System.out.println(m.solve(a, b));
    }
}

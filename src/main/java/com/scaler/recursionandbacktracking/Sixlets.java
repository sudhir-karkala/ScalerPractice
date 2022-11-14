package com.scaler.recursionandbacktracking;

/**
 * Given a array of integers A of size N and an integer B. Return number of non-empty subsequences of A of size B having sum <= 1000.
 *
 * @author sudhir on 29-Mar-2020
 */
public class Sixlets {
    public int solve(int[] A, int B) {
        return solve(A, B, 0, 0, 0);
    }

    private int solve(int[] A, int B, int sum, int index, int count) {
        if (sum > 1000) {
            return 0;
        }
        if (count == B) {
            return 1;
        }
        if (index == A.length) {
            return 0;
        }
        return solve(A, B, sum + A[index], index + 1, count + 1) + solve(A, B, sum, index + 1, count);
    }

    public static void main(String[] args) {
        Sixlets s = new Sixlets();
        int[] a1 = {1, 2, 8};
        int b1 = 2;
        int[] a2 = {5, 17, 1000, 11};
        int b2 = 4;
        System.out.println(s.solve(a1, b1));
        System.out.println(s.solve(a2, b2));
    }
}

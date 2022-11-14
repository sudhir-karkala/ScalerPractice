package com.scaler.searching;

import java.util.Arrays;

/**
 * Farmer John has built a new long barn, with N stalls.
 * Given an array of integers A of size N where each element of the array represents the location of the stall,
 * and an integer B which represent the number of cows.
 * His cows don't like this barn layout and become aggressive towards each other once put into a stall.
 * To prevent the cows from hurting each other, John wants to assign the cows to the stalls,
 * such that the minimum distance between any two of them is as large as possible.
 * What is the largest minimum distance?
 *
 * @author sudhir on 29-Feb-2020
 */
public class AggressiveCows {
    public static int solve(int[] A, int B) {
        Arrays.sort(A);
        int l = 0;//min distance
        int r = A[A.length - 1] - A[0];//max distance
        return binSearch(A, l, r, B);
    }

    static int binSearch(int[] A, int l, int r, int B) {
        if (l > r) {
            return r;
        }
        int mid = l + (r - l) / 2;
        //check if cows can be placed mid distance apart
        if (check(A, mid, B)) {
            return binSearch(A, mid + 1, r, B);
        } else {
            return binSearch(A, l, mid - 1, B);
        }
    }

    static boolean check(int[] A, int distance, int B) {
        int n = A.length;
        int count = 1;
        int lastPlaced = A[0];
        for (int i = 1; i < n; i++) {
            if (A[i] - lastPlaced >= distance) {
                lastPlaced = A[i];
                count++;
            }
        }
        if (count >= B) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a1 = {82, 61, 38, 88, 12, 7, 6, 12, 48, 8, 31, 90, 35, 5, 88, 2, 66, 19, 5, 96, 84, 95};
        int a2 = 8;
        int[] b1 = {0, 1000000000};
        int b2 = 2;
        int[] c1 = {1, 2, 3, 4, 5};
        int c2 = 3;
        System.out.println(solve(a1, a2));
        System.out.println(solve(b1, b2));
        System.out.println(solve(c1, c2));
    }
}

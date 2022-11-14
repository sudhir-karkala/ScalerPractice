package com.scaler.searching;

/**
 * @author sudhir on 01-Mar-2020
 */
public class SquareRootOfInteger {
    public int sqrt(int A) {
        if (A < 2) {
            return A;
        }
        int start = 1;
        int end = A / 2;
        int result = 0;
        return binarySearch(A, start, end, result);
    }

    int binarySearch(int A, int start, int end, int result) {
        if (start > end) {
            return result;
        }
        long mid = start + (end - start) / 2;
        if (mid * mid == A) {
            return (int) mid;
        } else if (mid * mid < A) {
            result = (int) mid;
            return binarySearch(A, (int) mid + 1, end, result);
        } else {
            return binarySearch(A, start, (int) mid - 1, result);
        }
    }

    public static void main(String[] args) {
        SquareRootOfInteger squareRootOfInteger = new SquareRootOfInteger();
        System.out.println(squareRootOfInteger.sqrt(4));
        System.out.println(squareRootOfInteger.sqrt(20));
    }
}

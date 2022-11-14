package com.scaler.dynamicprogramming;

/**
 * Given an integer N. Return count of minimum numbers, sum of whose squares is equal to N.<br/>
 * N = 6, possible combinations are: <br/>
 * 1^2 + 1^2 + 1^2 + 1^2 + 1^2 + 1^2 => 6 numbers<br/>
 * 1^2 + 1^2 + 2^2 => 3 numbers<br/>
 * So, minimum count of numbers is 3 (i.e. 1,1,2).
 *
 * @author sudhir on 25-Apr-2020
 */
public class MinimumNumberOfSquares {
    public int countMinSquaresRecursive(int A) {
        if (A <= 3) {
            return A;
        }
        int n = (int) Math.sqrt(A);
        int result = A;
        for (int x = 1; x <= n; x++) {
            int temp = x * x;
            if (temp <= A) {
                result = Math.min(result, 1 + countMinSquaresRecursive(A - temp));
            } else {
                break;
            }
        }
        return result;
    }

    public int countMinSquaresDP(int A) {
        if (A <= 3) {
            return A;
        }
        // minimum[i] represents minimum count of numbers whose sum of squares is equal to A.
        int[] minNumber = new int[A + 1];
        minNumber[0] = 0;
        minNumber[1] = 1;
        minNumber[2] = 2;
        minNumber[3] = 3;
        for (int i = 4; i <= A; i++) {
            // For any number i, maximum count of numbers whose sum of squares is equal to A will be i.
            minNumber[i] = i;
            int n = (int) Math.sqrt(i);
            for (int x = 1; x <= n; x++) {
                int temp = x * x;
                if (temp <= i) {
                    minNumber[i] = Math.min(minNumber[i], 1 + minNumber[i - temp]);
                } else {
                    break;
                }
            }
        }
        return minNumber[A];
    }

    public static void main(String[] args) {
        MinimumNumberOfSquares m = new MinimumNumberOfSquares();
        int n1 = 6;
        System.out.println(m.countMinSquaresRecursive(n1));
        System.out.println(m.countMinSquaresDP(n1));
        int n2 = 4;
        System.out.println(m.countMinSquaresRecursive(n2));
        System.out.println(m.countMinSquaresDP(n2));
    }
}

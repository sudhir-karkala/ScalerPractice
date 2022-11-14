package com.scaler.contests;

import java.util.ArrayList;

/**
 * Given a positive integer A.
 * Return an array of minimum length whose elements represent the powers of 3
 * and the sum of all the elements is equal to A.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= A <= 10^9
 * <p>
 * Input Format
 * <p>
 * Single argument is an integer A.
 * <p>
 * Output Format
 * <p>
 * Return an array of minimum length of powers of 3 whose elements sums to A.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * 13
 * <p>
 * Input 2:
 * <p>
 * 3
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * [1, 3, 9]
 * <p>
 * Output 2:
 * <p>
 * [3]
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * 3^0 = 1, 3^1 = 3, 3^2 = 9.
 * Also, 1 + 3 + 9 = 13. Here A = 13 which can be represented as the sum of 1, 3 and 9.
 *
 * @author sudhir on 17-Nov-2020
 */
public class PowersOf3 {
    public ArrayList<Integer> solve(int A) {
        int target = A;
        ArrayList<Integer> result = new ArrayList<>();
        int num = 1;
        // find the largest power of 3 <= target
        while (num < target) {
            while ((num * 3) <= target) {
                num *= 3;
            }
            result.add(num);
            target -= num;
            num = 1;
        }
        if (num == target) {
            result.add(num);
        }
        return result;
    }

    public static void main(String[] args) {
        PowersOf3 p = new PowersOf3();
        int a1 = 33;
        int a2 = 411;
        System.out.println(p.solve(a1));
        System.out.println(p.solve(a2));
    }
}

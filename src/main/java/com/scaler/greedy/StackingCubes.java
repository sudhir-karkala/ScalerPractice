package com.scaler.greedy;

/**
 * You are given A cubes that are of the same size. You are allowed to stack cubes on top of each other.<br/>
 * You should choose P cubes from the A cubes, such that you should stack all the remaining cubes
 * on top of the P cubes, and each of the P cubes should have equal number of cubes on top of them.<br/>
 * Find and return the number of ways you can stack cubes on top of each other in the given manner.<br/>
 * NOTE: Every cube has to be stacked, you cannot have unstacked cubes. No stack can consist of a total of just one cube.<br/>
 * 2 <= A <= 10^5
 *
 * @author sudhir on 4/6/20
 */
public class StackingCubes {
    public int solve(int A) {
        // Example: A = 6, the answer is the count of divisors < A, which are 1, 2, 3, totalling 3.
        int count = 1;
        int sqrtOfA = (int) Math.sqrt(A);
        for (int i = 2; i <= sqrtOfA; i++) {
            if (A % i == 0) {
                count += 2;
            }
        }
        if (sqrtOfA * sqrtOfA == A) {
            count--;
        }
        return count;
    }

    public static void main(String[] args) {
        StackingCubes s = new StackingCubes();
        int a1 = 6;
        int a2 = 2;
        System.out.println(s.solve(a1));
        System.out.println(s.solve(a2));
    }
}

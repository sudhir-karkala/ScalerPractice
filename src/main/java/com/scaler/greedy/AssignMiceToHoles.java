package com.scaler.greedy;

import java.util.Arrays;

/**
 * There are N Mice and N holes that are placed in a straight line. Each hole can accommodate only 1 mouse.<br/>
 * The positions of Mice are denoted by array A and the position of holes are denoted by array B.<br/>
 * A mouse can stay at his position, move one step right from x to x + 1, or move one step left from x to x - 1.<br/>
 * Any of these moves consumes 1 minute.<br/>
 * Assign mice to holes so that the time when the last mouse gets inside a hole is minimized.<br/>
 * 1 <= N <= 10^5, -10^9 <= A[i], B[i] <= 10^9 <br/>
 * Return an integer denoting the minimum time when the last mouse gets inside the holes.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * A = [-4, 2, 3]
 * B = [0, -2, 4]
 * <p>
 * Input 2:
 * A = [-2]
 * B = [-6]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * 2
 * Output 2:
 * 4
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Assign the mouse at position (-4 to -2), (2 to 0) and (3 to 4).
 * The number of moves required will be 2, 2 and 1 respectively.
 * So, the time taken will be 2.
 * <p>
 * Explanation 2:
 * <p>
 * Assign the mouse at position -2 to -6.
 * The number of moves required will be 4.
 * So, the time taken will be 4.
 *
 * @author sudhir on 5/6/20
 */
public class AssignMiceToHoles {
    public int mice(int[] A, int[] B) {
        // The idea is to assign mice to holes which are as closest to them as possible.
        // To achieve this, we can sort both the arrays so that mice at index i is assigned to hole at index i.
        // The time taken by every mice i is abs(A[i] - B[i]) and we need to return the max of them since all
        // mice start travelling to the respective holes at the same time.
        Arrays.sort(A);
        Arrays.sort(B);
        int moves = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            moves = Math.max(moves, Math.abs(A[i] - B[i]));
        }
        return moves;
    }

    public static void main(String[] args) {
        AssignMiceToHoles assignMiceToHoles = new AssignMiceToHoles();
        int[] a1 = {-4, 2, 3};
        int[] b1 = {0, -2, 4};
        System.out.println(assignMiceToHoles.mice(a1, b1));
        int[] a2 = {-2};
        int[] b2 = {-6};
        System.out.println(assignMiceToHoles.mice(a2, b2));
    }
}

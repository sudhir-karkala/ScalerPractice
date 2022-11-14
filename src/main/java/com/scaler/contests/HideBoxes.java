package com.scaler.contests;

import java.util.Arrays;

/**
 * There are N cubical boxes and the edge length of boxes is given by an array A of size N. <br/>
 * A box can hold another box inside it, if and only if the length of a box is at least twice as large than another box. <br/>
 * There can be atmost one box inside any other box. The box which is inside another box is not visible. <br/>
 * You have to minimize the number of boxes visible.
 *
 * @author sudhir on 22-Apr-2020
 */
public class HideBoxes {
    // Since at most N/2 boxes are not visible, we can sort the array A with edges length.
    // We can assume that the first half of the boxes do not hold any other box,
    // and the last half of the boxes are not held by any other box.
    // So we can split the boxes in two sets, such that the first set contains the box whose size is in smaller half
    // and the second set contains the box whose size is in larger half, and use an easy greedy algorithm.

    public int solve(int[] A) {
        Arrays.sort(A);
        int n = A.length;
        int i = 0;
        int j = n / 2;
        int result = n;
        while (j < n && i < n / 2) {
            if (2 * A[i] <= A[j]) {// ith box will fit in jth box, so reduce total boxes by 1 and increase i and j
                result--;
                i++;
                j++;
            } else {// increase jth box size hoping that ith box will fit in jth box
                j++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        HideBoxes h = new HideBoxes();
        int[] a = {1, 2, 2, 4, 3};
        System.out.println(h.solve(a));
    }
}

package com.scaler.stacks;

import java.util.Stack;

/**
 * Given an array of integers A describing ranking of trucks. Your task is to insert these rank in another array B by some means of operations such that B is sorted in ascending order. For performing these operations you can use one stack C. In one Operation you can perform any of the following steps:
 * <ul>
 * <li>
 * Remove the first element from A and append at the back of C.
 * </li>
 * <li>
 * Remove the last element from C and append at the back of B.
 * </li>
 * <li>
 * Remove the first element from A and append at the back of B.
 * </li>
 * </ul>
 * You can perform these operations any number of times (possibly zero). Return 1 if B can be formed in ascending order using some operations else return 0.
 *
 * @author sudhir on 24-Mar-2020
 */
public class OrderThem {
    private Stack<Integer> B = new Stack<>();
    private Stack<Integer> C = new Stack<>();

    public int solve(int[] A) {
        int i = 0;
        while (i < A.length) {
            if (C.isEmpty() || C.peek() >= A[i]) {
                C.push(A[i]);
                i++;
            } else {
                B.push(C.pop());
            }
        }
        while (!C.isEmpty()) {
            B.push(C.pop());
        }

        //check if B is in sorted order
        if (B.isEmpty()) {
            return 0;
        }
        int start = B.pop();

        while (!B.isEmpty()) {
            int temp = B.pop();
            if (start < temp) {
                return 0;
            }
            start = temp;
        }
        return 1;
    }

    public static void main(String[] args) {
        OrderThem o = new OrderThem();
        int[] a1 = {5, 1, 2, 4, 3};
        System.out.println(o.solve(a1));
        int[] a2 = {5, 1, 3, 4, 2};
        System.out.println(o.solve(a2));
    }
}

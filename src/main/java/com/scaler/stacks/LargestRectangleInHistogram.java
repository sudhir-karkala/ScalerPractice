package com.scaler.stacks;

import java.util.Stack;

/**
 * Given an array of integers A of size N. A represents a histogram i.e A[i] denotes height of the ith histogram's bar. <br/>
 * Width of each bar is 1.<br/>
 * Find the area of largest rectangle in the histogram.
 *
 * @author sudhir on 02-May-2020
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] A) {
        // array to hold index of previous smaller element for every A[i]
        int[] prev = new int[A.length];
        // array to hold index of next smaller element for every A[i]
        int[] next = new int[A.length];
        // if prev smaller is not available store -1.
        prev[0] = -1;
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for (int i = 1; i < A.length; i++) {
            while (!st.isEmpty() && A[st.peek()] >= A[i]) {
                st.pop();
            }
            if (st.isEmpty()) {
                prev[i] = -1;
            } else {
                prev[i] = st.peek();
            }
            st.push(i);
        }

        // if next smaller is not available, store size of the array as the answer
        next[A.length - 1] = A.length;
        st.clear();
        st.push(A.length - 1);
        for (int i = A.length - 2; i >= 0; i--) {
            while (!st.isEmpty() && A[st.peek()] >= A[i]) {
                st.pop();
            }
            if (st.isEmpty()) {
                next[i] = A.length;
            } else {
                next[i] = st.peek();
            }
            st.push(i);
        }
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            // for example: for given array(2, 1, 5, 6, 2, 3), consider array element 6(i = 3).
            // It's prev[i] = 2 and next[i] = 4. So the width of the histogram will be in between indices 2 and 4 i.e.
            // (2 + 1) = a and (4 - 1) = b and the width will be (b - a) + 1 and height will be 6.
            // so area = ((b - a) + 1) * 6
            int area = ((next[i] - 1) - (prev[i] + 1) + 1) * A[i];
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram l = new LargestRectangleInHistogram();
        int[] a = {2, 1, 5, 6, 2, 3};
        System.out.println(l.largestRectangleArea(a));
    }

}

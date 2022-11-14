package com.scaler.dynamicprogramming;

import java.util.Stack;

/**
 * Given a 2-D binary matrix A of size N x M filled with 0's and 1's, find the largest rectangle
 * containing all ones and return its area.
 * <p>
 * 1 <= N, M <= 100
 *
 * @author sudhir on 03-Aug-2020
 */
public class MaxRectangleInBinaryMatrix {
    public int maximalRectangle(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        // visualize the bars having base in that row which is currently considered.
        // i.e. if row element has value 6, it means the bar is of height 6 from that row.
        // Hence, we can calculate cumulative height from the given row elements by iterating each row.
        // In any row, if any element has value zero, it means that bar is of height zero from that base.
        // Hence, we do not sum up the values from the previous row for such elements.
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 1) {
                    A[i][j] += A[i - 1][j];
                }
            }
        }
        // consider histogram for each row and calculate maximum histogram
        int maxArea = Integer.MIN_VALUE;
        for (int row = 0; row < n; row++) {
            int[] prev = new int[m];
            int[] next = new int[m];
            prev[0] = -1;
            Stack<Integer> st = new Stack<>();
            st.push(0);
            for (int j = 1; j < m; j++) {
                while (!st.isEmpty() && A[row][st.peek()] >= A[row][j]) {
                    st.pop();
                }
                if (st.isEmpty()) {
                    prev[j] = -1;
                } else {
                    prev[j] = st.peek();
                }
                st.push(j);
            }
            // if next smaller is not available, store size of the array as the answer
            next[m - 1] = m;
            st.clear();
            st.push(m - 1);
            for (int j = m - 2; j >= 0; j--) {
                while (!st.isEmpty() && A[row][st.peek()] >= A[row][j]) {
                    st.pop();
                }
                if (st.isEmpty()) {
                    next[j] = m;
                } else {
                    next[j] = st.peek();
                }
                st.push(j);
            }
            // calculate area and update maxArea.
            for (int j = 0; j < m; j++) {
                int area = ((next[j] - 1) - (prev[j] + 1) + 1) * A[row][j];
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        MaxRectangleInBinaryMatrix m = new MaxRectangleInBinaryMatrix();
        int[][] a1 = {{1, 1, 1}, {0, 1, 1}, {1, 0, 0}};
        int[][] a2 = {{0, 1, 0}, {1, 1, 1}};
        System.out.println(m.maximalRectangle(a1));
        System.out.println(m.maximalRectangle(a2));
    }
}

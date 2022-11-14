package com.scaler.stacks;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an array, find the nearest smaller element G[i] for every element A[i]
 * in the array such that the element has an index smaller than i.
 *
 * @author sudhir on 02-May-2020
 */
public class NearestSmallerElement {
    public int[] prevSmaller(int[] A) {
        int[] result = new int[A.length];
        result[0] = -1;
        Stack<Integer> st = new Stack<>();
        st.push(A[0]);
        for (int i = 1; i < A.length; i++) {
            // if the stack top element is greater than the current element, then pop that element and do this
            // as long as the condition holds.
            while (!st.isEmpty() && st.peek() >= A[i]) {
                st.pop();
            }
            // if there isn't smaller element before the current element, then set -1 in the resultant array position
            if (st.isEmpty()) {
                result[i] = -1;
            } else {
                // else set the stack top element as the smaller element in that array position
                result[i] = st.peek();
            }
            // push the current array element onto the stack as it could be a candidate of smaller elements for future elements
            st.push(A[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        NearestSmallerElement n = new NearestSmallerElement();
        int[] a1 = {4, 5, 2, 10, 8};
        System.out.println(Arrays.toString(n.prevSmaller(a1)));
    }
}

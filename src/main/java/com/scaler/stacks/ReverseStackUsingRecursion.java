package com.scaler.stacks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Given a stack of integers A. You are required to reverse the stack using recursion. <br/>
 * You are not allowed to use loop constructs like while, for..etc, <br/>
 * Return A after reversing it using recursion. NOTE: It is mandatory to reverse A using recursion.
 *
 * @author sudhir on 02-May-2020
 */
public class ReverseStackUsingRecursion {
    public ArrayList<Integer> solve(ArrayList<Integer> A) {
        Stack<Integer> st = new Stack<>();
        st.addAll(A);
        reverse(st);
        return new ArrayList<>(st);
    }

    private void reverse(Stack<Integer> st) {
        if (st.isEmpty()) {
            return;
        }
        int temp = st.pop();
        reverse(st);
        insertAtBottom(st, temp);
    }

    private void insertAtBottom(Stack<Integer> st, int temp) {
        if (st.isEmpty()) {
            st.push(temp);
        } else {
            int t = st.pop();
            insertAtBottom(st, temp);
            st.push(t);
        }
    }

    public static void main(String[] args) {
        ReverseStackUsingRecursion r = new ReverseStackUsingRecursion();
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(r.solve(list));
    }
}

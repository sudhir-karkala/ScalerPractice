package com.scaler.stacks;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a stack of integers A, sort it using another stack. Return the array of integers after sorting the stack using another stack.
 *
 * @author sudhir on 23-Mar-2020
 */
public class SortStackUsingAnotherStack {
    private Stack<Integer> mainStack = new Stack<>();

    public int[] solve(int[] A) {
        for (int i = 0; i < A.length; i++) {
            sortInserted(mainStack, A[i]);
        }
        int[] result = new int[A.length];
        int i = A.length - 1;
        while (!mainStack.isEmpty()) {
            result[i--] = mainStack.pop();
        }
        return result;
    }

    private void sortInserted(Stack<Integer> mainStack, int element) {
        if (mainStack.isEmpty()) {
            mainStack.push(element);
        } else {
            if (mainStack.peek() <= element) {
                mainStack.push(element);
            } else {
                int temp = mainStack.pop();
                sortInserted(mainStack, element);
                mainStack.push(temp);
            }
        }
    }

    public static void main(String[] args) {
        SortStackUsingAnotherStack s = new SortStackUsingAnotherStack();
        int[] a = {5, 17, 100, 11};
        System.out.println(Arrays.toString(s.solve(a)));
    }
}

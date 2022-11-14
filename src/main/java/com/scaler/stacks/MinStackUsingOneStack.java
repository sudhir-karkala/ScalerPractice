package com.scaler.stacks;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * Implement this in O(1) space.
 *
 * @author sudhir on 22-Mar-2020
 */
public class MinStackUsingOneStack {
    private Stack<Integer> mainStack = new Stack<>();
    private int minElement = 0;

    public void push(int x) {
        if (mainStack.isEmpty()) {
            mainStack.push(x);
            minElement = x;
        } else {
            if (x >= minElement) {
                mainStack.push(x);
            } else {
                mainStack.push(2 * x - minElement);
                minElement = x;
            }
        }
    }

    public void pop() {
        if (!mainStack.isEmpty()) {
            int x = mainStack.pop();
            if (x < minElement) {
                minElement = 2 * minElement - x;
            }
        }
    }

    public int top() {
        if (mainStack.isEmpty()) {
            return -1;
        }
        if (mainStack.peek() < minElement) {
            return minElement;
        }
        return mainStack.peek();
    }

    public int getMin() {
        if (mainStack.isEmpty()) {
            return -1;
        }
        return minElement;
    }

    public static void main(String[] args) {
        MinStackUsingOneStack minStack = new MinStackUsingOneStack();
        minStack.push(10);
        minStack.push(5);
        System.out.println(minStack.getMin());
        minStack.push(11);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }
}

package com.scaler.stacks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * You are given a matrix A which represent operations of size N x 2. Assume initially you have a stack-like data structure you have to perform operations on it.
 * Operations are of two types:
 * <ol>
 * <li>
 * 1 x: push an integer x onto the stack and return -1
 * </li>
 * <li>
 * 2 0: remove and return the most frequent element in the stack.
 * </li>
 * </ol>
 * If there is a tie for the most frequent element, the element closest to the top of the stack is removed and returned.
 * A[i][0] describes the type of operation to be performed. A[i][1] describe the element x or 0 corresponding to the operation performed.
 *
 * @author sudhir on 24-Mar-2020
 */
public class MaximumFrequencyStack {
    // map of element->frequency
    private Map<Integer, Integer> freqMap = new HashMap<>();
    // map of frequency to stack of elements with that frequency. example: if element 7 has frequency 3, then element 7
    // will be present in all keys from 1 to 3. i.e. for keys 1,2,3.
    // This means freqGroup[i] will store information for ith copy of element x.
    private Map<Integer, Stack<Integer>> freqGroup = new HashMap<>();
    private int maxFreq = 0;

    public int[] solve(int[][] A) {
        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            if (A[i][0] == 1) {
                result[i] = push(A[i][1]);
            } else {
                result[i] = pop();
            }
        }
        return result;
    }

    private int push(int element) {
        int f = freqMap.getOrDefault(element, 0) + 1;
        freqMap.put(element, f);
        if (maxFreq < f) {
            maxFreq = f;
        }
        freqGroup.computeIfAbsent(f, s -> new Stack<>()).push(element);
        return -1;
    }

    private int pop() {
        int element = freqGroup.get(maxFreq).pop();
        freqMap.put(element, freqMap.get(element) - 1);
        if (freqGroup.get(maxFreq).isEmpty()) {
            maxFreq--;
        }
        return element;
    }

    public static void main(String[] args) {
        MaximumFrequencyStack m = new MaximumFrequencyStack();
        int[][] a1 = {{1, 5}, {1, 7}, {1, 5}, {1, 7}, {1, 4}, {1, 5}, {2, 0}, {2, 0}, {2, 0}, {2, 0}};
        int[][] a2 = {{1, 5}, {2, 0}, {1, 4}};
        System.out.println(Arrays.toString(m.solve(a1)));
        System.out.println(Arrays.toString(m.solve(a2)));
    }
}

package com.scaler.recursionandbacktracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string A consisting of lowercase English alphabets and parentheses '(' and '). <br/>
 * Remove the minimum number of invalid parentheses in order to make the input string valid. <br/>
 * Return all possible results. You can return the results in any order.<br/>
 * Input 1: A = ""()())()" <br/>
 * Output 1: ["()()()", "(())()"] <br/>
 *
 * @author sudhir on 31-Mar-2020
 */
public class RemoveInvalidParentheses {
    private Set<String> set = new HashSet<>();
    private int minRemoved = Integer.MAX_VALUE;

    public ArrayList<String> solve(String A) {
        reset();
        int n = A.length();
        int leftCount = 0;
        int rightCount = 0;
        int removalCount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        solve(A, n, leftCount, rightCount, removalCount, stringBuilder, 0);
        return new ArrayList<>(set);
    }

    private void reset() {
        set.clear();
        minRemoved = Integer.MAX_VALUE;
    }

    private void solve(String A, int n, int leftCount, int rightCount, int removalCount, StringBuilder builder, int index) {
        if (index == n) {
            if (leftCount == rightCount) {
                if (removalCount <= minRemoved) {
                    String str = builder.toString();
                    if (removalCount < minRemoved) {
                        minRemoved = removalCount;
                        set.clear();
                    }
                    set.add(str);
                }


            }
            return;
        }
        char currChar = A.charAt(index);
        int len = builder.length();
        if (currChar != '(' && currChar != ')') {
            builder.append(currChar);
            solve(A, n, leftCount, rightCount, removalCount, builder, index + 1);
            builder.deleteCharAt(len);
        } else {
            //leave the current character and recurse
            solve(A, n, leftCount, rightCount, removalCount + 1, builder, index + 1);
            builder.append(currChar);
            if (currChar == '(') {
                //consider this character and recurse
                solve(A, n, leftCount + 1, rightCount, removalCount, builder, index + 1);
            } else if (rightCount < leftCount) {//this is for ')' character
                //consider this character only when rightCount < leftCount and recurse
                solve(A, n, leftCount, rightCount + 1, removalCount, builder, index + 1);
            }
            builder.deleteCharAt(len);
        }
    }

    public static void main(String[] args) {
        RemoveInvalidParentheses r = new RemoveInvalidParentheses();
        String a = "()())()";
        String b = "()))";
        String c = "(a)())()";
        System.out.println(r.solve(a));
        System.out.println(r.solve(b));
        System.out.println(r.solve(c));
    }
}

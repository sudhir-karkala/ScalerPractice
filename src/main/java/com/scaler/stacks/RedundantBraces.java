package com.scaler.stacks;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Given a string A denoting an expression. It contains the following operators '+', '-', '*', '/'. <br/>
 * Check whether A has redundant braces or not. Return 1 if A has redundant braces, else return 0. <br/>
 * Note: A will be always a valid expression.
 *
 * @author sudhir on 02-May-2020
 */
public class RedundantBraces {
    // approach 1: by maintaining count of brackets and operator and checking if brackets count is greater than operator count
    public int braces(String A) {
        int operator = 0;
        int brackets = 0;
        int i = 0;
        int n = A.length();
        while (i < n) {
            if (A.charAt(i) == '+' || A.charAt(i) == '-' || A.charAt(i) == '*' || A.charAt(i) == '/') {
                operator++;
            }
            // this check is to cover cases like these : (a) which should return 1
            if (A.charAt(i) == '(' && (i + 2) < n && A.charAt(i + 2) == ')') {
                return 1;
            }
            if (A.charAt(i) == '(') {
                brackets++;
            }
            i++;
        }
        if (brackets > operator) {
            return 1;
        }
        // we observe that brackets count should be less than or equal to operator count which should return 0
        return 0;
    }

    // approach 2: Using stack: The basic crux is that we push '(' and operator to the stack
    // when we encounter ')' character we check if the top is an operator or not.
    // If it's not an operator, it means it is an '(' character which covers cases like "(a)", "((a+b))" etc
    // which are redundant. If top is an operator, then we pop and also pop '(' from the stack
    // and proceed further with the remaining part of the string.
    public int bracesUsingStack(String A) {
        Stack<Character> st = new Stack<>();
        Set<Character> operatorSet = new HashSet<>();
        operatorSet.add('+');
        operatorSet.add('-');
        operatorSet.add('*');
        operatorSet.add('/');
        int i = 0;
        int n = A.length();
        while (i < n) {
            // if the character is an operator or opening bracket, then push it to the stack
            if (A.charAt(i) == '(' || operatorSet.contains(A.charAt(i))) {
                st.push(A.charAt(i));
            } else {
                // if character is a closing bracket
                if (A.charAt(i) == ')') {
                    // check if stack top is an operator or not. If yes, then it's a valid case, else it's redundant
                    if (!st.isEmpty() && operatorSet.contains(st.peek())) {
                        st.pop();
                    } else {
                        return 1;
                    }
                    // popping '(' character from the stack
                    st.pop();
                }
            }
            i++;
        }
        return 0;
    }


    public static void main(String[] args) {
        RedundantBraces r = new RedundantBraces();
        String s1 = "((a + b))";
        String s2 = "(a + (a + b))";
        System.out.println(r.braces(s1));
        System.out.println(r.braces(s2));
        System.out.println(r.bracesUsingStack(s1));
        System.out.println(r.bracesUsingStack(s2));
    }
}

package com.scaler.stacks;

import java.util.Stack;

/**
 * An arithmetic expression is given by a character array A of size N. <br/>
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation. <br/>
 * Valid operators are +, -, *, /. Each operand may be an integer or operator.<br/>
 * 1 <= N <= 10^5
 *
 * @author sudhir on 02-May-2020
 */
public class EvaluateExpression {
    public int evalRPN(String[] A) {
        Stack<Integer> st = new Stack<>();
        int i = 0;
        int n = A.length;
        while (i < n) {
            if (isOperator(A[i])) {
                int op2 = st.pop();
                int op1 = st.pop();
                switch (A[i]) {
                    case "+":
                        st.push(op1 + op2);
                        break;
                    case "-":
                        st.push(op1 - op2);
                        break;
                    case "*":
                        st.push(op1 * op2);
                        break;
                    case "/":
                        st.push(op1 / op2);
                        break;
                }
            } else {
                st.push(Integer.parseInt(A[i]));
            }
            i++;
        }
        return st.pop();
    }

    private boolean isOperator(String str) {
        return "+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str);
    }

    public static void main(String[] args) {
        EvaluateExpression e = new EvaluateExpression();
        String[] s1 = {"2", "1", "+", "3", "*"};
        String[] s2 = {"4", "13", "5", "/", "+"};
        System.out.println(e.evalRPN(s1));
        System.out.println(e.evalRPN(s2));
    }
}

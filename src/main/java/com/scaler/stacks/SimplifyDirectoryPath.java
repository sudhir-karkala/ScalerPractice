package com.scaler.stacks;

import java.util.Stack;

/**
 * Given a string A representing an absolute path for a file (Unix-style).
 * Return the string A after simplifying the absolute path.
 * Note: Absolute path always begin with '/' ( root directory ).
 * Path will not have whitespace characters.
 * <ol>
 * <li>
 * Input 1: A = "/home/", Output 1: "/home"
 * </li>
 * <li>
 * Input 2: A = "/a/./b/../../c/", Output 2: "/c"
 * </li>
 * <li>
 * Input 3: A = "/a//b//c//////d", Output 3: "/a/b/c/d"
 * </li>
 * <li>
 * Input 4: A = "/a/../.././../../.", Output 4: "/"
 * </li>
 * <li>
 * Input 5: A = "/a/./b/./c/./d/", Output 5: "/a/b/c/d"
 * </li>
 * </ol>
 *
 * @author sudhir on 23-Mar-2020
 */
public class SimplifyDirectoryPath {
    public String simplifyPath(String A) {
        Stack<String> st = new Stack<>();
        int len = A.length();
        String result = "";
        for (int i = 0; i < len; i++) {
            result = "";
            while (i < len && A.charAt(i) == '/') {
                i++;
            }

            while (i < len && A.charAt(i) != '/') {
                result += A.charAt(i);
                i++;
            }
            if (result.equals(".")) {
                continue;
            } else if (result.equals("..")) {
                if (!st.isEmpty()) {
                    st.pop();
                }
            } else {
                if (result.length() != 0) {
                    st.push(result);
                }
            }
        }

        //pop one by one from the stack and construct path
        Stack<String> temp = new Stack<>();
        StringBuilder path = new StringBuilder();
        while (!st.isEmpty()) {
            temp.push(st.pop());
        }
        path.append("/");
        while (!temp.isEmpty()) {
            if (temp.size() != 1) {
                path.append(temp.pop());
                path.append("/");
            } else {
                path.append(temp.pop());
            }
        }
        return path.toString();
    }

    public static void main(String[] args) {
        SimplifyDirectoryPath s = new SimplifyDirectoryPath();
        String a1 = "/a//b//c//////d";
        String a2 = "/a/../.././../../.";
        String a3 = "/a/./b/../../c/";
        String a4 = "/home/";
        String a5 = "/a/./b/./c/./d/";
        String a6 = "/./.././ykt/xhp/nka/eyo/blr";
        System.out.println(s.simplifyPath(a1));
        System.out.println(s.simplifyPath(a2));
        System.out.println(s.simplifyPath(a3));
        System.out.println(s.simplifyPath(a4));
        System.out.println(s.simplifyPath(a5));
        System.out.println(s.simplifyPath(a6));

    }
}

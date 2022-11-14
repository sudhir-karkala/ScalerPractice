package com.scaler.stacks;

import java.util.*;

/**
 * Given a string A consisting of lowercase English alphabets.
 * Find and return lexicographically smallest string B after removing duplicate letters from A
 * so that every letter appears once and only once.
 *
 * @author sudhir on 02-May-2020
 */
public class RemoveDuplicateLetters {
    public String solve(String A) {
        Stack<Character> st = new Stack<>();
        Set<Character> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        int n = A.length();
        while (i < n) {
            map.put(A.charAt(i), map.getOrDefault(A.charAt(i), 0) + 1);
            i++;
        }
        i = 0;
        while (i < n) {
            if (st.isEmpty()) {
                // initially stack will be empty. so we simply push the character to the stack, add it to the set and
                // update the count in the map.
                st.push(A.charAt(i));
                set.add(A.charAt(i));
                map.put(A.charAt(i), map.get(A.charAt(i)) - 1);
            } else {
                if (!set.contains(A.charAt(i))) {
                    // if the current character is lexicographically smaller than the stack top
                    // we simply add the current character to the set and push it to the stack
                    // and update the count in the map.
                    if (st.peek() <= A.charAt(i)) {
                        st.push(A.charAt(i));
                        set.add(A.charAt(i));
                        map.put(A.charAt(i), map.get(A.charAt(i)) - 1);
                    } else {
                        // as long as stack top character is lexicographically greater than the incoming character,
                        // we pop that character from the stack because we know that we can add that character later.
                        // This inference can be made as we remove only if its count is greater than zero.
                        while (!st.isEmpty() && st.peek() > A.charAt(i) && map.get(st.peek()) > 0) {
                            set.remove(st.pop());
                        }
                        st.push(A.charAt(i));
                        set.add(A.charAt(i));
                        map.put(A.charAt(i), map.get(A.charAt(i)) - 1);
                    }
                } else {
                    // means that current character is present in the set. So, we should not consider
                    // this character now. Just decrement the count from the map.
                    map.put(A.charAt(i), map.get(A.charAt(i)) - 1);
                }
            }
            i++;
        }
        StringBuilder builder = new StringBuilder();
        while (!st.isEmpty()) {
            builder.append(st.pop());
        }
        return builder.reverse().toString();
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters r = new RemoveDuplicateLetters();
        String s1 = "cbacdcbc";
        String s2 = "bcabc";
        System.out.println(r.solve(s1));
        System.out.println(r.solve(s2));
    }
}

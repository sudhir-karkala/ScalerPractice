package com.scaler.queues;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Given a string A denoting a stream of lowercase alphabets. You have to make new string B. <br/>
 * B is formed such that we have to find first non-repeating character each time a character is inserted to the stream
 * and append it at the end to B. if no non-repeating character is found then append '#' at the end of B.<br/>
 * Return a string B after processing the stream of lowercase alphabets A.
 *
 * @author sudhir on 10-May-2020
 */
public class FirstNonRepeatingCharacterInAStreamOfCharacters {
    public String solve(String A) {
        Map<Integer, Integer> charCountsMap = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        int n = A.length();
        for (int i = 0; i < n; i++) {
            if (!charCountsMap.containsKey(A.charAt(i) - 'a')) {
                queue.offer(A.charAt(i));
            }
            // keep track of character counts in the hashmap
            // queue is used to maintain the sequence of characters in the stream.
            // when the queue is peeked to retrieve a character, we check its frequency and if it is 1, that means
            // it is the first non-repeating character, otherwise it is polled till we get such character
            // or queue becomes empty.
            charCountsMap.put(A.charAt(i) - 'a', charCountsMap.getOrDefault(A.charAt(i) - 'a', 0) + 1);
            while (!queue.isEmpty() && charCountsMap.get(queue.peek() - 'a') > 1) {
                queue.poll();
            }
            if (queue.isEmpty()) {
                builder.append('#');
            } else {
                builder.append(queue.peek());
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        FirstNonRepeatingCharacterInAStreamOfCharacters f = new FirstNonRepeatingCharacterInAStreamOfCharacters();
        String str = "abadbc";
        System.out.println(f.solve(str));
    }
}

package com.scaler.hashing;

/**
 * Given a string A and a string T, find the window with minimum length in A
 * which will contain all the characters in T in linear time complexity.<br/>
 * Note that when the count of a character C in T is N, then the count of C in minimum window in A should be at least N. <br/>
 * Note: If there is no such window in A that covers all characters in T, return the empty string.<br/>
 * If there are multiple such windows, return the first occurring minimum window ( with minimum start index )
 *
 * @author sudhir on 19-Apr-2020
 */
public class WindowString {
    public String minWindow(String A, String T) {
        int n = T.length();
        int m = A.length();
        // counter is to keep track of how many characters need to be matched against the input string A.
        int counter = T.length();
        int[] map = new int[128];
        for (int i = 0; i < n; i++) {
            map[T.charAt(i)]++;
        }
        int start = 0;
        int end = 0;
        int minStart = 0;
        int minLen = Integer.MAX_VALUE;
        while (end < m) {
            // if character exists in the hashmap which means it is from the string T, so decrement counter.
            if (map[A.charAt(end)] > 0) {
                counter--;
            }
            // decrease the count of that character
            map[A.charAt(end)]--;
            end++;
            // when the counter becomes 0, it means we get the window which has all the required characters.
            // so now we try to shrink the window.
            while (counter == 0) {
                // update minimum length
                if (minLen > (end - start)) {
                    minStart = start;
                    minLen = end - start;
                }
                // as we leave out characters pointed by start variable, we update the hashmap.
                map[A.charAt(start)]++;
                // when the count of that character becomes > 0, it means that these are characters from the string T,
                // so increment counter variable.
                // For characters not present in string T, when we decrement counts in the hashmap above,
                // it will move to negative values. so incrementing it will move the counts toward 0.
                if (map[A.charAt(start)] > 0) {
                    counter++;
                }
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : A.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        WindowString windowString = new WindowString();
        String A = "ADOBECODEBANC";
        String T = "ABC";
        System.out.println(windowString.minWindow(A, T));
    }
}

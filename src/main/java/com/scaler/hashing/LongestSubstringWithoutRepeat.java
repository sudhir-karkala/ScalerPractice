package com.scaler.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string A, find the length of the longest substring without repeating characters.
 *
 * @author sudhir on 19-Apr-2020
 */
public class LongestSubstringWithoutRepeat {
    // approach 1: Using hash set to check if a character is repeated.
    public int lengthOfLongestSubstring(String A) {
        int maxLength = Integer.MIN_VALUE;
        int start, end;
        int n = A.length();
        start = 0;
        end = 0;
        Set<Character> set = new HashSet<>();
        while (start <= end && end < n) {
            if (!set.contains(A.charAt(end))) {
                set.add(A.charAt(end));
            } else {
                while (start <= end && set.contains(A.charAt(end))) {
                    set.remove(A.charAt(start));
                    start++;
                }
                set.add(A.charAt(end));
            }
            end++;
            maxLength = Math.max(maxLength, end - start);
        }
        return maxLength;
    }

    // approach 2: Use hash map to store counts of each character. When the count becomes > 1, we move start pointer until count becomes 1.
    public int lengthOfLongestSubstring2(String A) {
        int maxLength = Integer.MIN_VALUE;
        int start, end;
        int n = A.length();
        start = 0;
        end = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (start <= end && end < n) {
            map.put(A.charAt(end), map.getOrDefault(A.charAt(end), 0) + 1);
            if (map.get(A.charAt(end)) > 1) {
                while (start <= end && map.get(A.charAt(end)) > 1) {
                    map.put(A.charAt(start), map.get(A.charAt(start)) - 1);
                    start++;
                }
            }
            end++;
            maxLength = Math.max(maxLength, end - start);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeat l = new LongestSubstringWithoutRepeat();
        String A = "abcabcbb";
        System.out.println(l.lengthOfLongestSubstring(A));
        System.out.println(l.lengthOfLongestSubstring2(A));
    }
}

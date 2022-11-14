package com.scaler.dynamicprogramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string A made up of multiple brackets of type "<>" , "[]" , "()" and "{}"
 * find the length of the longest substring which forms a balanced string . Conditions for a string to be balanced :<br/>
 * Blank string is balanced ( However blank string will not be provided as a test case ).<br/>
 * If B is balanced : (B) , [B] , {B} and <B> are also balanced.<br/>
 * If B1 and B2 are balanced then B1B2 i.e the string formed by concatenating B1 and B2 is also balanced.
 *
 * @author sudhir on 26-Apr-2020
 */
public class LongestBalancedSubstring {
    public int LBSlength(final String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put('>', '<');
        map.put('}', '{');
        map.put(']', '[');
        map.put(')', '(');
        Set<Character> closing = new HashSet<>();
        closing.add('>');
        closing.add(')');
        closing.add(']');
        closing.add('}');
        int[] len = new int[n];
        len[0] = 0;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (closing.contains(s.charAt(i))) {
                char closingChar = s.charAt(i);
                char openChar = map.get(s.charAt(i));
                if (s.charAt(i - 1) == openChar) {
                    len[i] = (i - 2 >= 0 ? len[i - 2] : 0) + 2;
                } else {
                    if (closing.contains(s.charAt(i - 1))) {
                        if ((i - len[i - 1]) > 0 && s.charAt(i - len[i - 1] - 1) == map.get(closingChar)) {
                            len[i] = len[i - 1] + 2 + ((i - len[i - 1] - 2) >= 0 ? len[i - len[i - 1] - 2] : 0);
                        }
                    }
                }
            }
            ans = Math.max(ans, len[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        LongestBalancedSubstring l = new LongestBalancedSubstring();
        String s1 = "((())";
        String s2 = "[()]";
        String s3 = "[(])";
        String s4 = "<<<<>";
        System.out.println(l.LBSlength(s1));
        System.out.println(l.LBSlength(s2));
        System.out.println(l.LBSlength(s3));
        System.out.println(l.LBSlength(s4));
    }
}

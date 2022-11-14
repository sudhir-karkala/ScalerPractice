package com.scaler.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string A representing a roman numeral. Convert A into integer. A is guaranteed to be within the range from 1 to 3999.
 *
 * @author sudhir on 26-Mar-2020
 */
public class RomanToInteger {
    public int romanToInt(String A) {
        Map<Character, Integer> mapRomanToInt = new HashMap<>();
        mapRomanToInt.put('M', 1000);
        mapRomanToInt.put('D', 500);
        mapRomanToInt.put('C', 100);
        mapRomanToInt.put('L', 50);
        mapRomanToInt.put('X', 10);
        mapRomanToInt.put('V', 5);
        mapRomanToInt.put('I', 1);
        int len = A.length();
        int prev = -1;
        int cur = 0;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            cur = mapRomanToInt.get(A.charAt(i));
            sum += cur;
            if (prev != -1 && cur > prev) {
                sum -= 2 * mapRomanToInt.get(A.charAt(i - 1));
            }
            prev = cur;
        }
        return sum;
    }

    public static void main(String[] args) {
        RomanToInteger rT = new RomanToInteger();
        String A = "MMMDXLIX";
        String B = "MCMIV";
        System.out.println(rT.romanToInt(A));
        System.out.println(rT.romanToInt(B));
    }
}

package com.scaler.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer A, convert it to a roman numeral, and return a string corresponding to its roman numeral version.
 * <p>
 * Example: 3549 = MMMDXLIX
 * </p>
 *
 * @author sudhir on 26-Mar-2020
 */
public class IntegerToRoman {
    public String intToRoman(int A) {
        Map<Integer, String> intToRoman = new HashMap<>();
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        intToRoman.put(1000, "M");
        intToRoman.put(900, "CM");
        intToRoman.put(500, "D");
        intToRoman.put(400, "CD");
        intToRoman.put(100, "C");
        intToRoman.put(90, "XC");
        intToRoman.put(50, "L");
        intToRoman.put(40, "XL");
        intToRoman.put(10, "X");
        intToRoman.put(9, "IX");
        intToRoman.put(5, "V");
        intToRoman.put(4, "IV");
        intToRoman.put(1, "I");
        StringBuilder builder = new StringBuilder();
        while (A > 0) {
            int i = 0;
            for (i = 0; i < 13; i++) {
                if (nums[i] <= A) {
                    break;
                }
            }
            int divisor = nums[i];
            String toAdd = intToRoman.get(divisor);
            int times = A / divisor;
            for (int j = 1; j <= times; j++) {
                builder.append(toAdd);
            }
            A = A % divisor;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman iR = new IntegerToRoman();
        int A = 3549;
        int B = 4000;
        System.out.println(iR.intToRoman(A));
        System.out.println(iR.intToRoman(B));
    }
}

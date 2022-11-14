package com.scaler.recursionandbacktracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a digit string, return all possible letter combinations that the number could represent.<br/>
 * The digit 0 maps to 0 itself. The digit 1 maps to 1 itself.
 *
 * @author sudhir on 29-Mar-2020
 */
public class LetterPhone {
    public ArrayList<String> letterCombinations(String A) {
        ArrayList<String> result = new ArrayList<>();
        int n = A.length();
        Map<Character, String> map = new HashMap<>();
        preprocessing(map);
        StringBuilder builder = new StringBuilder();
        generateCombinations(A, n, map, result, builder, 0);
        return result;
    }

    private void preprocessing(Map<Character, String> map) {
        map.put('0', "0");
        map.put('1', "1");
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
    }

    private void generateCombinations(String A, int n, Map<Character, String> map, ArrayList<String> result, StringBuilder builder, int start) {
        if (n == builder.length()) {
            result.add(builder.toString());
            return;
        }
        int len = map.get(A.charAt(start)).length();
        for (int i = 0; i < len; i++) {
            builder.append(map.get(A.charAt(start)).charAt(i));
            generateCombinations(A, n, map, result, builder, start + 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    public static void main(String[] args) {
        LetterPhone lp = new LetterPhone();
        String a = "23";
        System.out.println(lp.letterCombinations(a));
    }
}

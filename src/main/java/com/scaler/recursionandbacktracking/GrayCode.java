package com.scaler.recursionandbacktracking;

import java.util.ArrayList;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code.
 * A gray code sequence must begin with 0.<br/>
 * Example: For n = 3, the sequence is [0, 1, 3, 2, 6, 7, 5, 4]
 *
 * @author sudhir on 28-Mar-2020
 */
public class GrayCode {
    public ArrayList<Integer> grayCode(int a) {
        ArrayList<Integer> codes = new ArrayList<>();
        codes.add(0);
        codes.add(1);
        int start = 2;
        if (start <= a) {
            generateCodes(codes, start, a);
        }
        return codes;
    }

    private void generateCodes(ArrayList<Integer> codes, int start, int a) {
        if (start > a) {
            return;
        }
        int size = codes.size();
        for (int i = size - 1; i >= 0; i--) {
            codes.add((1 << (start - 1)) + codes.get(i));
        }
        generateCodes(codes, start + 1, a);
    }

    public static void main(String[] args) {
        GrayCode grayCode = new GrayCode();
        int a = 3;
        System.out.println(grayCode.grayCode(a));
    }
}

package com.scaler.maths;

/**
 * Given a positive integer A, return its corresponding column title as appear in an Excel sheet.
 *
 * 1 <= A <= 10^9
 * @author sudhir on 24-May-2020
 */
public class ExcelColumnTitle {
    public String convertToTitle(int A) {
        StringBuilder result = new StringBuilder();
        while (A > 0) {
            int rem = A % 26;
            if (rem == 0) {
                result.append('Z');
                A = A / 26 - 1;
            } else {
                result.append((char) (rem + 'A' - 1));
                A = A / 26;
            }
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        ExcelColumnTitle excelColumnTitle = new ExcelColumnTitle();
        int num1 = 3;
        int num2 = 27;
        System.out.println(excelColumnTitle.convertToTitle(num1));
        System.out.println(excelColumnTitle.convertToTitle(num2));
    }
}

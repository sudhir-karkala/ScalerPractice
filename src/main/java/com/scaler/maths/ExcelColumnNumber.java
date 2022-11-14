package com.scaler.maths;

/**
 * Given a column title as appears in an Excel sheet, return its corresponding column number.<br/>
 * 1 <= length of the column title <= 5
 *
 * @author sudhir on 24-May-2020
 */
public class ExcelColumnNumber {
    public int titleToNumber(String A) {
        int base = 1;
        int index = A.length() - 1;
        int columnNumber = 0;
        while (index >= 0) {
            columnNumber += (A.charAt(index) - 'A' + 1) * base;
            base = base * 26;
            index--;

        }
        return columnNumber;
    }

    public static void main(String[] args) {
        ExcelColumnNumber excelColumnNumber = new ExcelColumnNumber();
        String str1 = "ABCD";
        System.out.println(excelColumnNumber.titleToNumber(str1));
    }
}

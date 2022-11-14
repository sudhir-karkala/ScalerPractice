package com.scaler.strings;

/**
 * The count-and-say sequence is the sequence of integers beginning as follows:<br/>
 * 1, 11, 21, 1211, 111221, ...<br/>
 * 1 is read off as one 1 or 11. 11 is read off as two 1s or 21. 21 is read off as one 2, then one 1 or 1211.<br/>
 * Given an integer n, generate the nth sequence. <br/>
 * Note: The sequence of integers will be represented as a string.<br/>
 * Example: if n = 2, the sequence is 11.
 *
 * @author sudhir on 23-Apr-2020
 */
public class CountAndSay {
    public String countAndSay(int A) {
        if (A == 1) {
            return "1";
        } else if (A == 2) {
            return "11";
        } else {
            StringBuilder result = new StringBuilder();
            String tmp = "11";
            // generate all sequences from 3 to n
            for (int i = 3; i <= A; i++) {
                // append extra "#" character to increase the string length by 1 so that we can process the last character.
                // for example: 111221. Here when last character '1' is compared with character '#',
                // they are not equal, so count is appended to the string as 1.
                // so basically, '#' is used to compare last character with it's next character for equality.
                tmp += "#";
                int len = tmp.length();
                int count = 1;
                result.setLength(0);
                for (int j = 1; j < len; j++) {
                    if (tmp.charAt(j) != tmp.charAt(j - 1)) {
                        result.append(count);
                        result.append(tmp.charAt(j - 1));
                        count = 1;
                    } else {
                        count++;
                    }
                }
                tmp = result.toString();
            }
            return result.toString();
        }
    }

    public static void main(String[] args) {
        CountAndSay c = new CountAndSay();
        int n = 5;
        System.out.println(c.countAndSay(n));
    }
}

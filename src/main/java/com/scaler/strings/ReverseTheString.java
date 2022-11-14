package com.scaler.strings;

/**
 * Given a string A. Return the string A after reversing the string word by word.
 * <p>
 * NOTE:A sequence of non-space characters constitutes a word.
 * Your reversed string should not contain leading or trailing spaces, even if it is present in the input string.
 * If there are multiple spaces between words, reduce them to a single space in the reversed string.
 * </p>
 *
 * @author sudhir on 17-Mar-2020
 */
public class ReverseTheString {
    public String solve(String A) {
        char[] ch = A.toCharArray();
        int start = 0;
        int end = 0;
        //remove trailing and leading spaces
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] != ' ') {
                start = i;
                break;
            }
        }
        for (int i = ch.length - 1; i >= 0; i--) {
            if (ch[i] != ' ') {
                end = i;
                break;
            }
        }

        ch = A.substring(start, end + 1).toCharArray();

        //remove extra spaces in between words
        char[] result = new char[ch.length];
        boolean firstSpace = true;
        int i = -1;
        int j = 0;
        while (++i < ch.length) {
            if (ch[i] != ' ') {
                result[j++] = ch[i];
                if (!firstSpace) {
                    firstSpace = true;
                }
            } else if (ch[i] == ' ') {
                if (firstSpace) {
                    result[j++] = ch[i];
                    firstSpace = false;
                }
            }
        }
        //copy the result into a string
        String r = String.valueOf(result, 0, j);
        //swap the characters in the string
        ch = r.toCharArray();
        i = 0;
        j = ch.length - 1;
        while (i < j) {
            char temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;
            i++;
            j--;
        }
        //swap characters in each word
        start = 0;
        end = 0;
        while (start <= end && end < ch.length) {
            if (ch[end] == ' ' || ch[end] == '\0') {
                i = start;
                j = end - 1;
                while (i < j) {
                    char temp = ch[i];
                    ch[i] = ch[j];
                    ch[j] = temp;
                    i++;
                    j--;
                }
                end++;
                start = end;
            } else {
                end++;
            }
        }

        end--;
        while (start < end) {
            char temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;
            start++;
            end--;
        }
        return String.valueOf(ch, 0, ch.length);
    }

    public static void main(String[] args) {
        ReverseTheString r = new ReverseTheString();
        String s1 = "the sky is    blue   ";
        String s2 = "this is ib";
        System.out.println(r.solve(s1));
        System.out.println(r.solve(s2));
    }
}

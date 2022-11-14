package com.scaler.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a string A representing json object.
 * <p>
 * Return an array of string denoting json object with proper indentation.
 * </p>
 * <p>
 * Rules for proper indentation:
 * <li>
 * Every inner brace should increase one indentation to the following lines.
 * </li>
 * <li>
 * Every close brace should decrease one indentation to the same line and the following lines.
 * </li>
 * <li>
 * The indents can be increased with an additional '\t'
 * </li>
 * </p>
 * Note: [] and {} are only acceptable braces in this case. Assume for this problem that space characters can be done away with.
 *
 * @author sudhir on 27-Mar-2020
 */
public class PrettyJson {
    public String[] prettyJSON(String A) {
        List<String> output = new ArrayList<>();
        int i = 0;
        int len = A.length();
        int indentations = 0;
        while (i < len) {
            switch (A.charAt(i)) {
                case '{':
                case '[':
                    output.add(getIndentations(indentations++) + A.charAt(i));
                    break;
                case ',':
                    output.set(output.size() - 1, output.get(output.size() - 1) + A.charAt(i));
                    break;
                case '}':
                case ']':
                    output.add(getIndentations(--indentations) + A.charAt(i));
                    break;
                default:
                    // characters other than {, [, }, ] and ,
                    StringBuilder builder = new StringBuilder();
                    while (i < len && !isBracketOrComma(A.charAt(i))) {
                        builder.append(A.charAt(i));
                        i++;
                    }
                    output.add(getIndentations(indentations) + builder.toString());
                    i--;
            }
            i++;
        }
        return output.toArray(new String[output.size()]);
    }

    private boolean isBracketOrComma(Character ch) {
        return ch == '[' || ch == '{' || ch == '}' || ch == ']' || ch == ',';
    }

    private String getIndentations(int indentations) {
        if (indentations < 1) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        while (indentations > 0) {
            builder.append("\t");
            indentations--;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        PrettyJson pj = new PrettyJson();
        String A = "{A:\"B\",C:{D:\"E\",F:{G:\"H\",I:\"J\"}}}";
        String B = "[\"foo\", {\"bar\":[\"baz\",null,1.0,2]}]";
        System.out.println(Arrays.toString(pj.prettyJSON(A)));
        System.out.println(Arrays.toString(pj.prettyJSON(B)));
    }
}

package com.scaler.arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * You have a newspaper which has A lines to read. <br/>
 * You decided to start reading the 1st line from Monday. <br/>
 * You are given a schedule, B, where B[i] = number of lines you can read on that day. <br/>
 * You are very strict in reading the newspaper and you will read as much as you can every day. <br/>
 * Determine and return, which day of the week you will read the last line of the newspaper.<br/>
 * 1 <= A <= 1000 0 <= B[i] <= 1000
 *
 * @author sudhir on 04-Apr-2020
 */
public class ReadingNewspaper {
    public int solve(int A, ArrayList<Integer> B) {
        int i = 0;
        int remaining = A - B.get(0);
        while (remaining > 0) {
            i = (i + 1) % 7;
            remaining = remaining - B.get(i);
        }
        return (i + 1);
    }

    public static void main(String[] args) {
        ReadingNewspaper r = new ReadingNewspaper();
        int A = 100;
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(15, 20, 20, 15, 10, 30, 45));
        System.out.println(r.solve(A, B));
    }
}

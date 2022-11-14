package com.scaler.recursionandbacktracking;

import java.util.ArrayList;

/**
 * Given two integers A and B, return all possible combinations of B numbers out of 1 2 3 ... A . <br/>
 * Make sure the combinations are sorted. To elaborate,<br/>
 * Within every entry, elements should be sorted. [1, 4] is a valid entry while [4, 1] is not.<br/>
 * Entries should be sorted within themselves.
 *
 * @author sudhir on 29-Mar-2020
 */
public class Combinations {
    public ArrayList<ArrayList<Integer>> combine(int A, int B) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        combine(result, temp, A, B, 1, 0);
        return result;
    }

    private void combine(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> temp, int a, int b, int start, int count) {
        if (count == b) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i <= a; i++) {
            temp.add(i);
            combine(result, temp, a, b, i + 1, count + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        Combinations c = new Combinations();
        int a = 4;
        int b = 3;
        System.out.println(c.combine(a, b));
    }
}

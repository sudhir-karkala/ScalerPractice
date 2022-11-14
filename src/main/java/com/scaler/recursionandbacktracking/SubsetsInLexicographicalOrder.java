package com.scaler.recursionandbacktracking;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Given a set of distinct integers, S, return all possible subsets.
 * <p>
 * NOTE:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * Also, the subsets should be sorted in ascending ( lexicographic ) order.
 * The list is not necessarily sorted.
 * </p>
 *
 * @author sudhir on 06-Mar-2020
 */
public class SubsetsInLexicographicalOrder {
    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
        Collections.sort(A);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        subsets(result, A, temp, A.size(), 0);
        return result;
    }

    public void subsets(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> A, ArrayList<Integer> temp, int n, int i) {
        result.add(new ArrayList<>(temp));
        for (int index = i; index < n; index++) {
            temp.add(A.get(index));
            subsets(result, A, temp, n, index + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        SubsetsInLexicographicalOrder s = new SubsetsInLexicographicalOrder();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(s.subsets(list));
    }
}

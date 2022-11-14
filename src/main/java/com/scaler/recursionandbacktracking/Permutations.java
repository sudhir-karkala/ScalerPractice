package com.scaler.recursionandbacktracking;

import java.util.ArrayList;

/**
 * <p>
 * Given a collection of numbers, return all possible permutations.
 * </p>
 * Example: [1,2,3] will have the following permutations: [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]
 *
 * @author sudhir on 07-Mar-2020
 */
public class Permutations {
    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        permutation(result, A, 0, A.size());
        return result;
    }

    private void swap(ArrayList<Integer> a, int i, int j) {
        int temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    private void permutation(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> A, int start, int n) {
        if (start >= n) {
            result.add(new ArrayList<>(A));
            return;
        }
        for (int i = start; i < n; i++) {
            swap(A, start, i);
            permutation(result, A, start + 1, n);
            swap(A, start, i);
        }
    }

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(permutations.permute(list));
    }
}

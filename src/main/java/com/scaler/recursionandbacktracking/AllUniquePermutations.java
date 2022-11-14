package com.scaler.recursionandbacktracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * </p>
 * Example : [1,1,2] have the following unique permutations: [1,1,2], [1,2,1], [2,1,1]
 *
 * @author sudhir on 08-Mar-2020
 */
public class AllUniquePermutations {
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
        Set<Integer> set = new HashSet<>();
        for (int i = start; i < n; i++) {
            if (!set.contains(A.get(i))) {
                set.add(A.get(i));
                swap(A, start, i);
                permutation(result, A, start + 1, n);
                swap(A, start, i);
            }
        }
    }

    public static void main(String[] args) {
        AllUniquePermutations permutations = new AllUniquePermutations();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(permutations.permute(list));
    }
}

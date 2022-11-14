package com.scaler.recursionandbacktracking;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Given an array of size N of candidate numbers A and a target number B.
 * Return all unique combinations in A where the candidate numbers sums to B.
 * Each number in A may only be used once in the combination. Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 = a2 = … = ak).
 * The solution set must not contain duplicate combinations.
 *
 * @author sudhir on 06-Mar-2020
 */
public class CombinationSumII {
    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> a, int b) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Collections.sort(a);
        ArrayList<Integer> temp = new ArrayList<>();
        combinationSum(a, result, temp, b, 0);
        return result;
    }

    private void combinationSum(ArrayList<Integer> candidates, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> temp, int target, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < candidates.size(); i++) {
            // when we keep adding elements to the combination in a recursive manner,
            // it's correct to add next element with the same value as previous
            // because it can result in a valid combination (This is taken care as (i != start) will be false and element will be added successfully).
            // But once we complete recursion, we remove the last added element from the combination and then try with the next element.
            // (Here (i != start) will be true and duplicate check will be done).
            // Here if the next element has the same value, then the subsequent recursive calls results in the same combination
            // which is a duplicate combination.
            // Hence we filter out those cases.
            if (i != start && (candidates.get(i - 1) == candidates.get(i))) {
                continue;
            }
            if (candidates.get(i) > target) {
                break;
            }
            temp.add(candidates.get(i));
            combinationSum(candidates, result, temp, target - candidates.get(i), i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSumII combinationSumII = new CombinationSumII();
        ArrayList<Integer> arrList = new ArrayList<>();
        arrList.add(10);
        arrList.add(1);
        arrList.add(2);
        arrList.add(7);
        arrList.add(6);
        arrList.add(1);
        arrList.add(5);
        int b = 8;
        System.out.println(combinationSumII.combinationSum(arrList, b));
    }
}

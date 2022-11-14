package com.scaler.hashing;

import java.util.*;

/**
 * <b>Title: Sort an array according to the order defined by another array</b><br/><br/>
 * Given two array of integers A and B, Sort A in such a way that the relative order among the
 * elements will be the same as those are in B. <br/><br/>
 * For the elements not present in B, append them at last in sorted order.<br/>
 * Return the array A after sorting from the above method. <br/><br/>
 * Note: Elements of B are unique.
 *
 * @author sudhir on 29-Mar-2020
 */
public class SortArrayUsingAnotherArrayOrdering {
    public int[] solve(int[] A, int[] B) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }
        int i = 0;
        for (Integer num : B) {
            int size = map.getOrDefault(num, 0);
            for (int j = 0; j < size; j++) {
                A[i++] = num;
            }
            map.remove(num);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for (Integer num : list) {
            int size = map.get(num);
            for (int j = 0; j < size; j++) {
                A[i++] = num;
            }
            map.remove(num);
        }
        return A;
    }

    public static void main(String[] args) {
        SortArrayUsingAnotherArrayOrdering s = new SortArrayUsingAnotherArrayOrdering();
        int[] a1 = {1, 2, 3, 4, 5};
        int[] b1 = {5, 4, 2};
        int[] a2 = {5, 17, 100, 11};
        int[] b2 = {1, 100};
        System.out.println(Arrays.toString(s.solve(a1, b1)));
        System.out.println(Arrays.toString(s.solve(a2, b2)));
    }
}

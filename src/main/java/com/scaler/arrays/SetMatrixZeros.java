package com.scaler.arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a matrix, A of size M x N of 0s and 1s. If an element is 0, set its entire row and column to 0. <br/>
 * Note: This will be evaluated on the extra memory used. Try to minimize the space and time complexity.
 *
 * @author sudhir on 04-Apr-2020
 */
public class SetMatrixZeros {
    public void setZeroes(ArrayList<ArrayList<Integer>> a) {
        int m = a.size();
        int n = a.get(0).size();
        int[] row = new int[m];
        Arrays.fill(row, 1);
        int[] col = new int[n];
        Arrays.fill(col, 1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (a.get(i).get(j) == 0) {
                    row[i] = 0;
                    col[j] = 0;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] == 0 || col[j] == 0) {
                    a.get(i).set(j, 0);
                }
            }
        }
    }

    public static void main(String[] args) {
        SetMatrixZeros s = new SetMatrixZeros();
        ArrayList<ArrayList<Integer>> aList = new ArrayList<>();
        ArrayList<Integer> a1 = new ArrayList<>();
        a1.add(1);
        a1.add(0);
        a1.add(1);
        aList.add(a1);

        ArrayList<Integer> a2 = new ArrayList<>();
        a2.add(1);
        a2.add(1);
        a2.add(1);
        aList.add(a2);

        ArrayList<Integer> a3 = new ArrayList<>();
        a3.add(1);
        a3.add(1);
        a3.add(1);
        aList.add(a3);
        s.setZeroes(aList);
        System.out.println(aList);

        a1.clear();
        a2.clear();
        a3.clear();
        aList.clear();

        a1.add(1);
        a1.add(0);
        a1.add(1);
        aList.add(a1);

        a2.add(1);
        a2.add(1);
        a2.add(1);
        aList.add(a2);

        a3.add(1);
        a3.add(0);
        a3.add(1);
        aList.add(a3);
        s.setZeroes(aList);
        System.out.println(aList);

    }
}

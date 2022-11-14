package com.scaler.segmenttrees;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an integer array A of size N.
 * <p>
 * You have to perform two types of query, in each query you are given three integers x,y,z.
 * <p>
 * If x = 0, then update A[y] = z.
 * <p>
 * If x = 1, then output the minimum element in the array A between index y and z inclusive.
 * <p>
 * Queries are denoted by a 2-D array B of size M x 3 where B[i][0] denotes x, B[i][1] denotes y, B[i][2] denotes z.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N, M <= 10^5
 * <p>
 * 1 <= A[i] <= 10^9
 * <p>
 * If x = 0, 1<= y <= N and 1 <= z <= 109
 * <p>
 * If x = 1, 1<= y <= z <= N
 * <p>
 * Input Format
 * <p>
 * First argument is an integer array A of size N.
 * <p>
 * Second argument is a 2-D array B of size M x 3 denoting queries.
 * <p>
 * Output Format
 * <p>
 * Return an integer array denoting the output of each query where value of x is 1.
 *
 * @author sudhir on 16-Aug-2020
 */
public class RangeMinimumQuery {
    private int[] segTree;

    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        int n = A.size();
        // find the size of the segment tree needed.
        // if n is a power of 2, then 2*n-1 is the size, else find next power of 2 and find the size.
        int treeSize = 2 * nextPowerOf2(n) - 1;
        segTree = new int[treeSize];
        Arrays.fill(segTree, Integer.MAX_VALUE);
        buildTree(A, 0, 0, n - 1);
        int queriesSize = B.size();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < queriesSize; i++) {
            if (B.get(i).get(0) == 0) {
                // update query
                // B.get(i).get(1) is 1-indexed, decrement by 1 and pass the value to the method
                updateTree(0, 0, n - 1, B.get(i).get(1) - 1, B.get(i).get(2));
            } else {
                // B.get(i).get(1), B.get(i).get(2) are 1-indexed, decrement by 1
                // and pass the values to the method
                result.add(queryTree(0, 0, n - 1, B.get(i).get(1) - 1, B.get(i).get(2) - 1));
            }
        }
        return result;
    }

    private int nextPowerOf2(int n) {
        if (n > 0 && (n & (n - 1)) == 0) {
            return n;
        }
        int p = 1;
        while (p < n) {
            p <<= 1;
        }
        return p;
    }

    private void buildTree(ArrayList<Integer> A, int idx, int start, int end) {
        if (start == end) {
            segTree[idx] = A.get(start);
        } else {
            int mid = start + (end - start) / 2;
            int lc = 2 * idx + 1;// index of left child
            int rc = 2 * idx + 2;// index of right child
            buildTree(A, lc, start, mid);
            buildTree(A, rc, mid + 1, end);
            segTree[idx] = Math.min(segTree[lc], segTree[rc]);
        }
    }

    private int queryTree(int idx, int start, int end, int qleft, int qright) {
        if (qleft > end || qright < start) {// no overlap
            return Integer.MAX_VALUE;
        }
        if (qleft <= start && end <= qright) {// total overlap
            return segTree[idx];
        }
        int mid = start + (end - start) / 2;
        int lc = 2 * idx + 1;
        int rc = 2 * idx + 2;
        return Math.min(queryTree(lc, start, mid, qleft, qright), queryTree(rc, mid + 1, end, qleft, qright));
    }

    private void updateTree(int idx, int start, int end, int id, int value) {
        if (id > end || id < start) {
            return;
        }
        if (start == end) {
            segTree[idx] = value;
        } else {
            int mid = start + (end - start) / 2;
            int lc = 2 * idx + 1;
            int rc = 2 * idx + 2;
            updateTree(lc, start, mid, id, value);
            updateTree(rc, mid + 1, end, id, value);
            segTree[idx] = Math.min(segTree[lc], segTree[rc]);
        }
    }

    public static void main(String[] args) {
        RangeMinimumQuery r = new RangeMinimumQuery();
        ArrayList<Integer> a1 = new ArrayList<>(Arrays.asList(18, 10, 1, 20, 25, 4, 9, 13, 15, 6, 21, 7));
        ArrayList<ArrayList<Integer>> b1 = new ArrayList<>();
        b1.add(new ArrayList<>(Arrays.asList(1, 8, 12)));
        b1.add(new ArrayList<>(Arrays.asList(0, 4, 7)));
        b1.add(new ArrayList<>(Arrays.asList(1, 1, 3)));
        b1.add(new ArrayList<>(Arrays.asList(1, 5, 11)));
        b1.add(new ArrayList<>(Arrays.asList(1, 3, 9)));
        b1.add(new ArrayList<>(Arrays.asList(1, 7, 12)));
        b1.add(new ArrayList<>(Arrays.asList(1, 7, 10)));
        b1.add(new ArrayList<>(Arrays.asList(0, 12, 20)));
        System.out.println(r.solve(a1, b1));
    }
}

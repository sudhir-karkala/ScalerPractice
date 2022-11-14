package com.scaler.segmenttrees;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * You are given an array A of size N consisting of integers.
 * <p>
 * You have to process Q queries of two types on it:
 * <p>
 * i x, change the i-th element of A to x.
 * <p>
 * l r, find the maximum value of (A[i]+A[i+1]...A[j]) over all pairs (i,j) such that l <= i <= j <= r.
 * <p>
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N,Q <= 10^5
 * <p>
 * -1000 <= A[i] <= 1000 (for all i in [1...N])
 * <p>
 * For query of the 1st type,
 * <p>
 * 1 <= i <= N
 * <p>
 * -1000 <= x <= 1000
 * <p>
 * For query of the 2nd type,
 * <p>
 * 1 <= l <= r <= N
 * <p>
 * Input Format
 * <p>
 * The first argument of the input is the array A.
 * <p>
 * The second argument of the input is a 2-D array B containing the description of the queries.
 * <p>
 * Output Format
 * <p>
 * You should return an array of answers to each query of the 2nd type,
 * in the same order they were asked in the input.
 * <p>
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A: [3, -1, 2, -9, -15]
 * <p>
 * B: [
 * [2,1,4],
 * [1,3,7],
 * [2,5,5],
 * [2,1,3]
 * ]
 * <p>
 * Input 2:
 * <p>
 * A: [6, -1, 9]
 * <p>
 * B:  [
 * [2,1,3],
 * [1,2,10],
 * [2,1,3]
 * ]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * [4, -15, 9]
 * <p>
 * Output 2:
 * <p>
 * [14, 25]
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * For the 1st query, the required sum is A[1] + A[2] + A[3] = 4.
 * <p>
 * After the 2nd query, array becomes [3,-1,7,-9,-15].
 * <p>
 * For the 3rd query, there is only one answer possible, -15, which is thus the answer itself.
 * <p>
 * For the 4th query, the required sum is A[1] + A[2] + A[3] = 9.
 * <p>
 * Explanation 2:
 * <p>
 * For the 1st query, the required sum is A[1] + A[2] + A[3] = 14.
 * <p>
 * After the 2nd query, array becomes [6, 10, 9].
 * <p>
 * For the 3rd query, the required sum is A[1] + A[2] + A[3] = 14.
 *
 * @author sudhir on 02-Sep-2020
 */
public class MaxSumQueries {
    private SegTreeNode[] segTreeNodes;

    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        int n = A.size();
        int segTreeSize = 2 * nextPowerOf2(n) - 1;
        segTreeNodes = new SegTreeNode[segTreeSize];
        for (int idx = 0; idx < segTreeSize; idx++) {
            segTreeNodes[idx] = new SegTreeNode();
        }
        buildTree(A, 0, 0, n - 1);
        int queriesSize = B.size();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < queriesSize; i++) {
            if (B.get(i).get(0) == 1) {
                // update query
                // B.get(i).get(1) is 1-indexed, decrement by 1 and pass the value to the method
                updateTree(0, 0, n - 1, B.get(i).get(1) - 1, B.get(i).get(2));
            } else {
                // B.get(i).get(1), B.get(i).get(2) are 1-indexed, decrement by 1
                // and pass the values to the method
                result.add(queryTree(0, 0, n - 1, B.get(i).get(1) - 1, B.get(i).get(2) - 1).maxSum);
            }
        }
        return result;
    }

    private void buildTree(ArrayList<Integer> A, int idx, int start, int end) {
        if (start == end) {
            segTreeNodes[idx].sum = A.get(start);
            segTreeNodes[idx].maxSum = A.get(start);
            segTreeNodes[idx].maxPrefixSum = A.get(start);
            segTreeNodes[idx].maxSuffixSum = A.get(start);
        } else {
            int mid = start + (end - start) / 2;
            int lc = 2 * idx + 1;// index of left child
            int rc = 2 * idx + 2;// index of right child
            buildTree(A, lc, start, mid);
            buildTree(A, rc, mid + 1, end);
            segTreeNodes[idx].sum = segTreeNodes[lc].sum + segTreeNodes[rc].sum;
            segTreeNodes[idx].maxPrefixSum = Math.max(segTreeNodes[lc].maxPrefixSum,
                    segTreeNodes[lc].sum + segTreeNodes[rc].maxPrefixSum);
            segTreeNodes[idx].maxSuffixSum = Math.max(segTreeNodes[rc].maxSuffixSum,
                    segTreeNodes[lc].maxSuffixSum + segTreeNodes[rc].sum);
            segTreeNodes[idx].maxSum = Math.max(segTreeNodes[lc].maxPrefixSum,
                    Math.max(segTreeNodes[rc].maxSuffixSum,
                            Math.max(segTreeNodes[lc].maxSum,
                                    Math.max(segTreeNodes[rc].maxSum,
                                            segTreeNodes[lc].maxSuffixSum + segTreeNodes[rc].maxPrefixSum))));

        }
    }

    private SegTreeNode queryTree(int idx, int start, int end, int qleft, int qright) {
        SegTreeNode resultNode = new SegTreeNode();
        if (qleft > end || qright < start) {// no overlap
            return resultNode;
        }
        if (qleft <= start && end <= qright) {// total overlap
            return segTreeNodes[idx];
        }
        int mid = start + (end - start) / 2;
        int lc = 2 * idx + 1;
        int rc = 2 * idx + 2;
        if (qleft > mid) {
            // right subtree
            return queryTree(rc, mid + 1, end, qleft, qright);
        }
        if (qright <= mid) {
            // left subtree
            return queryTree(lc, start, mid, qleft, qright);
        }
        // else check both left and right subtrees
        SegTreeNode left = queryTree(lc, start, mid, qleft, qright);
        SegTreeNode right = queryTree(rc, mid + 1, end, qleft, qright);
        resultNode.sum = left.sum + right.sum;
        resultNode.maxPrefixSum = Math.max(left.maxPrefixSum,
                left.sum + right.maxPrefixSum);
        resultNode.maxSuffixSum = Math.max(right.maxSuffixSum,
                left.maxSuffixSum + right.sum);
        resultNode.maxSum = Math.max(left.maxPrefixSum,
                Math.max(right.maxSuffixSum,
                        Math.max(left.maxSum,
                                Math.max(right.maxSum,
                                        left.maxSuffixSum + right.maxPrefixSum))));
        return resultNode;
    }

    private void updateTree(int idx, int start, int end, int id, int value) {
        if (id > end || id < start) {
            return;
        }
        if (start == end) {
            segTreeNodes[idx].sum = value;
            segTreeNodes[idx].maxSum = value;
            segTreeNodes[idx].maxPrefixSum = value;
            segTreeNodes[idx].maxSuffixSum = value;
        } else {
            int mid = start + (end - start) / 2;
            int lc = 2 * idx + 1;
            int rc = 2 * idx + 2;
            if (id > mid) {
                // right subtree
                updateTree(rc, mid + 1, end, id, value);
            } else {
                // left subtree
                updateTree(lc, start, mid, id, value);
            }
            segTreeNodes[idx].sum = segTreeNodes[lc].sum + segTreeNodes[rc].sum;
            segTreeNodes[idx].maxPrefixSum = Math.max(segTreeNodes[lc].maxPrefixSum,
                    segTreeNodes[lc].sum + segTreeNodes[rc].maxPrefixSum);
            segTreeNodes[idx].maxSuffixSum = Math.max(segTreeNodes[rc].maxSuffixSum,
                    segTreeNodes[lc].maxSuffixSum + segTreeNodes[rc].sum);
            segTreeNodes[idx].maxSum = Math.max(segTreeNodes[lc].maxPrefixSum,
                    Math.max(segTreeNodes[rc].maxSuffixSum,
                            Math.max(segTreeNodes[lc].maxSum,
                                    Math.max(segTreeNodes[rc].maxSum,
                                            segTreeNodes[lc].maxSuffixSum + segTreeNodes[rc].maxPrefixSum))));
        }
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

    static class SegTreeNode {
        int sum;
        int maxPrefixSum;
        int maxSuffixSum;
        int maxSum;

        SegTreeNode() {
            sum = Integer.MIN_VALUE;
            maxPrefixSum = Integer.MIN_VALUE;
            maxSuffixSum = Integer.MIN_VALUE;
            maxSum = Integer.MIN_VALUE;
        }
    }

    public static void main(String[] args) {
        MaxSumQueries maxSumQueries = new MaxSumQueries();
        ArrayList<Integer> a1 = new ArrayList<>(Arrays.asList(3, -1, 2, -9, -15));
        ArrayList<ArrayList<Integer>> b1 = new ArrayList<>();
        b1.add(new ArrayList<>(Arrays.asList(2, 1, 4)));
        b1.add(new ArrayList<>(Arrays.asList(1, 3, 7)));
        b1.add(new ArrayList<>(Arrays.asList(2, 5, 5)));
        b1.add(new ArrayList<>(Arrays.asList(2, 1, 3)));
        System.out.println(maxSumQueries.solve(a1, b1));

        ArrayList<Integer> a2 = new ArrayList<>(Arrays.asList(6, -1, 9));
        ArrayList<ArrayList<Integer>> b2 = new ArrayList<>();
        b2.add(new ArrayList<>(Arrays.asList(2, 1, 3)));
        b2.add(new ArrayList<>(Arrays.asList(1, 2, 10)));
        b2.add(new ArrayList<>(Arrays.asList(2, 1, 3)));
        System.out.println(maxSumQueries.solve(a2, b2));
    }
}

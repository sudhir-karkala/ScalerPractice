package com.scaler.hashing;

import java.util.*;

/**
 * Given an array A of N integers, find the index of values that satisfy P + Q = R + s, where P,Q,R & S are integers values in the array.<br/>
 * 1) Return the indices `A1 B1 C1 D1`, so that A[A1] + A[B1] = A[C1] + A[D1], A1 < B1, C1 < D1, A1 < C1, B1 != D1, B1 != C1 <br/>
 * 2) If there are more than one solutions, then return the tuple of values which are lexicographical smallest. <br/>
 * Assume we have two solutions<br/>
 * S1 : A1 B1 C1 D1 ( these are values of indices in the array )<br/>
 * S2 : A2 B2 C2 D2<br/>
 * S1 is lexicographically smaller than S2 if:<br/>
 * A1 < A2 OR<br/>
 * A1 = A2 AND B1 < B2 OR<br/>
 * A1 = A2 AND B1 = B2 AND C1 < C2 OR<br/>
 * A1 = A2 AND B1 = B2 AND C1 = C2 AND D1 < D2<br/>
 * Note: indexes returned should be 0-based.
 *
 * @author sudhir on 19-Apr-2020
 */
public class Equal {
    static class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    // Approach 2: We use min heap to get the lexicographically smallest answer
    public int[] equal2(int[] A) {
        Map<Integer, Pair> map = new HashMap<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if ((o1[0] < o2[0]) || (o1[0] == o2[0] && o1[1] < o2[1])
                        || (o1[0] == o2[0] && o1[1] == o2[1] && o1[2] < o2[2])
                        || (o1[0] == o2[0] && o1[1] == o2[1] && o1[2] == o2[2] && o1[3] < o2[3])) {
                    return -1;
                } else if (o1[3] == o2[3]) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int sum = A[i] + A[j];
                if (!map.containsKey(sum)) {
                    map.put(sum, new Pair(i, j));
                } else if (map.get(sum).first != i && map.get(sum).first != j
                        && map.get(sum).second != i && map.get(sum).second != j) {
                    minHeap.offer(new int[]{map.get(sum).first, map.get(sum).second, i, j});
                }
            }
        }
        return minHeap.peek();
    }

    // Approach 1: We keep updating our final answer by checking every answer we receive so as to return lexicographically smallest
    public int[] equal(int[] A) {
        Map<Integer, Pair> map = new HashMap<>();
        int[] finalAns = new int[]{-1, -1, -1, -1};
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int sum = A[i] + A[j];
                if (!map.containsKey(sum)) {
                    map.put(sum, new Pair(i, j));
                } else {
                    // check if the indices are overlapping
                    if (overlaps(map.get(sum).first, map.get(sum).second, i, j)) {
                        continue;
                    }
                    // means there is no overlap
                    // check if the current answer is lexicographically smallest and update accordingly
                    int[] ans = {map.get(sum).first, map.get(sum).second, i, j};
                    boolean shouldReplace = false;
                    if (finalAns[0] == -1) {
                        finalAns[0] = ans[0];
                        finalAns[1] = ans[1];
                        finalAns[2] = ans[2];
                        finalAns[3] = ans[3];
                    } else {
                        for (int index = 0; index < ans.length; index++) {
                            if (ans[index] < finalAns[index]) {// means we have received lexicographically smallest, so replace the final answer
                                shouldReplace = true;
                                break;
                            }
                            if (ans[index] > finalAns[index]) {// means we already have best answer, so do not replace the final answer.
                                break;
                            }
                        }
                    }
                    if (shouldReplace) {
                        finalAns[0] = ans[0];
                        finalAns[1] = ans[1];
                        finalAns[2] = ans[2];
                        finalAns[3] = ans[3];
                    }

                }
            }
        }
        return finalAns;
    }

    private boolean overlaps(int i, int j, int k, int l) {
        if (i == k || i == l || j == k || j == l) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Equal equal = new Equal();
        int[] a1 = {3, 4, 7, 1, 2, 9, 8};
        int[] a2 = {1, 1, 1, 1, 1};
        System.out.println(Arrays.toString(equal.equal(a1)));
        System.out.println(Arrays.toString(equal.equal(a2)));
    }
}

package com.scaler.heaps;

import java.util.*;

/**
 * Given two integers arrays A and B of size N each. <br/>
 * Find the maximum N elements from the sum combinations (Ai + Bj)
 * formed from elements in array A and B.<br/>
 * 1 <= N <= 2 * 10^5, -1000 <= A[i], B[i] <= 1000
 * <p>
 * Input Format
 * <p>
 * First argument is an integer array A.
 * <p>
 * Second argument is an integer array B.
 * <p>
 * <p>
 * <p>
 * Output Format
 * <p>
 * Return an integer array denoting the N maximum element in descending order.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [1, 4, 2, 3]
 * <p>
 * B = [2, 5, 1, 6]
 * <p>
 * Input 2:
 * <p>
 * A = [2, 4, 1, 1]
 * <p>
 * B = [-2, -3, 2, 4]
 * <p>
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * [10, 9, 9, 8]
 * <p>
 * Output 2:
 * <p>
 * [8, 6, 6, 5]
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * 4 maximum elements are 10(6+4), 9(6+3), 9(5+4), 8(6+2).
 * <p>
 * Explanation 2:
 * <p>
 * 4 maximum elements are 8(4+4), 6(4+2), 6(4+2), 5(4+1).
 *
 * @author sudhir on 22-May-2020
 */
public class NMaxPairCombinations {
    class Info {
        int sum;
        Pair pair;

        public Info(int sum, Pair pair) {
            this.sum = sum;
            this.pair = pair;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || obj.getClass() != this.getClass())
                return false;
            Info other = (Info) obj;
            return ((this.sum == other.sum) && (this.pair == other.pair));
        }

        @Override
        public int hashCode() {
            return pair.hashCode();
        }
    }

    class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || obj.getClass() != this.getClass())
                return false;
            Pair other = (Pair) obj;
            return ((this.first == other.first) && (this.second == other.second));
        }

        @Override
        public int hashCode() {
            return (first + " " + second).hashCode();
        }
    }

    public int[] solve(int[] A, int[] B) {
        Set<Pair> set = new HashSet<>();
        PriorityQueue<Info> maxHeap = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return Integer.valueOf(o2.sum).compareTo(Integer.valueOf(o1.sum));
            }
        });
        int n = B.length;
        Arrays.sort(A);
        Arrays.sort(B);
        int[] result = new int[n];
        int resultSize = 0;
        Pair p = new Pair(n - 1, n - 1);
        maxHeap.offer(new Info(A[n - 1] + B[n - 1], p));
        set.add(p);
        while (resultSize < n && !maxHeap.isEmpty()) {
            Info info = maxHeap.poll();
            result[resultSize++] = info.sum;
            int i = info.pair.first;
            int j = info.pair.second;
            Pair p1 = new Pair(i, j - 1);
            Pair p2 = new Pair(i - 1, j);
            if (i >= 0 && j > 0 && !set.contains(p1)) {
                maxHeap.offer(new Info(A[i] + B[j - 1], p1));
                set.add(p1);
            }
            if (i > 0 && j >= 0 && !set.contains(p2)) {
                maxHeap.offer(new Info(A[i - 1] + B[j], p2));
                set.add(p2);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        NMaxPairCombinations n = new NMaxPairCombinations();
        int[] a1 = {1, 4, 2, 3};
        int[] b1 = {2, 5, 1, 6};
        int[] a2 = {2, 4, 1, 1};
        int[] b2 = {-2, -3, 2, 4};
        int[] a3 = {3, 4};
        int[] b3 = {3, 4};
        int[] a4 = {36, 27, -35, 43, -15, 36, 42, -1, -29, 12, -23, 40, 9, 13, -24, -10, -24, 22, -14, -39, 18, 17, -21, 32, -20, 12, -27, 17, -15, -21, -48, -28, 8, 19, 17, 43, 6, -39, -8, -21, 23, -29, -31, 34, -13, 48, -26, -35, 20, -37, -24, 41, 30, 6, 23, 12, 20, 46, 31, -45, -25, 34, -23, -14, -45, -4, -21, -37, 7, -26, 45, 32, -5, -36, 17, -16, 14, -7, 0, 37, -42, 26, 28};
        int[] b4 = {38, 34, -47, 1, 4, 49, -18, 10, 26, 18, -11, -38, -24, 36, 44, -11, 45, 20, -16, 28, 17, -49, 47, -48, -33, 42, 2, 6, -49, 30, 36, -9, 15, 39, -6, -31, -10, -21, -19, -33, 47, 21, 31, 25, -41, -23, 17, 6, 47, 3, 36, 15, -44, 33, -31, -26, -22, 21, -18, -21, -47, -31, 20, 18, -42, -35, -10, -1, 46, -27, -32, -5, -4, 1, -29, 5, 29, 38, 14, -22, -9, 0, 43};
        System.out.println(Arrays.toString(n.solve(a1, b1)));
        System.out.println(Arrays.toString(n.solve(a2, b2)));
        System.out.println(Arrays.toString(n.solve(a3, b3)));
        System.out.println(Arrays.toString(n.solve(a4, b4)));
    }
}

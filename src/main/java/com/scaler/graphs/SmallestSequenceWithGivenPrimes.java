package com.scaler.graphs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Given three prime number(A, B, C) and an integer D.
 * Find the first(smallest) D integers which have only A, B, C or a combination of them as their prime factors.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= A, B, C <= 10000
 * <p>
 * 1 <= D <= 100000
 * <p>
 * Input Format
 * <p>
 * First argument is an integer A.
 * <p>
 * Second argument is an integer B.
 * <p>
 * Third argument is an integer C.
 * <p>
 * Fourth argument is an integer D.
 * <p>
 * Output Format
 * <p>
 * Return an integer array of size D, denoting the first D integers described above.
 * <p>
 * NOTE: The sequence should be sorted in ascending order
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = 2
 * <p>
 * B = 3
 * <p>
 * C = 5
 * <p>
 * D = 5
 * <p>
 * Input 2:
 * <p>
 * A = 3
 * <p>
 * B = 2
 * <p>
 * C = 7
 * <p>
 * D = 3
 * <p>
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * [2, 3, 4, 5, 6]
 * <p>
 * Output 2:
 * <p>
 * [2, 3, 4]
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * 4 = A * A ( 2 * 2 ), 6 = A * B ( 2 * 3 )
 * <p>
 * Explanation 2:
 * <p>
 * 2 has only prime factor 2. Similary 3 has only prime factor 3. 4 = A * A ( 2 * 2 )
 *
 * @author sudhir on 24-Sep-2020
 */
public class SmallestSequenceWithGivenPrimes {
    public int[] solve(int A, int B, int C, int D) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int[] result = new int[D];
        int count = 0;
        int index = 0;
        Set<Integer> set = new HashSet<>();
        if (!set.contains(A)) {
            minHeap.add(A);
            set.add(A);
        }
        if (!set.contains(B)) {
            minHeap.add(B);
            set.add(B);
        }
        if (!set.contains(C)) {
            minHeap.add(C);
            set.add(C);
        }
        while (count < D) {
            if (!minHeap.isEmpty()) {
                int element = minHeap.poll();
                result[index++] = element;
                count++;
                if (!set.contains(element * A)) {
                    minHeap.add(element * A);
                    set.add(element * A);
                }
                if (!set.contains(element * B)) {
                    minHeap.add(element * B);
                    set.add(element * B);
                }
                if (!set.contains(element * C)) {
                    minHeap.add(element * C);
                    set.add(element * C);
                }
            } else {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SmallestSequenceWithGivenPrimes s = new SmallestSequenceWithGivenPrimes();
        int a1 = 2;
        int b1 = 3;
        int c1 = 5;
        int d1 = 5;
        System.out.println(Arrays.toString(s.solve(a1, b1, c1, d1)));
        int a2 = 3;
        int b2 = 2;
        int c2 = 7;
        int d2 = 3;
        System.out.println(Arrays.toString(s.solve(a2, b2, c2, d2)));
    }
}

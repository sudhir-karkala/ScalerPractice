package com.scaler.companyquestions;

import java.util.HashSet;
import java.util.Set;

/**
 * A permutation P is called unstable if it keeps changing every second based on the rule below.
 * Every element of the permutation is changing every second independently following a rule,
 * i.e., after one second every P[i] becomes P[P[i]]
 * <p>
 * Given a permutation, find the minimum time after which it will become stable.
 * If it will never become stable, return -1.
 * <p>
 * For example, given N = 3 and P[3,2,1], In one second it will become [P[P[3]], P[P[2]], P[P[1]] ] = [1, 2, 3].
 * <p>
 * The permutation [1,2,3] is stable as it will not change further after applying the same rule again.
 * <p>
 * <p>
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= |A| <= 200000
 * <p>
 * 1 <= A[i] <= |A|
 * <p>
 * Input Format
 * <p>
 * The first and only argument of the input is the array A
 * <p>
 * Output Format
 * <p>
 * Your function should return a single integer: the minimum time after which it will become stable otherwise -1.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A: [1, 3, 2, 5, 6, 7, 4]
 * <p>
 * Input 2:
 * <p>
 * A: [2, 3, 1, 5, 6, 7, 4]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 2
 * <p>
 * Output 2:
 * <p>
 * -1
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * The array will become stable after two seconds:
 * <p>
 * After one second, the permutation will become  [1, 2, 3, 6, 7, 4, 5]
 * .
 * After two seconds, the permutation will become [1, 2, 3, 4, 5, 6, 7]
 * .
 * <p>
 * Explanation 2:
 * <p>
 * The permutation never becomes stable, thus the answer is -1.
 *
 * @author sudhir on 27-Sep-2020
 */
public class LittlePonnyAndUnstablePermutation {
    /**
     * Algorithm:
     * 1. we need to find the number of connected components in the given array
     * 2. every connected component is a cycle. Record the length of the cycle
     * 3. if any one of the cycle lengths is not a power of 2, then identity permutation is not possible.
     * so return -1.
     * 4. if cycle lengths are a power of 2, then keep track of max cycle length and return log(max_cycle_length)
     * as the answer.
     *
     * @param A
     * @return
     */
    public int solve(int[] A) {
        int n = A.length;
        int[] adj = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            // adj[b] = a means that element 'b' is present in index 'a' of the array
            // for example: arr = [1, 3, 2, 5, 6, 7, 4]
            // adj = [0, 1, 3, 2, 7, 4, 5, 6] => adj[0] is insignificant as we consider 1-based indexing.
            adj[A[i - 1]] = i;
        }
        Set<Integer> set = new HashSet<>();
        int maxCycleLength = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i, visited, adj, set);
            }
            int cycleLength = set.size();
            set.clear();
            if (!isPowerOf2(cycleLength)) {
                return -1;
            }
            maxCycleLength = Math.max(cycleLength, maxCycleLength);
        }
        return (int) (Math.log(maxCycleLength) / Math.log(2));
    }


    private void dfs(int u, boolean[] visited, int[] adj, Set<Integer> set) {
        visited[u] = true;
        set.add(u);
        int v = adj[u];
        while (!visited[v]) {
            visited[v] = true;
            set.add(v);
            v = adj[v];
        }
    }

    private boolean isPowerOf2(int num) {
        return (num & (num - 1)) == 0 ? true : false;
    }

    public static void main(String[] args) {
        LittlePonnyAndUnstablePermutation l = new LittlePonnyAndUnstablePermutation();
        int[] a1 = {1, 3, 2, 5, 6, 7, 4};
        int[] a2 = {2, 3, 1, 5, 6, 7, 4};
        System.out.println(l.solve(a1));
        System.out.println(l.solve(a2));
    }
}

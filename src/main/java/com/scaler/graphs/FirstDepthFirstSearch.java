package com.scaler.graphs;

import java.util.*;

/**
 * You are given n towns (1 to n). All towns are connected via unique directed path as mentioned in the input.
 * <p>
 * Given 2 towns find whether you can reach the first town from the second without repeating any edge.
 * <p>
 * x y : query to find whether x is reachable from y.
 * <p>
 * Input contains an integer array A of size n and 2 integers x and y ( 1 <= x, y <= n ).
 * <p>
 * There exist a directed edge from A[i] to i+1 for every 1 <= i < n. Also, it's guaranteed that A[i] <= i.
 * <p>
 * NOTE: Array A is 0-indexed.
 * <p>
 * 1 <= n <= 100000
 *
 * @author sudhir on 04-Aug-2020
 */
public class FirstDepthFirstSearch {
    private boolean isPathPresent = false;

    public int solve(ArrayList<Integer> A, final int B, final int C) {
        // this check is to return 1 for the case where B=C
        if (B == C) {
            return 1;
        }
        isPathPresent = false;
        Map<Integer, Set<Integer>> adjMap = new HashMap<>();
        int n = A.size();
        for (int i = 0; i < n; i++) {
            adjMap.computeIfAbsent(A.get(i), z -> new HashSet<>()).add(i + 1);
        }
        boolean[] visited = new boolean[n + 1];
        recursiveDfs(adjMap, visited, C, B);
        return isPathPresent ? 1 : 0;
    }

    private void recursiveDfs(Map<Integer, Set<Integer>> adjMap, boolean[] visited, int src, int dest) {
        if (adjMap.containsKey(src)) {
            visited[src] = true;
            Set<Integer> adjValues = adjMap.get(src);
            for (Integer value : adjValues) {
                if (!visited[value]) {
                    if (value == dest) {
                        visited[value] = true;
                        isPathPresent = true;
                        return;
                    } else {
                        recursiveDfs(adjMap, visited, value, dest);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        FirstDepthFirstSearch f = new FirstDepthFirstSearch();
        ArrayList<Integer> a1 = new ArrayList<>(Arrays.asList(1, 1, 2));
        int b1 = 1;
        int c1 = 2;
        ArrayList<Integer> a2 = new ArrayList<>(Arrays.asList(1, 1, 2));
        int b2 = 2;
        int c2 = 1;
        ArrayList<Integer> a3 = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1));
        int b3 = 1;
        int c3 = 1;
        ArrayList<Integer> a4 = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 4, 3, 4, 1));
        int b4 = 7;
        int c4 = 7;
        System.out.println(f.solve(a1, b1, c1));
        System.out.println(f.solve(a2, b2, c2));
        System.out.println(f.solve(a3, b3, c3));
        System.out.println(f.solve(a4, b4, c4));
    }
}

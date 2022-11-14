package com.scaler.graphs;

import java.util.*;

/**
 * There are a total of A courses you have to take, labeled from 1 to A.
 * <p>
 * Some courses may have prerequisites, for example to take course 2
 * you have to first take course 1, which is expressed as a pair: [1,2].
 * <p>
 * So you are given two integer array B and C of same size
 * where for each i (B[i], C[i]) denotes a pair.
 * <p>
 * Given the total number of courses and a list of prerequisite pairs,
 * is it possible for you to finish all courses?
 * <p>
 * Return 1 if it is possible to finish all the courses, or 0 if it is not possible
 * to finish all the courses.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= A <= 6*10^4
 * <p>
 * 1 <= length(B) = length(C) <= 10^5
 * <p>
 * 1 <= B[i], C[i] <= A
 * <p>
 * Input Format
 * <p>
 * The first argument of input contains an integer A, representing the number of courses.
 * <p>
 * The second argument of input contains an integer array, B.
 * <p>
 * The third argument of input contains an integer array, C.
 * <p>
 * Output Format
 * <p>
 * Return 1 if it is possible to finish all the courses, or 0 if it is not possible to finish all the courses.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = 3
 * B = [1, 2]
 * C = [2, 3]
 * <p>
 * Input 2:
 * <p>
 * A = 2
 * B = [1, 2]
 * C = [2, 1]
 * <p>
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 1
 * Output 2:
 * <p>
 * 0
 * <p>
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * It is possible to complete the courses in the following order:
 * 1 -> 2 -> 3
 * <p>
 * Explanation 2:
 * <p>
 * It is not possible to complete all the courses.
 *
 * @author sudhir on 01-Dec-2020
 */
public class PossibilityOfFinishingAllCoursesGivenPreequisites {
    public int solve(int A, ArrayList<Integer> B, ArrayList<Integer> C) {
        int n = B.size();
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjMap.computeIfAbsent(B.get(i), z -> new ArrayList<>()).add(C.get(i));
        }
        if (hasCycle(adjMap, A)) {
            return 0;
        }
        return 1;
    }

    private boolean hasCycle(Map<Integer, List<Integer>> adjMap, int courses) {
        // visited[i] can have 0, 1, 2
        // 0 => not visited, 1 => visited, 2 => visited and processed.
        int[] visited = new int[courses + 1];
        for (int i = 1; i <= courses; i++) {
            if (visited[i] == 0) {
                if (hasCycleUtil(adjMap, visited, i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasCycleUtil(Map<Integer, List<Integer>> adjMap, int[] visited, int node) {
        if (visited[node] == 1) {
            return true;
        }
        if (visited[node] == 2) {
            return false;
        }
        visited[node] = 1;
        if (adjMap.containsKey(node)) {
            List<Integer> adjNodes = adjMap.get(node);
            for (Integer adjNode : adjNodes) {
                if (hasCycleUtil(adjMap, visited, adjNode)) {
                    return true;
                }
            }
        }
        visited[node] = 2;
        return false;
    }

    public static void main(String[] args) {
        PossibilityOfFinishingAllCoursesGivenPreequisites p = new PossibilityOfFinishingAllCoursesGivenPreequisites();
        int a1 = 3;
        ArrayList<Integer> b1 = new ArrayList<>(Arrays.asList(1, 2));
        ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 3));

        int a2 = 2;
        ArrayList<Integer> b2 = new ArrayList<>(Arrays.asList(1, 2));
        ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(2, 1));

        System.out.println(p.solve(a1, b1, c1));
        System.out.println(p.solve(a2, b2, c2));
    }
}

package com.scaler.graphs;

import java.util.*;

/**
 * Given an directed graph having A nodes labelled from 1 to A containing M edges given by matrix B
 * of size M x 2such that there is a edge directed from node B[i][0] to node B[i][1].
 * Find whether a path exists from node 1 to node A. Return 1 if path exists else return 0. <br/>
 * NOTE: There are no self-loops in the graph.<br/>
 * There are no multiple edges between two nodes.<br/>
 * The graph may or may not be connected.<br/>
 * Nodes are numbered from 1 to A.<br/>
 * Your solution will run on multiple test cases. If you are using global variables make sure to clear them
 * <p>
 * Problem Constraints
 * <p>
 * 2 <= A <= 10^5
 * <p>
 * 1 <= M <= min(200000,A*(A-1))
 * <p>
 * 1 <= B[i][0], B[i][1] <= A
 *
 * @author sudhir on 17-May-2020
 */
public class PathInDirectedGraph {
    private boolean pathExists = false;

    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        pathExists = false;
        Map<Integer, Set<Integer>> adjacencyMap = new HashMap<>();
        for (ArrayList<Integer> edge : B) {
            Set<Integer> set = null;
            if (adjacencyMap.containsKey(edge.get(0))) {
                set = adjacencyMap.get(edge.get(0));
            } else {
                set = new HashSet<>();
            }
            set.add(edge.get(1));
            adjacencyMap.put(edge.get(0), set);
        }
        // for simplicity, we can have visited array of size A+1 so that we can do 1-based indexing
        boolean visited[] = new boolean[A + 1];
//        recursiveDFS(adjacencyMap, visited, 1, A);
        iterativeDFS(adjacencyMap, visited, 1, A);
        return pathExists ? 1 : 0;
    }

    /**
     * This recursive implementation can also be used. But it can give stackoverflow issues if
     * due to recursive call limit(~6000 to 7000 steps).
     *
     * @param map
     * @param visited
     * @param source
     * @param destination
     */
    private void recursiveDFS(Map<Integer, Set<Integer>> map, boolean[] visited, int source, int destination) {
        if (map.containsKey(source)) {
            visited[source] = true;
            Set<Integer> adjacentNodes = map.get(source);
            for (Integer adjacentNode : adjacentNodes) {
                if (!visited[adjacentNode]) {
                    if (adjacentNode == destination) {
                        pathExists = true;
                        return;
                    } else {
                        recursiveDFS(map, visited, adjacentNode, destination);
                    }
                }
            }
        }
    }

    /**
     * This iterative implementation of DFS passes all test cases and time limit in scaler platform.
     *
     * @param map
     * @param visited
     * @param source
     * @param destination
     */
    private void iterativeDFS(Map<Integer, Set<Integer>> map, boolean[] visited, int source, int destination) {
        Stack<Integer> stack = new Stack<>();
        stack.push(source);
        while (!stack.isEmpty()) {
            int dest = stack.pop();
            visited[dest] = true;
            if (dest == destination) {
                pathExists = true;
                return;
            }
            if (map.containsKey(dest)) {
                Set<Integer> adjacentNodes = map.get(dest);
                for (Integer adjacentNode : adjacentNodes) {
                    if (!visited[adjacentNode]) {
                        stack.push(adjacentNode);
                    }
                }
            }
        }
    }
}

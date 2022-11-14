package com.scaler.graphs;

import java.util.*;

/**
 * Given an directed graph having A nodes. A matrix B of size M x 2 is given which
 * represents the M edges such that there is a edge directed from node B[i][0] to node B[i][1].
 * <p>
 * Find whether the graph contains a cycle or not, return 1 if cycle is present else return 0.
 * <p>
 * NOTE:
 * <p>
 * The cycle must contain at least two nodes.
 * <p>
 * There are no self-loops in the graph.
 * <p>
 * There are no multiple edges between two nodes.
 * <p>
 * The graph may or may not be connected.
 * <p>
 * Nodes are numbered from 1 to A.
 * <p>
 * Your solution will run on multiple test cases. If you are using global variables make sure to clear them.
 * <p>
 * Problem Constraints
 * <p>
 * 2 <= A <= 10^5
 * <p>
 * 1 <= M <= min(200000,A*(A-1))
 * <p>
 * 1 <= B[i][0], B[i][1] <= A
 * <p>
 * Input Format
 * <p>
 * The first argument given is an integer A representing the number of nodes in the graph.
 * <p>
 * The second argument given a matrix B of size M x 2 which represents the M edges
 * such that there is a edge directed from node B[i][0] to node B[i][1].
 * <p>
 * Output Format
 * <p>
 * Return 1 if cycle is present else return 0.
 *
 * @author sudhir on 11-Aug-2020
 */
public class CycleInDirectedGraph {
    public int solve(int A, int[][] B) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            adjMap.computeIfAbsent(B[i][0], z -> new ArrayList<>()).add(B[i][1]);
        }
        // WHITE : Vertex is not processed yet. Initially, all vertices are WHITE.
        // GRAY: Vertex is being processed (DFS for this vertex has started,
        // but not finished which means that all descendants (in DFS tree)
        // of this vertex are not processed yet.
        // BLACK : Vertex is processed.
        Set<Integer> whiteSet = new HashSet<>();
        Set<Integer> graySet = new HashSet<>();
        Set<Integer> blackSet = new HashSet<>();
        for (int i = 1; i <= A; i++) {
            whiteSet.add(i);
        }
        while (whiteSet.size() > 0) {
            int currentVertex = whiteSet.iterator().next();
            if (dfs(currentVertex, whiteSet, graySet, blackSet, adjMap)) {
                return 1;
            }
        }
        return 0;
    }

    private boolean dfs(int currentVertex, Set<Integer> whiteSet, Set<Integer> graySet,
                        Set<Integer> blackSet, Map<Integer, List<Integer>> adjMap) {
        // move current vertex from whiteSet to graySet to start exploring
        moveVertex(currentVertex, whiteSet, graySet);
        if (adjMap.containsKey(currentVertex)) {
            List<Integer> adjVertices = adjMap.get(currentVertex);
            for (Integer vertex : adjVertices) {
                // if current vertex is in blackSet, then it is already explored,
                // so continue with next vertex
                if (blackSet.contains(vertex)) {
                    continue;
                }
                if (graySet.contains(vertex)) {
                    // cycle is found
                    return true;
                }
                if (dfs(vertex, whiteSet, graySet, blackSet, adjMap)) {
                    return true;
                }
            }
        }
        // move current vertex from graySet to blackSet when done exploring
        moveVertex(currentVertex, graySet, blackSet);
        return false;
    }

    private void moveVertex(int vertex, Set<Integer> sourceSet, Set<Integer> destinationSet) {
        sourceSet.remove(vertex);
        destinationSet.add(vertex);
    }

    public static void main(String[] args) {
        CycleInDirectedGraph c = new CycleInDirectedGraph();
        int a1 = 5;
        int[][] b1 = {{1, 2},
                {4, 1},
                {2, 4},
                {3, 4},
                {5, 2},
                {1, 3}
        };
        int a2 = 5;
        int[][] b2 = {{1, 2},
                {2, 3},
                {3, 4},
                {4, 5}
        };
        System.out.println(c.solve(a1, b1));
        System.out.println(c.solve(a2, b2));
    }
}

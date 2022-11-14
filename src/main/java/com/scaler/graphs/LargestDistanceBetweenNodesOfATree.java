package com.scaler.graphs;

import java.util.*;

/**
 * Find largest distance given an arbitrary unweighted rooted tree which consists of N (2 <= N <= 40000) nodes.
 * <p>
 * The goal of the problem is to find largest distance between two nodes in a tree.
 * Distance between two nodes is a number of edges on a path between the nodes
 * (there will be a unique path between any pair of nodes since it is a tree).
 * <p>
 * The nodes will be numbered 0 through N - 1.
 * <p>
 * The tree is given as an array A, there is an edge between nodes A[i] and i (0 <= i < N).
 * Exactly one of the i's will have A[i] equal to -1, it will be root node.
 * <p>
 * Problem Constraints
 * <p>
 * 2 <= |A| <= 40000
 * <p>
 * Input Format
 * <p>
 * First and only argument is vector A
 * <p>
 * Output Format
 * <p>
 * Return the length of the longest path
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [-1, 0]
 * <p>
 * Input 2:
 * <p>
 * A = [-1, 0, 0]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 1
 * <p>
 * Output 2:
 * <p>
 * 2
 * <p>
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * Path is 0 -> 1.
 * <p>
 * Explanation 2:
 * <p>
 * Path is 1 -> 0 -> 2.
 *
 * @author sudhir on 23-Sep-2020
 */
public class LargestDistanceBetweenNodesOfATree {
    private int distance = 0;

    public int solve(int[] A) {
        distance = -1;
        int nodes = A.length;
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        int root = 0;
        for (int i = 0; i < nodes; i++) {
            if (A[i] != -1) {
                adjMap.computeIfAbsent(i, z -> new ArrayList<>()).add(A[i]);
                adjMap.computeIfAbsent(A[i], z -> new ArrayList<>()).add(i);
            } else {
                root = i;
            }
        }
        /*
        We start a BFS from any random node X to find the node Y at longest distance from X.
        This node Y will be a end node of the longest path in the tree.
        Then apply a BFS on this node Y to find the other end of the actual longest path
        and get the desired result.
         */
        int endNode = bfs(adjMap, nodes, root);
        distance = -1;
        int startNode = bfs(adjMap, nodes, endNode);
        return distance;
    }

    private int bfs(Map<Integer, List<Integer>> adjMap, int nodes, int startVertex) {
        boolean[] visited = new boolean[nodes];
        visited[startVertex] = true;
        int endNode = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startVertex);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int currentNode = queue.poll();
                endNode = currentNode;
                if (adjMap.containsKey(currentNode)) {
                    List<Integer> adjList = adjMap.get(currentNode);
                    int adjListSize = adjList.size();
                    for (int j = 0; j < adjListSize; j++) {
                        if (!visited[adjList.get(j)]) {
                            visited[adjList.get(j)] = true;
                            queue.offer(adjList.get(j));
                        }
                    }
                }
            }
            distance++;
        }
        return endNode;
    }

    public static void main(String[] args) {
        LargestDistanceBetweenNodesOfATree l = new LargestDistanceBetweenNodesOfATree();
        int[] a1 = {-1, 0, 0};
        int[] a2 = {-1, 0};
        System.out.println(l.solve(a1));
        System.out.println(l.solve(a2));
    }
}

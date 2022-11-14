package com.scaler.graphs;

import java.util.*;

/**
 * Given a weighted undirected graph having A nodes, a source node C and destination node D.
 * Find the shortest distance from C to D and if it is impossible to reach node D from C then return -1. <br/>
 * You are expected to do it in Time Complexity of O(A + M). <br/>
 * Note: There are no self-loops in the graph.<br/>
 * No multiple edges between two pair of vertices.<br/>
 * The graph may or may not be connected. Nodes are Numbered from 0 to A-1.<br/>
 * Your solution will run on multiple test cases.<br/>
 * If you are using global variables make sure to clear them.<br/>
 * 1 <= A <= 10^5, 0 <= B[i][0], B[i][1] < A, 1 <= B[i][2] <= 2, 0 <= C < A, 0 <= D < A
 *
 * @author sudhir on 17-May-2020
 */
public class AnotherBFS {
    public int solve(int A, ArrayList<ArrayList<Integer>> B, int C, int D) {
        if (C == D) {// if source == destination, then there is no cost involved, so return 0.
            return 0;
        }
        int minCost = 0;
        int tempNode = A;
        int n = B.size();
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (B.get(i).get(2) == 1) {
                // since the graph is undirected, we need to add two entries in adjMap i.e. s->d and d->s
                adjMap.computeIfAbsent(B.get(i).get(0), z -> new ArrayList<>()).add(B.get(i).get(1));
                adjMap.computeIfAbsent(B.get(i).get(1), z -> new ArrayList<>()).add(B.get(i).get(0));
            } else {
                // case when edge weight = 2. split the edge into two by adding tempNode in between
                // so that we have all edges of weight 1(same as having unweighted graph)
                adjMap.computeIfAbsent(B.get(i).get(0), z -> new ArrayList<>()).add(tempNode);
                adjMap.computeIfAbsent(tempNode, z -> new ArrayList<>()).add(B.get(i).get(0));
                adjMap.computeIfAbsent(tempNode, z -> new ArrayList<>()).add(B.get(i).get(1));
                adjMap.computeIfAbsent(B.get(i).get(1), z -> new ArrayList<>()).add(tempNode);
                tempNode += 1;
            }
        }
        minCost = performBFS(adjMap, C, D);
        return minCost;
    }

    private int performBFS(Map<Integer, List<Integer>> adjMap, int source, int destination) {
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(source);
        int cost = 1;
        Set<Integer> visited = new HashSet<>();
        visited.add(source);
        Integer endNode = source;
        boolean pathExists = false;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (adjMap.containsKey(node)) {
                List<Integer> adjList = adjMap.get(node);
                int size = adjList.size();
                for (int i = 0; i < size; i++) {
                    int t = adjList.get(i);
                    if (!visited.contains(t)) {
                        visited.add(t);
                        queue.offer(t);
                        if (t == destination) {
                            pathExists = true;
                            break;
                        }
                    }
                }
                if (pathExists) {
                    break;
                }
                if (node == endNode) {
                    cost++;
                    endNode = queue.peekLast();
                }
            }
        }
        return pathExists ? cost : -1;
    }

    public static void main(String[] args) {
        AnotherBFS anotherBFS = new AnotherBFS();
        int a1 = 6;
        ArrayList<ArrayList<Integer>> b1 = new ArrayList<>();
        b1.add(new ArrayList<>(Arrays.asList(2, 5, 1)));
        b1.add(new ArrayList<>(Arrays.asList(1, 3, 1)));
        b1.add(new ArrayList<>(Arrays.asList(0, 5, 2)));
        b1.add(new ArrayList<>(Arrays.asList(0, 2, 2)));
        b1.add(new ArrayList<>(Arrays.asList(1, 4, 1)));
        b1.add(new ArrayList<>(Arrays.asList(0, 1, 1)));
        int c1 = 3;
        int d1 = 2;
        System.out.println(anotherBFS.solve(a1, b1, c1, d1));

        int a2 = 2;
        ArrayList<ArrayList<Integer>> b2 = new ArrayList<>();
        b2.add(new ArrayList<>(Arrays.asList(0, 1, 1)));
        int c2 = 0;
        int d2 = 1;
        System.out.println(anotherBFS.solve(a2, b2, c2, d2));

        int a3 = 13;
        ArrayList<ArrayList<Integer>> b3 = new ArrayList<>();
        b3.add(new ArrayList<>(Arrays.asList(3, 11, 2)));
        b3.add(new ArrayList<>(Arrays.asList(5, 12, 1)));
        b3.add(new ArrayList<>(Arrays.asList(0, 7, 2)));
        b3.add(new ArrayList<>(Arrays.asList(5, 6, 2)));
        b3.add(new ArrayList<>(Arrays.asList(6, 10, 1)));
        b3.add(new ArrayList<>(Arrays.asList(5, 9, 1)));
        int c3 = 9;
        int d3 = 4;
        System.out.println(anotherBFS.solve(a3, b3, c3, d3));

        int a4 = 10;
        ArrayList<ArrayList<Integer>> b4 = new ArrayList<>();
        b4.add(new ArrayList<>(Arrays.asList(3, 8, 2)));
        b4.add(new ArrayList<>(Arrays.asList(3, 5, 1)));
        b4.add(new ArrayList<>(Arrays.asList(1, 7, 2)));
        b4.add(new ArrayList<>(Arrays.asList(6, 9, 2)));
        b4.add(new ArrayList<>(Arrays.asList(0, 7, 2)));
        b4.add(new ArrayList<>(Arrays.asList(3, 9, 1)));
        b4.add(new ArrayList<>(Arrays.asList(0, 8, 2)));
        b4.add(new ArrayList<>(Arrays.asList(0, 5, 2)));
        b4.add(new ArrayList<>(Arrays.asList(0, 3, 1)));
        int c4 = 2;
        int d4 = 3;
        System.out.println(anotherBFS.solve(a4, b4, c4, d4));
    }
}

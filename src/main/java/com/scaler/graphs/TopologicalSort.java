package com.scaler.graphs;

import java.util.*;

/**
 * Given an directed acyclic graph having A nodes.
 * A matrix B of size M x 2 is given which represents the M edges such that
 * there is a edge directed from node B[i][0] to node B[i][1].
 * <p>
 * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of
 * vertices such that for every directed edge uv, vertex u comes before v in the ordering.
 * Topological Sorting for a graph is not possible if the graph is not a DAG.
 * <p>
 * Return the topological ordering of the graph and if it doesn't exist
 * then return an empty array.
 * <p>
 * If there is a solution return the correct ordering.
 * If there are multiple solutions print the lexicographically smallest one.
 * <p>
 * Ordering (a, b, c) is said to be lexicographically smaller than ordering (e, f, g)
 * if a < e or if(a==e) then b < f and so on.
 * <p>
 * NOTE:
 * <p>
 * There are no self-loops in the graph.
 * There are no multiple edges between two nodes.
 * The graph may or may not be connected.
 * Nodes are numbered from 1 to A.
 * Your solution will run on multiple test cases.
 * If you are using global variables make sure to clear them.
 * <p>
 * Problem Constraints
 * <p>
 * 2 <= A <= 10^4
 * <p>
 * 1 <= M <= min(100000,A*(A-1))
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
 * Return a one-dimensional array denoting the topological ordering of the graph
 * and it it doesn't exist then return empty array.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = 6
 * B = [  [6, 3]
 * [6, 1]
 * [5, 1]
 * [5, 2]
 * [3, 4]
 * [4, 2] ]
 * <p>
 * Input 2:
 * <p>
 * A = 3
 * B = [  [1, 2]
 * [2, 3]
 * [3, 1] ]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * [5, 6, 1, 3, 4, 2]
 * <p>
 * Output 2:
 * <p>
 * []
 * <p>
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * The given graph contain no cycle so topological ordering exists
 * which is [5, 6, 1, 3, 4, 2]
 * <p>
 * Explanation 2:
 * <p>
 * The given graph contain cycle so topological ordering not possible
 * we will return empty array.
 *
 * @author sudhir on 29-Nov-2020
 */
public class TopologicalSort {

    // Use Kahn's algorithm to get topological sorting.
    public ArrayList<Integer> solve(int A, ArrayList<ArrayList<Integer>> B) {
        // There might be multiple edges between the same pair of nodes
        // which will be counted in indegree count but in set that edge will not appear twice.
        // So use Map<Integer, List<Integer>> adjMap instead of
        // Map<Integer, Set<Integer>> adjMap
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        int[] indegree = new int[A + 1];
        for (ArrayList<Integer> list : B) {
            // update adjMap for every edge
            adjMap.computeIfAbsent(list.get(0),
                    z -> new ArrayList<>()).add(list.get(1));
            // update indegree of the node.
            indegree[list.get(1)]++;
        }
        ArrayList<Integer> result = new ArrayList<>();
        // If lexicographical order is not required, then Queue is better instead of
        // PriorityQueue as complexity is reduced to O(V + E) from O(VlogV + E).
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // insert all nodes with indegree 0 and make sure that you pick lexicographically
        // smallest of them. Priority Queue is used to ensure that.
        for (int i = 1; i <= A; i++) {
            if (indegree[i] == 0) {
                priorityQueue.offer(i);
            }
        }
        int count = 0;
        while (!priorityQueue.isEmpty()) {
            int node = priorityQueue.poll();
            result.add(node);
            if (adjMap.containsKey(node)) {
                List<Integer> adjNodes = adjMap.get(node);
                for (Integer adjNode : adjNodes) {
                    // subtract the indegree of adjNode and if it becomes 0, then
                    // offer it to the queue.
                    indegree[adjNode]--;
                    if (indegree[adjNode] == 0) {
                        priorityQueue.offer(adjNode);
                    }
                }
            }
            count++;
        }
        if (count == A) {
            // if count != A, then graph contains a cycle.
            return result;
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        TopologicalSort t = new TopologicalSort();
        ArrayList<ArrayList<Integer>> b1 = new ArrayList<>();
        b1.add(new ArrayList<>(Arrays.asList(6, 3)));
        b1.add(new ArrayList<>(Arrays.asList(6, 1)));
        b1.add(new ArrayList<>(Arrays.asList(5, 1)));
        b1.add(new ArrayList<>(Arrays.asList(5, 2)));
        b1.add(new ArrayList<>(Arrays.asList(3, 4)));
        b1.add(new ArrayList<>(Arrays.asList(4, 2)));
        int a1 = 6;
        System.out.println(t.solve(a1, b1));

        ArrayList<ArrayList<Integer>> b2 = new ArrayList<>();
        b2.add(new ArrayList<>(Arrays.asList(1, 2)));
        b2.add(new ArrayList<>(Arrays.asList(2, 3)));
        b2.add(new ArrayList<>(Arrays.asList(3, 1)));
        int a2 = 3;
        System.out.println(t.solve(a2, b2));
    }
}

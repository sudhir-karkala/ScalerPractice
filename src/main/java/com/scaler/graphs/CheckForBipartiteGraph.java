package com.scaler.graphs;

import java.util.*;

/**
 * Given a undirected graph having A nodes. A matrix B of size M x 2 is given which
 * represents the edges such that there is an edge between B[i][0] and B[i][1].
 * <p>
 * Find whether the given graph is bipartite or not.
 * <p>
 * A graph is bipartite if we can split it's set of nodes into two independent subsets A and B
 * such that every edge in the graph has one node in A and another node in B.
 * <p>
 * Note:
 * <p>
 * There are no self-loops in the graph.
 * <p>
 * No multiple edges between two pair of vertices.
 * <p>
 * The graph may or may not be connected.
 * <p>
 * Nodes are Numbered from 0 to A-1.
 * <p>
 * Your solution will run on multiple testcases. If you are using global variables make sure to clear them.
 * <p>
 * Problem Constraints
 * 1 <= A <= 100000
 * <p>
 * 1 <= M <= min(A*(A-1)/2,200000)
 * <p>
 * 0 <= B[i][0],B[i][1] < A
 * <p>
 * Input Format
 * <p>
 * The first argument given is an integer A.
 * <p>
 * The second argument given is the matrix B.
 * <p>
 * Output Format
 * <p>
 * Return 1 if the given graph is bipartite else return 0.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = 2
 * B = [ [0, 1] ]
 * Input 2:
 * <p>
 * A = 3
 * B = [ [0, 1], [0, 2], [1, 2] ]
 * <p>
 * Example Output
 * <p>
 * Output 1: 1
 * Output 2: 0
 * <p>
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * Put 0 and 1 into 2 different subsets.
 * <p>
 * Explanation 2:
 * It is impossible to break the graph down to make two different subsets for bipartite matching
 *
 * @author sudhir on 20-Aug-2020
 */
public class CheckForBipartiteGraph {
    private int maxColorUsed = 1;
    private int nodes;

    /**
     * Algorithm steps:
     * <p>
     * 1. Initialize max color used as 1.
     * 2. Add the first node to the queue.
     * 3. Poll the node and iterate over the adj list of the polled node to check if the adj nodes
     * are colored and note down the colors in the color set.
     * 3. If any of the nodes are not colored yet, then we add them to the queue.
     * 4. Based on the used colors by the adj nodes, we assign first unused color to the current node.
     * 5. If there isn't any unused color, then we increment max color used by 1 and assign that color.
     * 6. Clear the color set after assignment is done for the current node.
     * 7. Repeat steps from 3 to 6 for every entry in the queue.
     *
     * @param A the number of nodes.
     * @param B M x 2 size matrix representing edges between B[i][0] and B[i][1].
     * @return
     */
    public int solve(int A, int[][] B) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            adjMap.computeIfAbsent(B[i][0], z -> new ArrayList<>()).add(B[i][1]);
            adjMap.computeIfAbsent(B[i][1], z -> new ArrayList<>()).add(B[i][0]);
        }
        int[] colors = new int[A];
        nodes = A;
        Arrays.fill(colors, -1);
        computeRequiredColors(adjMap, colors);
        return maxColorUsed == 2 ? 1 : 0;
    }

    private void computeRequiredColors(Map<Integer, List<Integer>> adjMap, int[] colors) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nodes; i++) {
            if (colors[i] == -1) {
                // means the node is not colored yet.
                queue.offer(i);
                // this set is used to maintain the colors used by the adj nodes of the current
                // node which is considered and we clear it after we iterate over the
                // adj list of the current node and assign a suitable color to the current node.
                Set<Integer> usedColors = new HashSet<>();
                while (!queue.isEmpty()) {
                    int temp = queue.poll();
                    if (adjMap.containsKey(temp)) {
                        List<Integer> adjList = adjMap.get(temp);
                        for (int adjNode : adjList) {
                            if (colors[adjNode] != -1) {
                                // means it is already colored
                                usedColors.add(colors[adjNode]);
                            } else {
                                // means this node is not colored yet. we add it to the queue
                                queue.offer(adjNode);
                            }
                        }
                        for (int color = 1; color <= maxColorUsed; color++) {
                            if (!usedColors.contains(color)) {
                                colors[temp] = color;
                                break;
                            }
                        }
                        usedColors.clear();
                        // if there is no color which could be assigned to the current node,
                        // then we increment maxColorUsed by 1
                        // and assign that color to the current node.
                        if (colors[temp] == -1) {
                            maxColorUsed++;
                            colors[temp] = maxColorUsed;
                        }
                    } else {
                        // if the node is not present in the adjMap, then we can assign any color.
                        // Let us assign color 1. This step can be skipped as it doesn't contribute
                        // to our answer.
                        colors[temp] = 1;
                    }
                }
            }
        }
    }

    public int solve2(int A, int[][] B) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            adjMap.computeIfAbsent(B[i][0], z -> new ArrayList<>()).add(B[i][1]);
            adjMap.computeIfAbsent(B[i][1], z -> new ArrayList<>()).add(B[i][0]);
        }
        int[] colors = new int[A];
        Arrays.fill(colors, -1);
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < A; i++) {
            // means the node is already colored, so skip the current node and check the next node.
            if (colors[i] != -1) {
                continue;
            }
            colors[i] = 1;
            queue.offer(i);
            while (!queue.isEmpty()) {
                int node = queue.poll();
                if (adjMap.containsKey(node)) {
                    List<Integer> adjList = adjMap.get(node);
                    int countOfAdjNodes = adjList.size();
                    for (int j = 0; j < countOfAdjNodes; j++) {
                        // if the adj node is not already colored, then color it with the opposite color.
                        // and add the node to the queue.
                        if (colors[adjList.get(j)] == -1) {
                            // can also write in this way: colors[adjList.get(j)] = colors[node] ^ 1
                            colors[adjList.get(j)] = 1 - colors[node];
                            queue.offer(adjList.get(j));
                        } else {
                            // if the adjacent node is colored with same color,
                            // then the graph cannot be bipartite, so return 0
                            if (colors[adjList.get(j)] == colors[node]) {
                                return 0;
                            }
                        }
                    }
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        CheckForBipartiteGraph c = new CheckForBipartiteGraph();
        int a1 = 2;
        int[][] b1 = {{0, 1}};
        int a2 = 3;
        int[][] b2 = {{0, 1}, {0, 2}, {1, 2}};
        System.out.println(c.solve(a1, b1));
        System.out.println(c.solve2(a1, b1));
        System.out.println(c.solve(a2, b2));
        System.out.println(c.solve2(a2, b2));
    }
}

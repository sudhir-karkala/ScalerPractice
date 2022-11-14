package com.scaler.graphs;

import java.util.*;

/**
 * Given an array of strings A of size N, find if the given strings can be chained to form a circle.
 * <p>
 * A string X can be put before another string Y in circle if the last character of X
 * is same as first character of Y.
 * <p>
 * NOTE: All strings consist of lower case characters.
 * <p>
 * Problem Constraints
 * 1 <= N <= 10^5
 * <p>
 * Sum of length of all strings <= 10^6
 * <p>
 * Input Format
 * <p>
 * First and only argument is a string array A of size N.
 * <p>
 * Output Format
 * <p>
 * Return an integer 1 if it is possible to chain the strings to form a circle else return 0.
 * <p>
 * Example Input
 * <p>
 * Input 1: A = ["aab", "bac", "aaa", "cda"]
 * <p>
 * Input 2: A = ["abc", "cbc"]
 * <p>
 * Example Output
 * <p>
 * Output 1: 1
 * <p>
 * Output 2: 0
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * We can chain the strings aab -> bac -> cda -> aaa -> aab. So this forms a circle. So, output will be 1.
 * <p>
 * Explanation 2:
 * There is no way to chain the given strings such that they forms a circle.
 *
 * @author sudhir on 21-Aug-2020
 */
public class MakeCircle {
    public int solve(ArrayList<String> A) {
        // construct an adjMap for the input
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        int[] inDegree = new int[26];
        int[] outDegree = new int[26];
        // this is to mark all nodes/characters which are part of edges in the graph.
        boolean[] mark = new boolean[26];
        for (String str : A) {
            // we add an edge between first character and last character of the string
            // also update inDegree and outDegree for every node in the graph
            int firstCharacter = str.charAt(0) - 'a';
            int lastCharacter = str.charAt(str.length() - 1) - 'a';
            adjMap.computeIfAbsent(firstCharacter, z -> new ArrayList<>()).add(lastCharacter);
            outDegree[firstCharacter]++;
            inDegree[lastCharacter]++;
            mark[firstCharacter] = true;
            mark[lastCharacter] = true;
        }
        // check if inDegree and outDegree match for every node
        boolean isDegreeMatching = isInDegreeEqualToOutDegree(inDegree, outDegree);
        boolean stronglyConnected;
        if (isDegreeMatching) {
            // check if the graph is strongly connected
            stronglyConnected = isGraphStronglyConnected(A.get(0).charAt(0) - 'a', mark, adjMap);
        } else {
            // if outDegree and inDegree don't match for every node, then make circle is not possible.
            // so return 0
            return 0;
        }
        return stronglyConnected ? 1 : 0;
    }

    private boolean isInDegreeEqualToOutDegree(int[] inDegree, int[] outDegree) {
        for (int i = 0; i < 26; i++) {
            if (inDegree[i] != outDegree[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isGraphStronglyConnected(int sourceNode, boolean[] mark,
                                             Map<Integer, List<Integer>> adjMap) {
        boolean[] visited = new boolean[26];
        dfs(sourceNode, visited, adjMap);
        for (int i = 0; i < 26; i++) {
            // if there is any marked node which is not visited, then the graph is not strongly connected.
            if (mark[i] != visited[i]) {
                return false;
            }
        }
        return true;
    }

    private void dfs(int node, boolean[] visited, Map<Integer, List<Integer>> adjMap) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            int tempNode = stack.pop();
            visited[tempNode] = true;
            if (adjMap.containsKey(tempNode)) {
                List<Integer> adjNodes = adjMap.get(tempNode);
                for (int adjNode : adjNodes) {
                    if (!visited[adjNode]) {
                        stack.push(adjNode);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        MakeCircle m = new MakeCircle();
        ArrayList<String> s1 = new ArrayList<>(Arrays.asList("aab", "bac", "aaa", "cda"));
        ArrayList<String> s2 = new ArrayList<>(Arrays.asList("abc", "cbc"));
        System.out.println(m.solve(s1));
        System.out.println(m.solve(s2));
    }
}

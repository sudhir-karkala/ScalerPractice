package com.scaler.contests;

/**
 * Given a permutation of size N. Each number has some color associated to it.<br/>
 * Find the maximum number of colors that can be used such that you are able to sort the permutation by
 * swapping the elements having same color.
 *
 * @author sudhir on 22-Apr-2020
 */
public class SortThePermutation {
    // Which of the numbers should we color the same?
    // The numbers which can be sorted amongst themselves independent of the other numbers in the permutation
    // should be colored the same. Let’s call the numbers colored in same color, a group.
    // For example: A: {5,3,2,1,4} Here, there are two groups {5,1,4} and {3,2}.
    // The two groups can be sorted independent of each other to result in the final sorted permutation.
    // {5,1,4} –> {1,4,5} //color1
    // {3,2} –> {2,3} //color2
    // {5,3,2,1,4} –> {1,2,3,4,5} Two colors are required because of the two independent groups.
    // Therefore, we need to find the total number of such independent groups in the permutation which can all be
    // colored in different colors. Total number of such groups will be the maximum number of colors that can be used.
    public int solve(int[] A) {
        int n = A.length;
        int result = n;
        for (int i = 0; i < n; i++) {
            while (A[i] != (i + 1)) {
                result--;
                // swap A[i] with A[A[i]-1])
                int temp = A[A[i] - 1];
                A[A[i] - 1] = A[i];
                A[i] = temp;
            }
        }
        return result;
    }

    // Approach 2: Using dfs way. As long as we are able to reach some node, we use the same color, else we change the color.
    public int solve2(int[] A) {
        int n = A.length;
        // since we have permutation of size N, numbers are from 1..N
        int[] adj = new int[n + 1];
        int colors = 0;
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[A[i - 1]] = i;
        }
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                colors++;
                dfs(i, visited, adj);
            }
        }
        return colors;
    }

    private void dfs(int u, boolean[] visited, int[] adj) {
        visited[u] = true;
        int v = adj[u];
        while (!visited[v]) {
            visited[v] = true;
            v = adj[v];
        }
    }

    public static void main(String[] args) {
        SortThePermutation s = new SortThePermutation();
        int[] a = {1, 4, 2, 3};
        int[] b = {5, 3, 2, 1, 4};
        int[] c = {3, 1, 2, 5, 6, 4};
        System.out.println(s.solve(a));
        System.out.println(s.solve(c));
        System.out.println(s.solve2(b));
    }

}

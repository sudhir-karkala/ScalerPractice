package com.scaler.graphs;

/**
 * Given the number of vertices A in a Cyclic Graph.
 * <p>
 * Your task is to determine the number of colors required to color the graph so that no two Adjacent vertices have the same color.
 * <p>
 * Problem Constraints
 * <p>
 * 3 <= A <= 10^9
 *
 * @author sudhir on 12-Aug-2020
 */
public class ColoringACycleGraph {
    public int solve(int A) {
        return A % 2 == 0 ? 2 : 3;
    }

    public static void main(String[] args) {
        ColoringACycleGraph c = new ColoringACycleGraph();
        int a1 = 3;
        int a2 = 6;
        System.out.println(c.solve(a1));
        System.out.println(c.solve(a2));
    }
}

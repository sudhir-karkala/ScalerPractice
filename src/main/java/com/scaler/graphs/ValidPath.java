package com.scaler.graphs;

/**
 * There is a rectangle with left bottom as (0, 0) and right up as (x, y).
 * <p>
 * There are N circles such that their centers are inside the rectangle.
 * <p>
 * Radius of each circle is R. Now we need to find out if it is possible that we can move from (0, 0) to (x, y)
 * without touching any circle.
 * <p>
 * Note : We can move from any cell to any of its 8 adjacent neighbours and we cannot move outside the boundary of the
 * rectangle at any point of time.
 * <p>
 * Problem Constraints
 * <p>
 * 0 <= x , y, R <= 100
 * <p>
 * 1 <= N <= 1000
 * <p>
 * Center of each circle would lie within the grid
 * <p>
 * Input Format
 * <p>
 * 1st argument given is an Integer x , denoted by A in input.
 * <p>
 * 2nd argument given is an Integer y, denoted by B in input.
 * <p>
 * 3rd argument given is an Integer N, number of circles, denoted by C in input.
 * <p>
 * 4th argument given is an Integer R, radius of each circle, denoted by D in input.
 * <p>
 * 5th argument given is an Array A of size N, denoted by E in input, where A[i] = x coordinate of ith circle
 * <p>
 * 6th argument given is an Array B of size N, denoted by F in input, where B[i] = y coordinate of ith circle
 * <p>
 * Output Format
 * <p>
 * Return YES or NO depending on weather it is possible to reach cell (x,y) or not starting from (0,0).
 *
 * @author sudhir on 11-Aug-2020
 */
public class ValidPath {
    public String solve(int A, int B, int C, int D, int[] E, int[] F) {
        int x = A;
        int y = B;
        int noOfCircles = C;
        int radius = D;
        int[][] grid = new int[x+1][y+1];
        return "";
    }
}

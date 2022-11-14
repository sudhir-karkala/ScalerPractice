package com.scaler.hashing;

import java.util.HashSet;
import java.util.Set;

/**
 * Given two arrays of integers A and B of size N each,
 * where each pair (A[i], B[i]) for 0 <= i < N represents a unique point (x, y) in 2D Cartesian plane.
 * Find and return the number of unordered quadruplet (i, j, k, l)
 * such that (A[i], B[i]), (A[j], B[j]), (A[k], B[k]) and (A[l], B[l]) form a rectangle
 * with the rectangle having all the sides parallel to either x-axis or y-axis.
 * <p>
 * Input: A = [1, 1, 2, 2, 3, 3], B = [1, 2, 1, 2, 1, 2] Output: 3
 * </p>
 *
 * @author sudhir on 16-Mar-2020
 */
public class CountRectangles {
    static class Point {
        int x;
        int y;

        public Point() {
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public boolean equals(Object obj) {
            if (obj == null || obj.getClass() != this.getClass())
                return false;
            Point other = (Point) obj;
            return ((this.x == other.x) && (this.y == other.y));
        }

        public int hashCode() {
            int prime = 31;
            return x * prime + y;
        }
    }

    public int solve(int[] A, int[] B) {
        //instantiate an array of points
        Point[] p = new Point[A.length];
        Set<Point> set = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            p[i] = new Point(A[i], B[i]);
            set.add(p[i]);
        }

        Point p1 = new Point();
        Point p2 = new Point();
        Point p3 = new Point();
        Point p4 = new Point();
        int ans = 0;

        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                p1.setX(A[i]);
                p1.setY(B[i]);
                p2.setX(A[j]);
                p2.setY(B[j]);

                p3.setX(A[i]);
                p3.setY(B[j]);
                p4.setX(A[j]);
                p4.setY(B[i]);
                if ((!(p1.equals(p3) || p1.equals(p4) || p2.equals(p3) || p2.equals(p4) || p3.equals(p4))) && set.contains(p3) && set.contains(p4)) {
                    ans++;
                }
            }
        }
        return ans / 2;
    }

    public static void main(String[] args) {
        CountRectangles c = new CountRectangles();
        int[] a1 = {1, 1, 2, 2, 3, 3};
        int[] b1 = {1, 2, 1, 2, 1, 2};
        int[] a2 = {1, 1, 2, 2};
        int[] b2 = {1, 2, 1, 2};
        System.out.println(c.solve(a1, b1));
        System.out.println(c.solve(a2, b2));
    }
}

package com.scaler.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * We have a list A, of points(x,y) on the plane.
 * Find the B closest points to the origin (0, 0). <br/>
 * Here, the distance between two points on a plane is the Euclidean distance. <br/>
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in.) <br/>
 * NOTE: Euclidean distance between two points P1(x1,y1) and P2(x2,y2) is sqrt( (x1-x2)^2 + (y1-y2)^2 ).<br/>
 *
 * @author sudhir on 10-Apr-2020
 */
public class BClosestPointsToOrigin {
    public ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> A, int B) {
        Collections.sort(A, (o1, o2) -> {
            Double d1 = Math.sqrt(Math.pow(o1.get(0), 2) + Math.pow(o1.get(1), 2));
            Double d2 = Math.sqrt(Math.pow(o2.get(0), 2) + Math.pow(o2.get(1), 2));
            return d1.compareTo(d2);
        });
        return new ArrayList<>(A.subList(0, B));
    }

    public static void main(String[] args) {
        BClosestPointsToOrigin b = new BClosestPointsToOrigin();
        int B = 1;
        ArrayList<Integer> p1 = new ArrayList<>(Arrays.asList(1, 3));
        ArrayList<Integer> p2 = new ArrayList<>(Arrays.asList(-2, 2));
        ArrayList<ArrayList<Integer>> points = new ArrayList<>(Arrays.asList(p1, p2));
        System.out.println(b.solve(points, B));
    }
}

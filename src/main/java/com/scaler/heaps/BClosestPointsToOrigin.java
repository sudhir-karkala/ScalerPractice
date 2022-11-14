package com.scaler.heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * We have a list A, of points(x,y) on the plane.
 * Find the B closest points to the origin (0, 0).
 * <p>
 * Here, the distance between two points on a plane is the Euclidean distance.
 * <p>
 * You may return the answer in any order.
 * The answer is guaranteed to be unique (except for the order that it is in.)
 * <p>
 * NOTE: Euclidean distance between two points P1(x1,y1) and P2(x2,y2) is sqrt( (x1-x2)^2 + (y1-y2)^2 ).
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= B <= length of the list A <= 100000
 * <p>
 * -100000 <= A[i][0] <= 100000
 * <p>
 * -100000 <= A[i][1] <= 100000
 * <p>
 * Input Format
 * <p>
 * The argument given is list A and an integer B.
 * <p>
 * Output Format
 * <p>
 * Return the B closest points to the origin (0, 0) in any order.
 *
 * @author sudhir on 16-Apr-2020
 */
public class BClosestPointsToOrigin {
    public ArrayList<ArrayList<Integer>> solve(ArrayList<ArrayList<Integer>> A, int B) {
        PriorityQueue<ArrayList<Integer>> priorityQueue = new PriorityQueue<>(new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                Double d1 = Math.sqrt(Math.pow(o1.get(0), 2) + Math.pow(o1.get(1), 2));
                Double d2 = Math.sqrt(Math.pow(o2.get(0), 2) + Math.pow(o2.get(1), 2));
                return d1.compareTo(d2);
            }
        });
        priorityQueue.addAll(A);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int i = 0;
        while (i < B) {
            result.add(priorityQueue.poll());
            i++;
        }
        return result;
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

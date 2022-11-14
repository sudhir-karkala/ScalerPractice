package com.scaler.maths;

/**
 * Given an integer A which denotes the number of doors in a row numbered 1 to A. <br/>
 * All the doors are closed initially. <br/>
 * A person moves to and fro changing the states of the doors as follows: <br/>
 * the person opens a door that is already closed and closes a door that is already opened. <br/>
 * In the first go, the person alters the states of doors numbered 1, 2, 3, … , A. <br/>
 * In the second go, she alters the states of doors numbered 2, 4, 6 … <br/>
 * In the third go, she alters the states of doors numbered 3, 6, 9 … <br/>
 * This continues till the A'th go in which you alter the state of the door numbered A. <br/>
 * Find and return the number of open doors at the end of the procedure.
 *
 * @author sudhir on 04-Apr-2020
 */
public class NumberOfOpenDoors {
    public int solve(int A) {
        return (int) Math.sqrt(A);
    }

    public static void main(String[] args) {
        NumberOfOpenDoors n = new NumberOfOpenDoors();
        int a = 10;
        int b = 20;
        System.out.println(n.solve(a));
        System.out.println(n.solve(b));
    }
}

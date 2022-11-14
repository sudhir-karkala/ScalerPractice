package com.scaler.greedy;

/**
 * N light bulbs are connected by a wire.<br/>
 * Each bulb has a switch associated with it, however due to faulty wiring, a switch also changes the
 * state of all the bulbs to the right of current bulb.<br/>
 * Given an initial state of all bulbs, find the minimum number of switches you have to press to turn on all the bulbs.<br/>
 * You can press the same switch multiple times.<br/>
 * Note: 0 represents the bulb is off and 1 represents the bulb is on.<br/>
 * 1 <= N <= 5*10^5, 0 <= A[i] <= 1
 *
 * @author sudhir on 1/6/20
 */
public class Bulbs {
    /**
     * We toggle for the below 2 cases:
     * <p>
     * a. When the original state at a position is 1,and the count of toggles performed is odd, then the current state will
     * be 0, so we need to change the state for that position.
     * <p>
     * b. When the original state at a position is 0,and the count of toggles performed is even, then the current state will
     * be 0, so we need to change the state for that position.
     * <p>
     * For other cases, we can safely ignore those positions.
     *
     * @param A state of N light bulbs.
     * @return count of switch presses to switch on all light bulbs.
     */
    public int bulbs(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if ((count % 2 == 0 && A[i] == 0) || (count % 2 != 0 && A[i] == 1)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Bulbs b = new Bulbs();
        int[] arr = {0, 1, 0, 1};
        System.out.println(b.bulbs(arr));
    }
}

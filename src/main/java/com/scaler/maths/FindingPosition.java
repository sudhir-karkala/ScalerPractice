package com.scaler.maths;

/**
 * Given an integer A which denotes the number of people standing in the queue.<br/>
 * A selection process follows a rule where people standing on even positions are selected.
 * Of the selected people a queue is formed and again out of these only people on even position are selected.<br/>
 * This continues until we are left with one person. <br/>
 * Find and return the position of that person in the original queue.
 *
 * @author sudhir on 04-Apr-2020
 */
public class FindingPosition {
    public int solve(int A) {
        int ans = 1;
        while (ans <= A) {
            ans = ans << 1;
        }
        return ans >> 1;
    }

    public static void main(String[] args) {
        FindingPosition f = new FindingPosition();
        int a = 10;
        System.out.println(f.solve(a));
    }
}

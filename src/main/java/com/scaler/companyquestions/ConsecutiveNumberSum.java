package com.scaler.companyquestions;

/**
 * Given an integer A.
 * Return number of ways we can write A as a sum of consecutive positive integers.
 *
 * @author sudhir on 08-Jul-2020
 */
public class ConsecutiveNumberSum {
    /**
     * N = (a+1) + (a+2) +....+ (a+k) which has k terms.
     * N = ka + (k*(k+1)/2) => a = (N - (k*(k+1)/2)) / k
     * From this, we can derive that k can range from 1 to sqrt(2N).
     * We can calculate RHS for every value of k and if (N - (k*(k+1)/2)) / k is an integer i.e. (N - (k*(k+1)/2)) % k == 0,
     * then we can use that k to construct sum to N with k terms starting from a
     *
     * @param A
     * @return
     */
    public int solve(int A) {
        int ans = 0;
        for (int k = 1; (k * (k + 1)) / 2 <= A; ++k) {
            if ((A - ((k * (k + 1)) / 2)) % k == 0) {
                ++ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ConsecutiveNumberSum c = new ConsecutiveNumberSum();
        int n = 25;
        System.out.println(c.solve(n));
    }
}

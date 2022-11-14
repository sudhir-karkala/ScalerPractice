package com.scaler.companyquestions;

/**
 * Find the number of ways you can have fun in A days,
 * given you can sleep every day, Pizza can be eaten every alternate day
 * and you can watch Tv shows every two days.
 * <p>
 * Since the answer could be large, return answer % 10^9 + 7.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= A <= 10^5
 * <p>
 * Input Format
 * <p>
 * First and only argument is an integer A denoting the number of days.
 * <p>
 * Output Format
 * <p>
 * Return an integer denoting the number of ways you can have fun in A days.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = 2
 * <p>
 * Input 2:
 * <p>
 * A = 3
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 7
 * <p>
 * Output 2:
 * <p>
 * 15
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * There will be 7 ways to have fun:
 * (SS), (SP), (ST), (PS), (PT), (TS), (TP).
 * <p>
 * Explanation 2:
 * <p>
 * There will be 15 ways to have fun.
 *
 * @author sudhir on 04-Nov-2020
 */
public class WaysToHaveFun {
    public int solve(int A) {
        int mod = (int) 1e9 + 7;
        // dp[i][j] represents number of ways to have fun till ith day
        // and performing activity j on day i.
        long[][] dp = new long[A + 1][4];
        dp[1][1] = dp[1][2] = dp[1][3] = 1;
        for (int i = 2; i <= A; i++) {
            dp[i][1] = (dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3]) % mod;
            dp[i][2] = (dp[i - 1][1] + dp[i - 1][3]) % mod;
            // since dp[i][3] can go negative, do not perform mod here.
            // if activity 3 is to be performed on day i, then it should not have been
            // performed on day i-2. so we subtract dp[i-2][3] twice,
            // which was included in the calculation of dp[i-1][1] and dp[i-1][2].
            dp[i][3] = dp[i - 1][1] + dp[i - 1][2] - 2 * dp[i - 2][3];
            // make sure dp[i][3] is positive.
            dp[i][3] += mod;
            dp[i][3] %= mod;
        }
        return (int) ((dp[A][1] + dp[A][2] + dp[A][3]) % mod);
    }

    public static void main(String[] args) {
        WaysToHaveFun w = new WaysToHaveFun();
        int n1 = 2;
        int n2 = 3;
        System.out.println(w.solve(n1));
        System.out.println(w.solve(n2));
    }
}

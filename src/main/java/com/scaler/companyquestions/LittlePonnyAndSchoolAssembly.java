package com.scaler.companyquestions;

/**
 * You are given B boys and G girls.
 * <p>
 * You want to arrange them to form a school assembly such that no more than C boys and
 * no more than D girls are standing together.
 * <p>
 * Two arrangements are different if there exists one position such that there is a girl at this position
 * in one of them and a boy in another.
 * <p>
 * Since, the result can be large, print it modulo 10^9 + 7
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= B, G <= 100
 * <p>
 * 1 <= C, D <= 10
 * <p>
 * Input Format
 * <p>
 * The argument of the input is the array A containing 4 elements representing the value of B, G, C and D respectively.
 * <p>
 * Output Format
 * <p>
 * Return an integer representing the answer
 *
 * @author sudhir on 08-Aug-2020
 */
public class LittlePonnyAndSchoolAssembly {
    private long[][][][] dp;
    private int mod = 1000000007;

    public int solve(int[] A) {
        int b = A[0];
        int g = A[1];
        int c = A[2];
        int d = A[3];
        dp = new long[b + 1][g + 1][c + 1][d + 1];
        for (int i = 0; i <= b; i++) {
            for (int j = 0; j <= g; j++) {
                for (int k = 0; k <= c; k++) {
                    for (int l = 0; l <= d; l++) {
                        if (i == 0 && j == 0) {
                            dp[i][j][c][d] = 1;
                        } else {
                            dp[i][j][k][l] = -1;
                        }
                    }
                }
            }
        }

        long result = arrangeUsingDPMemoization(b, g, 0, 0, c, d);
        return (int) result;
    }

    /**
     * This is to call arrangeUsingRecursion() function
     *
     * @param A
     * @return
     */
    public int solve2(int[] A) {
        int b = A[0];
        int g = A[1];
        int c = A[2];
        int d = A[3];
        int result = arrangeUsingRecursion(b, g, 0, 0, c, d);
        return result;
    }

    /**
     * This solution uses 4-d DP using memoization.
     *
     * @param rem_boys
     * @param rem_girls
     * @param consec_boys
     * @param consec_girls
     * @param maxB
     * @param maxG
     * @return
     */
    private long arrangeUsingDPMemoization(int rem_boys, int rem_girls, int consec_boys, int consec_girls, int maxB, int maxG) {
        if (rem_boys < 0 || rem_girls < 0 || consec_boys > maxB || consec_girls > maxG) {
            return 0;
        }
        if (rem_boys == 0 && rem_girls == 0) {
            return 1;
        }
        if (dp[rem_boys][rem_girls][consec_boys][consec_girls] != -1) {
            return dp[rem_boys][rem_girls][consec_boys][consec_girls] % mod;
        }
        dp[rem_boys][rem_girls][consec_boys][consec_girls] = (arrangeUsingDPMemoization(rem_boys - 1, rem_girls, consec_boys + 1, 0, maxB, maxG)
                + arrangeUsingDPMemoization(rem_boys, rem_girls - 1, 0, consec_girls + 1, maxB, maxG)) % mod;
        return dp[rem_boys][rem_girls][consec_boys][consec_girls];
    }

    /**
     * This recursive solution is just for reference as it gives TLE on scaler platform.
     *
     * @param rem_boys
     * @param rem_girls
     * @param consec_boys
     * @param consec_girls
     * @param maxB
     * @param maxG
     * @return
     */
    private int arrangeUsingRecursion(int rem_boys, int rem_girls, int consec_boys, int consec_girls, int maxB, int maxG) {
        int ans = 0;
        if (rem_boys == 0 && rem_girls == 0) {
            return 1;
        }
        if (rem_boys > 0 && consec_boys < maxB) {
            ans += arrangeUsingRecursion(rem_boys - 1, rem_girls, consec_boys + 1, 0, maxB, maxG);
        }
        if (rem_girls > 0 && consec_girls < maxG) {
            ans += arrangeUsingRecursion(rem_boys, rem_girls - 1, 0, consec_girls + 1, maxB, maxG);
        }
        return ans;
    }

    public static void main(String[] args) {
        LittlePonnyAndSchoolAssembly l = new LittlePonnyAndSchoolAssembly();
        int[] a1 = {2, 1, 1, 10};
        int[] a2 = {2, 3, 1, 2};
        int[] a3 = {2, 4, 1, 1};
        System.out.println(l.solve(a1));
        System.out.println(l.solve(a2));
        System.out.println(l.solve(a3));
        System.out.println(l.solve2(a1));
        System.out.println(l.solve2(a2));
        System.out.println(l.solve2(a3));
    }
}

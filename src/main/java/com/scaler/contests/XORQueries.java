package com.scaler.contests;

/**
 * Given three integers A, B and C.
 * <p>
 * Return the maximum possible value of A ^ D, such that D is an integer in range [B, C].
 * <p>
 * Note 1: ^ represents BITWISE xor.
 * <p>
 * Note 2: A single test file may contain multiple testcases up to 10^5.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= A, B, C <= 10^9
 * <p>
 * Input Format
 * <p>
 * The first argument given is the integer A.
 * <p>
 * The second argument given is the integer B.
 * <p>
 * The third argument given is the integer C.
 * <p>
 * Output Format
 * <p>
 * Return the maximum possible value of A ^ D, such that D is an integer in range [B, C].
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = 2,
 * B = 1,
 * C = 10
 * <p>
 * Input 2:
 * <p>
 * A = 3,
 * B = 5,
 * C = 6
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 11
 * <p>
 * Output 2:
 * <p>
 * 6
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * For D = 9, A ^ D = 11, which is maximum for any D in range [1, 10].
 * <p>
 * Explanation 2:
 * <p>
 * For D = 5, A ^ D = 6, which is maximum for any D in range [5, 6]
 *
 * @author sudhir on 15-Nov-2020
 */
public class XORQueries {

    public int solve(int A, int B, int C) {
        // find the max bits used by the number C since it is the largest number in the input.
        int bits = (int) (Math.log(C) / Math.log(2) + 1);
        // ans will be a number which is in the range(B,C) and will give max Xor when
        // xored with A.
        int ans = 0;
        for (int i = bits - 1; i >= 0; i--) {
            int temp = 1 << i;
            // if ith bit is set, then try unsetting this bit in the answer
            // as we know that Xor will be maximum if corresponding bits are complementary.
            // (temp - 1) will have current bit unset and rest of the bits set.
            if ((A & temp) > 0 && (ans | (temp - 1)) < B) {
                // if by unsetting the ith bit, the ans goes below B, then make no change
                // to the bit (keep it set), by performing ans = (ans | temp)
                ans |= temp;
            } else if ((A & temp) == 0 && (ans | temp) <= C) {
                // if the ith bit is unset, then try setting it only if the resulting number
                // is less than or equal to C.
                ans |= temp;
            }
        }
        return ans ^ A;
    }

    public static void main(String[] args) {
        XORQueries xorQueries = new XORQueries();
        int a1 = 2;
        int b1 = 1;
        int c1 = 10;
        System.out.println(xorQueries.solve(a1, b1, c1));

        int a2 = 3;
        int b2 = 5;
        int c2 = 6;
        System.out.println(xorQueries.solve(a2, b2, c2));
    }
}

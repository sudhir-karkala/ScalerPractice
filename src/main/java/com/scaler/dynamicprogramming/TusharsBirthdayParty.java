package com.scaler.dynamicprogramming;

/**
 * As it is Tushar's Birthday on March 1st, he decided to throw a party to all his friends at TGI Fridays in Pune.
 * Given are the eating capacity of each friend, filling capacity of each dish and cost of each dish.
 * A friend is satisfied if the sum of the filling capacity of dishes he ate is equal to his capacity.
 * Find the minimum cost such that all of Tushar's friends are satisfied (reached their eating capacity).
 * <p>
 * NOTE:
 * <p>
 * Each dish is supposed to be eaten by only one person. Sharing is not allowed.
 * <p>
 * Each friend can take any dish unlimited number of times.
 * <p>
 * There always exists a dish with filling capacity 1 so that a solution always exists.
 * <p>
 * Problem Constraints
 * <p>
 * |A| <= 1000
 * <p>
 * |B| <= 1000
 * <p>
 * |C| <= 1000
 * <p>
 * Input Format
 * <p>
 * First Argument is vector A, denoting eating capacities
 * <p>
 * Second Argument is vector B, denoting filling capacities
 * <p>
 * Third Argument is vector C, denoting cost
 * <p>
 * Output Format
 * <p>
 * Return a single integer, the answer to the problem
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [2, 4, 6]
 * <p>
 * B = [2, 1, 3]
 * <p>
 * C = [2, 5, 3]
 * <p>
 * Input 2:
 * <p>
 * A = [2]
 * <p>
 * B = [1]
 * <p>
 * C = [2]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 12
 * <p>
 * Output 2:
 * <p>
 * 4
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * First friend takes dish 1, Second friend takes dish 1 twice and third friend takes dish 3 twice.
 * <p>
 * So 2 + 2*2 + 3*2 = 12.
 * <p>
 * Explanation 2:
 * <p>
 * Only way is to take 2 dishes of cost 2, hence 4.
 *
 * @author sudhir on 12-Sep-2020
 */
public class TusharsBirthdayParty {
    public int solve(final int[] A, final int[] B, final int[] C) {
        // A=>eating capacities of friends
        // B=>filling capacities of each dish
        // C=>cost of each dish
        int maxCapacity = -1;
        for (int k = 0; k < A.length; k++) {
            maxCapacity = Math.max(maxCapacity, A[k]);
        }
        // mincost[i][j] represents min cost considering dishes till ith index(1-based indexing)
        // and eating capacity j(1-based indexing)
        int[][] mincost = new int[B.length + 1][maxCapacity + 1];
        for (int i = 0; i <= B.length; i++) {
            for (int j = 0; j <= maxCapacity; j++) {
                if (j == 0) {
                    // if capacity is 0, then mincost is 0.
                    mincost[i][j] = 0;
                } else {
                    // set the initial values to INT_MAX so that we can get min value at the end after computations.
                    mincost[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 1; i <= B.length; i++) {
            for (int j = 0; j <= maxCapacity; j++) {
                if (B[i - 1] <= j && mincost[i][j - B[i - 1]] != Integer.MAX_VALUE) {
                    mincost[i][j] = Math.min(mincost[i - 1][j], mincost[i][j - B[i - 1]] + C[i - 1]);
                } else {
                    mincost[i][j] = mincost[i - 1][j];
                }
            }
        }
        int totalCost = 0;
        for (int k = 0; k < A.length; k++) {
            totalCost += mincost[B.length][A[k]];
        }
        return totalCost;
    }

    public static void main(String[] args) {
        TusharsBirthdayParty t = new TusharsBirthdayParty();
        int[] eatingCapacity = {2, 4, 6};
        int[] fillingCapacityOfDish = {2, 1, 3};
        int[] costOfDish = {2, 5, 3};
        System.out.println(t.solve(eatingCapacity, fillingCapacityOfDish, costOfDish));
    }
}

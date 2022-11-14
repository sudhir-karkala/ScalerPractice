package com.scaler.twopointers;

/**
 * Given n non-negative integers A[0], A[1], ..., A[n-1] , where each represents a point at coordinate (i, A[i]).
 * N vertical lines are drawn such that the two endpoints of line i is at (i, A[i]) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container.
 * <p>
 * Problem Constraints
 * <p>
 * 0 <= N <= 10^5
 * <p>
 * 1 <= A[i] <= 10^5
 * <p>
 * Input Format
 * <p>
 * Single Argument representing a 1-D array A.
 * <p>
 * Output Format
 * <p>
 * Return single Integer denoting the maximum area you can obtain.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [1, 5, 4, 3]
 * <p>
 * Input 2:
 * <p>
 * A = [1]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 6
 * <p>
 * Output 2:
 * <p>
 * 0
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * 5 and 3 are distance 2 apart. So size of the base = 2. Height of container = min(5, 3) = 3.
 * <p>
 * So total area = 3 * 2 = 6
 * <p>
 * Explanation 2:
 * <p>
 * No container is formed.
 *
 * @author sudhir on 12-Mar-2020
 */
public class ContainerWithMostWater {
    public int maxArea(int[] A) {
        int max = Integer.MIN_VALUE;
        int i = 0;
        int j = A.length - 1;
        while (i < j) {
            int area = (j - i) * Math.min(A[i], A[j]);
            if (area > max) {
                max = area;
            }
            if (A[i] < A[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    public static void main(String[] args) {
        ContainerWithMostWater c = new ContainerWithMostWater();
        int[] a = {1, 5, 4, 3};
        System.out.println(c.maxArea(a));
    }
}

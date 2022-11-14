package com.scaler.companyquestions;

/**
 * You are given an array A.
 * Consider the sum of all the subsequences of this array (There are 2|A| subsequence of this array).
 * Calculate the median of all these subarrays.
 * Since the median can be a floating-point number, return 2* Median.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= |A| <= 100000
 * <p>
 * Each element of the array is between 1 and 10^3 (inclusive)
 * <p>
 * Input Format
 * <p>
 * The first and only argument of the input is the array A
 * <p>
 * Output Format
 * <p>
 * Your function should return a single integer - 2 * Median of all subsequence sum.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A: [2, 1, 3]
 * <p>
 * Input 2:
 * <p>
 * A: [1, 1, 1]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 6
 * <p>
 * Output 2:
 * <p>
 * 3
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * There are 8 subsequence of the given array:
 * <p>
 * [], [2] , [1], [3], [2+1], [2+3], [1+3], [1+2+3].
 * <p>
 * The list of subsequence sum will look like : [0, 2, 1, 3, 3, 5, 4, 6].
 * <p>
 * If we sort this list, we will get: [0, 1, 2, 3, 3, 4, 5, 6].
 * <p>
 * The Median of this array will be (3 + 3)/2 = 3, thus 2*Median = 6.
 * <p>
 * Explanation 2:
 * <p>
 * There are 8 subsequence of the given array:
 * <p>
 * [], [1] , [1], [1], [1+1], [1+1], [1+1], [1+1+1].
 * <p>
 * The list of subsequence sum will look like : [0, 1, 1, 1, 2, 2, 2, 3].
 * <p>
 * If we sort this list, we will get: [0, 1, 1, 1, 2, 2, 2, 3].
 * <p>
 * The Median of this array will be (1 + 2)/2 = 1.5, thus 2*Median = 3.
 *
 * @author sudhir on 17-Sep-2020
 */
public class LittlePonnyAndSubsetMedian {
    /**
     * Algorithm:
     * For every subset S there exist it’s compliment subset such that their sum is total sum of all numbers.
     * Thus in sorted array(based on sum of elements in the subset) of subsets,
     * the sum of first subset elements + last subset elements = second + second last =
     * ….. (2^(n-1))th subset sum + (2^(n-1) +1)th subset sum = Total sum of all the elements.
     * Thus median will always be Sum/2.
     * Thus 2*Median = Sum of all elements.
     *
     * @param A
     * @return
     */
    public int solve(int[] A) {
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        // return 2*median. Here median = sum / 2. so we can directly return sum.
        return sum;
    }

    public static void main(String[] args) {
        LittlePonnyAndSubsetMedian l = new LittlePonnyAndSubsetMedian();
        int[] a1 = {2, 1, 3};
        int[] a2 = {1, 1, 1};
        System.out.println(l.solve(a1));
        System.out.println(l.solve(a2));
    }
}

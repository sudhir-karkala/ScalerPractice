package com.scaler.maths;

/**
 * A subsequence is a sequence that can be derived from another sequence by deleting some elements without
 * changing the order of the remaining elements. <br/>
 * For example, the sequence {2, 3, 5} is a subsequence of {1, 2, 3, 4, 5} obtained after removal of elements {1, 4}. <br/>
 * Given is an array of integers A of size N. <br/>
 * An array of size N can have (2^N - 1) number of non empty subsequences.
 * <pre>
 * solve (int subsequence[]) {
 *      int count[];    //array initialised to 0.
 *      for(int i = 0; i < subsequence.length; i++) {
 *          number = subsequence[i];
 *          for(int j = 2; j <= number; j++) {
 *              if(number % j == 0) {
 *                  count[j]++;
 *                  if(count[j] == subsequence.length)  return 0;
 *              }
 *          }
 *      }
 *      return 1;
 * }
 * </pre>
 * If all the subsequences of the array A are passed in the above function. What will be the bitwise OR of all the returned values from the given function.
 *
 * @author sudhir on 04-Apr-2020
 */
public class SolveSubSequencesAndReturnOR {
    private int findgcd(int a, int b) {
        int m = Math.max(a, b);
        int n = Math.min(a, b);
        while (n != 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        return m;
    }

    public int solve(int[] A) {
        int gcd = 0;
        for (int i = 0; i < A.length; i++) {
            gcd = findgcd(gcd, A[i]);
        }
        return gcd == 1 ? 1 : 0;
    }

    public static void main(String[] args) {
        SolveSubSequencesAndReturnOR s = new SolveSubSequencesAndReturnOR();
        int[] a1 = {1, 2, 3};
        int[] a2 = {2, 4, 6, 8};
        System.out.println(s.solve(a1));
        System.out.println(s.solve(a2));
    }
}

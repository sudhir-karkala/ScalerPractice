package com.scaler.bitmanipulation;

/**
 * You have an array A[] with N elements. We have 2 types of operation available on this array :<br/>
 * We can split a element B into 2 elements C and D such that B = C + D.<br/>
 * We can merge 2 elements P and Q as one element R such that R = P^Q i.e XOR of P and Q.<br/>
 * You have to determine whether it is possible to make array A[] containing only 1 element 0 after several splits and/or merge?
 *
 * @author sudhir on 04-Apr-2020
 */
public class InterestingArray {
    public String solve(int[] A) {
        // if we have an even number, say 22, w can split it evenly say 11 and 11 and do 11^11 = 0
        // if we have an odd number, say 9, we can split it as 1 and 8 so that we have one odd and one even number.
        // for even number, we can do the same as done in point 1. now number 1 remain. if we have even number of 1's then
        // we can get result as 0 by doing 1^1^...^1 even number of times.
        // so the essence becomes to find the count of odd numbers. if the count is even then possible, else not possible
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 != 0) {
                count++;
            }
        }
        return count % 2 == 0 ? "Yes" : "No";
    }

    public static void main(String[] args) {
        InterestingArray interestingArray = new InterestingArray();
        int[] a = {9, 17};
        System.out.println(interestingArray.solve(a));
    }
}

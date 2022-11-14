package com.scaler.recursionandbacktracking;

import java.util.ArrayList;
import java.util.List;

/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * <p>
 * By listing and labeling all of the permutations in order, We get the following sequence (ie, for n = 3 ) :
 * <p>
 * 1. "123"
 * 2. "132"
 * 3. "213"
 * 4. "231"
 * 5. "312"
 * 6. "321"
 * <p>
 * Given n and k, return the kth permutation sequence.
 * <p>
 * For example, given n = 3, k = 4, ans = "231"
 * <p>
 * Good questions to ask the interviewer :
 * <p>
 * What if n is greater than 10. How should multiple digit numbers be represented in string?
 * <p>
 * In this case, just concatenate the number to the answer. so if n = 11, k = 1, ans = "1234567891011"
 * <p>
 * What's the maximum value of n and k?
 * <p>
 * In this case, k will be a positive integer thats less than INT_MAX.
 * n is reasonable enough to make sure the answer does not bloat up a lot.
 *
 * @author sudhir on 09-Aug-2020
 */
public class KthPermutationSequence {
    private int[] fact;

    public String getPermutation(int A, int B) {
        if (A <= 0 || B <= 0) {
            return "";
        }
        fact = new int[A + 1];
        computeFactorial(A);
        if (B > fact[A]) {
            return "";
        }
        List<Integer> digits = new ArrayList<>();
        for (int i = 1; i <= A; i++) {
            digits.add(i);
        }
        StringBuilder builder = new StringBuilder();
        computeKthPermutation(builder, digits, A, B);
        return builder.toString();
    }

    private void computeFactorial(int n) {
        fact[0] = 1;
        // Integer range can hold factorial upto 12!. So if n > 12, we store INT_MAX.
        for (int i = 1; i <= Math.min(n, 12); i++) {
            fact[i] = fact[i - 1] * i;
        }
        for (int i = 13; i <= n; i++) {
            fact[i] = Integer.MAX_VALUE;
        }
    }

    private void computeKthPermutation(StringBuilder builder, List<Integer> digits, int n, int k) {
        // base case: there is only oen digit to select. we will append this digit to string builder
        if (n == 1) {
            builder.append(digits.get(0));
            return;
        }
        // fact[n-1] gives the block size
        // (k / fact[n - 1]) represents index of the block which is 0-indexed.
        // Example: index = 2 means 2nd block in 0-indexing. At this point, if digits = {1,2,3,4} then digits[index] = 3
        // which means the most significant digit will be 3.
        int index = k / fact[n - 1];
        // boundary case: if k % fact[n - 1] == 0, then kth permutation will be a boundary element.
        // example: let digits = {1,2,3,4} and k = 12, now, k % fact[n-1] == 0 and 12/6 = 2.
        // if we take index = 2, it means we will look at block number = 2 and fill the first digit as 3 which is wrong.
        // so we decrement index by 1 and pick the digit in case of permutations lying in the boundary.
        if (k % fact[n - 1] == 0) {
            index -= 1;
        }
        builder.append(digits.get(index));
        // remove the number which is already used
        digits.remove(index);
        // we have to update k so that we can skip elements(i.e. num_blocks * num_elements_in_each_block) and
        // find the index for the next element in the permutation.
        // fact[n-1] = block size/num_elements_in_each_block, index = num_blocks
        k = k - fact[n - 1] * index;
        // in the next call, decrement n by 1 and call the method.
        computeKthPermutation(builder, digits, n - 1, k);
    }

    public static void main(String[] args) {
        KthPermutationSequence kthPermuteSeq = new KthPermutationSequence();
        int n = 4;
        int k = 14;
        System.out.println(kthPermuteSeq.getPermutation(n, k));
    }
}

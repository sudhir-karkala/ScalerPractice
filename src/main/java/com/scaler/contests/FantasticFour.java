package com.scaler.contests;

/**
 * Given an array of positive integers of length n.
 * Our task is to find minimum number of operations to convert an array so that arr[i] % 4 is zero for each i. <br/>
 * In each operation, we can take any two elements from the array, remove both of them and put back their sum in the array.
 *
 * @author sudhir on 01-Jul-2020
 */
public class FantasticFour {
    public int solve(int[] A) {
        int count[] = new int[4];
        for (int i = 0; i < A.length; i++) {
            count[A[i] % 4]++;
        }

        // We club the numbers with remainders 1 and 3 and  add to the answer the minimum of these and update the count of 1 and 3.
        int ops = 0;
        int val = Math.min(count[1], count[3]);
        ops += val;
        count[1] -= val;
        count[3] -= val;

        // We combine two numbers with remainder 1 which in turn increase the count of 2 by one and
        // decrease count of 1 by two. We will do this repeatedly.
        ops += count[1] / 2;
        count[2] += count[1] / 2;
        count[1] %= 2;

        // We combine two numbers with remainder 3 which in turn increase the count of 2 by one and
        // decrease count of 3 by two. We will do this repeatedly.
        ops += count[3] / 2;
        count[2] += count[3] / 2;
        count[3] %= 2;

        // Divide the remaining numbers with remainder 2 into 2 equal halves.
        // After this, if there is still any number left, the answer is -1
        ops += count[2] / 2;
        count[2] = count[2] % 2;
        return (count[2] > 0 || count[1] > 0 || count[3] > 0) ? -1 : ops;
    }

    public static void main(String[] args) {
        FantasticFour f = new FantasticFour();
        int[] a1 = {1, 3, 4, 4, 2, 2};
        System.out.println(f.solve(a1));
    }
}

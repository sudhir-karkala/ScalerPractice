package com.scaler.arrays;

import java.util.Arrays;

/**
 * Implement the next permutation, which rearranges numbers into the numerically next greater permutation of numbers
 * for a given array A of size N.
 * If such arrangement is not possible, it must be rearranged as the lowest possible order i.e., sorted in an ascending order.
 * <li>
 * Input 1: A = [1, 2, 3], Output 1: [1, 3, 2]
 * </li>
 * <li>
 * Input 2: A = [3, 2, 1] Output 2: [1, 2, 3]
 * </li>
 * <li>
 * Input 3: A = [1, 1, 5] Output 3: [1, 5, 1]
 * </li>
 * <li>
 * Input 4: A = [20, 50, 113] Output 4: [20, 113, 50]
 * </li>
 *
 * @author sudhir on 02-Feb-2020
 */
public class NextPermutation {
    public int[] nextPermutation(int[] A) {
        int firstCharacterIndex = getFirstCharacterIndex(A);
        int secondCharacterIndex = getSecondCharacterIndex(firstCharacterIndex, A);
        if (firstCharacterIndex < secondCharacterIndex) {
            int temp = A[firstCharacterIndex];
            A[firstCharacterIndex] = A[secondCharacterIndex];
            A[secondCharacterIndex] = temp;
            reverseBySwapping(A, firstCharacterIndex + 1, A.length - 1);
        } else {
            reverseBySwapping(A, 0, A.length - 1);
        }
        return A;
    }

    private int getFirstCharacterIndex(int[] A) {
        int index = 0;
        for (int i = A.length - 2; i >= 0; i--) {
            if (A[i] < A[i + 1]) {
                index = i;
                break;
            }
        }
        return index;
    }

    private int getSecondCharacterIndex(int index, int[] A) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = index + 1; i < A.length; i++) {
            if (A[index] < A[i] && min > A[i]) {
                min = A[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private void reverseBySwapping(int[] A, int start, int end) {
        while (start < end) {
            int temp = A[start];
            A[start] = A[end];
            A[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3};
        NextPermutation np = new NextPermutation();
        System.out.println(Arrays.toString(np.nextPermutation(A)));
    }
}

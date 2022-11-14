package com.scaler.searching;

/**
 * Given an array of integers A of size N and an integer B. College library has N books,the ith book has A[i] number of pages. You have to allocate books to B number of students so that maximum number of pages alloted to a student is minimum.
 * A book will be allocated to exactly one student.
 * Each student has to be allocated at least one book.
 * <p>
 * Allotment should be in contiguous order, for example: A student cannot be allocated book 1 and book 3, skipping book 2.
 * Calculate and return that minimum possible number.
 * </p>
 * NOTE: Return -1 if a valid assignment is not possible.
 *
 * @author sudhir on 01-Mar-2020
 */
public class AllocateBooks {
    public int books(int[] A, int B) {
        int min = 0;
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            max += A[i];
        }
        return binSearch(A, min, max, B);
    }

    private int binSearch(int[] A, int l, int r, int B) {
        int result = Integer.MAX_VALUE;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (check(mid, A, B)) {
                result = Math.min(result, mid);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return result;
    }

    private boolean check(int target, int[] A, int B) {
        int studentCount = 1;
        int curSum = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > target) {
                return false;
            }
            if (curSum + A[i] <= target) {
                curSum += A[i];
            } else {
                studentCount++;
                curSum = A[i];
                if (studentCount > B) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] a1 = {12, 34, 67, 90};
        int b1 = 2;
        AllocateBooks allocateBooks = new AllocateBooks();
        System.out.println(allocateBooks.books(a1, b1));
    }
}


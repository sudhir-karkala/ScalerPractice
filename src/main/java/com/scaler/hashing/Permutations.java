package com.scaler.hashing;

/**
 * You are given two strings A and B of size N and M respectively. <br/>
 * You have to find the count of all permutations of A present in B as a substring. <br/>
 * You can assume a string will have only lowercase letters. <br/>
 *
 * @author sudhir on 21-Apr-2020
 */
public class Permutations {
    public int solve(String A, String B) {
        // hashmap array of size 26 is enough since only lowercase letters are present
        int[] hashmapA = new int[26];
        int[] hashmapB = new int[26];
        int m = A.length();
        int n = B.length();
        for (int i = 0; i < m; i++) {
            hashmapA[A.charAt(i) - 'a']++;
        }

        // consider the first window of size m in string B
        for (int i = 0; i < m; i++) {
            hashmapB[B.charAt(i) - 'a']++;
        }
        int count = 0;
        // check if window of characters is same.
        count += isSame(hashmapA, hashmapB);

        // keep moving the window one step and update count
        for (int i = 0, j = m; j < n && i < n; j++, i++) {
            hashmapB[B.charAt(i) - 'a'] -= 1; // decrement count for outgoing character
            hashmapB[B.charAt(j) - 'a'] += 1; // increment count for incoming character
            count += isSame(hashmapA, hashmapB);
        }
        return count;
    }

    private int isSame(int[] hashmapA, int[] hashmapB) {
        for (int i = 0; i < hashmapA.length; i++) {
            if (hashmapA[i] != hashmapB[i]) {
                return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        String a = "abc";
        String b = "abcbacabc";
        System.out.println(permutations.solve(a, b));
    }
}

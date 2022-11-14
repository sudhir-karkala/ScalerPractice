package com.scaler.tries;

import java.util.Arrays;

/**
 * We want to make a custom contacts finder applications as part of our college project.
 * The application must perform two types of operations:
 * <p>
 * Type 1: Add name B[i] ,denoted by 0 in vector A where B[i] is a string in vector B denoting a contact name.
 * This must store B[i] as a new contact in the application.
 * <p>
 * Type 2: Find partial for B[i] ,denoted by 1 in vector A where B[i] is a string in vector B denoting a partial name
 * to search the application for. It must count the number of contacts starting with B[i].
 * <p>
 * You have been given sequential add and find operations. You need to perform each operation in order.
 * <p>
 * And return as an array of integers, answers for each query of type 2(denoted by 1 in A) .
 * <p>
 * Problem Constraints<br/>
 * 1 <= |A| <= 10000
 * <p>
 * 1 <= |length of strings in B| <= 10
 * <p>
 * <p>
 * <p>
 * Input Format<br/>
 * First argument is the vector A, which denotes the type of query.
 * <p>
 * Second argument is the vector B, which denotes the string for corresponding query.
 * <p>
 * <p>
 * <p>
 * Output Format<br/>
 * Return an array of integers, denoting answers for each query of type 1.
 *
 * @author sudhir on 28-Jul-2020
 */
public class ContactFinder {
    private TrieNode root;

    public void insertKey(String key) {
        TrieNode trieNode = root;
        int n = key.length();
        for (int i = 0; i < n; i++) {
            if (!trieNode.getChildren().containsKey(key.charAt(i))) {
                trieNode.getChildren().put(key.charAt(i), new TrieNode());
            }
            trieNode = trieNode.getChildren().get(key.charAt(i));
            trieNode.setCount(trieNode.getCount() + 1);
        }
        trieNode.setEnd(true);
    }

    public int searchKey(String key) {
        TrieNode trieNode = root;
        int n = key.length();
        for (int i = 0; i < n; i++) {
            if (!trieNode.getChildren().containsKey(key.charAt(i))) {
                return 0;
            }
            trieNode = trieNode.getChildren().get((key.charAt(i)));
        }
        return trieNode.getCount();
    }

    public int[] solve(int[] A, String[] B) {
        root = new TrieNode();
        int queryCount = A.length;
        int resultSize = 0;
        for (int i = 0; i < queryCount; i++) {
            if (A[i] == 1) {
                resultSize++;
            }
        }
        int[] result = new int[resultSize];
        int k = 0;
        for (int i = 0; i < queryCount; i++) {
            switch (A[i]) {
                case 0:
                    insertKey(B[i]);
                    break;
                case 1:
                    result[k++] = searchKey(B[i]);
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ContactFinder cf = new ContactFinder();
        int[] a1 = {0, 0, 1, 1};
        String[] b1 = {"hack", "hacker", "hac", "hak"};
        int[] a2 = {0, 1};
        String[] b2 = {"abcde", "abc"};
        System.out.println(Arrays.toString(cf.solve(a1, b1)));
        System.out.println(Arrays.toString(cf.solve(a2, b2)));
    }
}

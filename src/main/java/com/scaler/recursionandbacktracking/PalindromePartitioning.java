package com.scaler.recursionandbacktracking;

import java.util.ArrayList;

/**
 * Given a string s, partition s such that every string of the partition is a palindrome.<br/>
 * Return all possible palindrome partitioning of s. <br/>
 * Ordering the results in the answer :<br/>
 * Entry i will come before Entry j if :<br/>
 * 1. len(Entryi[0]) < len(Entryj[0]) OR<br/>
 * 2. (len(Entryi[0]) == len(Entryj[0]) AND len(Entryi[1]) < len(Entryj[1])) OR * * *<br/>
 * 3. (len(Entryi[0]) == len(Entryj[0]) AND ... len(Entryi[k] < len(Entryj[k]))
 *
 * @author sudhir on 29-Mar-2020
 */
public class PalindromePartitioning {
    // Backtracking approach
    public ArrayList<ArrayList<String>> partition(String a) {
        ArrayList<ArrayList<String>> partitions = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        int n = a.length();
        partition(a, n, partitions, temp, 0);
        return partitions;
    }

    private void partition(String a, int len, ArrayList<ArrayList<String>> partitions, ArrayList<String> temp, int index) {
        if (index == len) {
            partitions.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < len; i++) {
            String str = a.substring(index, i + 1);
            if (isPalindrome(str)) {
                temp.add(str);
                partition(a, len, partitions, temp, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str) {
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning p = new PalindromePartitioning();
        String a = "aab";
        System.out.println(p.partition(a));
    }
}

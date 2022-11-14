package com.scaler.linkedlists;

/**
 * Given a linked list of integers.
 * Find and return the length of the longest palindrome list that exists in that linked list. <br/>
 * Note: A palindrome list is a list that reads the same backward and forward. <br/>
 * Expected memory complexity : O(1)
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of the linked list <= 2000
 * <p>
 * 1 <= Node value <= 100
 *
 * @author sudhir on 09-Apr-2020
 */
public class LengthOfLongestPalindromicList {
    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public int solve(ListNode A) {
        // consider every node as center denoted by cur pointer and keep updating max length
        // this is to consider odd length palindrome.
        ListNode cur = A;
        int maxLength = Integer.MIN_VALUE;
        int len = 1;
        ListNode prev = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            ListNode start = prev;
            ListNode end = next;
            while (start != null && end != null) {
                if (start.val == end.val) {
                    len += 2;
                    start = start.next;
                    end = end.next;
                } else {
                    break;
                }
            }
            //update maxLength
            maxLength = Math.max(maxLength, len);
            len = 1;
            prev = cur;
            cur = next;
        }
        // consider even length palindrome. In this case there will be two centers denoted by cur and next pointers
        cur = prev;
        len = 0;
        prev = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            ListNode start = cur;
            ListNode end = next;
            while (start != null && end != null) {
                if (start.val == end.val) {
                    len += 2;
                    start = start.next;
                    end = end.next;
                } else {
                    break;
                }
            }
            //update maxLength
            maxLength = Math.max(maxLength, len);
            len = 0;
            prev = cur;
            cur = next;
        }
        // we could have updated head pointer here if that was supposed to be returned, but here it is not necessary
        // as we return only the maxLength
        return maxLength;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(2);
        LengthOfLongestPalindromicList l = new LengthOfLongestPalindromicList();
        System.out.println(l.solve(head));
    }
}

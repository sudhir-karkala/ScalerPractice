package com.scaler.linkedlists;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * Problem Constraints
 * <p>
 * 0 <= length of linked list <= 10^6
 *
 * @author sudhir on 05-Apr-2020
 */
public class RemoveDuplicates {
    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode deleteDuplicates(ListNode A) {
        if (A == null || A.next == null) {
            return A;
        }
        ListNode cur = A;
        ListNode prev = cur;
        cur = cur.next;
        while (cur != null) {
            if (prev.val == cur.val) {
                cur = cur.next;
            } else {
                prev.next = cur;
                prev = cur;
                cur = cur.next;
            }
        }
        prev.next = null;
        return A;
    }
}

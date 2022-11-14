package com.scaler.linkedlists;

/**
 * Merge two sorted linked lists A and B and return it as a new list. <br/>
 * The new list should be made by splicing together the nodes of the first two lists, and should also be sorted.
 * <p>
 * Problem Constraints
 * <p>
 * 0 <= |A|, |B| <= 10^5
 *
 * @author sudhir on 09-Apr-2020
 */
public class MergeTwoSortedLists {
    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // approach 1: using iterative approach
    public ListNode mergeTwoLists(ListNode A, ListNode B) {
        if (A == null) {
            return B;
        }
        if (B == null) {
            return A;
        }
        ListNode p1 = A;
        ListNode p2 = B;
        ListNode head = null;
        ListNode p = head;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                if (p == null) {
                    p = p1;
                    head = p;
                } else {
                    p.next = p1;
                    p = p.next;
                }
                p1 = p1.next;
            } else {
                if (p == null) {
                    p = p2;
                    head = p;
                } else {
                    p.next = p2;
                    p = p.next;
                }
                p2 = p2.next;
            }
        }
        while (p1 != null) {
            p.next = p1;
            p = p.next;
            p1 = p1.next;
        }
        while (p2 != null) {
            p.next = p2;
            p = p.next;
            p2 = p2.next;
        }
        return head;
    }

    // approach 2: using recursive approach
    public ListNode mergeTwoListsRecursive(ListNode A, ListNode B) {
        if (A == null) {
            return B;
        }
        if (B == null) {
            return A;
        }
        ListNode result;
        if (A.val < B.val) {
            result = A;
            result.next = mergeTwoListsRecursive(A.next, B);
        } else {
            result = B;
            result.next = mergeTwoListsRecursive(A, B.next);
        }
        return result;
    }
}

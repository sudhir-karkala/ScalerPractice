package com.scaler.linkedlists;

/**
 * Given a linked list A, swap every two adjacent nodes and return its head. <br/>
 * NOTE: Your algorithm should use only constant space. You may not modify the values in the list,
 * only nodes itself can be changed.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= |A| <= 10^6
 * <p>
 * Input Format
 * <p>
 * The first and the only argument of input contains a pointer to the head of the given linked list.
 * <p>
 * Output Format
 * <p>
 * Return a pointer to the head of the modified linked list.
 *
 * @author sudhir on 05-Apr-2020
 */
public class SwapNodesInPairs {
    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode swapPairs(ListNode A) {
        if (A == null || A.next == null) {
            return A;
        }
        ListNode cur = A;
        ListNode next;
        ListNode prev = A;
        A = A.next;
        while (cur != null && cur.next != null) {
            next = cur.next.next;
            cur.next.next = cur;
            prev = cur;
            prev.next = (next == null ? next : next.next);
            cur = next;
        }
        prev.next = cur;
        return A;
    }
}

package com.scaler.linkedlists;

/**
 * Given a singly linked list A<br/>
 * A: A0 -> A1 -> … -> An-1 -> An<br/>
 * reorder it to: A0 -> An -> A1 -> An-1 -> A2 -> An-2 -> …<br/>
 * You must do this in-place without altering the nodes' values.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= |A| <= 10^6
 * <p>
 * Input Format
 * <p>
 * The first and the only argument of input contains a pointer to the head of the linked list A.
 * <p>
 * Output Format
 * <p>
 * Return a pointer to the head of the modified linked list.
 *
 * @author sudhir on 01-Apr-2020
 */
public class ReorderList {
    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode reorderList(ListNode A) {
        // suppose A: 1->2->3->4->5->6->null
        // output is A: 1->6->2->5->3->4->null
        if (A == null || A.next == null || A.next.next == null) {
            return A;
        }
        ListNode slow = A;
        ListNode fast = A;
        ListNode prev = A;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;

        // reverse nodes from slow to end
        ListNode cur = slow;
        prev = null;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        slow = prev;

        // now we have two lists L1->L2->L3->...->null and
        // L11->L12->L13->..->null and we have to merge them like this:
        // L1->L11->L2->L12->...->null
        // Note that list1 has same nodes or one node less compared to list2
        ListNode p1 = A;
        ListNode p2 = slow;
        while (p1 != null && p2 != null) {
            ListNode next1 = p1.next;
            ListNode next2 = p2.next;
            p1.next = p2;
            // Note that list1 has same nodes or one node less compared to list2.
            // Hence, next1 can be null in which case we just continue with list2
            if (next1 != null) {
                p2.next = next1;
            }
            p1 = next1;
            p2 = next2;
        }
        return A;
    }
}

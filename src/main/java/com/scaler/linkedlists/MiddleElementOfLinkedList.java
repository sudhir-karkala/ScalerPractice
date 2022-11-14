package com.scaler.linkedlists;

/**
 * Given a linked list of integers. Find and return the middle element of the linked list.
 * <p>
 * NOTE: If there are N nodes in the linked list and N is even then return the (N/2 + 1)th element.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of the linked list <= 100000
 * <p>
 * 1 <= Node value <= 10^9
 * <p>
 * Input Format
 * <p>
 * The only argument given head pointer of linked list.
 * <p>
 * Output Format
 * <p>
 * Return the middle element of the linked list.
 *
 * @author sudhir on 22/08/20
 */
public class MiddleElementOfLinkedList {
    public int solve(ListNode A) {
        ListNode slow = A;
        ListNode fast = A;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.val;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}

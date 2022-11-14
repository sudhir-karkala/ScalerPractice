package com.scaler.linkedlists;

/**
 * Given a doubly linked list of integers with one pointer of each node pointing to the next node
 * (just like in a single link list) while the second pointer, however, can point to any node in the list
 * and not just the previous node.<br/>
 * You have to create a copy of this list and return the head pointer of the duplicated list.<br/>
 * Problem Constraints
 * <p>
 * 1 <= length of the list <= 100000
 * <p>
 * 1 <= Value of node <= 100000
 * <p>
 * Return the head pointer of the duplicated list.
 *
 * @author sudhir on 05-Apr-2020
 */
public class CloneLinkedList {
    static class ListNode {
        int val;
        ListNode next, random;

        ListNode(int x) {
            val = x;
            next = random = null;
        }
    }

    ListNode cloneList(ListNode A) {
        ListNode cur = A;
        ListNode next = null;
        //add intermediate nodes.
        while (cur != null) {
            next = cur.next;
            ListNode temp = new ListNode(cur.val);
            cur.next = temp;
            temp.next = next;
            cur = next;
        }

        // update random pointer for intermediate nodes.
        cur = A;
        while (cur != null && cur.next != null) {
            cur.next.random = cur.random.next;
            cur = cur.next.next;
        }

        // separate out original list from the list of intermediate nodes.
        ListNode cur1 = A;
        ListNode cur2 = A.next;
        ListNode head = cur2;
        while (cur1 != null && cur1.next != null) {
            cur1.next = cur2.next;
            cur2.next = (cur1.next == null ? cur1.next : cur1.next.next);
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return head;
    }
}

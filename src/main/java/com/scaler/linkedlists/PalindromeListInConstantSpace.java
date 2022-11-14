package com.scaler.linkedlists;

/**
 * Given a singly linked list, determine if its a palindrome.
 * Return 1 or 0 denoting if its a palindrome or not, respectively.<br/>
 * Note: Expected solution is linear in time and constant in space.<br/>
 * Note: Since it is a constant space problem, you can modify the list but restore the original state
 * once solution is obtained.
 *
 * @author sudhir on 23-Mar-2020
 */
public class PalindromeListInConstantSpace {
    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public int lPalin(ListNode A) {
        //first find the middle of the linked list
        if (A == null || A.next == null) {
            return 1;
        }
        ListNode slow = A;
        ListNode fast = A;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;//set the 1st linked list
        //reverse the 2nd list which starts from slow to end.
        ListNode cur = slow;
        ListNode p = null;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = p;
            p = cur;
            cur = next;
        }
        ListNode p1 = A;
        ListNode p2 = p;
        int returnVal = 1;
        //and compare with 1st list which starts from head to previous of slow.
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                returnVal = 0;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        //restore the original linked list by again reversing the 2nd list and merging it with 1st list
        cur = p;
        p = null;
        while (cur != null) {
            next = cur.next;
            cur.next = p;
            p = cur;
            cur = next;
        }
        cur = A;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = p;//point the end of 1st linked list to head of second linked list
        return returnVal;
    }

    public static void main(String[] args) {
        PalindromeListInConstantSpace p = new PalindromeListInConstantSpace();
        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        ListNode head = node;
        System.out.println(p.lPalin(head));
    }
}

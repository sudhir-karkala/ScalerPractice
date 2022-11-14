package com.scaler.linkedlists;

/**
 * Given a singly linked list A and an integer K, reverse the nodes of the list K at a time
 * and return modified linked list.<br/>
 * 1 <= |A| <= 10^3, K always divides A
 *
 * @author sudhir on 04-Apr-2020
 */
public class KReverseLinkedList {
    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // This solution will work even for cases where K doesn't divide A
    public ListNode reverseList(ListNode A, int K) {
        if (A == null || A.next == null) {
            return A;
        }
        ListNode prev = A;
        ListNode start = A;
        ListNode end = A;
        ListNode p = A;
        ListNode q = A;
        int i = 1;
        while (end != null && i < K) {
            p = end;
            end = end.next;
            i++;
        }
        // set the head pointer to the end of the first range of nodes to be reversed.
        A = (end == null ? p : end);
        if (end != null) {
            ListNode next = end.next;
            while (next != null) {
                prev = start;
                reverse(start, end);
                start = next;
                end = start;
                i = 1;
                while (end != null && i < K) {
                    q = end;
                    end = end.next;
                    i++;
                }
                end = (end == null && i <= K) ? q : end;
                prev.next = end;
                if (end != null) {
                    next = end.next;
                }
            }
            // once next pointer becomes null, we need to reverse the nodes pointed by
            // start and end  pointers before returning.
            reverse(start, end);
        } else {
            // this will be the case where K > number of nodes in the list. So just reverse the entire list.
            reverse(start, end);
        }
        return A;
    }

    private void reverse(ListNode start, ListNode end) {
        if (start == end) {
            return;
        }
        ListNode cur = start;
        ListNode p = null;
        ListNode n = null;
        while (cur != end) {
            n = cur.next;
            cur.next = p;
            p = cur;
            cur = n;
        }
        if (cur != null) {
            cur.next = p;
        }
    }

    public static void main(String[] args) {
        KReverseLinkedList kReverseLinkedList = new KReverseLinkedList();
        ListNode head = null;
        ListNode one = new ListNode(1);
        head = one;
        one.next = new ListNode(2);
        one.next.next = new ListNode(3);
        one.next.next.next = new ListNode(4);
        one.next.next.next.next = new ListNode(5);
        one.next.next.next.next.next=new ListNode(6);
        int k = 2;
        head = kReverseLinkedList.reverseList(head, k);
        System.out.println(head.val + " " + head.next.val + " "
                + head.next.next.val + " "
                + head.next.next.next.val + " "
                + head.next.next.next.next.val + " "
                + head.next.next.next.next.next.val);
    }
}

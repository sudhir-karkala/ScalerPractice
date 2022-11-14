package com.scaler.linkedlists;

/**
 * Reverse a linked list A from position B to C. <br/>
 * NOTE: Do it in-place and in one-pass.<br/>
 * 1 <= |A| <= 10^6, 1 <= B <= C <= |A|
 * <p>
 * Input Format
 * <p>
 * The first argument contains a pointer to the head of the given linked list, A.
 * <p>
 * The second argument contains an integer B.
 * <p>
 * The third argument contains an integer C.
 * <p>
 * Output Format
 * <p>
 * Return a pointer to the head of the modified linked list.
 *
 * @author sudhir on 05-Apr-2020
 */
public class ReverseLinkListII {
    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode reverseBetween(ListNode A, int B, int C) {
        if (A == null || A.next == null || B == C) {
            return A;
        }
        ListNode start;
        ListNode end;
        int i = 1;
        ListNode prev = null;
        ListNode p = A;
        while (i < B) {
            prev = p;
            p = p.next;
            i++;
        }
        ListNode prevNode = prev;
        start = (B == 1 ? A : p);
        i = B;
        while (i < C) {
            p = p.next;
            i++;
        }
        end = p;
        if (B > 1) {
            prevNode.next = end;
        }
        ListNode nextNode = end.next;
        //reverse from start to end
        ListNode cur = start;
        ListNode next;
        prev = nextNode;
        int count = C - B + 1;
        i = 1;
        while (i <= count) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            i++;
        }
        start.next = nextNode;
        if (B == 1) {
            A = end;
        }
        return A;
    }
}

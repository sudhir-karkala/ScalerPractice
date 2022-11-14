package com.scaler.stacks;

import java.util.Stack;

/**
 * Given a singly linked list, determine if its a palindrome. Return 1 or 0 denoting if its a palindrome or not, respectively.
 * Extra space can be used in this case. This code uses stack.
 *
 * @author sudhir on 23-Mar-2020
 */
public class PalindromeList {
    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public int lPalin(ListNode A) {
        Stack<Integer> st = new Stack<>();
        ListNode head = A;
        while (head != null) {
            st.push(head.val);
            head = head.next;
        }
        head = A;
        while (!st.isEmpty() && head != null) {
            if (st.pop() != head.val) {
                return 0;
            }
            head = head.next;
        }
        return 1;
    }

    public static void main(String[] args) {
        PalindromeList p = new PalindromeList();
        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        node.next = node1;
        node1.next = node2;
        ListNode head = node;
        System.out.println(p.lPalin(head));
    }
}

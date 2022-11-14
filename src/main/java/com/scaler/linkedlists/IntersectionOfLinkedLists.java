package com.scaler.linkedlists;

/**
 * Write a program to find the node at which the intersection of two singly linked lists, A and B, begins. <br/>
 * For example, the following two linked lists:<br/>
 * A = [1, 2, 3, 4, 5], B = [6, 3, 4, 5]<br/>
 * Output: [3, 4, 5] => The nodes have the same values after 3rd node in A and 2nd node in B.
 * Thus, the linked lists are intersecting after that point.
 * NOTE:<br/>
 * If the two linked lists have no intersection at all, return null.<br/>
 * The linked lists must retain their original structure after the function returns.<br/>
 * You may assume there are no cycles anywhere in the entire linked structure.<br/>
 * Your code should preferably run in O(n) time and use only O(1) memory.<br/>
 * The custom input to be given is different than the one explained in the examples. Please be careful.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= |A|, |B| <= 10^6
 * <p>
 * Input Format
 * <p>
 * The first argument of input contains a pointer to the head of the linked list A.
 * <p>
 * The second argument of input contains a pointer to the head of the linked list B.
 * <p>
 * Output Format
 * <p>
 * Return a pointer to the node after which the linked list is intersecting.
 *
 * @author sudhir on 09-Apr-2020
 */
public class IntersectionOfLinkedLists {
    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return null;
        }
        ListNode p1 = a;
        ListNode p2 = b;
        ListNode cur = p1;
        int count = 0;
        while (cur.next != null) {
            cur = cur.next;
            count++;
        }
        cur.next = p1;//make list 'a' circular
        count++;
        ListNode tail = cur;// we need this to restore the original state later
        // traverse 'count' number of nodes in second list
        for (int i = 0; i < count; i++) {
            if (p2 != null) {
                p2 = p2.next;
            }
            if (p2 == null && i < count) {// means there is no intersection point
                tail.next = null;
                return null;
            }
        }
        cur = b;
        while (cur != p2) {
            cur = cur.next;
            if (p2 != null) {
                p2 = p2.next;
            } else {
                // p2 has reached end
                tail.next = null;
                return null;// means cur and p2 can never meet as there is no intersection point.
            }
        }
        tail.next = null;
        return cur;
    }
}

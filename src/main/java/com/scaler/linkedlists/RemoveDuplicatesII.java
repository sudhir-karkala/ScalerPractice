package com.scaler.linkedlists;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list. <br/>
 * For example, Given 1->2->3->3->4->4->5, return 1->2->5. Given 1->1->1->2->3, return 2->3.
 *
 * @author sudhir on 05-Apr-2020
 */
public class RemoveDuplicatesII {
    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode deleteDuplicates(ListNode A) {
        if (A == null || A.next == null) {
            return A;
        }
        //add a dummy node.
        ListNode dummy = new ListNode(-1);
        //dummy's next will point to head.
        dummy.next= A;
        ListNode currentNode = A;
        ListNode prevNode = dummy;
        while (currentNode != null) {
            //if value in previous node's next and current's next are same, keep updating current.
            while (currentNode.next != null && prevNode.next.val == currentNode.next.val) {
                currentNode = currentNode.next;
            }
            //if current is not updated, then it means there was no duplicate. so we can update previous node to current node.
            if (prevNode.next == currentNode) {
                prevNode  = prevNode.next;
            } else {//else previous node's next will point to current's next.
                prevNode.next = currentNode.next;
            }
            //keep updating current pointer
            currentNode = currentNode.next;
        }
        //since we added dummy, head will be dummy's next.
        return dummy.next;
    }
}

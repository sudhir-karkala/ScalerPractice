package com.scaler.linkedlists;

/**
 * Given a linked list where every node represents a linked list and contains two pointers of its type:<br/>
 * Pointer to next node in the main list (right pointer)<br/>
 * Pointer to a linked list where this node is head (down pointer). All linked lists are sorted.<br/>
 * You are asked to flatten the linked list into a single list. Use down pointer to link nodes of the flattened list.<br/>
 * The flattened linked list should also be sorted.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= Total nodes in the list <= 100000
 * <p>
 * 1 <= Value of node <= 109
 * <p>
 * Input Format
 * <p>
 * The only argument given is head pointer of the doubly linked list.
 * <p>
 * Output Format
 * <p>
 * Return the head pointer of the Flattened list.
 *
 * @author sudhir on 01-May-2020
 */
public class FlattenLinkedList {
    static class ListNode {
        int val;
        ListNode right, down;

        ListNode(int x) {
            val = x;
            right = down = null;
        }
    }

    ListNode flatten(ListNode root) {
        if (root == null || root.right == null) {
            return root;
        }
        // recursively flatten till we reach the last node branch whose right is null. so we start flattening from the right most
        // and travel to the left since we need sorted list at the end.
        root.right = flatten(root.right);

        // recursively merge two branches: root and root.right
        root = merge(root, root.right);
        return root;
    }

    ListNode merge(ListNode a, ListNode b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        ListNode result;
        if (a.val < b.val) {
            result = a;
            result.down = merge(a.down, b);
        } else {
            result = b;
            result.down = merge(a, b.down);
        }
        // we make right pointer of the head node null for each branch so that we break the link
        // between two branches and only have down pointer
        result.right = null;
        return result;
    }
}

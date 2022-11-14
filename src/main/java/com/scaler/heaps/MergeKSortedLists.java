package com.scaler.heaps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list.<br/>
 * Ex: 1 -> 10 -> 20, 4 -> 11 -> 13, 3 -> 8 -> 9 will result in 1 -> 3 -> 4 -> 8 -> 9 -> 10 -> 11 -> 13 -> 20
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= total number of elements in given linked lists <= 100000
 * <p>
 * Input Format
 * <p>
 * First and only argument is a list containing N head pointers.
 * <p>
 * Output Format
 * <p>
 * Return a pointer to the head of the sorted linked list after merging all the given linked lists.
 *
 * @author sudhir on 18-Apr-2020
 */
public class MergeKSortedLists {
    static class ListNode {
        int val;
        ListNode next, random;

        ListNode(int x) {
            val = x;
            next = random = null;
        }
    }

    public ListNode mergeKLists(ArrayList<ListNode> a) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });
        minHeap.addAll(a);
        ListNode result = null;
        ListNode head = null;
        while (minHeap.peek() != null) {
            ListNode node = minHeap.poll();
            if (head == null) {
                head = node;
            }
            if (result == null) {
                result = node;
            } else {
                result.next = node;
                result = node;
            }
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }
        return head;
    }
}

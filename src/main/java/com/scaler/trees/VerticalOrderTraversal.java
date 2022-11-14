package com.scaler.trees;

import java.util.*;

/**
 * Given a binary tree, return a 2-D array with vertical order traversal of it.<br/>
 * Note : If 2 Tree Nodes shares the same vertical level then the one with lesser depth will come first.
 *
 * @author sudhir on 12-Apr-2020
 */
public class VerticalOrderTraversal {

    // object which holds the current node and it's distance from the root.
    // for example: root is at distance 0. It's left child is at a distance -1 from its root and right child at +1 from its root.
    // this logic applies to all nodes in the tree.
    static class Pair {
        TreeNode node;
        int distance;

        Pair(TreeNode node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {
        // map of <distance, list of nodes with that distance>
        // since it is a treemap, it will sort all <key,value> pairs by distance as key.
        Map<Integer, List<Integer>> map = new TreeMap<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (A == null) {
            return result;
        }
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(A, 0));
        map.computeIfAbsent(0, z -> new ArrayList<>()).add(A.val);
        while (!queue.isEmpty()) {
            Pair p = queue.remove();
            if (p.node.left != null) {
                queue.add(new Pair(p.node.left, p.distance - 1));
                map.computeIfAbsent(p.distance - 1, z -> new ArrayList<>()).add(p.node.left.val);
            }
            if (p.node.right != null) {
                queue.add(new Pair(p.node.right, p.distance + 1));
                map.computeIfAbsent(p.distance + 1, z -> new ArrayList<>()).add(p.node.right.val);
            }
        }
        for (Integer key : map.keySet()) {
            result.add(new ArrayList<>(map.get(key)));
        }
        return result;
    }
}

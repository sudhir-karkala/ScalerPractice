package com.scaler.companyquestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Two persons are playing a word game where players alternate appending
 * letters to a word. The person who spells out a word, or creates a prefix
 * for which there is no continuation possible, loses.
 * <p>
 * Given a dictionary of words denoted by string array A,
 * determine the letters the first player should start with,
 * such that with the optimal play they cannot lose.
 * <p>
 * NOTE: If no letter satisfies the above condition,
 * return a character array with only character '0'.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= length of A <= 10^5
 * <p>
 * 1 <= Sum of length of all words <= 10^6
 * <p>
 * Words consist of only lowercase letters
 * <p>
 * Input Format
 * <p>
 * First and only argument is a string array A.
 * <p>
 * Output Format
 * <p>
 * Return a character array denoting the letters as described
 * above in lexicographically sorted order.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = ["cat", "calf", "dog", "bear"]
 * <p>
 * Input 2:
 * <p>
 * A = ["ace", "act"]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * ['b', 'c']
 * <p>
 * Output 2:
 * <p>
 * ['0']
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * If first player chooses letter 'b' or 'c' and plays optimally,
 * first player wins the game.
 * <p>
 * Explanation 2:
 * <p>
 * There is no character such that first player wins the game. Return ['0']
 *
 * @author sudhir on 22-Nov-2020
 */
public class GameOfPrefixes {
    private TrieNode root;

    static class TrieNode {
        Map<Character, TrieNode> children;
        // id can be 1 or 0, we designate 0 for first player and 1 for second player.
        int id;
        boolean result;// default value is false.

        TrieNode() {
            children = new HashMap<>();
            id = 1;
        }
    }

    private void insertIntoTrie(String key) {
        int len = key.length();
        TrieNode trieNode = root;
        for (int i = 0; i < len; i++) {
            if (!trieNode.children.containsKey(key.charAt(i))) {
                TrieNode node = new TrieNode();
                // by doing xor and calculating new id,
                // we alternate between first player and second player turns.
                // for example: in "bear", if player 1 chooses b, player 2 chooses e,
                // player 1 chooses a and player 2 chooses r.
                node.id = trieNode.id ^ 1;
                trieNode.children.put(key.charAt(i), node);
            }
            trieNode = trieNode.children.get(key.charAt(i));
        }
    }

    private boolean dfs(TrieNode trieNode) {
        if (trieNode.children.isEmpty()) {
            // means leaf node
            // we return true if the node has id = 1, else false if id = 0
            return (trieNode.id == 1);
        }
        if (trieNode.id == 0) {
            // if the current player is 0, then all recursive calls must return true
            // so that first player can win.
            Boolean result = null;
            for (Map.Entry<Character, TrieNode> entry : trieNode.children.entrySet()) {
                boolean r = dfs(entry.getValue());
                if (result == null) {
                    result = r;
                } else {
                    result &= r;
                }
            }
            trieNode.result = result;
        } else {
            // if the current player is 1, then at least one of the recursive calls
            // must return true so that second player can win.
            boolean result = false;
            for (Map.Entry<Character, TrieNode> entry : trieNode.children.entrySet()) {
                result |= dfs(entry.getValue());
            }
            trieNode.result = result;
        }
        return trieNode.result;
    }

    public ArrayList<Character> solve(ArrayList<String> A) {
        root = new TrieNode();
        for (String key : A) {
            // insert all strings in trie
            insertIntoTrie(key);
        }
        ArrayList<Character> result = new ArrayList<>();
        for (Map.Entry<Character, TrieNode> entry : root.children.entrySet()) {
            entry.getValue().result = dfs(entry.getValue());
            if (entry.getValue().result == true) {
                result.add(entry.getKey());
            }
        }
        if (result.isEmpty()) {
            // There is no character such that first player wins the game
            result.add('0');
        }
        return result;
    }

    public static void main(String[] args) {
        GameOfPrefixes g = new GameOfPrefixes();
        ArrayList<String> s1 = new ArrayList<>(
                Arrays.asList("cat", "calf", "dog", "bear"));
        System.out.println(g.solve(s1));
        ArrayList<String> s2 = new ArrayList<>(
                Arrays.asList("ace", "act"));
        System.out.println(g.solve(s2));
    }
}

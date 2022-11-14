package com.scaler.tries;

import java.util.HashMap;
import java.util.Map;

/**
 * Structure of TrieNode
 *
 * @author sudhir on 24-Jul-2020
 */
public class TrieNode {
    private Map<Character, TrieNode> children;
    // isEnd = True means that particular character is the ending of any given word,
    // False means it is just a prefix of any given word.
    private boolean isEnd;
    // count represents the count of branches arising out of that particular character node which represents
    // number of references made by the given list of words.
    private int count;

    public TrieNode() {
        children = new HashMap<>();
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, TrieNode> children) {
        this.children = children;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

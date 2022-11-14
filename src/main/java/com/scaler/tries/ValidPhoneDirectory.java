package com.scaler.tries;

/**
 * Given a phone directory in the form of string array A containing N numeric strings.
 * <p>
 * If any phone number is prefix of another phone number then phone directory is invalid else it is valid.
 * <p>
 * You need to check whether the given phone directory is valid or not if it is valid then return 1 else return 0.
 * <p>
 * <p>
 * <p>
 * Problem Constraints
 * 2 <= N <= 10^5
 * <p>
 * 1 <= |A[i]| <= 50
 * <p>
 * A[i] consists only of numeric digits.
 * <p>
 * Return 1 if the given phone directory is valid else return 0.
 *
 * @author sudhir on 02-Aug-2020
 */
public class ValidPhoneDirectory {
    private TrieNode root;

    public int solve(String[] A) {
        root = new TrieNode();
        for (String s1 : A) {
            if (!checkValidPhoneDirectory2(s1)) {
                return 0;
            }
        }
        return 1;
    }

    /**
     * Note that we use count variable to check if the ending node already has some child or not/already set as ending node.
     *
     * @param key
     * @return boolean
     */
    private boolean checkValidPhoneDirectory(String key) {
        int n = key.length();
        TrieNode trieNode = root;
        for (int i = 0; i < n; i++) {
            if (!trieNode.getChildren().containsKey(key.charAt(i))) {
                trieNode.getChildren().put(key.charAt(i), new TrieNode());
            } else {
                // if the current node is the end of some other word, then return false.
                // example: "1234" is present and we try to insert "12345". In this case return false.
                if (trieNode.isEnd()) {
                    return false;
                }
            }
            trieNode = trieNode.getChildren().get(key.charAt(i));
            trieNode.setCount(trieNode.getCount() + 1);
        }
        trieNode.setEnd(true);
        // example: "1234" is present and we try to insert "123". In this case count is > 1. so return false
        return (trieNode.getCount() == 1);
    }

    /**
     * We can directly check if the current element's ending node already has any child or not/already set as ending node
     * without using count variable.
     *
     * @param key
     * @return boolean
     */
    private boolean checkValidPhoneDirectory2(String key) {
        int n = key.length();
        TrieNode trieNode = root;
        for (int i = 0; i < n; i++) {
            if (!trieNode.getChildren().containsKey(key.charAt(i))) {
                trieNode.getChildren().put(key.charAt(i), new TrieNode());
            }
            trieNode = trieNode.getChildren().get(key.charAt(i));
            // if the current node is the end of some other word, then return false.
            // example: "1234" is present and we try to insert "12345". In this case return false.
            // example: "5678" is present and we try to insert "5678". In this case, 8 is already the ending node. so return false.
            if (trieNode.isEnd()) {
                return false;
            }
        }
        trieNode.setEnd(true);
        // example: "1234" is present and we try to insert "123". In this case 3 already has a child which is 4. so return false
        return trieNode.getChildren().isEmpty();
    }

    public static void main(String[] args) {
        ValidPhoneDirectory v = new ValidPhoneDirectory();
        String[] s1 = {"1234", "2342", "567"};
        String[] s2 = {"00121", "001"};
        String[] s3 = {"5678", "5678"};
        System.out.println(v.solve(s1));
        System.out.println(v.solve(s2));
        System.out.println(v.solve(s3));
    }
}

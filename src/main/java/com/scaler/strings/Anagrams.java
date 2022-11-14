package com.scaler.strings;

import java.util.*;

/**
 * Given an array A of N strings, return all groups of strings that are anagrams. <br/>
 * Represent a group by a list of integers representing the index(1-based) in the original list. <br/>
 * Look at the sample case for clarification. <br/>
 * A = [cat, dog, god, tca], Output: [ [1, 4], [2, 3] ]<br/>
 * "cat" and "tca" are anagrams which correspond to index 1 and 4 and "dog" and "god"
 * are another set of anagrams which correspond to index 2 and 3.<br/>
 * The indices are 1 based ( the first element has index 1 instead of index 0).<br/>
 * NOTE: Anagram : a word, phrase, or name formed by rearranging the letters of another, such as 'spar', formed from 'rasp'.
 *
 * @author sudhir on 09-Apr-2020
 */
public class Anagrams {
    public ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {
        Map<String, ArrayList<Integer>> map = new HashMap<>();
        int size = A.size();
        for (int i = 0; i < size; i++) {
            char[] temp = A.get(i).toCharArray();
            Arrays.sort(temp);
            String str = new String(temp);
            map.computeIfAbsent(str, z -> new ArrayList<>()).add(i + 1);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Anagrams a = new Anagrams();
        List<String> list = new ArrayList<>(Arrays.asList("cat", "dog", "god", "tca"));
        System.out.println(a.anagrams(list));
    }
}

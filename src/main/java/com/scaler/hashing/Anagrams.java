package com.scaler.hashing;

import java.util.*;

/**
 * Given an array A of N strings, return all groups of strings that are anagrams. <br/>
 * Represent a group by a list of integers representing the index(1-based) in the original list.
 * Look at the sample case for clarification. <br/>
 * NOTE: Anagram : a word, phrase, or name formed by rearranging the letters of another, such as 'spar', formed from 'rasp'.<br/>
 * Input: A = [cat, dog, god, tca]<br/>
 * Output: [ [1, 4],[2, 3] ]
 *
 * @author sudhir on 19-Apr-2020
 */
public class Anagrams {
    public ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {
        int size = A.size();
        Map<String, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            char[] temp = A.get(i).toCharArray();
            Arrays.sort(temp);
            String toInsert = new String(temp);
            map.computeIfAbsent(toInsert, z -> new ArrayList<>()).add(i + 1);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Anagrams anagrams = new Anagrams();
        List<String> A = new ArrayList<>(Arrays.asList("cat", "dog", "god", "tca"));
        System.out.println(anagrams.anagrams(A));
    }
}

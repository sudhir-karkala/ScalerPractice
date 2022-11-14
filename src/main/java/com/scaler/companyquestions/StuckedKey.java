package com.scaler.companyquestions;

/**
 * Given a string array A containing N strings representing a dictionary.
 * <p>
 * You were typing a random word from the dictionary but your keyboard
 * was very old and it might be possible that the key you pressed down got
 * stuck for some time and at the end you got a different string from the
 * original one you were typing.
 * <p>
 * Like for example suppose you were typing "cat" and it got typed as "ccaaaatttt.
 * <p>
 * Your are given a string B find out whether this string can be typed by
 * picking any word from the given dictionary A.
 * If it can be typed then return 1 else return 0.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 10^3
 * <p>
 * 1 <= |A[i]| <= 10^3
 * <p>
 * 1 <= |B| <= 10^6
 * <p>
 * Each string only consist of lowercase characters.
 * <p>
 * Input Format
 * <p>
 * First argument is an string array A representing the dictionary.
 * <p>
 * Second argument is an string B.
 * <p>
 * Output Format
 * <p>
 * Return 1 if string B can be typed using any word from A via the faulty keyboard ,
 * else return 0.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = ["hello", "cat", "world", "dog", "bird", "grass", "green", "help", "greet", "great"]
 * B = "bbbirrrdddd"
 * <p>
 * Input 2:
 * <p>
 * A = ["hello", "cat", "world", "dog", "bird", "grass", "green", "help", "greet", "great"]
 * B = "gggraasssa"
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * 1
 * <p>
 * Output 2:
 * <p>
 * 0
 * <p>
 * Example Explanation
 * <p>
 * Explanation 1:
 * <p>
 * The character's 'b', 'r', & 'd' all repeat.
 * Assuming their keys got stuck, we can form the word using string 'bird',
 * which exists in the dictionary.
 * <p>
 * Explanation 2:
 * <p>
 * No such word exist in the dictionary that can be used to type the
 * given string "gggraasssa"
 *
 * @author sudhir on 05-Nov-2020
 */
public class StuckedKey {
    public int solve(String[] A, String B) {
        int bLen = B.length();
        for (String str : A) {
            int aPtr = 0;
            int bPtr = 0;
            int alen = str.length();
            boolean match = true;
            while (true) {
                while (aPtr < alen && bPtr < bLen) {
                    if (aPtr < alen && bPtr < bLen && str.charAt(aPtr) != B.charAt(bPtr)) {
                        match = false;
                        break;
                    } else {
                        aPtr++;
                        bPtr++;
                        int aCount = 1;
                        int bCount = 1;
                        while (aPtr < alen && str.charAt(aPtr) == str.charAt(aPtr - 1)) {
                            aCount++;
                            aPtr++;
                        }

                        while (bPtr < bLen && B.charAt(bPtr) == B.charAt(bPtr - 1)) {
                            bCount++;
                            bPtr++;
                        }
                        if (aCount > bCount) {
                            match = false;
                            break;
                        }
                    }
                }
                if (bPtr < bLen || aPtr < alen) {
                    // this means there is atleast one character in B which we cannot match
                    // with dictionary word as we have reached end of dictionary word
                    // traversal or the other way round where we have reached the end
                    // of word B and we cannot match dictionary word with this.
                    match = false;
                }
                if (match == false) {
                    break;
                } else {
                    return 1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        StuckedKey s = new StuckedKey();
        String[] a1 = {"hello", "cat", "world", "dog", "bird", "grass", "green", "help", "greet", "great"};
        String b1 = "bbbirrrdddd";
        System.out.println(s.solve(a1, b1));

        String[] a2 = {"hello", "cat", "world", "dog", "bird", "grass", "green", "help", "greet", "great"};
        String b2 = "gggraa";
        System.out.println(s.solve(a2, b2));
    }
}

package com.scaler.stacks;

import java.util.Stack;

/**
 * Given a String A and an integer B. String A is encoded consisting of lowercase English letters and numbers. <br/>
 * A is encoded in a way where repetitions of substrings are represented as substring followed by the count of substrings. <br/>
 * For example: if the encrypted string is “ab2cd2” and B=6, so the output will be ‘d’ because the decrypted string is “ababcdababcd”
 * and 4th character is ‘b’. <br/>
 * You have to find and return the Bth character in the decrypted string. <br/>
 * Note: Frequency of encrypted substring can be of more than one digit. <br/>
 * For example, in “ab12c3”, ab is repeated 12 times. No leading 0 is present in the frequency of substring.<br/>
 * 1 <= length of the array <= 10^5, 1 < = K < = 10^9
 *
 * @author sudhir on 02-May-2020
 */
public class KthCharacterInDoubleDecryptedString {
    static class Pair {
        // this holds the current character and the position of that character after decrypting
        private Character ch;
        private long size;

        public Pair(Character ch, long size) {
            this.ch = ch;
            this.size = size;
        }
    }

    public String solve(String A, int B) {
        Stack<Pair> st = new Stack<>();
        int i = 0;
        int n = A.length();
        long size = 0;
        while (i < n) {
            if (Character.isLetter(A.charAt(i))) {
                st.push(new Pair(A.charAt(i), size + 1));
                size++;
            } else {
                // extract the number which can have more than one digit.
                // once extracted, update the size by multiplying it with this number.
                StringBuilder builder = new StringBuilder();
                while (i < n && Character.isDigit(A.charAt(i))) {
                    builder.append(A.charAt(i));
                    i++;
                }
                // we would have incremented i in the while loop one step ahead. so we decrement it to process it the next iteration
                i--;
                size *= (Integer.parseInt(builder.toString()));
            }
            i++;
        }

        // we pop pairs one by one and check if the value of B % pair.size equals zero which means
        // we found that character.
        while (!st.isEmpty()) {
            Pair p = st.pop();
            B = (int) (B % p.size);
            if (B == 0) {
                return "" + p.ch;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        KthCharacterInDoubleDecryptedString k = new KthCharacterInDoubleDecryptedString();
        String s1 = "ab2c3";
        int b1 = 8;
        String s2 = "x2y3";
        int b2 = 3;
        String s3 = "xd32pz67cd93lz72yp20";
        int b3 = 67;
        System.out.println(k.solve(s1, b1));
        System.out.println(k.solve(s2, b2));
    }
}

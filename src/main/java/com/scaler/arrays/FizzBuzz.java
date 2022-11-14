package com.scaler.arrays;

import java.util.Arrays;

/**
 * Given a positive integer A, return an array of strings with all the integers from 1 to N. <br/>
 * But for multiples of 3 the array should have “Fizz” instead of the number. <br/>
 * For the multiples of 5, the array should have “Buzz” instead of the number. <br/>
 * For numbers which are multiple of 3 and 5 both, the array should have "FizzBuzz" instead of the number.
 * <p>
 * 1 <= A <= 500000
 *
 * @author sudhir on 04-Apr-2020
 */
public class FizzBuzz {
    public String[] fizzBuzz(int A) {
        String[] result = new String[A];
        result[0] = "1";
        for (int i = 2; i <= A; i++) {
            result[i - 1] = "";
            boolean set = false;
            if (i % 3 == 0) {
                result[i - 1] = "Fizz";
                set = true;
            }
            if (i % 5 == 0) {
                result[i - 1] = result[i - 1] + "Buzz";
                set = true;
            }
            if (!set) {
                result[i - 1] = String.valueOf(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FizzBuzz f = new FizzBuzz();
        int A = 5;
        System.out.println(Arrays.toString(f.fizzBuzz(A)));
    }
}

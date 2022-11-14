package com.scaler.maths;

/**
 * Given an integer A, find and return the A'th magic number. <br/>
 * A magic number is defined as a number which can be expressed as a power of 5 or sum of unique powers of 5. <br/>
 * First few magic numbers are 5, 25, 30(5 + 25), 125, 130(125 + 5), ….
 *
 * @author sudhir on 04-Apr-2020
 */
public class FindNthMagicNumber {
    public int solve(int A) {
        //1st number = 01(binary) = 5
        //2nd number = 10 = 5^2
        //3rd number = 11 = 5^2+5
        //4th number = 100 = 5^3 and so on
        int ans = 0;
        int base = 1;
        while (A > 0) {
            base *= 5;
            if (A % 2 == 1) {
                ans += base;
            }
            A = A / 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        FindNthMagicNumber f = new FindNthMagicNumber();
        int a1 = 3;
        int a2 = 10;
        System.out.println(f.solve(a1));
        System.out.println(f.solve(a2));
    }
}

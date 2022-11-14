package com.scaler.maths;

/**
 * You are given a number A and a number B. <br/>
 * Greatest Common Divisor (GCD) of all numbers between A and B inclusive is taken (GCD(A, A+1, A+2 ... B)). <br/>
 * As this problem looks a bit easy, it is given that numbers A and B can be in the range of 10^100. <br/>
 * You have to return the value of GCD found. <br/>
 * Greatest common divisor of 2 numbers A and B is the largest number D that divides both A and B perfectly.<br/>
 * Return a string which contains the digits of the integer which represents the GCD. The returned string should not have any leading zeroes.
 *
 * @author sudhir on 04-Apr-2020
 */
public class EnumeratingGCD {
    public String solve(String A, String B) {
        if (A.equals(B)) {
            return A;
        }
        return "1";
    }

    public static void main(String[] args) {
        EnumeratingGCD e = new EnumeratingGCD();
        String A = "1", B = "3";
        System.out.println(e.solve(A, B));

    }
}

package com.scaler.maths;

/**
 * The Guardians of the Galaxy have just landed on Earth. <br/>
 * There are N cities in a row numbered from 1 to N. The cost of going from a city with number i to a city with number j
 * is the remainder when (i+j) is divided by (n+1). <br/>
 * The Guardians want to visit every city but in the minimum cost. <br/>
 * What is the minimum cost that will be incurred?<br/>
 * 1 <= N <= 10000000
 *
 * @author sudhir on 24-May-2020
 */
public class ToAndFro {
    public int solve(int A) {
        // We go from 1 to A with cost 0 as (1 + A) % (A + 1) = 0
        // We then go from A to 2 with cost 1 as (2 + A) % (A + 1) = 1
        // We then go from 2 to A-1 with cost 0 as (2 + A - 1) % (A + 1) = 0 and so on
        // If A = 5, then we get 1 + 1 = 2 as total cost
        // If A = 4, then we get 1 as total cost.
        // Hence in general the total cost is ((A - 1) / 2) or simply ((A - 1) >> 1).
        return (A - 1) >> 1;
    }

    public static void main(String[] args) {
        ToAndFro t = new ToAndFro();
        int num1 = 3;
        int num2 = 6;
        System.out.println(t.solve(num1));
        System.out.println(t.solve(num2));
    }
}

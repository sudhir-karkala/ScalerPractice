package com.scaler.greedy;

import java.util.ArrayList;

/**
 * The monetary system in DarkLand is really simple and systematic. The locals only use coins.
 * The coins come in different values. The values used are: 1, 5, 25, 125, 625, 3125, 15625, ...<br/>
 * Formally, for each K >= 0 there are coins worth 5^K.<br/>
 * Given an integer A denoting the cost of an item,
 * find and return the smallest number of coins necessary to pay exactly the cost of the item
 * (assuming you have a sufficient supply of coins of each of the types you will need).<br/>
 * 1 <= A <= 2*10^9
 *
 * @author sudhir on 3/6/20
 */
public class AnotherCoinProblem {
    public int solve(int A) {
        int count = 0;
        while (A > 0) {
            count += A % 5;
            A = A / 5;
        }
        return count;
    }

    public int solve2(int A) {
        int count = 0;
        ArrayList<Integer> change = new ArrayList<>();
        int num = 1;
        // find all denominations upto maximum possible.
        while (num <= A) {
            change.add(num);
            num *= 5;
        }
        // start from the highest denomination and count number of coins of that denomination
        int i = change.size() - 1;
        while (A > 0) {
            count += A / change.get(i);
            A = A % change.get(i);
            i--;
        }
        return count;
    }

    public static void main(String[] args) {
        AnotherCoinProblem anotherCoinProblem = new AnotherCoinProblem();
        int num1 = 47;
        int num2 = 139;
        System.out.println(anotherCoinProblem.solve(num1));
        System.out.println(anotherCoinProblem.solve2(num1));
        System.out.println(anotherCoinProblem.solve(num2));
        System.out.println(anotherCoinProblem.solve2(num2));
    }
}

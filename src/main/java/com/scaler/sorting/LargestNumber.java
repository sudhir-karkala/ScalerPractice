package com.scaler.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a array A of non negative integers, arrange them such that they form the largest number. <br/>
 * Note: The result may be very large, so you need to return a string instead of an integer.
 *
 * @author sudhir on 10-Apr-2020
 */
public class LargestNumber {
    public String largestNumber(final List<Integer> A) {
        Collections.sort(A, (o1, o2) -> {
            String first = o1.toString() + o2.toString();
            String second = o2.toString() + o1.toString();
            return second.compareTo(first);
        });
        if (A.get(0) == 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        for (Integer num : A) {
            result.append(num);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        LargestNumber l = new LargestNumber();
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(30);
        list.add(34);
        list.add(5);
        list.add(9);
        System.out.println(l.largestNumber(list));
    }
}

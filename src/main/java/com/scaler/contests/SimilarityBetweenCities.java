package com.scaler.contests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 2-D array A of size N x 2 denoting the list of ( city, visitor ) pairs that represents visitors visiting cities. <br/>
 * Come up with a program that identifies the pair of two different cities with the greatest similarity.<br/>
 * The similarity metric between two different cities x, y is defined as:<br/>
 * Let a be the count of common visitors among x, y.<br/>
 * Let b be the total of number of distinct visitors among x,y i.e union of visitors of city x and city y.<br/>
 * Then Similarity(x, y) = a / b where ('/' implies division operator).<br/>
 * NOTE:<br/>
 * If suppose two pair of cities (a, b) and (c, d) has same similarity then more priority will be given to the pair
 * which is lexicographically smaller one.<br/>
 * A pair (a, b) is lexicographically smaller than pair (c, d) if a < c or if a == c then b < d <br/>
 * Constraints:<br/>
 * 2 <= N <= 10000<br/>
 * 1 <= Number of Distinct Cities <= 1000<br/>
 * 1 <= Total Number of visitors <= 10<br/>
 * Max Number of visitors to any city is at-most 10.<br/>
 * Each ( city, visitor ) pair occurs at-most one times in the input.<br/>
 * It is assumed that answer always exists.
 *
 * @author sudhir on 13-May-2020
 */
public class SimilarityBetweenCities {
    public ArrayList<Integer> solve(ArrayList<ArrayList<Integer>> A) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        // map of <city, list of visitors>
        for (ArrayList<Integer> list : A) {
            map.computeIfAbsent(list.get(0), z -> new ArrayList<>()).add(list.get(1));
        }
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> cities = new ArrayList<>(map.keySet());
        Collections.sort(cities);
        // initially add first 2 cities to the result
        result.add(cities.get(0));
        result.add(cities.get(1));
        int noOfCities = cities.size();
        double greatestSimilar = Double.MIN_VALUE;
        for (int i = 0; i < noOfCities; i++) {
            for (int j = i + 1; j < noOfCities; j++) {
                // city x
                int x = cities.get(i);
                // city y
                int y = cities.get(j);
                ArrayList<Integer> xVisitors = map.get(x);
                int xSize = xVisitors.size();
                ArrayList<Integer> yVisitors = map.get(y);
                int ySize = yVisitors.size();
                // calculate the number of common visitors to city x and city y
                int common = 0;
                for (int k = 0; k < xSize; k++) {
                    if (yVisitors.contains(xVisitors.get(k))) {
                        common++;
                    }
                }
                // calculate the number of distinct visitors among x and y
                int distinct = xSize + ySize - common;
                // update greatestSimilar
                if (greatestSimilar < (double) (common * 1.0 / distinct)) {
                    greatestSimilar = (double) (common * 1.0 / distinct);
                    result.clear();
                    result.add(x);
                    result.add(y);
                }
            }
        }
        return result;
    }
}

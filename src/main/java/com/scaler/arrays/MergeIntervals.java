package com.scaler.arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary). <br/>
 * You may assume that the intervals were initially sorted according to their start times.<br/>
 * Example 1: Given intervals [1,3],[6,9] insert and merge [2,5] would result in [1,5],[6,9]. <br/>
 * Example 2: Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] would result in [1,2],[3,10],[12,16].<br/>
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10]. Make sure the returned intervals are also sorted.
 *
 * @author sudhir on 04-Apr-2020
 */
public class MergeIntervals {
    static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        public String toString() {
            return "[" + start + " , " + end + "]";
        }
    }

    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<>();
        for (Interval interval : intervals) {
            if (newInterval.end < interval.start) {
                result.add(newInterval);
                newInterval = interval;
            } else if (interval.end < newInterval.start) {
                result.add(interval);
            } else {
                Interval insertInterval = new Interval();
                insertInterval.start = Math.min(newInterval.start, interval.start);
                insertInterval.end = Math.max(newInterval.end, interval.end);
                newInterval = insertInterval;
            }
        }
        result.add(newInterval);
        return result;
    }

    public static void main(String[] args) {
        MergeIntervals m = new MergeIntervals();
        Interval i1 = new Interval(1, 2);
        Interval i2 = new Interval(3, 5);
        Interval i3 = new Interval(6, 7);
        Interval i4 = new Interval(8, 10);
        Interval i5 = new Interval(12, 16);
        Interval insert = new Interval(4, 9);
        ArrayList<Interval> intervals = new ArrayList<>(Arrays.asList(i1, i2, i3, i4, i5));
        ArrayList<Interval> result = m.insert(intervals, insert);
        System.out.println(result);
    }
}

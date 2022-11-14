package com.scaler.tries;

/**
 * Given two arrays of strings A of size N and B of size M.
 * <p>
 * Return a binary string C where C[i] = '1' if B[i] can be found in dictionary A
 * using exactly one modification in B[i], Else C[i] = '0'.
 * <p>
 * NOTE: modification is defined as converting a character into another character.
 * <p>
 * Problem Constraints
 * <p>
 * 1 <= N <= 30000
 * <p>
 * 1 <= M <= 2500
 * <p>
 * 1 <= length of any string either in A or B <= 20
 * <p>
 * strings contains only lowercase alphabets
 * <p>
 * Input Format
 * <p>
 * First argument is the string arrray A.
 * <p>
 * Second argument is the string array B.
 * <p>
 * Output Format
 * <p>
 * Return a binary string C where C[i] = '1' if B[i] can be found in dictionary A
 * using one modification in B[i], Else C[i] = '0'.
 * <p>
 * Example Input
 * <p>
 * Input 1:
 * <p>
 * A = [data, circle, cricket]
 * <p>
 * B = [date, circel, crikket, data, circl]
 * <p>
 * Input 2:
 * <p>
 * A = [hello, world]
 * <p>
 * B = [hella, pello, pella]
 * <p>
 * Example Output
 * <p>
 * Output 1:
 * <p>
 * "10100"
 * <p>
 * Output 2:
 * <p>
 * "110"
 *
 * @author sudhir on 01-Sep-2020
 */
public class ModifiedSearch {
}

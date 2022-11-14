package com.scaler.maths;

/**
 * There are N players each with strength A[i]. when player i attack player j, player j strength reduces to max(0, A[j]-A[i]). <br/>
 * When a player's strength reaches zero, it loses the game and the game continues in the same manner among
 * other players until only 1 survivor remains.<br/>
 * Can you tell the minimum health last surviving person can have? <br/>
 *
 * @author sudhir on 04-Apr-2020
 */
public class Pubg {
    private int findgcd(int a, int b) {
        int m = Math.max(a, b);
        int n = Math.min(a, b);
        while (n != 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        return m;
    }

    public int solve(int[] A) {
        int gcd = 0;
        for (int i = 0; i < A.length; i++) {
            gcd = findgcd(gcd, A[i]);
        }
        return gcd;
    }

    public static void main(String[] args) {
        Pubg p = new Pubg();
        int[] a1 = {6, 4};
        int[] a2 = {2, 3, 4};
        System.out.println(p.solve(a1));
        System.out.println(p.solve(a2));
    }
}

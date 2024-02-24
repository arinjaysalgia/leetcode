package com.company;

import java.util.*;
/*
 *  This is the LeetCode Problem:
 * https://leetcode.com/problems/n-th-tribonacci-number/editorial/
 *
 * Where you are expected to find the Tribonacci number.
 *
 *
 * Logic:
 *   This is a classic dp problem where the same problem can be solved with
 *   Overlapping subproblems and Optimal substructures.
 *   Base Condition : dp(0) = 0, dp(1) = 1, dp(2) = 1.
 *   Recurrence Relationship : Cost at any step  i is given by :
 *       dp[i] = dp[i -1] + dp[i - 2] dp[i - 3]
 * Space Complexity:
 *   The space Complexity is O(n) for all 3 solution
 *
 * Time Complexity:
 *   The time complexity is also O(n) for TopDowm and BottomUp solution.
 *   O(1) for betterBottomUp solution.
 *
 * */

public class TribonacciNumber {

    Map<Integer, Integer> memo = new HashMap<>();
    public int topDown(int n){

        if(n <= 0)
            return 0;
        if(n == 1 || n == 2)
            return 1;

        if(memo.containsKey(n))
            return memo.get(n);

        int tn = bottomUp(n -1) + bottomUp(n - 2) + bottomUp(n -3);
        memo.put(n ,tn);

        return memo.get(n);
    }

    public int bottomUp(int n){
        int[] dp = new int[n + 1];

        //base conditions
        if(n == 0)
          return 0;
        if(n == 1 || n == 2)
          return 1;

        dp[0] = 0;
        dp[1]  = 1;
        dp[2] = 1;

        for(int i = 3; i <= n; i++){
          //recurrence relations
          dp[i] = dp[i - 1] + dp[i - 2] + dp[i -3];
        }

        return dp[n];
    }

    public int betterBottomUp(int n){

        if(n <= 0)
            return 0;
        if(n == 1 || n == 2)
            return 1;

        int a1 = 0;
        int a2 = 1;
        int a3 = 1;

        for(int i = 2; i < n; i++ ){
            int temp = a1 + a2 + a3;
            a1 = a2;
            a2 = a3;
            a3 = temp;
        }

        return a3;
    }

    public static void testTribonacciNumber(){
        TribonacciNumber tn = new TribonacciNumber();
        int n = 10;
        System.out.println("The Tribonacci number at  " + n + " from top Down approach is : " + tn.topDown(n));
        System.out.println("The Tribonacci number at  " + n + " from bottom up approach is : " + tn.bottomUp(n));
        System.out.println("The Tribonacci number at  " + n + " from better bottom up approach is : " + tn.betterBottomUp(n));
    }

}

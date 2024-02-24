package com.company;
import java.util.*;
/*
        *  This is the LeetCode Problem:
        * https://leetcode.com/problems/min-cost-climbing-stairs/description/
        *
        * Where you are expected to find the minimum cost to climb the stair and get beyond the final index.
        *
 *
        * Logic:
        *   This is a classic dp problem where the same problem can be solved with
        *   Overlapping subproblems and Optimal substructures.
        *   Base Condition : as starting step either from 0 or 1 will both have cost to get to them of 0.
        *   Recurrence Relationship : Cost at any step  i is given by :
        *       dp[i] = Math.min((dp[i -1] + cost[ i - 1]), (dp[i - 2] + cost[i - 2]))
        * Space Complexity:
        *   The space Complexity is O(n) for both solution
        *
        * Time Complexity:
        *   The time complexity is also O(n) for both solution as well.
        *
        * */

public class MinCostClimbingStairs {

    public int bottomUpSolution(int[] cost){
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for(int i = 2; i <=n; i++){
            dp[i] = Math.min((dp[i -1] + cost[ i - 1]), (dp[i - 2] + cost[i - 2]));
        }
        return dp[n];
    }
    Map<Integer, Integer> memo = new HashMap<>();

    public int topDownSolution(int[] cost){

        return topDownHelper(cost, cost.length);
    }
    public int topDownHelper(int[] cost , int position){

        //base condition
        if(position <= 1){
            return 0;
        }

        if(memo.containsKey(position))
            return memo.get(position);

        int postionCostFromOneLess = cost[position - 1] + topDownHelper(cost, position - 1);
        int postionCostFromTwoLess = cost[position - 2] + topDownHelper(cost, position - 2);
        memo.put(position, Math.min(postionCostFromOneLess, postionCostFromTwoLess));
        return memo.get(position);
    }


    public static void testMinCostClimbingStairs(){
        int[] cost = new int[] {1,100,1,1,1,100,1,1,100,1};
        MinCostClimbingStairs mcs = new MinCostClimbingStairs();
        System.out.println("Minimum cost to climb stairs from Bottom Up is : " + mcs.bottomUpSolution(cost));
        System.out.println("Minimum cost to climb stairs from Top Down is : " + mcs.topDownSolution(cost));

    }
}

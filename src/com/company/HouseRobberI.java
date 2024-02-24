package com.company;

import java.util.*;
/*
 *  This is the LeetCode Problem:
 * https://leetcode.com/problems/house-robber/description/
 *
 * Where you are expected to find the maximum amount of money you can rob tonight without alerting the police
 *
 *
 * Logic:
 *   This is a classic dp problem where the same problem can be solved with
 *   Overlapping subproblems and Optimal substructures.
 *   Base Condition : dp(0) = nums(0), dp(1) Maximum of (nums at(0),nums(1))
 *   Recurrence Relationship : Cost at any step  i is given by :
 *       dp[i] = Math.max(dp[i -1] , dp{i -2] + nums[i])
 * Space Complexity:
 *   The space Complexity is O(n) for all 3 solution
 *
 * Time Complexity:
 *   The time complexity is also O(n) for TopDowm and BottomUp solution.
 *   O(1) for betterBottomUp solution.
 *
 * */

public class HouseRobberI {

    HashMap<Integer,Integer> memo = new HashMap<>();

    public int topDownRob(int[] nums, int i){
        if (i < 0)
            return 0;
        if(i == 0)
            return nums[0];
        if(i == 1)
            return Math.max(nums[0], nums[1]);

        if(memo.containsKey(i))
            return memo.get(i);

        int rob = Math.max(topDownRob(nums, i - 1), topDownRob(nums, i - 2) + nums[i]);
        memo.put(i, rob);

        return memo.get(i);
    }

    public int bottomUpRob(int[] nums) {

        int n = nums.length;
        if(n == 1)
            return nums[0];
        int[] dp = new int [n + 1];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);

        for (int i = 2; i < nums.length; i++){
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length -1];
    }
}

package com.company;
import java.util.*;
/*
 *  This is the LeetCode Problem:
 *https://leetcode.com/explore/learn/card/dynamic-programming/631/strategy-for-solving-dp-problems/4147/
 *
 * Where you are expected to Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
    Return the maximum number of points you can earn by applying the above operation some number of times.
 *
 *
 * Logic:
 *   This is a classic dp problem where the same problem can be solved with
 *   Overlapping subproblems and Optimal substructures.
 *   Something to be aware of here is we only need the sum of total occurrence of each number to
 *   decide if we should pick that number, its postion is irrelevant.
 *    We use a hashMap to store the sum of occurrence of each number.
 *   Base Condition : dp(0) = 0, dp(1) = Map.getOrDefault(1, 0)
 *    Recurrence Relationship : Cost at any step  i is given by :
 *       dp[i] = dp[i -1] + dp[i - 2]  + map.getOrDefault(i, 0).
 *
 * Time complexity: O(N+k)
     * To populate points, we need to iterate through nums once, which costs O(N) time.
     * Then, we call maxPoints(maxNumber). This call will repeatedly call maxPoints until we get
     * down to our base cases. Because of cache, already solved sub-problems will only cost O(1) time.
     * Since maxNumber = k, we will solve k unique sub-problems so,
     * this recursion will cost O(k) time. Our final time complexity is O(N + k).
 *
 * Space complexity: O(N + k) for TopDown and BottomUp
    * The extra space we use is the hash table points,
    * the recursion call stack needed to find maxPoints(maxNumber), and the hash table cache.
    * The size of points is equal to the number of unique elements in nums. In the worst case,
    * where every element in nums is unique, this will take O(N) space.
    * The recursion call stack will also grow up to size k, since we start our recursion at maxNumber,
    * and we don't start returning values until our base cases at 0 and 1.
    * Lastly, cache will store the answer for all states, from 2 to maxNumber,
    * which means it also grows up to k size. Our final space complexity is O(N+2⋅k)= O(N+k).
*
* Space complexity : For optimizedBottomUP is O(N)
* Here the only thing that has changes is we are using constant space for computation and,
* The space required is only for the HashMap to store N elemets.
  */

public class DeleteAndEarn {

    Map<Integer, Integer> no = new HashMap<>();
    Map<Integer, Integer> memo = new HashMap<>();

    public int solutionDeleteAndEarn(int[] nums) {
        int maxNumber = Integer.MIN_VALUE;
        for(int num : nums){
            no.put(num, no.getOrDefault(num, 0) + num);
            maxNumber = Math.max(maxNumber, num);
        }
        System.out.println("The maximum points that can be earned from given array from topDown approach is : " + topDown(maxNumber));
        System.out.println("The maximum points that can be earned from given array from bottomUP approach is : " + bottomUp(nums, maxNumber));
        System.out.println("The maximum points that can be earned from given array from optimized BottomUP approach is : " + optimizedBottomUP(nums, maxNumber));
        return bottomUp(nums, maxNumber);

    }
    public int topDown(int number){
        if(number == 0)
        return 0;
        if(number == 1)
            return no.getOrDefault(1, 0);

        if(memo.containsKey(number))
            return memo.get(number);

        int maxPoints = Math.max(topDown(number - 1) , topDown(number - 2) + no.getOrDefault(number, 0));
        memo.put(number, maxPoints);

        return maxPoints;
    }

    public int bottomUp(int[] nums, int maxNumber){
        int[] dp = new int[maxNumber + 1];
        dp[0] = 0;
        dp[1] = no.getOrDefault(1, 0);

        for(int i = 2; i < dp.length; i++){
            dp[i] = Math.max(dp[i - 1] , dp[i - 2] + no.getOrDefault(i, 0));
        }
        return dp[maxNumber];
    }
    public int optimizedBottomUP(int[] nums, int maxNumber){

        // Base cases
        int twoBack = 0;
        int oneBack = no.getOrDefault(1, 0);

        for (int num = 2; num <= maxNumber; num++) {
            int temp = oneBack;
            oneBack = Math.max(oneBack, twoBack + no.getOrDefault(num, 0));
            twoBack = temp;
        }

        return oneBack;
    }
    public static void testDeleteAndEarn(){
        int[] nums = new int[]{2,2,3,3,3,4};
        DeleteAndEarn dae = new DeleteAndEarn();
        dae.solutionDeleteAndEarn(nums);
    }
}

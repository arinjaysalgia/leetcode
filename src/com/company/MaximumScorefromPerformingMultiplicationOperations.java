package com.company;

import java.sql.SQLOutput;

public class MaximumScorefromPerformingMultiplicationOperations {
    private int length;
    private int[][] memo;

    public int maximumScore(int[] nums, int[] multipliers) {
        length = multipliers.length;
        memo = new int[length][length];

        return helperDp(0,0,nums,multipliers);


    }
    private int helperDp(int i, int left, int[] nums, int[] multipliers){
        if(i == length)
            return 0;

        if(memo[i][left] != 0){
            return memo[i][left];
        }
        int right = nums.length - 1 -(i - left);
        memo[i][left] = Math.max(multipliers[i] * nums[left] + helperDp(i + 1, left + 1, nums, multipliers),
                                 multipliers[i] * nums[right] + helperDp(i + 1 , left, nums, multipliers));

        return memo[i][left];
    }
    public static void testMaximumScorefromPerformingMultiplicationOperations(){

        int[] nums = new int[]{-5,-3,-3,-2,7,1};
        int[] multipliers = new int[] {-10,-5,3,4,6};
        MaximumScorefromPerformingMultiplicationOperations msfpmo= new MaximumScorefromPerformingMultiplicationOperations();
        System.out.println("Maximum Score from Performing Multiplication Operations is : " + msfpmo.maximumScore(nums,multipliers));

    }

}

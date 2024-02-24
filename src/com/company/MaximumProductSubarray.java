package com.company;
/*
This is a problem of finding Maximum product of a subarray in a given array look at the following link for more information:
https://leetcode.com/problems/maximum-product-subarray/

Logic here : We keep track of 3 product the tempSoFar, MaxSoFar and MinSoFar reason being that because the numbers are
integers, the product of even negative integers with one positive integer could change the entire product to be max.
The reason for having tempSoFar is so that we can compute the minSoFar and maxSoFar with the current num variable.
Also, we compute nums[i] * minSoFar and num[i] * maxSoFar and check the Math.max and Math.min for maxSoFar and minSoFar respectively.
* */

public class MaximumProductSubarray {

    public int maxProduct(int[] nums) throws Exception{
        int result = nums[0];
        int minSoFar = nums[0];
        int maxSoFar = nums[0];
        try {
            for (int i = 1; i < nums.length; i++) {
                if(Integer.MAX_VALUE / 10 < maxSoFar)
                    throw new ArithmeticException("Product expected to be out of range");
                int tempSoFar = Math.max(nums[i], Math.max(nums[i] * maxSoFar, nums[i] * minSoFar));
                minSoFar = Math.min(nums[i], Math.min(nums[i] * minSoFar, nums[i] * maxSoFar));
                maxSoFar = tempSoFar;
                result = Math.max(maxSoFar, result);
            }
        } catch (ArithmeticException e) {
            throw new ArithmeticException(e.getMessage());
        }
        return result;
    }

    public static void testMaximumProductSubarray() {
        int[] nums = { Integer.MAX_VALUE/5, 1, 2, 2, 3, -2, 4,};
        MaximumProductSubarray mps = new MaximumProductSubarray();
        try{
            var res = mps.maxProduct(nums);
            System.out.println("The maximum product of the subarray is : " + res);
            //System.out.println("The Max Value of Integer is : " + Integer.MAX_VALUE);
        }
        catch(Exception e){
            System.out.println(e);
        }

    }
}

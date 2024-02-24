package com.company;
/*
* This is a variation of this leetcode problem:
*          https://leetcode.com/problems/find-the-duplicate-number/
*
* The problem here is that we are given a range of number with strictly increasing order and the range can start from any natural number
* but there is one number in this range that is repeated  find the repeated number
*
*
*/
public class DuplicateRepeatingNumber {

    public int getDuplicateRepeatingNumber(int[] nums){
        int offset = nums[0] - 0;
        for(int i = 0; i <nums.length; i++){
            if(nums[i] - offset != i){
               return nums[i];
            }
        }
        return -1;
    }

    public static void testDuplicateRepeatingNumber(){

        int[] nums1 = {4,5,6,6,8,9};
        int[] nums2 = {4,5,6,7,8,9};
        DuplicateRepeatingNumber drn = new DuplicateRepeatingNumber();
        System.out.println("The Duplicate Repeating Number is : " + drn.getDuplicateRepeatingNumber(nums1));
        System.out.println("The Duplicate Repeating Number is : " + drn.getDuplicateRepeatingNumber(nums2));
    }
}

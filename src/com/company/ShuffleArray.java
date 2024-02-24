package com.company;
import java.util.Random;
/*
* This is a problem where you are given an array and have to shuffle it so that the output array is each position is
* equiprobable. That is all number are equally likely for all the position.
* This is also called Fischer Yates Algorithm
* Logic:
*   The logic here is to generate a random number less than the current position and swap the current number with that position
*   Then decrement the counter and keep on going that way each number has all position as equally likely.
*/
public class ShuffleArray {

    public int[] shuffledArray(int[] nums){
        System.out.println("Array Before shuffling : ");
        for(int num : nums){
            System.out.println(num);
        }
        Random rand = new Random();
        for(int i = nums.length -1; i > 0; i--){
            int swapPos = rand.nextInt(i);
            int temp = nums[swapPos];
            nums[swapPos] = nums[i];
            nums[i] = temp;
        }
        System.out.println("Array After shuffling : ");
        for(int num : nums){
            System.out.println(num);
        }
        return nums;
    }
    public static void testShuffledArray(){
        int[] nums = new int[]{1,2,3,4};
        ShuffleArray sa = new ShuffleArray();
        sa.shuffledArray(nums);
    }
}

package com.company;
import java.util.*;
public class maxSubArray {

    public static int maxSubArrayLength(List<Integer> badges){
        int positive_length = 0;
        int negative_length = 0;
        int maxL = 0;
        for(int i=0;i< badges.size();i++){

            if(badges.get(i) < 0){
                int t = negative_length;
                negative_length = positive_length;
                positive_length = t;
            }

            positive_length = (badges.get(i) <0 && positive_length== 0)? 0 : ++positive_length ;
            negative_length = (badges.get(i)> 0 && negative_length==0)? 0 : ++negative_length;
            maxL = Math.max(maxL , positive_length);
        }
        return maxL;
    }
    public static void testMaxSubArrayLength(){
        List<Integer> badges = new LinkedList<>();
        badges.add(-1);
        badges.add(-1);
        badges.add(-1);
        badges.add(1);
        badges.add(1);
        badges.add(-1);
        System.out.println(maxSubArray.maxSubArrayLength(badges));
    }

}

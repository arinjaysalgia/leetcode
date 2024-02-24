package com.company;
import java.util.*;
public class Test {

    public static long getMaxUsers(long budget, List<List<Long>> advertisementTypes){
        Queue<List<Long>> pqA = new PriorityQueue<>((a, b) -> (int) (a.get(1) - b.get(1)));

        for(List<Long>advertisementType : advertisementTypes){
            pqA.offer(advertisementType);
        }
        long resA = 0;
        long budgetA = budget;
        while(!pqA.isEmpty()){
            List<Long> temp = pqA.poll();
            if(budgetA >= temp.get(0)){
                resA += temp.get(1);
            }
            budgetA = budgetA - temp.get(0);
        }

        Queue<List<Long>> pqB = new PriorityQueue<>((a, b) -> (int) (b.get(1) - a.get(1)));
        for(List<Long>advertisementType : advertisementTypes){
            pqB.offer(advertisementType);
        }
        long resB = 0;
        long budgetB = budget;
        while(!pqB.isEmpty()){
            List<Long> temp = pqB.poll();
            if(budgetB >= temp.get(0)){
                resB += temp.get(1);
            }
            budgetB =budgetB - temp.get(0);
        }
        return resA > resB ? resA : resB;
    }
    public static void testTest(){
        List<List<Long>> advertisementTypes = new LinkedList<>();
        long[][] temps = new long[][]{{1000,3000}, {500,2000}, {300, 1001}, {200, 1000}, {100, 800},{50,200}};
        for(long[] temp : temps){
            List<Long> at = new LinkedList<>();
            for(long t : temp){
                at.add(t);
            }
            advertisementTypes.add(at);
        }

        //System.out.println(advertisementTypes);
        long budget = 1500;
        System.out.println(Test.getMaxUsers(budget, advertisementTypes));
    }

    //public static List<Integer> categoryAndSubcategory(int)
}

package com.company;
import java.util.*;

/*
 students [0, ..., n-1] participate in a marathon. You are given an int array standings where standings[i] = j means,
  that student j finished just before student i. standings[k] = -1 means that k is the first student.
 There are no ties. List out the students in the order in which they finished the marathon.

 Logic:
 This is a classic problem of topological sort.
 Here we create a dependency map between the positionAfter as key and all the position that are dependent on it in a list.
 Then get the position of -1  and dump it in the Queue and then iterate over all the position that are dependent on it.
 Following are the edge cases you would check:
 If the list is empty from the map then continue;
 If the map does not contain's key then don't query the map.
 */
public class Marathon {

    public int[] solution(int[] inp){
        int[] res = new int[inp.length];
        // int[] input = new int[]{-1, 2, 0};
        Queue<List<Integer>> q = new LinkedList<>();
        Map<Integer, List<Integer>> dependencyMap= new HashMap<>();
        for(int i = 0; i < inp.length; i++){
            int positionAt = i;
            int positionAfter = inp[i];
            dependencyMap.putIfAbsent(positionAfter, new LinkedList<>());
            dependencyMap.get(positionAfter).add(positionAt);
        }
        q.add(dependencyMap.get(-1));
        int index = 0;
        List<Integer> temp = null;
        while(!q.isEmpty()){
            temp = q.poll();
            if(temp == null)
                continue;
            for(int t1 : temp){
                res[index] = t1;
                index++;
               if(dependencyMap.containsKey(t1))
                     q.add(dependencyMap.get(t1));
            }
        }

        return res;
    }




    public static void testMarathon(){

        Marathon m = new Marathon();
        int[] input = new int[]{2, 2, -1};

        int[] res = m.solution(input);
        //m.reorder(input);
        for(int i : res){
            System.out.println(i);
        }

    }
}

package com.company;
import java.util.*;
import java.util.stream.Collectors;

/*
* You are given input balls in different color and  each color correspond to weight
* Green :1, Yellow :2, Blue :3, Red: 4
*
* Return the kth highest weight combinations of balls that have of all the balls combined in any order
    K = 6
*  eg : input [
*           [R, B, Y, G], = 10
*           [R, B, Y], = 9
*           [R, B, G], = 8
*           [R, Y, G], = 7
*           [R, B] = 7
*           [B, Y, G] = 6
*Logic:
*  Change the input array from character to number
* * */



public class GoogleInterviewQuestion {

    public List<List<Character>> optimalKMaxColourSum(char[] colors ,int k) {
        if(colors.length == 0)
            return new ArrayList<>();

        List<List<Character>> res = new LinkedList<>();
        Map<Character, Integer> colorMap = new HashMap<>();
        colorMap.put('R', 4);
        colorMap.put('B', 3);
        colorMap.put('Y', 2);
        colorMap.put('G', 1);
        int MaxSum = 0;
        Set<List<Character>> colorValues = new HashSet<>();
        List<Character> currentColors = new LinkedList<>();
        List<List<Character>> listCurrentColors = new LinkedList<>();
        Map<Integer, List<List<Character>>> weightedValues = new HashMap<>();
        Queue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        for(int i = 0; i < colors.length; i++){
            MaxSum += colorMap.get(colors[i]);
            currentColors.add(colors[i]);
        }
        listCurrentColors.add(currentColors);
        weightedValues.put(MaxSum, listCurrentColors);
        pq.offer(MaxSum);
        while(!pq.isEmpty()){
            int sum = pq.poll();
            listCurrentColors = weightedValues.get(sum);
            for(int i = 0; i < listCurrentColors.size(); i++){
               currentColors = listCurrentColors.get(i);
               res.add(currentColors);
               if(res.size() == k)
                   break;
               for(int j = currentColors.size() - 1; j >=0; j--){
                   List<Character> tempChar = new LinkedList<>(currentColors);
                   char c = tempChar.get(j);
                   int tempSum = sum - colorMap.get(c);
                   tempChar.remove(j);
                   if(!colorValues.contains(tempChar)){
                       List<List<Character>> allChars = null;
                       if(weightedValues.containsKey(tempSum)){
                           allChars = weightedValues.get(tempSum);
                       }
                       else{
                           allChars = new LinkedList<>();
                           pq.offer(tempSum);
                       }
                       if(!allChars.contains(tempChar)){
                           allChars.add(tempChar);
                           weightedValues.put(tempSum, allChars);
                           colorValues.add(tempChar);
                       }
                   }
               }
           }
           if(res.size() >= k)
               break;
       }
       for(List<Character> r:  res){
           System.out.println(r);
       }
        return res;
    }



    public List<List<Character>> bruteForceKMaxColourSum(char[] colors ,int k) {
        //Transform colour to numbers
        if( colors.length == 0)
            return new ArrayList<>();
        List<List<Character>> fres = new LinkedList<>();
        Map<Character, Integer> colorMap = new HashMap<>();
        colorMap.put('R', 4);
        colorMap.put('B', 3);
        colorMap.put('Y', 2);
        colorMap.put('G', 1);

        Map<Integer, Character> colorMapToInt = new HashMap<>();
        colorMapToInt.put(4, 'R');
        colorMapToInt.put(3, 'B');
        colorMapToInt.put(2, 'Y');
        colorMapToInt.put(1, 'G');
        int[] inp = new int[colors.length];

        for(int i = 0; i < colors.length; i++){
            inp[i] = colorMap.get(colors[i]);
        }
        //Arrays.sort(inp, Collections.reverseOrder());
        Arrays.sort(inp);

        Queue<List<Integer>> pq = new PriorityQueue<>((a, b) -> computeWeight(a) - computeWeight(b));

        for(int i = inp.length; i > 0; i--){
            Set<Integer> sumSet = new HashSet<>();
            helperBackTrack(inp, new LinkedList<>(), i, pq, k, sumSet);
            while(pq.size() > k)
                pq.poll();
        }
        //Transform the result back to inputs
        while(!pq.isEmpty()){
            List<Integer> tempResInt = pq.poll();
            List<Character> tempRes = new LinkedList<>();
            //System.out.println(tempResInt +" sum is : " + computeWeight(tempResInt));
            for(int i = 0; i < tempResInt.size(); i++){
                tempRes.add(colorMapToInt.get(tempResInt.get(i)));
            }
            System.out.println(tempRes +" sum is : " + computeWeight(tempResInt));
            fres.add(tempRes);
        }
        for(List<Character> tres : fres){
            //System.out.println(tres);
        }
        return fres;

    }

    public void helperBackTrack(int[] inp, List<Integer> res, int size, Queue<List<Integer>> pq, int k, Set<Integer> sumSet){
        if(size == res.size() && !sumSet.contains(computeWeight(res))){
            //System.out.println(res);
            sumSet.add(computeWeight(res));
            pq.offer(new LinkedList<>(res));
            return;
        }
        for(int i = 0; i < inp.length; i++){
            if(res.contains(inp[i]))
                continue;
            res.add(inp[i]);
            helperBackTrack(inp, res, size, pq, k, sumSet);
            res.remove(res.size() - 1);
        }
    }
    public static int computeWeight(List<Integer> res){
        int resSum = 0;
        for(int j : res){
            resSum += j;
        }
        return resSum;
    }

    public static void testGoogleInterviewQuestion(){
        int k = 6;
        char[] colors = new char[]{ 'R', 'B', 'Y', 'G'};
        GoogleInterviewQuestion gIQ = new GoogleInterviewQuestion();
        //gIQ.bruteForceKMaxColourSum(colors, k);
        gIQ.optimalKMaxColourSum(colors, k);

    }
}

package com.company;
import java.sql.SQLOutput;
import java.util.*;

public class LearnStructureTest {
    public static void testLearnStructureTest(){
        LearnStructureTest lst = new LearnStructureTest();
        //lst.learnPriorityQueues();
        //System.out.println(lst.patchPothole(".X..X"));
        //System.out.println(lst.patchPothole("X.XXXXX.X"));
       // System.out.println(lst.patchPothole("XX.XXX.."));
      //  System.out.println(lst.patchPothole("XXXX"));
        //lst.printTargetSumCombination(4,38, 6, new ArrayList<>());
        int[] A  = new int[]{-3,-2,-4,-3,5, -8, 9};
        //lst.solution(A,2,4);
        //Set<Integer> testLearn = new HashSet<>();
        //testLearn.add(1);
        //testLearn.add(2);
        //testLearn.contains(1);
        List<String> in = null;
        lst.learnList(in);
        in = new LinkedList<>();
        in.add("test");
        lst.learnList(in);

    }

    public List<List<Integer>> res = new LinkedList<>();

    public int[] solution(int[] A, int F, int M) {

        // write your code in Java SE 8
        int[] resArray;
        int sum = Arrays.stream(A).sum();

        int target = (M * (A.length + F)) - sum;

        //System.out.println(target);

        sumOfDice(F, target, 6, new ArrayList<>());

        //System.out.println(res);
        List<Integer> temp = res.get(0);
        resArray = new int[temp.size()];
        for(int i =0 ; i< temp.size(); i++){
            resArray[i] = temp.get(0);
        }
        for(int i =0 ; i< resArray.length; i++){
            System.out.println(resArray[i]);
        }
        return resArray;

    }



    public void sumOfDice(int die, int target, int sides, List<Integer> result){

        int currentSum = result.stream().mapToInt(Integer::intValue).sum();
        //System.out.println(result);

        if(die < 0 || currentSum > target ||  res.size() > 0){

            return;

        }


        if(die == 0) {

            if (currentSum == target){

                res.add(new ArrayList<>(result));

                //System.out.println(result);

            }

            return;
        }
        else {
                for (int i = 1; i<= sides; i++){
                    // make move
                    result.add(i);
                    // dfs
                    sumOfDice(die - 1, target, sides, result);
                    // backtrack
                    result.remove(result.size() - 1);
                }
        }
    }

    private void printTargetSumCombination(int numDie, int target, int sides, List<Integer> chosen) {
        if (numDie == 0) {
            if(chosen.stream().mapToInt(Integer::intValue).sum()==target)
                System.out.println(chosen);
        } else {
            for (int i = 1; i <= sides; i++) {
                chosen.add(i);
                printTargetSumCombination(numDie - 1, target, sides, chosen);
                chosen.remove(chosen.size() - 1);
            }
        }
    }
    public void learnPriorityQueues(){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(10);
        pq.offer(11);
        pq.offer(9);
        pq.offer(5);
        int i = 0;
        while(!pq.isEmpty()){
            i++;
            System.out.println("Polling time " +  i +" got integer : " + pq.poll());
        }
        //This is max heap
        PriorityQueue<Character> cpq = new PriorityQueue<>((a,b) ->  (int)b - (int)a);

        //This is min heap
        //PriorityQueue<Character> cpq = new PriorityQueue<>((a,b) ->  (int)a - (int)b);
        //The following statement is same as the above statement
        //PriorityQueue<Character> cpq = new PriorityQueue<>();

        cpq.offer('a');
        cpq.offer('d');
        cpq.offer('b');
        cpq.offer('c');
        i = 0;
        while(!cpq.isEmpty()){
            i++;
            System.out.println("Polling time " +  i +" got character : " + cpq.poll());
        }
    }



    public void learnQueues(){
        List<Integer> q = new LinkedList<>();
        q.add(10);
        q.add(11);
        q.add(9);
        q.add(5);
        int j = 0;
        while(!q.isEmpty()){
           int size = q.size();
           int k = size - 1;
           for(int i = 0; i < q.size(); i++){
               System.out.println(q.get(k));
               k--;
           }
        }
        //This is max heap
        PriorityQueue<Character> cpq = new PriorityQueue<>((a,b) ->  (int)b - (int)a);

        //This is min heap
        //PriorityQueue<Character> cpq = new PriorityQueue<>((a,b) ->  (int)a - (int)b);
        //The following statement is same as the above statement
        //PriorityQueue<Character> cpq = new PriorityQueue<>();

        cpq.offer('a');
        cpq.offer('d');
        cpq.offer('b');
        cpq.offer('c');
        int i = 0;
        while(!cpq.isEmpty()){
            i++;
            System.out.println("Polling time " +  i +" got character : " + cpq.poll());
        }
    }
    public void learnList(List<String> in){
        if(in == null || in.size() == 0)
            return;
        for(String i : in)
            System.out.print(i);

    }
}

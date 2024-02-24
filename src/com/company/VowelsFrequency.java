package com.company;
//import jdk.jfr.Frequency;

import java.util.*;
public class VowelsFrequency {

    public String solution(String S) {
        // write your code in Java SE 8
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('o');
        vowels.add('i');
        vowels.add('u');
        Map<Character, Integer> frequencyTable = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            char c = Character.toLowerCase(S.charAt(i));
            if (vowels.contains(c)) {
                frequencyTable.put(c, frequencyTable.getOrDefault(c, 0) + 1);
            }
        }
        //Sort the map
        Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() == b.getValue() ? a.getKey() - b.getKey() : b.getValue() - a.getValue());

        for (Map.Entry<Character, Integer> e : frequencyTable.entrySet()) {
            pq.add(e);
        }
        StringBuilder sb = new StringBuilder();
        int place = pq.size();
        boolean isMore = true;
        while (!pq.isEmpty() && isMore) {
            char c = pq.poll().getKey();
            sb.append(c);
            sb.append(" appears ");
            sb.append(frequencyTable.get(c));
            if(frequencyTable.get(c) > 1){
                sb.append(" times");
            }
            else
                sb.append(" time" );
            if(!pq.isEmpty() && pq.peek().getValue() == frequencyTable.get(c))
            {
                sb.append("\n");
            }
            else
                isMore = false;
        }

        //System.out.println(sb);
        return sb.toString();
    }

/*
Logic here:
The function iterates over the string and creates a frequency table of their occurence. While keeping tab of the max value.
Then removes everything from the map that is less than the max from the map.
If there are more than one keys with max value then dump the map on the heap and order then in 'a' 'e' 'i' 'o' 'u' order.
Use the helper to create the output string.

 */
    public String optimalSolution(String S) {
        // write your code in Java SE 8
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('o');
        vowels.add('i');
        vowels.add('u');
        Map<Character, Integer> frequencyTable = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < S.length(); i++) {
            char c = Character.toLowerCase(S.charAt(i));
            if (vowels.contains(c)) {
                frequencyTable.put(c, frequencyTable.getOrDefault(c, 0) + 1);
                max = Math.max(max, frequencyTable.get(c));
            }
        }
        //System.out.println("Max as of now is : " + max);
        //Get all the keys and remove those keys that have occurrence less than the max value;
        Character[] keys = frequencyTable.keySet().toArray(new Character[0]);
        for(Character key : keys){
            if(max > frequencyTable.get(key)){
                frequencyTable.remove(key);
            }
        }

        boolean isMoreThanOne = frequencyTable.size() == 1 ? false : true;
        //Sort the map
        StringBuilder sb = new StringBuilder();

        if(isMoreThanOne){
            Queue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> a.getKey() - b.getKey() );
            for (Map.Entry<Character, Integer> e : frequencyTable.entrySet()) {
                pq.add(e);
            }
            while(!pq.isEmpty()) {
                int size = pq.size();
                char inp = pq.poll().getKey();
                sb.append(helper(frequencyTable.get(inp), inp));
                if (size > 1)
                    sb.append("\n");
            }
        }
        else{
            for(Map.Entry<Character, Integer> e : frequencyTable.entrySet()){
                sb.append(helper(e.getValue(), e.getKey()));
            }
        }

        return sb.toString();
    }
    public String helper(int count, char c) {
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        sb.append(" appears ");
        sb.append(count);
        if (count > 1)
            sb.append(" times");
        else
            sb.append(" time");
        //System.out.println(sb);
        return sb.toString();
    }

    public static void testVowelsFrequency(){
        String s1 = "SAMPLE";
        String s2 = "this is a sentence";
        String s3 = "The quick brown fox jumps over the lazy dog";
        String s4 = "this is a sentence";
        String s5 = "The Chicago Bears are a professional American football team based in Chicago, Illinois. The Bears compete in the National Football League as a member club of the league's National Football Conference North division.";
        VowelsFrequency vf  = new VowelsFrequency();
        //System.out.println(vf.solution(s1));
        //System.out.println("Length of the output string is : " + vf.solution(s1).length());
        //System.out.println(vf.solution(s2));
        //System.out.println("Length of the output string is : " + vf.solution(s2).length());
        //System.out.println(vf.solution(s3));
        //System.out.println("Length of the output string is : " + vf.solution(s3).length());
        //System.out.println(vf.solution(s4));
        //System.out.println("Length of the output string is : " + vf.solution(s4).length());
        System.out.println(vf.optimalSolution(s5));
        System.out.println("Length of the output string is : " + vf.optimalSolution(s5).length());
    }
}

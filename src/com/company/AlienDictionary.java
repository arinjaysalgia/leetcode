package com.company;
import java.util.*;
/*
        *  This is the LeetCode Problem:
        *   https://leetcode.com/problems/alien-dictionary/description/
        *
        * Where you are expected to find the order of letter with a given words that are lexicographically sorted:
        * lexicographically sorting:
             A string a is lexicographically smaller than a string b if in the first position where a and b differ,
             string a has a letter that appears earlier in the alien language than the corresponding letter in b.
             If the first min(a.length, b.length) characters do not differ, then the shorter string is the lexicographically smaller one.
 *
        * Logic:
        *      Something to be really careful about while building the graph is we can only assert for a 1 character in same position
        *      in 2 words about the dependency in 2 sequential words.
        *      Here we use the Topological Sorting
        *      Build the depedencyList and the inDegrees
        *      Then add the vertices with inDegrees = 0 to the queue and decrement the inDegree,
        *      Repeat until there are none vertex left in the queue
        *  Things to be careful about:
        *      Topological Sorting can only be used in Directed Acyclic Graph
        *      Therefore, always be aware of the corner case where there is cycle
        *
        *
        * */


public class AlienDictionary {

    public String alienOrder(String[] words) {

        Map<Character, Set<Character>> adj = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        for(String s : words){
            for(char c : s.toCharArray()){
                indegree.put(c, 0);
            }
        }
        StringBuilder res = new StringBuilder();

        for(int i = 0; i < words.length -1; i++){
            String cur = words[i];
            String next = words[i + 1];
            if(cur.length() > next.length() && cur.startsWith(next))
                return "";
            int len = Math.min(cur.length(), next.length());
            //System.out.println("Current Word : " + cur);
           // System.out.println("Next Word : " + next);

            for( int j = 0; j < len; j++){
                char c = cur.charAt(j);
                char n = next.charAt(j);
                //System.out.println("Current character : " + c);
                //System.out.println("Next character : " + n);
                if(c == n)
                    continue;
                Set<Character> temp = adj.containsKey(c) == true ? adj.get(c) : new HashSet<>() ;

                if(!temp.contains(n)){
                    indegree.put(n, indegree.get(n) + 1);
                    temp.add(n);
                    adj.put(c, temp);
                }
                break;

            }
        }
        Queue<Character> q = new LinkedList<>();

        for(Map.Entry<Character, Integer> e : indegree.entrySet()){
            if(e.getValue() == 0)
                q.add(e.getKey());
        }

        while(!q.isEmpty()){
            char a = q.remove();
            res.append(a);
            if(adj.containsKey(a)){
                for(char c : adj.get(a)){
                    indegree.put(c, indegree.get(c) -1);
                    if(indegree.get(c) == 0)
                        q.add(c);
                }
            }

        }

        if(res.length() != indegree.size())
            return "";
        return res.toString();
    }

    static void testAlienDictionary(){
        AlienDictionary ad = new AlienDictionary();
        String[] words = new String[]{"wrt","wrf","er","ett","rftt"};
        System.out.println("The Alien Order of following given words  ");
        for(String word : words){
            System.out.print(" " + word);
        }
        System.out.println( " : " + ad.alienOrder(words) );
    }
}


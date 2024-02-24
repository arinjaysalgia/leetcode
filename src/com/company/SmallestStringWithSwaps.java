package com.company;
import java.sql.SQLOutput;
import java.util.*;
/*
 * This a problem to find the smallest string with the given number of swaps
 * Description here : https://leetcode.com/problems/smallest-string-with-swaps/
 *
 * Logic:
 * As the number of swaps are unlimited we just need to find the set of string that are connected with swaps
 * and arrange those character in the increasing order by their vaule
 * Here, we do 2 things
 *  a) we create the union find data structure for the connected position
 *  b) keep a map of
 *      the root position(smallest here) as key
 *      and all the character in those connected positions are sorted in lowest to highest and we do that using a priorityQueue as value
 *
 */


public class SmallestStringWithSwaps {

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (s.length() == 0)
            return s;
        UnionFind uf = new UnionFind(s.length());
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }
        Map<Integer, PriorityQueue<Character>> positionMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int root = uf.find(i);
            positionMap.putIfAbsent(root, new PriorityQueue<>());
            positionMap.get(root).offer(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int root = uf.find(i);
            sb.append(positionMap.get(root).poll());
        }
        return sb.toString();

    }

    class UnionFind{
        int[] rank;
        int[] root;
        public UnionFind(int n){
            root = new int[n];
            rank = new int[n];
            for(int i =0; i < n; i++){
                root[i] = i;
                rank[i] = 1;
            }
        }
        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY)
                return;
            if(rank[rootX] < rank[rootY]){
                root[rootY] = rootX;
                rank[rootX]++;
            }
            else{
                root[rootX] = rootY;
                rank[rootY]++;
            }
        }
        public int find(int x){
            if(root[x] == x)
                return x;
            return root[x] = find(root[x]);
        }
    }

    public static void testSmallestStringWithSwaps(){

        // Adding [[0,3],[1,2]]
        int[][] arrayInps = {{0,3},{1,2}};
        List<List<Integer>> inp = new LinkedList<>();
        for(int[] arrayInp : arrayInps){
            List<Integer> t = new ArrayList<>();
            for(int i : arrayInp){
                t.add(i);
            }
            inp.add(t);
        }
        String s = "dcab";

        SmallestStringWithSwaps ssws= new SmallestStringWithSwaps();
        System.out.println("Minimum of the input string " + s + " is : " + ssws.smallestStringWithSwaps(s, inp));
    }
}

package com.company;
import java.util.Map;
import java.util.HashMap;
/*
* This is the LeetCode question where you have to find the minimum Hamming distance between source and target given that you can perform swaps on the source
* More description here: https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/
* Logic:
* The core logic is to create a UnionFind data structure and then create a map of map which store the occurrence count of a given value in source and its
* connection to the root element.
* We increment the counter for the result if the give value is not found the root of the map.
* */
public class MinimumHammingDistance {

    static void testMinimumHammingDistance(){
        int[] source = {50,46,54,35,18,42,26,72,75,47,50,4,54,21,18,18,61,64,100,14};
        int[] target = {83,34,43,73,61,94,10,68,74,31,54,46,28,60,18,18,4,44,79,92};
        int[][] allowedSwaps = {{1,8},{14,17},{3,1},{17,10},{18,2},{7,12},{11,3},{1,15},{13,17},{18,19},{0,10},{15,19},{0,15},{6,7},{7,15},
                                {19,4},{7,16},{14,18},{8,10},{17,0},{2,13},{14,10},{12,17},{2,9},{6,15},{16,18},{2,16},{2,6},{4,5},{17,5},
                                {10,13},{7,2},{9,16},{15,5},{0,5},{8,0},{11,12},{9,7},{1,0},{11,17},{4,6},{5,7},{19,12},{3,18},{19,1},
                                {13,18},{19,6},{13,6},{6,1},{4,2}};
        MinimumHammingDistance mhd = new MinimumHammingDistance();
        System.out.println("The minimum Hamming Distance between the source and target is : " + mhd.minimumHammingDistance(source, target, allowedSwaps));

    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {

        UnionFind uf = new UnionFind(target.length);
        for(int[] allowedSwap : allowedSwaps){
            uf.union(allowedSwap[0], allowedSwap[1]);
        }
        Map<Integer, Map<Integer, Integer>> mp = new HashMap<>();

        for(int i = 0; i < source.length; i++){
            int root = uf.find(i);
            mp.putIfAbsent(root, new HashMap<>());
            Map<Integer, Integer> OccurrenceStore = mp.get(root);
            OccurrenceStore.put(source[i], OccurrenceStore.getOrDefault(source[i], 0) + 1);
        }

        int res =0;
        for(int i = 0; i < target.length; i++){
            int root = uf.find(i);
            Map<Integer, Integer> OccurrenceStore = mp.get(root);
            if(OccurrenceStore.getOrDefault(target[i], 0) == 0){
                res++;
            }
            else{
                OccurrenceStore.put(target[i], OccurrenceStore.get(target[i]) - 1);
            }

        }
        return res;
    }
    class UnionFind{
        int[] root;
        int[] rank;
        public UnionFind(int n){
            root = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++){
                root[i] = i;
                rank[i] = 1;
            }
        }
        void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY)
                return;
            if(rank[rootX] >= rank[rootY]){
                root[rootY] = rootX;
                rank[rootX] = (rank[rootX] == rank[rootY]) ? rank[rootX] += 1 : rank[rootX];
            }
            else{
                root[rootX] = rootY;
            }
        }
        public int find(int x){
            if(root[x] == x)
                return x;
            return root[x] = find(root[x]);
        }

    }
}

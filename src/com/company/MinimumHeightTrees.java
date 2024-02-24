package com.company;
import java.util.*;
/*
* This a problem to find the root node(s) of the graph(Spanning Tree) such that its height is minimum;
* Description here : https://leetcode.com/problems/minimum-height-trees/
*
* Logic:
*   The only case we can get a tree with the Minimum height is when the node or nodes with the highest degree of connectivity
*   are on the top;
*   Therefore, this problem translates to ideal problem of topological sorting and get the node with the highest degrees.
*   We go from the node with the minimum degree to the node with highest degrees;
*
*
*/
public class MinimumHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(edges.length == 0 || n == 1){
            List<Integer> res = new ArrayList<>(0);
            //res.add(0);
            return res;
        }

        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        int[] inDegrees = new int[n];

        for(int[] edge : edges){
            int node1 = edge[0];
            int node2 = edge[1];
            adjacencyList.putIfAbsent(node1, new LinkedList<>());
            adjacencyList.putIfAbsent(node2, new LinkedList<>());
            adjacencyList.get(node1).add(node2);
            adjacencyList.get(node2).add(node1);
            inDegrees[node1]++;
            inDegrees[node2]++;
        }
        Queue<Integer> bfsQ = new LinkedList<>();
        List<Integer> res = null;

        for(int i = 0; i < inDegrees.length; i++){
            if (inDegrees[i] == 1) {
                bfsQ.offer(i);
            }
        }
        int height = 0;
        while(!bfsQ.isEmpty()){
            int size = bfsQ.size();
            res = new LinkedList<>();
            for(int i = 0; i < size; i++){
                int node = bfsQ.poll();
                res.add(node);
                List<Integer> adjacentNodes = adjacencyList.get(node);
                for(int adjacentNode : adjacentNodes){
                    inDegrees[adjacentNode] -= 1;
                    if(inDegrees[adjacentNode] == 1){
                        bfsQ.offer(adjacentNode);
                    }
                }
            }
            height++;
        }
        System.out.println("Height of this tree is : " + height);
        return res;
    }
    public static void testMinimumHeightTrees() {
        MinimumHeightTrees mht = new MinimumHeightTrees();
        int n = 6;
        int[][] edges = new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}};
        List<Integer> res = mht.findMinHeightTrees(n, edges);
        for(int t : res){
            System.out.println(t);
        }

        n = 4;
        edges = new int[][]{{1, 0}, {1, 2}, {1, 3}};
        res = mht.findMinHeightTreesII(n, edges);
        for(int t : res){
            System.out.println(t);
        }
    }


    public List<Integer> findMinHeightTreesII(int n, int[][] edges) {

        int[] inDegrees = new int[n];
        List<List<Integer>> adjList = new LinkedList<>();
        Arrays.fill(inDegrees, 0);
        for(int i = 0; i< n; i++){
            List<Integer> temp= new LinkedList<>();
            adjList.add(temp);
        }
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            inDegrees[from]++;
            inDegrees[to]++;
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        Queue<Integer> q = new LinkedList<>();
        List<Integer> res = null;
        for(int i =0; i < n; i++){
            if(inDegrees[i] == 1){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int size = q.size();
            res = new LinkedList<>();
            for(int i =0; i < size; i++){
                int current = q.remove();
                res.add(current);
                List<Integer> temp = adjList.get(current);
                for(int next : temp){
                    inDegrees[next] -= 1;
                    if(inDegrees[next] == 1){
                        q.add(next);
                    }
                }
            }
        }

        return res;
    }


}

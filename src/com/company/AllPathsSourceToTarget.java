package com.company;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
/*
*   This is a problem to find all the paths between node 0 and the last node of the give directed acyclic Graph, Here is Description:
*   https://leetcode.com/problems/all-paths-from-source-to-target/
*
*   Logic:
*   This is a classic DFS problem where you create an adjacencyList from the graph and then preform a DFS on that.
*   The only change here is that we don't have to keep a visited set as the graph is acyclic in nature.
*
*   For a BFS solution use a queue of LinkedList here and then dump all the elements in the queue.
*   The keep going level after and check the last element of the list to see if it is the target, if it is then add it
*   to result, if not them add it to the bfsQ for processing.
*
*
* */


public class AllPathsSourceToTarget {


    public List<List<Integer>> allPathsSourceToTarget(int[][] graph){
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        int target = graph.length - 1;

        List<Integer> temp = new LinkedList<>();
        temp.add(0);
        List<List<Integer>> res = new LinkedList<>();
        dfs(graph, temp, target, 0, res);
        return res;

    }

    public void dfs(int[][] graph, List<Integer> temp, int target, int node, List<List<Integer>> res){

        if( node == target){
            res.add(new LinkedList<Integer>(temp));
            return;
        }

        for(int nextNode : graph[node]){
            temp.add(nextNode);
            dfs(graph, temp, target, nextNode, res);
            temp.remove(temp.size() - 1);
        }

    }

    public List<List<Integer>> allPathsSourceTargetBFS(int[][] graph) {
        int target = graph.length - 1;
        Queue<List<Integer>> bfsQ = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();
        temp.add(0);
        bfsQ.add(temp);
        List<List<Integer>> res = new LinkedList<>();

        while(!bfsQ.isEmpty()){
            temp = bfsQ.poll();
            int val = temp.get(temp.size() -1);
            for(int v : graph[val]){
                List<Integer> newTemp = new ArrayList<>(temp);
                newTemp.add(v);
                if(v == target){
                    res.add(newTemp);
                }
                else{
                    bfsQ.add(newTemp);
                }
            }
        }
        return res;
    }

    public static void testAllPathsSourceToTarget(){
        int[][] graph = new int[][] {{1, 2}, {3}, {3}, {}};
        AllPathsSourceToTarget apstt = new AllPathsSourceToTarget();
        System.out.println("Here are all the paths between node " + 0  + " and " + (graph.length - 1) + " node via DFS");
        System.out.println(apstt.allPathsSourceToTarget(graph));

        System.out.println("Here are all the paths between node " + 0  + " and " + (graph.length - 1) + " node via BFS");
        System.out.println(apstt.allPathsSourceTargetBFS(graph));
    }

}

package com.company;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.PriorityQueue;

/*
 *  This is the LeetCode Problem:
 *   https://leetcode.com/problems/network-delay-time/description/
 *   We have to find the maximum time it takes to reach all the nodes from a node in a given graph
 *
 * Logic:
 *      This is a classic Dijkstra's Algorithm,
 *      Create an adjacencyList list for the graph representation from edges.
 *      we use a Priority queue(min heap) of the min edge weight and array for the cost of the nodes
 *      Each time we encounter a node, and we see a smaller cost then we update the cost array and then add it to Priority queue for recalculation.
 * */

public class NetworkDelayTime {
    public Map<Integer,List<int[]>> adjacencyList = new HashMap<>();
    public int networkDelayTime(int[][] times, int n, int k) {
        for(int[] time : times){
            int source = time[0];
            int dest = time[1];
            int travelTime = time[2];
            adjacencyList.putIfAbsent(source, new ArrayList<>());
            adjacencyList.get(source).add(new int[]{dest, travelTime});
        }
        //System.out.println(adjacencyList);
        int[] costAtNode = new int[n + 1];
        Arrays.fill(costAtNode, Integer.MAX_VALUE);
        int res = 0;
        dijkstra(costAtNode, k);
        for(int i = 1 ; i < costAtNode.length; i++){
            res = Math.max(costAtNode[i], res);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    public void dijkstra(int[] costAtNode, int k){
        Queue<int[]> next = new PriorityQueue<>((a,b) -> a[1] - b[1]);

        //Following 2 lines are key as we start with the given node and asssume the cost to that node is 0.
        next.add(new int[]{k,0});
        costAtNode[k] = 0;

        while(!next.isEmpty()){
            int[] temp1 = next.poll();
            int currNode = temp1[0];
            int currCost = temp1[1];
            if(costAtNode[currNode] < currCost)
                continue;
            if(!adjacencyList.containsKey(currNode))
                continue;

            for(int[] adjList : adjacencyList.get(currNode)){
                int nextNode = adjList[0];
                int nextCost = adjList[1];
                if(costAtNode[nextNode] > currCost + nextCost){
                    costAtNode[nextNode] = currCost + nextCost;
                    next.offer(new int[] {nextNode, costAtNode[nextNode]});
                }
            }
        }
    }
    public static void testNetWorkDelayTime(){
        int[][] times = new int[][]{{2,1,1},{2,3,1},{3,4,1}};
        int n = 4;
        int k = 2;
        NetworkDelayTime ndt = new NetworkDelayTime();
        System.out.println("The maximum network delay time is : " + ndt.networkDelayTime(times, n, k) );
    }
}

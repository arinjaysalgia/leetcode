package com.company;
/*
* This is LeetCode problem to Reconstruct the itinerary in order and return it, the man always starts from JFK airport.
*
*   Description : https://leetcode.com/problems/reconstruct-itinerary/
*
*   Logic:
*       This is classic DFS problem where we are going to find the Eulerian Path(Also called Hierholzer's Algorithm),
*       which is order of node when each edge is visited exactly once.
*       1) First we create adjacencyList with neighbours sorted in a priority Queue.
*       2) Then we start with the "JFK" airport and start DFS
*       3) visit, the node that  is of the smallest lexical order(constraint of the question).
*       4) Then visit its child nodes until you can't go any forward.
*       5) Add the current node and the end of process at the head of the List.
*       6) Let the recursion unfold and all previous airports will be added at the head.
*
*   Complexity:
*       Time complexity
*           The Greatest Time complexity of this question sorting of the airport in the PriorityQueue of NLogN where N is:
*               E/2 in the worst Case.
*               E/2v in average case.
*               where, E is the edges and V are the vertices.
*
*       Space complexity:
*           O( V + E), where V are the vertices and E is the Edges.
* */


import java.util.PriorityQueue;
import java.util.Map;
import java.util.Queue;
import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;
public class ReconstructItinerary {
    public Map<String, Queue<String>> adjacencyList = new HashMap<>();
    public List<String> res = new LinkedList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        for(List<String> ticket : tickets){
            String origin = ticket.get(0);
            String dest = ticket.get(1);
            Queue<String> destOrder = adjacencyList.getOrDefault(origin, new PriorityQueue<>());
            destOrder.add(dest);
            adjacencyList.put(origin, destOrder);
        }
        dfs("JFK");
        return res;

    }

    public void dfs(String origin){
        if(adjacencyList.containsKey(origin)){
            Queue<String> destOrder = adjacencyList.get(origin);
            while(!destOrder.isEmpty()){
                String dest = destOrder.poll();
                dfs(dest);
            }
        }
        //This is important step as we want to add the current node in the recursion at the head of the list.
        res.add(0, origin);

    }

    public static void testReconstructItinerary(){

        String[][] inps = new String[][] {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        List<List<String>> tickets = new LinkedList<>();
        for(String[] inp : inps){
            String origin = inp[0];
            String dest = inp[1];
            List<String> temp = new LinkedList<>();
            temp.add(origin);
            temp.add(dest);
            tickets.add(temp);
        }
        ReconstructItinerary ri = new ReconstructItinerary();
        System.out.println("The Reconstructed Itinerary is : " + ri.findItinerary(tickets));
    }
}

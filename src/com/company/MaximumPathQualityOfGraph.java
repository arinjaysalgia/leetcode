package com.company;
import java.util.*;
/*
Find the maximum Path Quality of a graph  in a given time with each node having a value and each edge having a cost of time.
https://leetcode.com/problems/maximum-path-quality-of-a-graph/

Logic:
We create 2 sub-classes GraphNode and edges to keep the value of  each Node of graph and Edge respectively.
Insert data in the GraphNode and edges
Link each Edge with all the GraphNode.
Sort the edges for each Node so that whenever we reach at edge where sum of that edge value and currentTime is greater than the maxTime, the recursion breaks

The core logic is that of DFS where we keep track of the nodes visited in a HashSet nodesVisited.
Find the next node and add its value to a new variable newCurrentValue and then run DFS again.
The reason to compute temporary variable like newCurrentValue which is going to be passed in the dfs, keep the currentValue unchanged for the next iteration.
Then the subsequent DFS will pick the edge with min value of next node and run the above algorithm all again moving to the edge with maximum value.
Also, have a boolean hasNodeBeenVisited so that when  the DFS unfolds that node could be removed, keeping the path open(if needed) for next iteration clean .
Finally, each DFS return an integer maxCurrentValue which is the sum of all the DFS underlying.
 */

public class MaximumPathQualityOfGraph {

    public static void testMaximumPathQuality(){
        int[] values = new int[]{0,32,10, 43};
        int maxTime = 49;
        int[][] edges = new int[][]{{0,1,10},{1,2,15},{0,3,10}};
        MaximumPathQualityOfGraph mpq = new MaximumPathQualityOfGraph();
        System.out.println("Maximum Path Quality of Graph is : " + mpq.maximalPathQuality(values, edges, maxTime));
    }

    public  int maximalPathQuality(int[] values, int[][] edges, int maxTime) {

        List<GraphNode> nodes = new LinkedList<>();

        for(int i = 0; i < values.length; i++){
            GraphNode temp = new GraphNode(i, values[i]);
            nodes.add(temp);
        }
        for(int[] edge : edges){
            Edge e = new Edge(edge[0], edge[1], edge[2]);
            nodes.get(edge[0]).edges.add(e);
            nodes.get(edge[1]).edges.add(e);
        }
        //Sort edges so that we can get max node in the while moving in the path;

        for(GraphNode node : nodes){
            Collections.sort(node.edges, (e1, e2) -> e1.cost - e2.cost);
        }

        Set<Integer> nodesVisited = new HashSet<>();
        nodesVisited.add(0);
        return dfsVisit(nodes, 0,  nodesVisited, 0, maxTime, nodes.get(0).value);

    }

    public int dfsVisit(List<GraphNode> nodes, int currentNodeId, Set<Integer> nodesVisited, int currentTime, int maxTime, int currentValue){
        if(currentTime > maxTime)
            return Integer.MIN_VALUE;

        int maxCurrentValue = Integer.MIN_VALUE;
        if(currentNodeId == 0 && maxTime >= currentTime)
            maxCurrentValue = Math.max(currentValue, maxCurrentValue);


        for(Edge e : nodes.get(currentNodeId).edges){
            if(currentTime + e.cost > maxTime)
                break;

            int nextNodeId = e.id1 == currentNodeId ? e.id2 : e.id1;
            boolean hasNodeBeenVisited = nodesVisited.contains(nextNodeId);
            nodesVisited.add(nextNodeId);
            int newCurrentValue = hasNodeBeenVisited ? currentValue : currentValue + nodes.get(nextNodeId).value;
            maxCurrentValue = Math.max(maxCurrentValue, dfsVisit(nodes, nextNodeId, nodesVisited, currentTime + e.cost, maxTime, newCurrentValue));
            if(!hasNodeBeenVisited)
                nodesVisited.remove(nextNodeId);
        }
        return maxCurrentValue;
    }
}

class GraphNode{
    int id;
    int value;
    List<Edge> edges = null;
    public GraphNode(int id, int value){
        this.id = id;
        this.value = value;
        edges = new LinkedList<>();
    }
}

class Edge{
    int id1;
    int id2;
    int cost;
    public Edge(int id1, int id2, int cost){
        this.id1 = id1;
        this.id2 = id2;
        this.cost = cost;
    }
}
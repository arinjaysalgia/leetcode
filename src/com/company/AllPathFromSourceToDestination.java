package com.company;

/*
* This a problem where we are checking if all paths from source node lead to destination node;
*   Description here : https://leetcode.com/explore/learn/card/graph/619/depth-first-search-in-graph/3951/
*
* Logic:
*   This is a classic DFS problem because we have to account for all the paths from source to destination.
*   There are 2 DFS methods I have used here:
*   Regular DFS :
*       We create an adjacencyList first or all the nodes as the given graph is directed here.
*       Then visit each node and then mark it as visited.
*       If we encounter leaf node then check if this is destination node or not.
*       Time Complexity : O(V + E), were V are vertices and E is edges.
*       Space Complexity : O(V + E), the major space complexity is the time taken to build the adjacencyList.
*       Downside of this approach is that if we visited a node that leads to the destination once, we cannot
*       use that information later if we come to that node via another path.
*   Color DFS:
*       Here we color all node with White, Grey or Black color
*       Where, White is unvisited node, Grey is in processing and Black is completed node.
*       The idea is that we create  the List of all the unvisited nodes and start from source and mark it Grey and keep going.
*       If we encounter any node that is not Grey then that is a cycle and return false.
*       If we encounter a Black node then we use memoization and return true as this node will lead to destination.
*       If we encounter leaf node then check if this is destination node or not.
*       Time Complexity: Typically for dfs O(V + E)O
        Space Complexity: O(V+E) where, O(E) is occupied by the adjacency list and O(V) is occupied by the recursion stack and the color states.
* */
import java.util.*;

public class AllPathFromSourceToDestination {
    Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
    List<Node> vertices = null;
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        //Rand is just to choose which DFS runs
        Random rand = new Random();
        int randInt = rand.nextInt();
        //int randInt = 2;
        if(randInt % 2 == 0){
            //Regular DFS create an adjacencyList and then use it for DFS.
            for(int edge[] : edges){
                int fromNode = edge[0];
                int toNode = edge[1];
                List<Integer> temp = null;
                if(adjacencyList.containsKey(fromNode)){
                    temp = adjacencyList.get(fromNode);
                }
                else{
                    temp = new LinkedList<>();
                }
                temp.add(toNode);
                adjacencyList.put(fromNode, temp);
            }
            System.out.println("From regular DFS");
            return dfs(source, destination, new HashSet<>());
        }
        else{
            // Color DFS where we create List of vertices
            vertices = new LinkedList<>();
            for(int i =0; i < n; i++){
                Node vertex = new Node();
                vertices.add(vertex);
            }
            for(int[] edge : edges){
                int fromNode = edge[0];
                int toNode = edge[1];
                if(vertices.get(fromNode).neighbours == null){
                    vertices.get(fromNode).neighbours = new LinkedList<>();
                }

                vertices.get(fromNode).neighbours.add(toNode);
            }
            System.out.println("From color DFS");
            return colorDfs(source, destination);
        }

    }
    public boolean dfs(int source, int destination, Set<Integer> visited){
        if(!adjacencyList.containsKey(source)){
            return source == destination;
        }

        if(visited.contains(source)){
            return false;
        }
        for(int nextSource : adjacencyList.get(source)){
            visited.add(source);
            if(!dfs(nextSource, destination, visited)){
                return false;
            }
            visited.remove(source);
        }
        return true;
    }

    public boolean colorDfs(int source, int destination) {

        if(vertices.get(source).neighbours == null)
            return source == destination;

        if(vertices.get(source).color != "White")
            return vertices.get(source).color == "Black";

        vertices.get(source).color = "Grey";
        for(int neighbour : vertices.get(source).neighbours){
            if (!colorDfs(neighbour, destination))
                return false;
        }

        vertices.get(source).color = "Black";
        return true;
    }

    class Node{
        String color;
        List<Integer> neighbours = null;
        public Node(){
            color = "White";
        }
    }


    public static void testAllPathFromSourceToDestination(){
        int source = 0;
        int destination = 3;
        int[][] edges = new int[][]{{0,1}, {0,3}, {1,2}, {2,1}};
        int n = 4;
        AllPathFromSourceToDestination apfstd = new AllPathFromSourceToDestination();
        System.out.println("All paths in the graph lead from node "+ source + " to node " + destination +" : " + apfstd.leadsToDestination(n, edges,source,destination));
    }
}

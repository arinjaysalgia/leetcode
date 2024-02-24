package com.company;

/*
* This is a problem to create a deep copy of the given graph.
*  Detailed Description here:  https://leetcode.com/explore/learn/card/graph/619/depth-first-search-in-graph/3900/
*
* Can be done by DFS and BFS:
*   DFS Logic:
*       In here we will use a map of  original Node and the Copy node and iterate over to see if node exists, if not, then create it
*        and visit its neighbors.
*   BFS Logic:
*       Map logic is same as DFS the only change here is that the queue only has original node and we get neighbors from the map
*       only.
*
*
* */
import java.util.*;


class NodeOfGraph{

    public int val;
    public List<NodeOfGraph> neighbors;

    public NodeOfGraph(int val){
        this.val = val;
        neighbors = new ArrayList<>();
    }
}

public class CloneGraph {

    public NodeOfGraph cloneGraph(NodeOfGraph node){
        if(node == null) {
            return node;
        }
        //Following is to choose which approach will run:
        Random rand = new Random();
        int a = rand.nextInt();
        Map<NodeOfGraph, NodeOfGraph> VisitedNode= new HashMap<>();

        if(a % 2 == 1){
            System.out.println("Solution via DFS: ");
            return dfsCopyNodes(node, VisitedNode);
        }
        else{
            System.out.println("Solution via BFS: ");
            return bfsCopyNodes(node, VisitedNode);
        }
    }
    public NodeOfGraph dfsCopyNodes (NodeOfGraph node, Map<NodeOfGraph, NodeOfGraph> visitedNode){
        if(visitedNode.containsKey(node))
            return visitedNode.get(node);

        NodeOfGraph newNode = new NodeOfGraph(node.val);
        visitedNode.put(node, newNode);

        for(NodeOfGraph neighbor : node.neighbors){
            newNode.neighbors.add(dfsCopyNodes(neighbor, visitedNode));
        }
        return newNode;
    }

    public NodeOfGraph bfsCopyNodes (NodeOfGraph node, Map<NodeOfGraph, NodeOfGraph> copyNodeMap){

        NodeOfGraph newNode = new NodeOfGraph(node.val);
        copyNodeMap.put(node, newNode);
        Queue<NodeOfGraph> visitNeighbor = new LinkedList<>();
        visitNeighbor.add(node);

        while(!copyNodeMap.isEmpty()) {
            NodeOfGraph n = visitNeighbor.poll();
            for (NodeOfGraph neighbor : node.neighbors) {
                if (!copyNodeMap.containsKey(neighbor)) {
                    NodeOfGraph newNeighbor = new NodeOfGraph(neighbor.val);
                    copyNodeMap.put(neighbor, newNeighbor);
                    visitNeighbor.add(neighbor);
                }
                List<NodeOfGraph> newNeighbors = copyNodeMap.get(n).neighbors;
                newNeighbors.add(copyNodeMap.get(neighbor));
            }
        }
        return newNode;
    }
    public NodeOfGraph createGraph(int[][] nodeNeighbors){
        //[[1,3],[0,2],[1,3],[0,2]]

        List<NodeOfGraph> nodes = new LinkedList<>();
        for(int i =0; i < nodeNeighbors.length; i++){
            NodeOfGraph n = new NodeOfGraph(i);
            nodes.add(n);
        }
        for(int i =0; i< nodeNeighbors.length; i++){
            NodeOfGraph n = nodes.get(i);
            for(int j =0; j< nodeNeighbors[i].length; j++){
                n.neighbors.add(nodes.get(nodeNeighbors[i][j]));
            }
        }
        return nodes.get(0);
    }

    public static void testCloneGraph(){
        CloneGraph cg = new CloneGraph();
        int[][] nodeNeighbors = new int[][]{{1,3}, {0,2}, {1,3}, {0,2}};
        NodeOfGraph node = cg.createGraph(nodeNeighbors);
        cg.cloneGraph(node);

    }
}

package com.company;
import java.util.*;

/*
 *  This is the LeetCode Problem:
 *      https://leetcode.com/explore/learn/card/graph/621/algorithms-to-construct-minimum-spanning-tree/3860/
 *
 * Logic:
 *   Prims:
 *      Basic logic of Prims is that we start with one vertex and connect the adjacent vertex to it with the minimum edge.
 *      Take any vertex and compute all its edges and dump it in a priority queue
 *      Add that vertex to set of visited node set and add that edge weight to result
 *      repeat this until n -1 edges are connected.
 *
 *   Kruskals:
 *      Basic Logic of Kruskals is that we start with the edge with minimum weight and then move the next edge with min weight
 *      Here we use Union Find Data structure
 *      Compute all edges weight and sort them
 *      Create UnionFind data structure of all the vertices
 *      Iterate over the edges and if the vertices of the that edge's have not been connected(union) then add edge weight  to result.
 * */

public class MinCostToConnectAllPoints {

    public int minCostConnectPoints(int[][] points) {
        if(points.length == 0 || points == null)
            return 0;
        System.out.println("Output from Prim's : "+ prims(points));
        System.out.println("Output from Kruskal's : "+ kruakals(points));

        return prims(points) == kruakals(points) ? prims(points) : kruakals(points);
    }
    public int prims(int[][] points){
        PriorityQueue<int[]> edges = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = points.length;
        List<Integer> visited = new LinkedList<>();
        visited.add(0);
        for(int i = 1; i < n; i++){
            int weight = Math.abs(points[0][0] - points[i][0]) + Math.abs(points[0][1] - points[i][1]);
            edges.offer(new int[]{weight, 0, i});
        }
        int count = n - 1;
        int res = 0;
        while(!edges.isEmpty() && count >0){
            //To remove all the edges that have lower weight but are of visited vertex
            while(!edges.isEmpty() &&  visited.contains(edges.peek()[2]) ){
                edges.poll();
            }
            int[] temp = edges.poll();
            visited.add(temp[2]);
            res += temp[0];
            count--;
            int next = temp[2];
            for(int i =0; i < n; i++){
                if(i == next)
                    continue;
                int weight = Math.abs(points[next][0] - points[i][0]) + Math.abs(points[next][1] - points[i][1]);
                edges.offer(new int[]{weight, next, i});
            }
        }
        return res;

    }
    public int kruakals(int[][] points){
        List<int[]> edges = new LinkedList<>();
        int n = points.length;
        for(int i =0; i < 	n; i++){
            for(int j = 0; j < n; j++){
                if(i == j){
                    continue;
                }
                int weight = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[j][1] - points[i][1]);
                int[] e = new int[]{weight, i , j};
                edges.add(e);
            }
        }
        Collections.sort(edges, (a, b) -> a[0] - b[0]);
        UnionFind uf = new UnionFind(n);
        int count = n - 1;
        int res = 0;
        for(int[] edge : edges){
            if(count <=0)
                break;
            if(uf.Union(edge[1], edge[2])){
                res += edge[0];
                count = count - 1;
            }
        }
        return res;
    }
    class UnionFind{
        int[] root;
        int[] rank;
        public UnionFind(int n){
            root = new int[n];
            rank = new int [n];
            for(int i =0; i < n; i++){
                root[i] = i;
            }
        }
        public int find(int x){
            if( root[x] == x){
                return x;
            }
            root[x] = find(root[x]);
            return root[x];
        }

        public boolean Union(int a, int b){
            int rootA = find(a);
            int rootB = find(b);
            if( rootA == rootB)
                return false;
            if(rank[rootA] >= rank[rootB]){
                root[rootB] = rootA;
                rank[rootA] = rank[rootA] == rank[rootB] ? rank[rootA] + 1 : rank[rootA];
            }
            else{
                root[rootA] = rootB;
            }
            return true;
        }
    }

    public static void testMinCostToConnectAllPoints(){

        int[][] points = new int[][] {{3,12},{-2,5},{-4,1}};
        MinCostToConnectAllPoints mctcap = new MinCostToConnectAllPoints();
        mctcap.minCostConnectPoints(points);

    }

}

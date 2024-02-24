package com.company;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/*
This classic DFS problem where given  a set of edges and source and destination can assert if a path exists between source and destination return true if it does.
Problem Description here : https://leetcode.com/explore/learn/card/graph/619/depth-first-search-in-graph/3893/
Logic :
    Method 1 : RegualarDFS
        Here in regular DFS we build a bi-directional adjacencyList as the graph is undirected and then,
        Starting from source recursive call to a DFS function and keep track of the visited nodes along
        with a global variable to return result when a connection is found.
    Method 2 : DFSWithStack
        Build AdjacencyList same as Method 1 and then,
        use a stack to dump all the neighbours of the source till destination is found.
    Method 3 : UnionFind:
        Build a UnionFind Data Structure and then check if the source and destination belong to the same root.
 */
public class FindIfPathExist {

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer , List<Integer>> adjList = new HashMap<>();

        for(int[] edge :edges){
            adjList.putIfAbsent(edge[0], new LinkedList<>());
            adjList.putIfAbsent(edge[1], new LinkedList<>());
            List<Integer> temp = adjList.containsKey(edge[0]) == true ? adjList.get(edge[0]) : new LinkedList<>();
            List<Integer> temp2 = adjList.containsKey(edge[1]) == true ? adjList.get(edge[1]) : new LinkedList<>();
            temp.add(edge[1]);
            temp2.add(edge[0]);
            adjList.put(edge[0], temp);
            adjList.put(edge[1], temp2);
        }
       // System.out.println(adjList);

        return dfs(adjList, source, new LinkedList<>(), source, destination);
    }
    public boolean dfs(Map<Integer, List<Integer>> adjList, int vertex, List<Integer> visited ,int source, int destination){

        if(vertex == destination)
            return true;
        if(visited.contains(vertex))
            return false;
        boolean res = false;
        List<Integer> temp = null;
        if(adjList.containsKey(vertex)){
            temp = adjList.get(vertex);
        }
        else{
            return false;
        }
        for(int nVertex : temp){
            visited.add(vertex);
            res = res || dfs(adjList, nVertex, visited, source, destination);
            visited.remove(visited.size() - 1);
        }

        return res;

    }

    public boolean validPathUnionFind(int n, int[][] edges, int source, int destination) {

        if(source == destination)
            return true;
        UnionFind uf = new UnionFind(n);
        for(int[] edge : edges){
            uf.union(edge[0], edge[1]);
        }
        return uf.find(source) == uf.find(destination);

    }
    class UnionFind{
        public int[] root;
        public int[] rank;

        public UnionFind(int n){
            root = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++){
                root[i] = i;
                rank[i]  = 1;
            }
        }

        public void union(int a ,int b){
            int rootA = find(a);
            int rootB = find(b);
            if(rootA == rootB)
                return;
            if(rank[rootA] >= rank[rootB]){
                root[rootB] = rootA;
                rank[rootA] = rank[rootA] == rank[rootB] ? rank[rootA] + 1 : rank[rootA];
            }
            else{
                root[rootA] = rootB;
            }

            return;
        }
        public int find(int a){
            if(root[a] == a)
                return a;
            root[a] = find(root[a]);
            return root[a];
        }
    }

    public boolean validPathWithStack(int n, int[][] edges, int source, int destination) {


        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

        for(int[] edge : edges){
            adjacencyList.putIfAbsent(edge[0], new LinkedList<>());
            adjacencyList.putIfAbsent(edge[1], new LinkedList<>());
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        Stack<Integer> st = new Stack<>();
        st.push(source);
        boolean[] visited= new boolean[n];

        while(!st.isEmpty()){
            int node = st.pop();
            if(visited[node])
                continue;
            if(node == destination)
                return true;
            visited[node] = true;
            List<Integer> neighbours = adjacencyList.get(node);
            for(int neighbour : neighbours){
                st.push(neighbour);
            }

        }
        return false;
    }

    public static void testFindIfPathExist(){
        int n = 10;
        int [][] edges = new int[][]{{4, 3}, {1, 4}, {4, 8}, {1, 7}, {6, 4}, {4,2}, {7,4}, {4,0}, {0,9}, {5,4}};
        int source = 5;
        int destination =9;

        FindIfPathExist fipe= new FindIfPathExist();
        //fipe.validPath(n, edges, source, destination);
        System.out.println("From regular recursion : "+fipe.validPath(n, edges, source, destination));
        System.out.print("From Union Find : "+ fipe.validPathUnionFind(n, edges, source, destination));
        System.out.print("From DFS Stack : "+ fipe.validPathWithStack(n, edges, source, destination));

    }
}

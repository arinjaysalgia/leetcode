package com.company;

public class ConnectedComponentGraph {

    public static void testConnectedComponentGraph(){
        int nodes = 5;
        int[][] edges = {{0,1},{1,2},{3,4}};
        ConnectedComponentGraph ccg = new ConnectedComponentGraph();
        System.out.println(ccg.countComponents(nodes, edges) + " are the connected components in a given graph");
    }

    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        int count = n;
        for(int[] edge: edges){
            if(uf.union(edge[0], edge[1])){
                count--;
            }
        }
        return count;
    }

    class UnionFind{
        int[] rank;
        int[] root;
        public UnionFind(int n){
            rank = new int[n];
            root = new int[n];
            for(int i = 0; i < n; i++){
                rank[i] = 1;
                root[i] = i;
            }
        }
        public boolean union(int x , int y){
            int rootX = find(x);
            int rootY = find(y);

            if(rootX == rootY){
                return false;
            }

            if(rank[rootX] > rank[rootY]){
                root[rootY] = rootX;
            }
            else if(rank[rootY] > rank[rootX]){
                root[rootX] = rootY;
            }
            else{
                rank[rootX] += 1;
                root[rootY] = rootX;
            }

            return true;
        }

        public int find(int a){
            if(root[a] == a)
                return a;
            return root[a] = find(root[a]);
        }
    }
}

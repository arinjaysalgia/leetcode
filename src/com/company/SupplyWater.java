package com.company;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
public class  SupplyWater{
    public  int minCostToSupplyWater(int n, int[] wells, int[][] pipes){
        List<int[]> pipeCosts = new LinkedList<>();
        //add sudo vertex with number 0 for cost to drill a well.
        for(int i = 0; i < wells.length; i++){
            pipeCosts.add(new int[] {0, i + 1, wells[i]});
        }
        for(int[] pipe : pipes){
            pipeCosts.add(pipe);
        }
        Collections.sort(pipeCosts, (a, b) -> a[2] - b[2]);
        UnionFind uf = new UnionFind(n);
        int res =0;
        for(int[] pipeCost : pipeCosts){
            if(uf.union(pipeCost[0], pipeCost[1])){
                res += pipeCost[2];
            }
        }
        return res;
    }

    public class UnionFind{
        public int root[];
        public int rank[];
        public UnionFind(int n){
            root = new int[n +1];
            rank = new int[n + 1];
            for(int i =0; i < n +1; i++){
                root[i] = i;
                rank[i] = 0;
            }
        }
        public boolean union( int a, int b){
            int rootA = find(a);
            int rootB = find(b);
            if(rootA == rootB)
                return false;
            if(rank[rootA] >= rank[rootB]){
                root[rootB] = rootA;
                rank[rootA] = rank[rootA] == rank[rootB] ? rank[rootA] +1 : rank[rootA];
            }
            else{
                root[rootA] = rootB;
            }
            return true;
        }
        public int find(int a){
            if(root[a] == a){
                return a;
            }
            root[a] = find(root[a]);
            return root[a];
        }
    }
    public static void testSupplyWater(){
        int n = 3;
        int[] wells = new int[]{5,2,2};
        int [][] pipes = new int[][]{{1, 2, 1}, {2, 3, 8}};
        SupplyWater sw = new SupplyWater();
        System.out.println("Cost to supply water to  all " + n + " wells is : " + sw.minCostToSupplyWater(n, wells, pipes));
    }
}




package com.company;
import java.util.Set;
import java.util.HashSet;

/*
This is Leetcode problem to find the number of connected vertices, more information here:
    https://leetcode.com/problems/number-of-provinces/

UnionFind Solution logic:
    We create the root and rank array and the keep on connecting them as we encounter isConnected[i][j] = 1
    And then at the end iterate over the vertices to find get the root and add them to HashSet.
        -Size of the HashSet is the number of the province.

DFS Solution Logic:
    In this solution we take the array visited and then check if that has been visited if not we trigger dfs on that
    which in-turn will set all the connected node in the visited array to true.
 */

public class NumberOfProvince {


    public static void testNumberOfProvince(){
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        NumberOfProvince nop = new NumberOfProvince();
        System.out.println("Result from dfsSolution : " + nop.dfsSolution(isConnected));
        System.out.println("Result from unionFindSolution : " + nop.unionFindSolution(isConnected));

    }

    public int dfsSolution(int [][] isConnected){
        int count = 0;
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < isConnected.length; i++){
            if(!visited[i]){
                dfs(isConnected, visited, i);
                count++;
            }
        }
        return count;
    }

    public void dfs(int[][] isConnected, boolean[] visited, int person){
        for(int other =0; other < isConnected.length; other++){
            if(isConnected[person][other] == 1 && !visited[other]){
                visited[other] = true;
                dfs(isConnected, visited, other);
            }
        }
    }
    public int unionFindSolution(int[][] isConnected) {

        UnionFind uf = new UnionFind(isConnected.length);
        Set<Integer> provinceRoot = new HashSet<>();
        for(int i = 0; i < isConnected.length; i++){
            for(int j = 0; j < isConnected[0].length; j++){
                if(isConnected[i][j] == 1){
                    uf.union(i, j);
                }
            }
        }
        for (int i = 0; i< isConnected.length; i++){
            int root = uf.find(i);
            if(!provinceRoot.contains(root))
                provinceRoot.add(root);
        }
        return provinceRoot.size();


    }

    class UnionFind{
        int[] rootArray;
        int[] rankArray;
        public UnionFind(int n){
            rootArray = new int[n];
            rankArray = new int[n];

            for(int i = 0; i < rootArray.length; i++){
                rootArray[i] = i;
                rankArray[i] = 1;
            }
        }
        public int find(int x){
            if(x == rootArray[x])
                return x;
            return rootArray[x] = find(rootArray[x]);
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY)
                return;
            if(rankArray[rootX] > rankArray[rootY]){
                rootArray[rootY] = rootX;
            }
            else if(rankArray[rootY] > rankArray[rootX]){
                rootArray[rootX] = rootY;
            }
            else{
                rootArray[rootY] = rootX;
                rankArray[rootX] += 1;
            }
            return;
        }
    }
}

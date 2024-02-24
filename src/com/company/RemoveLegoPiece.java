package com.company;
/* This is a problem where you are given a 2D input array(consisting of 0's and 1's, 1 represent connectedness)which represent a connected lego blocks,
*  that are eventually connected to a 1 or series of 1's on the bottom row.
*  One of the connected component is removed from the lego, and you have to find out which other components will fall.
*  A component is expected to fall if it is not connected in to a series of 1's that are eventually connected to 1 at the bottom row.
*  the components are connected in left, right, up, and down direction
*    For example:
*       1 1 1 1
*       0 0 1 1
*       0 0 1 0
*   If you remove the element at 0,2 then the 1's at [[0,0], [0,1] will fall.
*
* Logic :
*   For DFS:
*       The idea is to keep 2 list 1 for result and 1 for points connected to the bottom of the lego Block grid and then run dfs
*       in all 4 direction to check if this component is connected to the bottom  return true of if it and false if it not.
*       Also, only keep connected components as 1.
*   For UnionFind:
*       This is a suboptimal solution;
*       Connect each position to its adjacent component in all 4 directions which have 1 in them.
*       Union By rank, rank of each component is given by (i * n) + j, where i and j are row and columns and n is offset
*       which is number of columns. The rank does not change as we need to find components with higher ranks here.
*       For the second pass just check if the rank of the root is less than the rank in the bottom row.
* */

import java.util.*;
public class RemoveLegoPiece {

    public List<List<Integer>> dfsSolution(int[][] legoBlocks, int[] pieceOut){

        List<List<Integer>> res = new LinkedList<>();
        List<List<Integer>> connected =new LinkedList<>();
        legoBlocks[pieceOut[0]][pieceOut[1]] = 0;
        for(int i = 0; i < legoBlocks.length; i++){
            for(int j = 0; j< legoBlocks[0].length; j++){
                if(legoBlocks[i][j] == 1){
                    dfs(legoBlocks, res, i, j, connected);
                }
            }
        }
        //System.out.println(connected);
        return res;
    }

    public boolean dfs(int[][] legoBlocks, List<List<Integer>> res, int x, int y, List<List<Integer>> connected){

        if(x< 0 || x> legoBlocks.length -1 || y< 0 || y > legoBlocks[0].length - 1 || legoBlocks[x][y] == 0)
            return false;
        List<Integer> temp = new ArrayList<>();
        temp.add(x);
        temp.add(y);
        if(connected.contains(temp))
            return true;
        if(x == legoBlocks.length -1 && y >=0 && y< legoBlocks[0].length && legoBlocks[x][y] == 1){
            return true;
        }
        legoBlocks[x][y] = 0;
        boolean a = dfs(legoBlocks, res, x + 1, y, connected) ||
                    dfs(legoBlocks, res, x - 1, y, connected) ||
                    dfs(legoBlocks, res, x, y + 1, connected) ||
                    dfs(legoBlocks, res, x, y - 1, connected);

        if(a){
            legoBlocks[x][y] = 1;
            connected.add(temp);
        }
        else{
            res.add(temp);
        }
        return a;
    }

    public int root[];
    public int rank[];
    public List<List<Integer>> unionFindSolution(int[][] legoBlocks, int[] pieceOut){

        int m = legoBlocks.length;
        int n = legoBlocks[0].length;
        root = new int[m * n];
        rank = new int[m * n];
        List<List<Integer>> result = new LinkedList<>();
        List<List<Integer>> connected = new LinkedList<>();
        legoBlocks[pieceOut[0]][pieceOut[1]] = 0;
        for(int i =0; i < m * n; i++){
            root[i] = i;
            rank[i] = i;
        }
        for(int i = 0; i < legoBlocks.length; i++){
            for(int j = 0; j< legoBlocks[0].length; j++){
                if(legoBlocks[i][j] == 1){
                    connectComponents(i ,j, m, n, legoBlocks);
                }
            }
        }
        int min = (m - 1) * n;
        int max = (m * n) -1;
        for(int i = 0; i < legoBlocks.length; i++){
            for(int j = 0; j< legoBlocks[0].length; j++){
                if(legoBlocks[i][j] == 0)
                    continue;
                List<Integer> temp = new LinkedList<>();
                temp.add(i);
                temp.add(j);
                int node = i * n + j;
                int root = find(node);
                if((root >= min && root <= max)){
                    connected.add(temp);
                }
                else{
                    result.add(temp);
                }
            }
        }
        //System.out.println("Connected List : " + connected);
        return result;
    }
    public void connectComponents(int x, int y, int m, int n,int[][] legoBlocks){

        int node = (x * n) + y;
        int[][] directions = {{0,-1}, {0, 1},{1,0}, {-1,0}};

        for(int[] direction : directions){
            int nextX = x + direction[0];
            int nextY = y + direction[1];
            if(nextX < 0 || nextX >= m || nextY <= 0 || nextY >= n ||  legoBlocks[nextX][nextY] == 0)
                continue;
            int nextNode =  (nextX * n) + nextY;
            union(node, nextNode);

        }

    }
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY)
            return;
        if (rank[rootX] > rank[rootY]){
            root[rootY] = root[rootX];
        }
        else{
            root[rootX] = root[rootY];
        }
    }
    public int find(int x){
        if(root[x] == x){
            return x;
        }
        return root[x] = find(root[x]);
    }

    public static void testRemoveLegoPiece(){
        int[][] legoBlocks = {{0, 1, 1, 1},{0, 1, 1, 0},{0, 1, 1, 0},{0, 1, 1, 0}};
        //int[][] legoBlocks = {{1, 1, 1, 1},{0, 0, 1, 1},{0, 0, 1, 0}};
        int[] pieceOut = {0,2};
        RemoveLegoPiece rlp = new RemoveLegoPiece();
        System.out.println("Union Find Solution : " + rlp.unionFindSolution(legoBlocks,pieceOut));
        System.out.println("dfs Solution : " + rlp.dfsSolution(legoBlocks, pieceOut));
    }
}

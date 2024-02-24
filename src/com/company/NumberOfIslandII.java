package com.company;
import java.util.*;
/*
This is the leetcode problem https://leetcode.com/problems/number-of-islands-ii/

The Logic here:
    0) This is a classic example of Union find problem as we are encountering data in the matrix at the go and we need
      to check if that data point can be accessible via the already seen position and build the path accordingly
    1) We find build a DisjontSet of the all the possible value in the m cross n input and have their parent intialized
        as -1. For those values that are in the input array or can be reached from input array only their parent is
        initialized as themselves or some other node value;
    2) Everytime we encounter a new position in the m by n matrix we set its parent to itself and then see if its
        neighbouring positions are the ones that we have already encountered. This is done by finding the Union of the new
        position and its neighbouring position.
            a) If we are able to perform union than we decrement the count as this island is reachable.
            b) If not then the count stays the same.
     3) There are 2 optimization done in this algorithm
        a) Path compression - every time we look for a parent of a data co- ordinate we make sure all its parent are only
            pointing to its parent in the process( look at find function line 84)
        b) Union by Rank - parents are only set by comparing the rank of 2 nodes and if the ranks are equal then we choose
            one of then at random and increment its rank.
 */


public class NumberOfIslandII {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if(positions.length == 0 || positions[0].length == 0)
            return res;
        int count = 0;
        int neighbours[][] = new int[][]{{1,0},{-1,0}, {0,1}, {0,-1}};
        DisjointSetNumberOfIslandII ds = new DisjointSetNumberOfIslandII();
        ds.makeSet(m * n);
        for(int[] position : positions){
            int x = position[0]; //x ->  x co-ordinate of position}
            int y = position[1]; // y -> y co-ordinate of position}
            int c = x * n + y; //{c -> short for co-ordinates of x & y yielding a unique number}
            if(ds.parent[c] != -1){ // condition to see if we have already seen this before and this a duplicate value
                res.add(count);
                continue;
            }
            ds.parent[c] = c;
            count++;
            for(int[] neighbour : neighbours){
                int nx = x + neighbour[0]; //{nx -> short for new x co-ordinates}
                int ny = y + neighbour[1]; //{ny -> short for new y co-ordinates}
                int nc = nx * n + ny;
               // if(nx < 0 || nx >= m || ny < 0 || ny >= n || ds.parent[nc] == -1) Same check as isValidNeighbour function
               //     continue;
                if(isValidNeighbour(m, n, nx, ny, nc, ds)){
                if(ds.union(nc, c))
                    count--;
                }
            }
            res.add(count);
        }
        return res;
    }
    public boolean isValidNeighbour(int row, int col, int x, int y, int c, DisjointSetNumberOfIslandII ds){
        if(x < 0 || x >= row || y < 0 || y >= col || ds.parent[c] == -1)
            return false;

        return true;
    }
    public static void testNumberOfIslandII(){
        int row = 3;
        int col = 3;
        int[][] positions = new int[][] {{0,0}, {0, 1}, {1,2}, {2,1}};
        NumberOfIslandII nsII = new NumberOfIslandII();
        List<Integer> res = nsII.numIslands2(row,col, positions);
        for(int i =0; i< res.size(); i++){
            System.out.println(res.get(i));
        }
    }
}

class DisjointSetNumberOfIslandII{
    public int[] parent;
    public int[] rank;
    public void makeSet(int n){
        parent = new int[n];
        rank = new int[n];
        Arrays.fill(parent, -1);
    }
    public int find(int x){
        if(parent[x] == x)
            return x;
        parent[x] = find(parent[x]);
        return parent[x];
    }
    public boolean union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);

        if(parent[parentA] == parent[parentB]){
            return false;
        }
        if(rank[parentA] < rank[parentB]){
            parent[parentA] = parentB;
        }
        else if(rank[parentA] > rank[parentB]){
            parent[parentB] = parentA;
        }
        else{
            parent[parentB] = parentA;
            rank[parentA] += 1;
        }
        return true;
    }
}

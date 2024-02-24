package com.company;

import java.util.LinkedList;
import java.util.Queue;

/*
* This is LeetCode problem where we have to find the shortest path in the Matrix from top left to bottom right corner:
*   Here is the description of the problem:
*   https://leetcode.com/problems/shortest-path-in-binary-matrix/
*
*  Logic:
*   For Almost all the problems which translate to finding the shortest path between two nodes in a graph is almost always done using BFS,
*   and all programmers should know this.
*   We do classic BFS here where we start for top left corner and AddNodes to a queue and keep going
*   We keep track of visited Node in a Visited Matrix.
* */
public class ShortestPathInBinaryMatrix {

    public int ShortestPath(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        if(grid[0][0] == 1 || grid[m -1][n -1] == 1){
            return -1;
        }
        boolean[][] visited = new boolean[m][n];
        Queue<Integer[]> q = new LinkedList<>();
        visited[0][0] = true;
        Integer[] temp = new Integer[]{0,0};
        q.add(temp);
        int distance = 1;
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size;i++){
                temp = q.poll();
                int x = temp[0];
                int y = temp[1];
                if(x == m - 1 && y == n - 1)
                    return distance;
                for(int[] direction : directions){
                    int a = x + direction[0];
                    int b = y + direction[1];
                    if(a < 0 || a > m -1 || b < 0 || b > n - 1 ||  grid[a][b] == 1 ||visited[a][b])
                        continue;
                    visited[a][b] = true;
                    temp = new Integer[]{a ,b};
                    q.offer(temp);
                }
            }
            distance++;
        }
        return -1;
    }

    public static void testShortestPathInBinaryMatrix(){
        ShortestPathInBinaryMatrix spim = new ShortestPathInBinaryMatrix();
        int[][] grid = new int[][]{{0,0,0},{1,1,0},{1,1,0}};
        System.out.println("The Shortest Path is : " + spim.ShortestPath(grid));

    }
}

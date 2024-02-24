package com.company;

import java.util.LinkedList;
import java.util.Queue;

/*
*  This is the LeetCode Problem:
*   https://leetcode.com/problems/longest-increasing-path-in-a-matrix/solution/
*   I was asked a similar problem where the path had to be consecutively increasing that is the values in the adjacent cells cannot differ for more than 1.
*
* Logic:
*   DFS:
*       For DFS here we are doing a DFS and keeping the track of the visited node and the values for that traversal in a cache 2D matrix
*   BFS:
*       For BFS this is a classic Topological Sorting problem where we first find the outDegrees of each cell.
*       We pad the matrix with 1 row  and 1 column on either side so that we can avoid the boundary conditions.
*       The find the cells with the outDegrees as 0 and perform a BFS and for each iteration of the height we get the longest increasing path size.
* */
public class LongestIncreasingPath {

    public int dfsComputeLongestIncreasingPath(int[][] matrix) {
         int m = matrix.length;
         int n = matrix[0].length;
         int[][] cache = new int[m][n];
         int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
         int res = 1;
         for(int i = 0; i < m; i++){
             for(int j = 0; j< n; j++){
                 res = Math.max(res, dfs(matrix, cache, i, j, m - 1, n - 1, dirs));
             }
         }
         return res;
    }

    public int dfs(int[][] matrix, int[][] cache, int i, int j, int m, int n, int[][] dirs){
        if(i < 0 || i > m || j <0 || j> n)
            return 0;
        if(cache[i][j] != 0)
            return cache[i][j];

        int len = 1;
        for(int[] dir : dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            //For BloomReach matrix[i][j] >= matrix[x][y] condition changes to matrix[i][j] != matrix[x][y] - 1
            if(x < 0 || x > m|| y< 0 || y > n|| matrix[i][j] >= matrix[x][y]){
                continue;
            }
            len = Math.max(len, 1 + dfs(matrix, cache, x, y, m, n, dirs));
        }
        cache[i][j] = len;
        return len;
    }

    public int bfsComputeLongestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;

        // padding the matrix with zero as boundaries
        // assuming all positive integer, otherwise use INT_MIN as boundaries
        int[][] matrix2 = new int[m + 2][n + 2];
        for (int i = 0; i < m; ++i)
            System.arraycopy(matrix[i], 0, matrix2[i + 1], 1, n);

        // change the boundary length as matrix has been copied in matrix2.
        n += 2;
        m += 2;

        // calculate outDegrees
        int[][] outDegrees = new int[m + 2][n + 2];
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                for (int[] dir : dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (matrix2[i][j] < matrix2[x][y])
                        outDegrees[i][j]++;
                }
            }
        }

        // find leaves who have zero out degree as the initial level
        Queue<int[]> leaves = new LinkedList<>();
        for (int i = 1; i < m - 1; i++)
            for (int j = 1; j < n - 1; j++)
                if (outDegrees[i][j] == 0) leaves.offer(new int[]{i, j});

        // remove leaves level by level in topological order
        int height = 0;
        while (!leaves.isEmpty()) {
            height++;
            int size = leaves.size();
            for(int i =0; i< size; i++){
                int[] node = leaves.poll();
                for (int[] dir : dirs) {
                    int x = node[0] + dir[0];
                    int y = node[1] + dir[1];
                    if (matrix2[node[0]][node[1]] > matrix2[x][y]) {
                        outDegrees[x][y] -= 1;
                        if (outDegrees[x][y] == 0)
                            leaves.offer(new int[]{x, y});
                    }
                }
            }
        }
        return height;
    }

    public static void testLongestIncreasingPath(){
        int[][] matrix =    {{9, 9, 4},
                            {6, 6, 8},
                            {2, 1, 1}};
        LongestIncreasingPath lip = new LongestIncreasingPath();
        System.out.println("The longest increasing path via DFS is :  " + lip.dfsComputeLongestIncreasingPath(matrix));
        System.out.println("The longest increasing path via BFS is :  " + lip.bfsComputeLongestIncreasingPath(matrix));
    }

}

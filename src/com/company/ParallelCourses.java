package com.company;
import java.util.*;
/*
 * This a problem to find the root node(s) of the graph(Spanning Tree) such that its height is minimum;
 * Description here : https://leetcode.com/problems/parallel-courses/description/
 *
 * Logic:
 *   This is a topological sorting problem where we build a graph(adjacency List) and inDegrees
 *   and then traverse the graph from nodes with 0 inDegree and to highest in BFS fashion and return
 *   The number of levels that we have traversed.
 *   Finally, check the inDegrees to see if we had  a cycle in the graph.
 */
public class ParallelCourses {

    public int minimumSemesters(int n, int[][] relations) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] inDegrees = new int[n + 1];

        for(int[] relation : relations){
            int prevCourse = relation[0];
            int nextCourse = relation[1];
            adjList.putIfAbsent(prevCourse, new LinkedList<>());
            adjList.get(prevCourse).add(nextCourse);
            inDegrees[nextCourse]++;
        }
        int res = 0;

        Queue<Integer> bfsQ = new LinkedList<>();

        for(int i = 0; i <= n; i++){
            if(inDegrees[i] == 0){
                bfsQ.offer(i);
            }
        }

        while(!bfsQ.isEmpty()){
            int size = bfsQ.size();
            for(int i = 0; i< size; i++){
                int current = bfsQ.poll();
                if(adjList.containsKey(current)){
                    for(int next : adjList.get(current)){
                        inDegrees[next]--;
                        if(inDegrees[next] == 0){
                            bfsQ.offer(next);
                        }
                    }
                }
            }
            res++;
        }
        //checking for cycle as all nodes should have inDegree of 0.
        for(int i = 0; i <= n; i++){
            if(inDegrees[i] != 0)
                return -1;
        }
        return res;

    }
    static void testParallelCourses(){
        int n = 3 ;
        int[][] relations = new int[][]{{1, 3}, {2, 3}};
        ParallelCourses pc = new ParallelCourses();
        System.out.println("To complete the given courses it takes " +  pc.minimumSemesters(n, relations) + " semesters."  );
        //n = 3;
        //relations = new int[][]{{1, 2}, {2, 3}, {3, 1}};
        //System.out.println("To complete the given courses it takes " +  pc.minimumSemesters(n, relations) + " semesters."  );

    }
}

package com.company;
import java.util.*;
/*
 *  This is the LeetCode Problem:
 *   https://leetcode.com/explore/learn/card/graph/623/kahns-algorithm-for-topological-sorting/3869/
 *
 * Where you are expected to find the order for the courses that can be taken given the pre-requisites
 *
 * Logic:
 *      Here we use the Topological Sorting
 *      Build the depedencyList and the inDegrees
 *      Then add the vertices with inDegrees = 0 to the queue and decrement the inDegree,
 *      Repeat until there are none vertex left in the queue
 *  Things to be careful about:
 *      Topological Sorting can only be used in Directed Acyclic Graph
 *      Therefore, always be aware of the corner case where there is cycle
 *
 * */

public class CourseScheduleII {
    //for displaying only


    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        List<List<Integer>> adjacencyList = new LinkedList<>();
        int[] inDegrees = new int[numCourses];
        for(int i =0; i< numCourses; i++){
            inDegrees[i] = 0;
            adjacencyList.add(new LinkedList<>());
        }

        for(int[] prerequisite : prerequisites){
            int to = prerequisite[0];
            int from = prerequisite[1];
            adjacencyList.get(from).add(to);
            inDegrees[to]++;
        }
        Queue<Integer> zeroDegree = new LinkedList<>();
        int index = 0;
        for(int i =0; i< numCourses;i++){
            if(inDegrees[i] == 0){
                zeroDegree.add(i);
            }
        }

        while(!zeroDegree.isEmpty()){
            int current = zeroDegree.poll();
            res[index] = current;
            index++;
            for(int next : adjacencyList.get(current)){
                inDegrees[next]--;
                if(inDegrees[next] == 0){
                    zeroDegree.add(next);
                }
            }
        }

        //corner condition
        for(int i = 0; i< numCourses; i++){
            if( inDegrees[i] != 0)
                return new int[0];
        }

        return res;
    }

    public static void testCourseSchedule(){
        int[][] inp1 = new int[][] {{1,0}};
        int inp1Courses = 2;

        int[][] inp2 = new int[][] {{1,0},{2,0},{3,1},{3,2}};
        int inp2Courses = 4;
        CourseScheduleII csII = new CourseScheduleII();
        int[] res1 = csII.findOrder(inp1Courses, inp1);
        display(inp1, res1, inp1Courses);
        int[] res2 = csII.findOrder(inp2Courses, inp2);
        display(inp2, res2, inp2Courses);
    }

    public static void display(int[][] prerequisites, int[] res ,int numCourses){
        List<List<Integer>> prereq = new LinkedList<>();
        List<Integer> result = new LinkedList<>();;
        for(int[] pre : prerequisites){
            List<Integer> pr = new ArrayList<>();
            for(int p : pre){
                pr.add(p);
            }
            prereq.add(pr);
        }
        for(int r : res){
            result.add(r);
        }
        System.out.println("For given " +  numCourses + "  courses, with the prerequisite List as : " );
        System.out.println(prereq);
        System.out.println("Sorted Order is :" );
        System.out.println(result);

    }

}

package com.company;
import java.util.Arrays;
/*
 *  This is the LeetCode Problem:
 *   https://leetcode.com/explore/learn/card/graph/622/single-source-shortest-path-algorithm/3866/
 *
 * Where you are expected to find the cheapest flight from source to destination with at most K stops
 *
 * Logic:
 *      Here we use the basic bellman ford alogrithm rather than the Optimized one because we need to ensure the path has only K stops.
 *      We iterate the given flights K + 1 times as for K stops we will require K + 1 edges.
 *      We have 2 arrays current and previous and fill them with Integer.MAX_VALUE at beginning except for source
 *      Then compute the min  cost to each node in current based on previous in each pass
 *      Finally we assign current to previous before the next pass
 *
 * */

public class CheapestFlightsWithKStops {

    public int findCheapestPrice( int n, int[][] flights, int src, int dst, int k) {

        if(src == dst)
            return 0;

        int[] previous = new int[n];
        int[] current = new int[n];

        Arrays.fill(previous, Integer.MAX_VALUE);
        Arrays.fill(current, Integer.MAX_VALUE);

        previous[src] = 0;

        for(int i =0; i < k + 1; i ++){
            for(int[] flight : flights){
                int from = flight[0];
                int to = flight[1];
                int price = flight[2];
                if(previous[from] < Integer.MAX_VALUE){
                    current[to] = Math.min(current[to], previous[from] + price);
                }
            }
            previous = current.clone();
        }

        return current[dst] == Integer.MAX_VALUE ? -1 : current[dst];
    }

    public static void testCheapestFlightsWithKStops(){

        int[][] flights = new int[][]{{0,1,100},{1,2,100},{0,2,500}};
        int src = 0;
        int dst = 2;
        int k = 1;
        int n  = 3;
        CheapestFlightsWithKStops cfwks = new CheapestFlightsWithKStops();
        System.out.println("Cheapest Flight between " + src + " to " + dst + " is : " + cfwks.findCheapestPrice(n, flights,src,dst, k));
    }
}

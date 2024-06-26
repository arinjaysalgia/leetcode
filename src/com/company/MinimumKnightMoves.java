package com.company;
/*
*   This is the LeetCode problem :
*       https://leetcode.com/problems/minimum-knight-moves/
* */

import java.sql.SQLOutput;
import java.util.List;
import java.util.Queue;
import java.util.Deque;
import java.util.LinkedList;
public class MinimumKnightMoves {

    public int minKnightMoves(int x, int y) {
        // the offsets in the eight directions
        int[][] offsets = {{1, 2}, {2, 1}, {2, -1}, {1, -2},
                {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

        // - Rather than using the inefficient HashSet, we use the bitmap
        //     otherwise we would run out of time for the test cases.
        // - We create a bitmap that is sufficient to cover all the possible
        //     inputs, according to the description of the problem.
        List<int[]> visited = new LinkedList<>();

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        int steps = 0;

        while (!queue.isEmpty()) {
            int currLevelSize = queue.size();
            // iterate through the current level
            for (int i = 0; i < currLevelSize; i++) {
                int[] curr = queue.poll();
                if (curr[0] == x && curr[1] == y) {
                    return steps;
                }

                for (int[] offset : offsets) {
                    int[] next = new int[]{curr[0] + offset[0], curr[1] + offset[1]};
                    // align the coordinate to the bitmap
                    if (!visited.contains(next)) {
                        visited.add(next);
                        queue.offer(next);
                    }
                }
            }
            steps++;
        }
        // move on to the next level
        return steps;
    }

    public static void testMinimumKnightMoves(){
        MinimumKnightMoves mkm = new MinimumKnightMoves();
        System.out.println("The minimum Knight Moves are : "+ mkm.minKnightMoves(2, 112));
    }
}

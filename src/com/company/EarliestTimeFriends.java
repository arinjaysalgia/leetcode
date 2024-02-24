package com.company;
import java.util.Arrays;
/*
* This is leetcode problem when was the earliest time everyone became friends :
* https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/
*
* Logic:
* The idea here is sort logs in the order of the timestamp and then apply Union find to see if all the components are connected,
* when we find that all components are connected then we return the timestamp of that log
* If we do not find such case then we return -1;
* */

public class EarliestTimeFriends {

    public int earliestAcq(int[][] logs, int n) {

        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        UnionFind uf = new UnionFind(n);
        int count = n;
        int res = 0;
        for(int[] log : logs){
            if(uf.union(log[1], log[2])){
                count--;
            }
            if(count == 1){
                res = log[0];
                break;
            }
        }
        return res == 0 ? -1 : res;

    }
    public static void testEarliestTimeFriends(){
        EarliestTimeFriends etf = new EarliestTimeFriends();
        int[][] logs = {{20190322,4,5},{20190101,0,1},{20190104,3,4},{20190107,2,3},{20190211,1,5},{20190224,2,4},{20190301,0,3},{20190312,1,2}};
        int n = 6;

        System.out.println("Earliest time everyone became friends is : " + etf.earliestAcq(logs, n));
    }

    class UnionFind{
        int[] root;
        int[] rank;
        public UnionFind(int n){
            root = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++){
                root[i] = i;
                rank[i] = 1;
            }
        }
        public boolean union(int x, int y){
            int rootx = find(x);
            int rooty = find(y);

            if(rootx == rooty)
                return false;
            if(rank[rootx] > rank[rooty]){
                root[rooty] = rootx;
            }
            else if(rank[rooty] > rank[rootx]){
                root[rootx] = rooty;
            }
            else{
                root[rooty] = rootx;
                rank[rootx] += 1;
            }
            return true;
        }
        public int find(int x){
            if(root[x] == x)
                return x;
            return root[x] = find(root[x]);
        }
    }
}


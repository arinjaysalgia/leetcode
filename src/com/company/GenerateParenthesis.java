package com.company;

/*
 * https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3914/
 *
 * This is a LeetCode Problem where you are given a integer n and you have to generate the parenthesis.
 * Please read the following for more description:
 *   https://leetcode.com/problems/generate-parentheses/
 *
 * Logic:
 *   Here we use, dfs approach which is referred as backtrack where were start with open parenthesis and the keep going down the
 *   branch until we hit the limit and then add closing parenthesis and as the recursion unfolds we explore the subsequent branches.
 *  Time and space complexity is the  (4^n)/ root(n)
 *
 * */
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
public class GenerateParenthesis {
    public List<String> res = null;
    public List<String> solutionGenerateParenthesisOfSize(int n){
        if(n == 0){
            return new ArrayList<>();
        }
        res = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        backTracking(0, 0, n, sb);
        return res;
    }

    public void backTracking(int open, int close, int n, StringBuilder sb){
        if(sb.length() == n * 2){
            res.add(sb.toString());
            return;
        }
        if(open < n){
            sb.append('(');
            backTracking(open +1, close, n ,sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if(close < open){
            sb.append(')');
            backTracking(open, close +1, n, sb);
            sb.deleteCharAt(sb.length() -1 );
        }
        return;

    }

    public static void testGenerateParenthesis(){
        GenerateParenthesis gp = new GenerateParenthesis();
        System.out.println("Parenthesis for size 3 : "+ gp.solutionGenerateParenthesisOfSize(3));
        System.out.println("Parenthesis for size 3 : "+ gp.solutionGenerateParenthesisOfSize(4));
    }
}

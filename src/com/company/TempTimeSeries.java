package com.company;
//Given a timeseries that soters information about temperature reading
// for a city, return a timeseries of the same length that
// provides fora a given day, how long hastath vaule been
// highest running temperature for.

import java.util.Stack;

//Example :
// input =   [7, 3, 4, 6, 9, 1, 5, 6, 3, 7, 4, 8, 2, 10]
// output =  [1, 1, 2, 3, 5, 1, 2, 3, 1, 5, 1, 7, 1, 14]
// my out =  [1, 1, 2, 3, 5, 1, 2, 3, 1, 5, 1, 7, 1, 14]
public class TempTimeSeries {

    public int[] solution(int[] inp){
        int[] res = new int[inp.length];
        res[0] = 1;
        for(int i = 1 ; i< inp.length; i++){
            int cnt = 1;
            int j =  i - 1;
            while( j >= 0 && inp[i] > inp[j]){
                    cnt++;
                    j--;
            }
            res[i] = cnt;

        }
        for(int j : res)
            System.out.println(j);

        return res;
    }
// Logic : here is that we push the elements position in the stack and check for the top and keep popping the top of the element of the stack
// to find the maximum elements that was bigger than the current element we are comparing
//  the maximum element that the difference between that element position and current element is the answer
// if the stack is empty then the current element is the largest yet and should be i + 1.
//
    public int[] optimalSolution(int[] inp){
        Stack<Integer> stack = new Stack<>();

        int[] res = new int[inp.length];
        res[0] = 1;
        stack.push(0);
        for(int i = 1 ; i < inp.length; i++){
            while(!stack.empty() && inp[stack.peek()] < inp[i]){
                stack.pop();
            }
            if(!stack.empty())
                res[i] = i - stack.peek();
            else
                res[i] = i + 1;
            stack.push(i);
        }
        for(int j : res)
            System.out.println(j);
        return res;
    }

    public static void testTempTimeSeries(){
        TempTimeSeries t = new TempTimeSeries();
        int[] input = new int[] {7, 3, 4, 6, 9, 1, 5, 6, 3, 7, 4, 8, 2, 10};
        t.optimalSolution(input);
    }
}





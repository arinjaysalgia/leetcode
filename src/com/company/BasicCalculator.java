package com.company;
import java.util.Stack;

/*
* This is Basic Calculator I question from leetcode where you are given input string with +, -, (, ) and spaces and integer and we have to compute the result of that
*   https://leetcode.com/problems/basic-calculator/
*
* Logic:
*   Here we have 2 variable 1 for sign and another for result for regular processing of the string.
*   When we come across the ( we dump the current res and sign in the stack and start processing the string from there
*   When we encounter ) we pop the sign and the Previous result from the stack multiply sign with te current res and add it to  previous result.
*
*
* */
public class BasicCalculator{
    public int calcuateString(String s){
        int n = s.length();
        int res = 0;
        int sign = 1;
        int i =0;
        Stack<Integer> st = new Stack<>();
        while(i < n){
            while(i < n && s.charAt(i) == ' ')
            i++;
            if(Character.isDigit(s.charAt(i))){
                int num = 0;
                while(i < n &&  Character.isDigit(s.charAt(i))){
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                res = res + sign * num;
            }
            if(i >= n)
                break;
            if(s.charAt(i) == '-')
                sign = -1;
            else if(s.charAt(i) == '+')
                sign = 1;
            if(s.charAt(i) == '('){
                st.push(res);
                st.push(sign);
                res = 0;
                sign = 1;
            }
            if(s.charAt(i) == ')'){
                res = res * st.pop() +  st.pop();
            }
            i++;
        }
        return res;
    }
    public static void testBasicCalculator(){
        String s = "- ((4  + 5) + 3)";
        BasicCalculator bc = new BasicCalculator();
        System.out.println("The computed output of " + s + " is : " + bc.calcuateString(s));
    }
}


package com.company;
/*
* https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3914/
*
* This is a LeetCode Problem where you are given a set of input equations and then asked to calculate the if the division of the provided output set is possible.
* Please read the following for more description:
*   https://leetcode.com/explore/learn/card/graph/618/disjoint-set/3914/
*
* Logic:
*   Here we used the Concept of the Union/Find Data Structure with more focus on find data Structure.
*   We take the concept that every new denominator is set to 1.0 and numerator is n times the denominator
*   And we put root of denominator as root of the numerator.
*   The key component here is that we have 2 hashMaps that we need to manage where one is for connection of a node to the root
*   The other one is for the weight of that connection of the node to the root.
*   As usual, we use path compression optimization in the Find function.
*
* */
import java.util.*;

public class EvaluateDivisionEquation {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        double[] res = new double[queries.size()];
        Map<String, String> root = new HashMap<>();
        Map<String, Double> distance = new HashMap<>();
        int i = 0;
        for(List<String> equation : equations){
            String numerator = equation.get(0);
            String denominator = equation.get(1);
            String rootNumerator = find(numerator, root, distance);
            String rootDenominator = find(denominator, root, distance);
            root.put(rootNumerator, rootDenominator);
            distance.put(rootNumerator, distance.get(denominator) * values[i] / distance.get(numerator));
            i++;
        }

        i = 0;
        for(List<String> query : queries){
            String numerator = query.get(0);
            String denominator = query.get(1);
            if(!root.containsKey(numerator) || !root.containsKey(denominator)){
                res[i] = -1.0;
                i++;
                continue;
            }
            String rootNumerator = find(numerator, root, distance);
            String rootDenominator = find(denominator, root, distance);
            if(!rootNumerator.equals(rootDenominator)){
                res[i] = -1.0;
                i++;
                continue;
            }
            res[i] = (double) distance.get(numerator) / distance.get(denominator);
            i++;
        }

        return res;
    }
    public String find(String s, Map<String, String> root, Map<String, Double> distance){
        if(!root.containsKey(s)){
            root.put(s, s);
            distance.put(s, 1.0);
            return s;
        }
        //condition to check if this is the root of itself.
        if(root.get(s).equals(s))
            return s;

        String currentRoot = root.get(s);
        String finalRoot = find(currentRoot, root, distance);
        root.put(s, finalRoot);
        //              {distance from current to final root} * {distance from given node to current root}
        distance.put(s, distance.get(currentRoot) * distance.get(s));
        return finalRoot;
    }

    public static void testEvaluateDivisionEquation(){
        String[][] inpEquations = {{"a","b"},{"b","c"}};
        double[] values = {2.0, 3.0};
        String[][] inpQueries = {{"a","c"},{"b","a"},{"a","e"},{"a","a"},{"x","x"}};
        List<List<String>> equations = new LinkedList<>();
        List<List<String>> queries = new LinkedList<>();
        for(int i =0; i < Math.max(inpEquations.length, inpQueries.length); i++){

            if(i < inpEquations.length){
                List<String> temp = Arrays.asList(inpEquations[i]);
                equations.add(temp);
            }
            if(i < inpQueries.length){
                List<String> temp = Arrays.asList(inpQueries[i]);
                queries.add(temp);
            }
        }
        EvaluateDivisionEquation ede = new EvaluateDivisionEquation();
        double[] res = ede.calcEquation(equations, values, queries);
        System.out.println("Following is the result: ");
        for(double r : res){
            System.out.print(r + ", ");
        }
    }
}

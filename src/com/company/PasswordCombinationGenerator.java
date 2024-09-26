package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class PasswordCombinationGenerator {
    public List<String> fres;
    public List<String> generateCombination(String inp, Map<Character, List<Character>> inpMap){
        fres = new LinkedList<>();
        backtracking(inp, inpMap, 0, new StringBuilder());
        return  fres;

    }

    public void backtracking(String inp, Map<Character, List<Character>> inpMap, int i, StringBuilder temp){

        if(i == inp.length()){
            fres.add(temp.toString());
            System.out.println(temp.toString());
            return;
        }

        List<Character> cList = inpMap.get(inp.charAt(i));
        for(char c : cList){
            temp.append(c);
            backtracking(inp, inpMap, i + 1, temp);
            temp.deleteCharAt(temp.length() - 1);
        }

    }
    public List<Character> helper(char[] inp){
        List<Character> chl = new LinkedList<>();
        for(char ch : inp) {
            chl.add(ch);
        }
        return chl;
    }

    public static void test_PasswordCombinationGenerator(){
        PasswordCombinationGenerator pcg = new PasswordCombinationGenerator();
        String inp = "illo";
        Map<Character, List<Character>> inpMap = new HashMap<>();
        char[] i = new char[] {'i', '!', 'I'};
        inpMap.put('i', pcg.helper(i));
        char[] l = new char[] {'l', 'L'};
        inpMap.put('l', pcg.helper(l));
        char[] o = new char[] {'o', 'O', '0'};
        inpMap.put('o', pcg.helper(o));
        pcg.generateCombination(inp, inpMap);
    }



}

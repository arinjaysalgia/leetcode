package com.company;
/*
* This leetcode problem for String to check if that is string is Stretchy
* Please see the problem description here:https://leetcode.com/problems/expressive-words/
*
* The idea here is that we go word by word and find the substring if that matches the condition of problem i.e:
*   1) If the input String has 3 or more of the same character and that is greater than the occurrence of the character in word
*   2) If the input string has less than 3 character than it should be exactly equal to the occurrence of the character in word
*
* */
public class StretchyWords {

    public static void testStretchWords(){
        StretchyWords sw = new StretchyWords();


        String[] words = new String[] {"hello", "hi", "helo"};
        String s = "heeelllooo";
        //String[] words = new String[] {"zzyy","zy","zyy"};
        //String[] s = ""zzzzzyyyyy"";
        int res = sw.expressiveWords(s, words);
        System.out.println(res + " Words are expressive/stretchy in the input String");
    }
    public int expressiveWords(String s, String[] words) {
        int count = 0;
        for(String word : words){
            if(isStretchy(s, word)){
                count++;
            }
        }
        return count;
    }

    public boolean isStretchy(String s, String word){
        if(word.length() > s.length())
            return false;
        int i = 0, j = 0;
        while(i < s.length() && j < word.length()){
            if(s.charAt(i) != word.charAt(j)){
                return false;
            }
            int repLenS = getRepeatedWordLength(s, i);
            int repLenWord = getRepeatedWordLength(word, j);
            if((repLenS < 3 && repLenS != repLenWord) || (repLenS >= 3 && repLenS < repLenWord)) // The condition to check
                return false;
            i += repLenS;
            j += repLenWord;
        }
        return i == s.length() && j == word.length();
    }

    public int getRepeatedWordLength(String s, int i){
        int j = i;
        while(j < s.length() && s.charAt(i) == s.charAt(j)){
            j++;
        }
        return j - i;
    }
}

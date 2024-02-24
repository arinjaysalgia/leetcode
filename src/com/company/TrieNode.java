package com.company;

public class TrieNode {
    public boolean isEndOfWord = false;
    public String Word = null;
    public TrieNode[] children = new TrieNode[26];
    TrieNode(){
        for(int i = 0; i <26; i++){
            this.children[i] = null;
        }
    }
}

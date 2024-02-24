package com.company;
import java.util.*;
import com.company.TrieNode;
/*
This is the leetcode problem https://leetcode.com/problems/word-search-ii/

Logic:
    1) Build a Trie of all the word in the list code
        class Trie is here "C:\from_old_machine\Java_projects\LeetCode\src\com\company\TrieNode.java"
    2) Depth First Search in the Trie a given character on the board and see if that froms a String:
        a) if yes, then set than add that to the result and set that value as Null;
 */
public class WordPrefixII {
    public List<String> findWords(String[] words, char[][] board){
        List<String> res = new LinkedList<>();
        TrieNode root = buildTrie(words);

        for(int i = 0; i< board.length ; i++){
            for(int j = 0; j < board[0].length; j++){
                dfs(board, i, j, res, root, new StringBuilder());
            }
        }

        return res;
    }
    public void dfs(char[][] board, int i, int j, List<String> res, TrieNode root, StringBuilder sb) {
        //check if we are out of the length of board or reached the same point in the board where the recursion started
        if(i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] == '*')
            return;

        //check if current level of Trie has and further children at the given node
        if (root.children[board[i][j] - 'a'] == null)
            return;

        root = root.children[board[i][j] - 'a'];
        if (root.isEndOfWord) {   // found one
            //res.add(root.Word);
            res.add(sb.toString());
            root.isEndOfWord = false;
            root.Word = null;     // de-duplicate
        }

        char c = board[i][j];
        //add character to the StringBuilder
        sb.append(board[i][j]);
        board[i][j] = '*';
        dfs(board, i - 1, j, res, root, sb);
        dfs(board, i, j - 1, res, root, sb);
        dfs(board, i + 1, j, res, root, sb);
        dfs(board, i, j + 1, res, root, sb);
        board[i][j] = c;
        //Remove character from the StringBuilder as the  function call from previous call stack should only get the same
        // StringBuilder it sent to this instance of the function
        sb.deleteCharAt(sb.length() - 1);
    }

    public TrieNode buildTrie(String[] words){
        TrieNode root = new TrieNode();
        for(String word : words){
            TrieNode iterator = root;
            word = word.toLowerCase();
            for(int i = 0; i < word.length(); i++){
                int index = word.charAt(i) - 'a';
                //Only create a new node if the old node is null
                if(iterator.children[index] == null)
                    iterator.children[index] = new TrieNode();
                iterator = iterator.children[index];
            }
            iterator.isEndOfWord = true;
            iterator.Word = new String(word);
        }
        return root;
    }
}

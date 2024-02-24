package com.company;

import java.util.LinkedList;
import java.util.Queue;

/*
* This Leetcode problem to find the Kth element smallest  in a binary searchTree:
*   Description here: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
* Logic:
*   The idea here is to traverse in one direction until you reach the end and then increment the global variables to get to the largest;
*   I have created dfs for both KthLargest and KthSmallest Elements
*   The
* */
public class KthElementInBST {
    public static int count, res, n = 0;
    public int kthSmallest(TreeNode root, int k){
       // System.out.println("Control Passed here : "+ n++);
        KthSmallestDFS(root, k);
        return res;
    }
    public void KthSmallestDFS(TreeNode root, int k){
        if(root == null)
            return;
        KthSmallestDFS(root.left, k);
        count++;
        if(count == k){
            res = root.val;
            return;
        }
        KthSmallestDFS(root.right, k);
    }

    public int kthLargest(TreeNode root, int k){
        //This is a peculiar behavior that in the process of recursion unfolding the control is passed back to
        // the first line of this calling method and everything executes again a new.
        // The PrintLine below is to demonstrate that behavior.
       // System.out.println("Control Passed here : "+ n++);
        TreeNode temp = root;
        kthLargestDFS(root, k);
        return res;
    }
    public void kthLargestDFS(TreeNode root, int k){
        if(root == null)
            return;
        kthLargestDFS(root.right, k);
        count++;
        if(count == k){
            res = root.val;
            return;
        }
        kthLargest(root.left, k);

    }

    public TreeNode insertIntoBST(TreeNode root, int val){
        if(root == null){
            return new TreeNode(val);
        }
        if(root.val > val){
            root.left = insertIntoBST(root.left, val);
        }
        else{
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
    public void traverseTree(TreeNode root){
        if(root == null)
            return;
        traverseTree(root.left);
        System.out.print(" " + root.val);
        traverseTree(root.right);
    }
    public void levelOrderTraverse(TreeNode root){
        Queue<TreeNode> q= new LinkedList<>();
        q.offer(root);
        int level = 0;
        while(!q.isEmpty()){
            int size = q.size();
            System.out.println("value at level : " + level);
            for(int i =0; i < size; i++){
                TreeNode temp = q.poll();
                System.out.print(" " + temp.val);
                if(temp.left != null)
                    q.offer(temp.left);
                if(temp.right != null)
                    q.offer(temp.right);
            }
            level++;
            System.out.println();
        }
    }

    public static void testKthElementInBST(){
        int[] inputs = {40,20,60,10,30,50,70,25};
        //int[] inputs = {4,2,7,1,3,5};
        TreeNode root = null;

        KthElementInBST keibst = new KthElementInBST();
        for(int input : inputs){
            root =  keibst.insertIntoBST(root, input);
        }
        int k = 8;
        keibst.traverseTree(root);
        //keibst.levelOrderTraverse(root);
        System.out.println();
        keibst.count = 0;
        keibst.res = 0;
        System.out.println("Largest "+ k + " element in the tree is : " + keibst.kthLargest(root, k));
        keibst.count = 0;
        keibst.res = 0;
        keibst.n = 0;
        System.out.println("Smallest "+ k + " element in the tree is : " + keibst.kthSmallest(root, k));
    }
}

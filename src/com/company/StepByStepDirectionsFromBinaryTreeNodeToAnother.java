package com.company;
/*
This is leetcode problem 2096
https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/

We have to find path from one node to another in a given tree and return a String which have U, L R characters only where directions are
U up
L Left(down)
R Right(down)

Logic:
This problem is from the Lowest Common Ancestor(LCA) Category. But we do a little modification to that approach here.
We find the path to root from starting point and destination in form of the above string
Then we reverse the strings and start removing the common character as both paths are till root
Then replace all the character of the start string with the U as you can only go up(if  starting node is not the LCA) from the starting node

*/
public class StepByStepDirectionsFromBinaryTreeNodeToAnother {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder startToRoot = new StringBuilder();
        StringBuilder destToRoot = new StringBuilder();
        findPath(root, startValue, startToRoot);
        findPath(root, destValue, destToRoot);
        startToRoot.reverse();
        destToRoot.reverse();
        while(startToRoot.length() >0 && destToRoot.length() >0 && startToRoot.charAt(0) == destToRoot.charAt(0)){
            startToRoot.deleteCharAt(0);
            destToRoot.deleteCharAt(0);
        }
        return "u".repeat(startToRoot.length()) + destToRoot.toString();
    }

    public boolean findPath(TreeNode root, int value, StringBuilder sb){
        if(root == null)
            return false;
        if(root.val == value)
            return true;
        if(findPath(root.left, value, sb)){
            sb.append('L');
            return true;
        }
        if(findPath(root.right, value, sb)) {
            sb.append('R');
            return true;
        }
        return false;
    }

}

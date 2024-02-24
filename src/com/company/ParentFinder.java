package com.company;
import java.util.*;
// The only utility of the childNode variable is that it helps to point to the Node which you want to find in the tree structure;
// This will only be possible when you have executed the function getChild();
public class ParentFinder {
     public Node Parent = null;
     public Node childNode = null;
     ParentFinder(String Name){
         Parent = new Node(Name);
     }

    public List<String> listAllChildrenOnlevel(Node parent, Node child){

        Queue<Node> bfs = new LinkedList<>();
        bfs.add(parent);
        int levelFound = Integer.MIN_VALUE;
        Map<Integer, List<String>> nodeLevel = new HashMap<>();
        int level = 0;
        while(!bfs.isEmpty()){
            int size = bfs.size();
            for(int i = 0; i< size; i++){
                Node temp = bfs.poll();
                if(temp == child){
                    levelFound = level;
                }
                List<String> allCurrentNodes = null;
                if(nodeLevel.containsKey(level)){
                    allCurrentNodes =  nodeLevel.get(level);
                }
                else{
                    allCurrentNodes = new LinkedList<>();
                }
                allCurrentNodes.add(temp.Name);
                nodeLevel.put(level, allCurrentNodes);
                if(temp.child != null){
                    for(int j = 0; j < temp.child.size(); j++){
                        Node childOftemp = temp.child.get(j);
                        bfs.add(childOftemp);
                    }
                }
            }
            level++;
        }
        return levelFound == Integer.MIN_VALUE ? new LinkedList<>() : nodeLevel.get(levelFound);
    }

     public boolean addChildren(String[] childNames, Node Parent){
         if (childNames.length == 0) {
             return true;
         }
         if(Parent.child == null){
             Parent.child = new LinkedList<>();
         }
         for(String childName : childNames){
             Parent.child.add(new Node(childName));
         }
         return true;
     }
     public List<String> listChildren(Node Parent){

         if(Parent == null)
             return new ArrayList<>();

         List<String> res = new ArrayList<>();
         for( int i = 0; i < Parent.child.size(); i++){
             res.add(Parent.child.get(i).Name);
             //System.out.println(res.get(i));
         }
         return res;
     }

     public void getChild(String Name, Node p){
        if(p == null)
            return;
        if(p.Name.equals((Name))){
            childNode = p;
            return;
        }
        if(p.child == null)
            return;
        for(int i = 0; i < p.child.size(); i++) {
            getChild(Name, p.child.get(i));
        }
     }
    // This is a function that calls all the addChildren to add children for a given Node.
    // The way it works is that is first gets the Node in which the children have to be added in the childNode Pointer.
    // By calling the getChild() function and then, that pointer is passed to addChildren() function with the list of Strings

    public static void testParentFinder(){
        ParentFinder pf = ParentFinder.setDataForParentFinder();
        pf.getChild("Shital", pf.Parent);
        System.out.println(pf.listChildren(pf.childNode));
        List<String> ress = pf.listAllChildrenOnlevel(pf.Parent, pf.childNode);
        for(String res : ress){
            System.out.print(res + " ");
        }
    }

    public static ParentFinder setDataForParentFinder(){
        ParentFinder pf = new ParentFinder("KishoriLal");
        String[] s = new String[] {"Khaniyalal", "Sundarlal", "ShikharChand", "Kailash", "Gajendra"};
        pf.addChildren(s, pf.Parent);

        pf.getChild("Khaniyalal", pf.Parent);
        s = new String[] {"Ashok", "Rajkumar", "Paras", "Mukesh", "Shital"};
        pf.addChildren(s, pf.childNode);
        //System.out.println(pf.childNode.child.size());

        pf.getChild("Ashok", pf.Parent);
        //System.out.println(pf.childNode.Name);
        s = new String[] {"Vishwa", "Lali", "Chinmayi"};
        pf.addChildren(s, pf.childNode);
        //System.out.println(pf.childNode.child.size());

        pf.getChild("Rajkumar", pf.Parent);
        //System.out.println(pf.childNode.Name);
        s = new String[] {"Ronak", "Roshini"};
        pf.addChildren(s, pf.childNode);
        //System.out.println(pf.childNode.child.size());

        pf.getChild("Paras", pf.Parent);
        //System.out.println(pf.childNode.Name);
        s = new String[] {"Purvika", "Prachi"};
        pf.addChildren(s, pf.childNode);
        //System.out.println(pf.childNode.child.size());

        pf.getChild("Mukesh", pf.Parent);
        //System.out.println(pf.childNode.Name);
        s = new String[] {"Nupur", "Somya"};
        pf.addChildren(s, pf.childNode);
        //System.out.println(pf.childNode.child.size());

        pf.getChild("Shital", pf.Parent);
        //System.out.println(pf.childNode.Name);
        s = new String[] {"Arinjay"};
        pf.addChildren(s, pf.childNode);
        //System.out.println(pf.childNode.child.size());

        pf.getChild("Sundarlal", pf.Parent);
        //System.out.println(pf.childNode.Name);
        s = new String[] {"Arun", "Rekha", "Guddi", "Moti"};
        pf.addChildren(s, pf.childNode);
        //System.out.println(pf.childNode.child.size());

        pf.getChild("Arun", pf.Parent);
        //System.out.println(pf.childNode.Name);
        s = new String[] {"Arihant", "Ashima"};
        pf.addChildren(s, pf.childNode);
        //System.out.println(pf.childNode.child.size());

        pf.getChild("Rekha", pf.Parent);
        //System.out.println(pf.childNode.Name);
        s = new String[] {"Rishi", "Samyak"};
        pf.addChildren(s, pf.childNode);
        //System.out.println(pf.childNode.child.size());

        pf.getChild("Moti", pf.Parent);
        //System.out.println(pf.childNode.Name);
        s = new String[] {"Kushboo", "Kushal"};
        pf.addChildren(s, pf.childNode);
        //System.out.println(pf.childNode.child.size());

        pf.getChild("Guddi", pf.Parent);
        //System.out.println(pf.childNode.Name);
        s = new String[] {"Romil", "Rashi"};
        pf.addChildren(s, pf.childNode);
        //System.out.println(pf.childNode.child.size());

        pf.getChild("ShikharChand", pf.Parent);
        //System.out.println(pf.childNode.Name);
        s = new String[] {"Dinesh", "Pappi", "Deepash", "Depika"};
        pf.addChildren(s, pf.childNode);
        //System.out.println(pf.childNode.child.size());

        pf.getChild("Kailash", pf.Parent);
        //System.out.println(pf.childNode.Name);
        s = new String[] {"Priyanka", "Vipin", "Shaman", "Sonam"};
        pf.addChildren(s, pf.childNode);
        //System.out.println(pf.childNode.child.size());

        pf.getChild("Gajendra", pf.Parent);
        //System.out.println(pf.childNode.Name);
        s = new String[] {"Laddo", "Pinto"};
        pf.addChildren(s, pf.childNode);
        //System.out.println(pf.childNode.child.size());

        return pf;
    }

}

class Node{
    public String Name;
    public List<Node> child = null;
    Node(String Name){
        this.Name = Name;
    }
}
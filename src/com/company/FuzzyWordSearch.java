package com.company;
/*
* This is indeed interview question where the you are given a set of input sentences with ID and with string of words in them
* Then search words with other
* You are supposed to find the DocumentIDs of how many times each search word occurs in the input and return the ID in the descending order of occurence
*  If no word occurs then return -1
* IF the 2 ID have the same count of the words then the ID with the lower value is returned first.
*
*   Logic :
*   For function insertDocument:
*       We will create a map with each String word as key and the DocumentID as the value while inserting;
*   For function performSearch:
*       Then when searching we will get each documentID the give String is in and then add to its occurrence map;
*       Then we will sort the documentID's by their occurrence and if the same occurrence then min weight
* */

import java.util.*;
public class FuzzyWordSearch {

    public static Map<String, List<Integer>> inputDocumentMap = new HashMap<>();

    public static void insertDocument(String input, int documentID){
        String[] inpData = input.split(" ");

        for(String inp : inpData){
            List<Integer> temp = null;
            if(inputDocumentMap.containsKey(inp)){
                temp = inputDocumentMap.get(inp);
            }
            else{
                temp = new LinkedList<>();
            }
            if(!temp.contains(documentID)){
                temp.add(documentID);
            }
            inputDocumentMap.put(inp, temp);
        }

    }

    public static String performSearch(String searchWords){
        String[] arraySearchWords = searchWords.split(" ");
        if(arraySearchWords.length == 1 && !inputDocumentMap.containsKey(arraySearchWords[0])) {
            return "-1";
        }
        Map<Integer, Integer> occurrence = new HashMap<>();
        for(String arraySearchWord : arraySearchWords){
            List<Integer> temp = inputDocumentMap.getOrDefault(arraySearchWord, null);
            if(temp != null){
                for(int i =0; i < temp.size(); i++){
                    int documentID = temp.get(i);
                    occurrence.put(documentID, occurrence.getOrDefault(documentID, 0) + 1);
                }
            }
        }
        PriorityQueue<Map.Entry<Integer,Integer>> pq  = new PriorityQueue<>((a,b) -> a.getValue() == b.getValue() ? a.getKey() - b.getKey() : b.getValue() - a.getValue());

        for(Map.Entry<Integer, Integer> e : occurrence.entrySet()){
            pq.offer(e);
        }

        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()){
            Map.Entry<Integer, Integer> e = pq.poll();
            sb.append(e.getKey());
            sb.append(" ");
        }

        return sb.toString();
    }

    public static  void testFuzzyWordSearch(){

        Date date = new Date();
        long timeMilli = date.getTime();
        long oldTime = timeMilli;
        System.out.println("Time in millisconds before execution : " + timeMilli);

        String[] inp = {
                "online transaction application and accounting internship position",
                "keep records of money and negotiable instruments involved in a financial institution and various transactions receive and pay out money",
                "position requires previous cash handling sales and customer service experience member service associates assist columbia credit union members in reaching",
                "ability to work a schedule that includes working weekends and some holidays the nationwide mortgage licensing system nmls web site mortgage",
                "minimum of six months cash handling experience tellers primary responsibilities include processing transactions accurately and efficiently in accordance with",
                "high school diploma or equivalent the fifth largest bank in the united states were one of the countrys most respected innovative and successful financial",
                "you may check the status of your application online 24 hours a day 7 days a week through usajobs by signing in and selecting quot application status",
                "responsible for reviewing new loan application requests to ensure that all required forms and or documentation have been provided by the borrower",
                "accurately and efficiently process transactions such as customer deposits and cashing checks minimum of six months cash handling experience",
                "may be required to work weekends and or extended hours and regular reliable attendance is critical ability to demonstrate initiative a commitment to",
                "rockstar and ninja coder needed"
        };
        for(int i =0; i< inp.length; i++){
            insertDocument(inp[i], i);
        }
        //System.out.println(inputDocumentMap);
        String[] searchWords = {
                "financial",
                "weekends holidays",
                "accurately transactions",
                "and",
                "them",
                "online"
        };
        for(String searchWord : searchWords){
            System.out.println(searchWord + " in " + performSearch(searchWord));
        }

        date = new Date();
        timeMilli = date.getTime();
        System.out.println("Time in millisconds after execution : " + timeMilli);
        System.out.println("Time in millisconds difference  for execution : " + (timeMilli - oldTime));

    }

}

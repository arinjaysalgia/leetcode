package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
You've been asked to program a bot for a popular bank that will automate the management of incoming requests. Every request has its own timestamp in seconds, and it is guaranteed that all requests come sequentially, i.e. the timestamp is strictly increasing. There are two types of incoming requests:

deposit <timestamp> <holder_id> <amount> — request to deposit <amount> amount of money in the <holder_id> account;
withdraw <timestamp> <holder_id> <amount> — request to withdraw <amount> amount of money from the <holder_id> account. As a bonus, bank also provides a cashback policy — 2% of the withdrawn amount rounded down to the nearest integer will be returned to the account exactly 24 hours after the request timestamp. If the cashbask and deposit/withdrawal happen at the same timestamp, assume cashback happens earlier.
Your system should also handle invalid requests. There are two types of invalid requests:

invalid account number;
withdrawal of a larger amount of money than is currently available.
For the given list of initial balances and requests, return the state of balances straight after the last request has been processed, or an array of a single element [-<request_id>] (please note the minus sign), where <request_id> is the 1-based index of the first invalid request. Note that cashback requests which haven't happened before the last request should be ignored.

Example

Example 1
For balances = [1000, 1500] and requests = ["withdraw 1613327630 2 480", "withdraw 1613327644 2 800", "withdraw 1614105244 1 100", "deposit 1614108844 2 200", "withdraw 1614108845 2 150"], the output should be solution(balances, requests) = [900, 295].

Explanation
Here are the states of balances after each request:

initially: [1000, 1500];
"withdraw 1613327630 2 480": [1000, 1020];
"withdraw 1613327644 2 800": [1000, 220];
At 1613414030 the 2nd account will receive the cashback of 480 * 0.02 = 9.6, which is rounded down to 9: [1000, 229];
At 1613414044 the 2nd account will receive the cashback of 800 * 0.02 = 16: [1000, 245];
"withdraw 1614105244 1 100": [900, 245];
"deposit 1614108844 2 200": [900, 445];
"withdraw 1614108845 2 150": [900, 295], which is the answer.
Cashbacks for the last two withdrawals happen at 1614191644 and 1614195245, which is after the last request timestamp 1614108845, so they are ignored.
Example 2
For balances = [20, 1000, 500, 40, 90] and requests = ["deposit 1613327630 3 400", "withdraw 1613327635 1 20", "withdraw 1613327651 1 50", "deposit 1613327655 1 50"], the output should be solution(balances, requests) = [-3].

Explanation
Here are the states of balances after each request:

initially: [20, 1000, 500, 40, 90];
"deposit 1613327630 3 400": [20, 1000, 900, 40, 90];
"withdraw 1613327635 1 20": [0, 1000, 900, 40, 90];
"withdraw 1613327651 1 50": it is not possible to withdraw 50 from the 1st account, so the request is invalid.
the rest of the requests are not processed
Input/Output

[execution time limit] 3 seconds (java)

[input] array.integer balances

Array of integers, where balances[i] is the amount of money in the (i + 1)th account.

Guaranteed constraints:
1 ≤ balances.length ≤ 100,
0 ≤ balances[i] ≤ 105.

[input] array.string requests

Array of requests in the order they should be processed. Each request is guaranteed to be in the format described above. It is guaranteed that requests come sequentially, i.e. the timestamp strictly increases.

Guaranteed constraints:
1 ≤ requests.length ≤ 100.

[output] array.integer

The balances after executing all of the requests or array with a single integer - the index of the first invalid request preceded b



 */
public class AccountTransaction {
    Queue<List<Integer>> cashBackQueue = new PriorityQueue<>((a, b) -> a.get(0) - b.get(0));
    int[] solution(int[] balances, String[] requests) {
        String lastRequest = requests[requests.length - 1];
        String[] lastRequestBreakdown = lastRequest.split(" ");
        int res[] = new int[balances.length];
        int LastTransactionTimeStamp =  Integer.parseInt(lastRequestBreakdown[1]);
        for(int i = 0; i < requests.length; i++){
            if(requests[i].contains("deposit")){
                int[] temp = deposit(balances, requests[i], i + 1);
                if(temp[0] < 0){
                    return new int[]{temp[1]};
                }
                res[temp[1]] = temp[2];
                balances[temp[1]] = temp[2];
            }
            if(requests[i].contains("withdraw")){
                int[] temp = withdrawn(balances, requests[i], i + 1, LastTransactionTimeStamp);
                if(temp[0] < 0){
                    return new int[]{ temp[1]};
                }
                res[temp[1]] = temp[2];
                balances[temp[1]] = temp[2];
            }
        }
        return balances;
    }

    int[] deposit(int[] balances, String request, int requestnum){
        int[] res = new int[3];
        String[] breakdown = request.split(" ");
        int accountId = Integer.parseInt(breakdown[2]);
        if(!isValidAcount(accountId, balances.length)){
            res[0] = -1;
            res[1] = requestnum * -1;
            return res;
        }
        res[0] = 1;
        res[1] = accountId - 1;
        res[2] = balances[accountId - 1] +  Integer.parseInt(breakdown[3]);
        return res;
    }

    int[] withdrawn(int[] balances, String request ,int requestnum, int LastTransactionTimeStamp){
        int[] res = new int[3];
        String[] breakdown = request.split(" ");
        int accountId = Integer.parseInt(breakdown[2]);
        int amountWithdrawn = Integer.parseInt(breakdown[3]);
        int currentTranTimeStamp = Integer.parseInt(breakdown[1]);
        if(!isValidAcount(accountId, balances.length) || balances[accountId - 1] < amountWithdrawn){
            res[0] = -1;
            res[1] = requestnum * -1;
            return res;
        }
        res[0] = 1;
        res[1] = accountId - 1;
        res[2] = balances[accountId - 1] - amountWithdrawn;
        int cashback = 0;
        if(amountWithdrawn > 50) {
            cashback =  (int) Math.floor(amountWithdrawn * 0.02) ;
        }
        processCashBackEligible(currentTranTimeStamp, accountId, cashback, balances);
        return res;
    }
    public static void testAccountTransaction(){
        /*
        String s = "withdraw 1613327630 2 480";
        String[] s1 = s.split(" ");
        int t1  = Integer.parseInt(s1[3]);
        int ts = 1646322875;
        System.out.println(ts);
        for(String s3 : s2){
            System.out.println(s3);
        }
         */

       String[] s2 = {"withdraw 1613327630 2 480", "withdraw 1613327644 2 800", "withdraw 1614105244 1 100", "deposit 1614108844 2 200", "withdraw 1614108845 2 150"};
        //String[] s2 = {"withdraw 1613327630 2 480", "withdraw 1613327644 2 800"};
         int[] balances = new int[]{1000, 1500};
        //String[] s2 = {"deposit 1613327630 3 400", "withdraw 1613327635 1 20", "withdraw 1613327651 1 50", "deposit 1613327655 1 50"};
        //int[] balances = new int[]{20, 1000, 500, 40, 90};
        AccountTransaction ac = new AccountTransaction();
        int[] res = ac.solution(balances, s2);
        for(int ans : res){
            System.out.println(ans);
        }



    }
    public int[] processCashBackEligible(int currentTransactionTimeStamp, int accoundId, int cashBack, int[] balances){

        while(!cashBackQueue.isEmpty() && cashBackQueue.peek().get(0) <= currentTransactionTimeStamp - 86400){
            List<Integer> temp = cashBackQueue.poll();
            balances[temp.get(1) - 1] += temp.get(2);
        }

        if(cashBack > 0){
            List<Integer> cashBackEntry = new ArrayList<>();
            cashBackEntry.add(currentTransactionTimeStamp);
            cashBackEntry.add(accoundId);
            cashBackEntry.add(cashBack);
            cashBackQueue.add(cashBackEntry);
        }

        return balances;

    }
    public boolean isValidAcount(int accountNumber, int accounts){
        if(accountNumber > accounts){
            return false;
        }
        return true;
    }

}

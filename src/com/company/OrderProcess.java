package com.company;

import java.util.Arrays;

public class OrderProcess {
    private int numberOfOrders;
    private int duration;
    int[] ordersIn;
    int counter;
    public OrderProcess(int numberOfOrders, int duration){
        this.numberOfOrders = numberOfOrders;
        this.duration = duration;
        ordersIn = new int[numberOfOrders];
        Arrays.fill(ordersIn, -1);
        counter = 0;
    }

    public boolean canOrderBeFilled(int timestamp){
        if(counter == numberOfOrders){
            counter = 0;
        }
        if (ordersIn[counter] == -1) {
            ordersIn[counter] = timestamp;
            counter++;
            return true;
        }
        else if(counter < numberOfOrders - 1  && ordersIn[counter] + duration < timestamp){
            counter++;
            ordersIn[counter] = timestamp;
            return true;
        }
        else if( counter == numberOfOrders - 1 ){
            if(ordersIn[counter] + duration < timestamp){
                ordersIn[0] = timestamp;
                counter++;
               return true;
            }
        }
        return false;
    }
}

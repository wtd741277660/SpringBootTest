package com.wtd.stack;

import java.util.Stack;

public class DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        if(T.length == 0){
            return result;
        }
        Stack<Integer> temp = new Stack<>();//保存T的下标
        temp.push(0);
        for(int i = 1;i < T.length;i++){
            while (temp.size() != 0 && T[i] > T[temp.peek()]){
                result[temp.peek()] = i - temp.peek();
                temp.pop();
            }
            temp.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] a = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = new DailyTemperatures().dailyTemperatures(a);
        for(int i:result){
            System.out.print   (i);
        }
    }
}

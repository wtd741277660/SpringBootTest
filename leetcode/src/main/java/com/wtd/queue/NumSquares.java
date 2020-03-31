package com.wtd.queue;

import com.mchange.v2.lang.StringUtils;

import java.util.*;

/**
 * 采用BFS计算完全平方数
 */
public class NumSquares {

    public static void main(String[] args) {
        Date date1 = new Date();
        int num = new NumSquares().numSquares(10);
//        System.out.println(new Date().getTime() - date1.getTime());
//        System.out.println(num);
//        System.out.println(Math.ceil(Math.sqrt(12)));
//        System.out.println(Math.pow(4,2));
    }


    public int numSquares(int n) {
        Queue<Integer> nums = new LinkedList();
        Queue<Integer> minus = new LinkedList();
        int step = 0;
        nums.offer(n);
        minus.offer(n);

        while(true){
            System.out.println(minus);
            int size = nums.size();
            for(int i = 0;i < size;i++){
                int curNum = nums.peek();
                int curMinus = minus.peek();
                if(curMinus == 0){
                    return step;
                }
                List<Integer> nextSquare = nextSquare(curNum);
                for(Integer square:nextSquare){
                    if(curMinus - square > 0){
                        nums.offer(square);
                        minus.offer(curMinus - square);
                    }else if(curMinus - square == 0){
                        return step + 1;
                    }
                }
                nums.poll();
                minus.poll();
            }
            step++;
        }
    }

    public List<Integer> nextSquare(int num){
        List<Integer> nextSquare = new ArrayList<>();
        int a = (int) Math.sqrt(num);
        for(int i = a;i >= 1;i--){
            nextSquare.add((int) Math.pow(i,2));
        }
        return nextSquare;
    }
}

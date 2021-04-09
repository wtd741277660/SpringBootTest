package com.springboot.test.SpringBootTest.masterWorker;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class Main {

    //实现1+2+3...+100的结果计算
    //分别分成100个子任务，让5个线程去执行，master来收集所有的结果，并最后统一处理
    public static void main(String[] args) {
        Master master = new Master(new PlusWorker(),5);
        //循环放入100个子任务
        for(int i = 1;i <= 100;i++){
            master.submit(i);
        }
        master.execute();//开始执行所有的子任务

        Set set = new HashSet();
        set.add(123);


        //等待所有的子任务执行完毕，最后收集所有的结果
        int result = 0;
        Map<String,Object> resultMap = master.getResultMap();
        while(resultMap.size() > 0 || !master.isComplete()){
            Set<String> keySet = resultMap.keySet();
            String key = null;
            for(String k:keySet){
                key = k;
                break;
            }
            Integer i = null;
            if(key != null){
                i = (Integer) resultMap.get(key);
            }
            if(i != null){
                result += i;
            }
            //从结果集中拿出一个结果就删除一个结果
            if(key != null){
                resultMap.remove(key);
            }
        }
        System.out.println("最后的结果是：" + result);
    }
}

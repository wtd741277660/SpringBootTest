package com.springboot.test.SpringBootTest.masterWorker;

import java.util.Map;
import java.util.Queue;

public class Worker implements Runnable {

    //任务队列
    Queue<Object> workQueue;
    //处理结果集合
    Map<String,Object> resultMap;

    public void setWorkQueue(Queue<Object> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    public Object handle(Object input){
        return input;
    }

    @Override
    public void run() {
        while(true){
            //获取子任务，poll取出队列中的任务，并从队列中删除
            //所有子任务都从同一个队列中获取任务
            Object input = workQueue.poll();
            if(input == null){
                break;
            }
            //处理子任务
            Object result = handle(input);
            //将处理结果放到结果集中
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程：" + Thread.currentThread().getName() + " >> " + input);
            resultMap.put(Integer.toString(input.hashCode()),result);
        }
    }
}

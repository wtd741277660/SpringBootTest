package com.springboot.test.SpringBootTest.masterWorker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {

    //任务队列
    Queue<Object> workQueue = new ConcurrentLinkedQueue<>();
    //worker进程集合
    Map<String,Thread> threadMap = new HashMap<>();
    //子任务结果集合
    Map<String,Object> resultMap = new ConcurrentHashMap<>();

    public Master(Worker worker, int threadNum){
        worker.setWorkQueue(workQueue);
        worker.setResultMap(resultMap);
        for(int i = 0;i < threadNum;i++){
            System.out.println("name+" + i);
            threadMap.put(Integer.toString(i),new Thread(worker,"name+" + i));
        }
    }

    //判断是否所有的子任务的进程都执行完毕
    public boolean isComplete(){
        for(Map.Entry<String,Thread> entry:threadMap.entrySet()){
            if(entry.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    //提交一个子任务
    public void submit(Object obj){
        workQueue.add(obj);
    }

    //获取子任务的执行结果集
    public Map<String,Object> getResultMap(){
        return resultMap;
    }

    public Queue<Object> getWorkQueue() {
        return workQueue;
    }

    public Map<String, Thread> getThreadMap() {
        return threadMap;
    }

    //开始执行所有的子任务
    public void execute(){
        for(Map.Entry<String,Thread> entry:threadMap.entrySet()){
            entry.getValue().start();
        }
    }

}

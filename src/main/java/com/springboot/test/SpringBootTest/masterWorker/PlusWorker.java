package com.springboot.test.SpringBootTest.masterWorker;

//worker的具体实现类
public class PlusWorker extends Worker{

    @Override
    public Object handle(Object input){
        Integer num = (Integer) input;
        return num;
    }
}

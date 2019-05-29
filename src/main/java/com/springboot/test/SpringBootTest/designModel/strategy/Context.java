package com.springboot.test.SpringBootTest.designModel.strategy;

/**
 * 策略模式对外的封装类
 */
public class Context {

    private IStrategy strategy = null;

    public Context(IStrategy strategy){
        this.strategy = strategy;
    }

    public void doAnyThing(){
        strategy.operate();
    }
}

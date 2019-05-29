package com.springboot.test.SpringBootTest.designModel.strategy;

public class ConcreteStrategy1 implements IStrategy{
    @Override
    public void operate() {
        System.out.println("strategy1 do something.....");
    }
}

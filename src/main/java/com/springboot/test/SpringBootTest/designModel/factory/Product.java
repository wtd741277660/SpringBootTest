package com.springboot.test.SpringBootTest.designModel.factory;

/**
 * 抽象产品类，利于工厂类的入参统一
 */
public abstract class Product {

    public String str = "1111";

    public void commonMethod(){
        System.out.println("Product commonMethod");
    }

    /**
     * 抽象方法，用于具体实现类中来实现
     */
    public abstract void method();
}

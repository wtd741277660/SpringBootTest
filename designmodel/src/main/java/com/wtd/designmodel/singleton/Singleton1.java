package com.wtd.designmodel.singleton;

/**
 * 饿汉模式创建单例模式
 */
public class Singleton1 {

    private static Singleton1 singleton = new Singleton1();

    private Singleton1(){}

    public static Singleton1 getInstance(){
        return singleton;
    }
}

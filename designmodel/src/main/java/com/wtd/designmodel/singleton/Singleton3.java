package com.wtd.designmodel.singleton;

/**
 * 静态内部类创建单例
 */
public class Singleton3 {

    private Singleton3(){}

    /**
     * 静态内部类，在Singleton3类在初始化时不会初始化该内部类，所以不会有饿汉模式的问题
     */
    private static class SingletonHolder{
        private static Singleton3 singleton = new Singleton3();
    }

    /**
     * 当调用该方法时，会初始化静态内部类，实例被初始化，并且实例是唯一的，是线程安全的
     * @return
     */
    public static Singleton3 getInstance(){
        return SingletonHolder.singleton;
    }

}

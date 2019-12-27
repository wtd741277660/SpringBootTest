package com.wtd.designmodel.singleton;

/**
 * 懒汉模式创建单例
 */
public class Singleton2 {

    private static volatile Singleton2 singleton = null;

    private Singleton2(){}

    /**
     * 多线程环境下可能创建多个实例
     * @return
     */
    private static Singleton2 getInstance(){
        if(singleton == null){
            singleton = new Singleton2();
        }
        return singleton;
    }

    /**
     * 采用双重校验锁，保证创建的实例唯一
     * @return
     */
    private static Singleton2 getInstance2(){
        if(singleton == null){
            synchronized (Singleton2.class){
                //再进行一次校验，防止在等待锁的过程中实例已经被其他线程创建
                if(singleton == null){
                    singleton = new Singleton2();
                }
            }
        }
        return singleton;
    }
}

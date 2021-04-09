package com.springboot.test.SpringBootTest.designModel.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 温度数据的观察者，温度数据主题发生变化时会通知此观察者
 */
public class TemperatureObserver2 implements Observer {

    /**
     * 主题通过调用update方法来通知观察者
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("观察者2收到数据：" + arg);
    }
}

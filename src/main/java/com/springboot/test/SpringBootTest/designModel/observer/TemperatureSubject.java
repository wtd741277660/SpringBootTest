package com.springboot.test.SpringBootTest.designModel.observer;

import java.util.Observable;

/**
 * 温度数据，为观察者模式中的主题，如果温度发生变化，将通知所对应的观察者
 */
public class TemperatureSubject extends Observable {

    //当前温度
    private float currentTemperature;

    /**
     * 当温度发生变化时，调用此方法，通知所有的观察者
     * @param temperature
     */
    public void temperatureChange(float temperature){
        //如果温度数据发生改变就通知观察者
        if(temperature != this.currentTemperature){
            //将数据通知给观察者
            System.out.println("通知观察者");
            //改变主题对象的状态，使其能够通知观察者
            setChanged();
            notifyObservers(temperature);
            this.currentTemperature = temperature;
        }
    }
}

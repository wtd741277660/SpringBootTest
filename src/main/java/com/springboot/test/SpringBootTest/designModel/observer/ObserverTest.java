package com.springboot.test.SpringBootTest.designModel.observer;

/**
 * 观察者模式测试类
 */
public class ObserverTest {

    public static void main(String[] args) {
        //定义温度数据主题对象
        TemperatureSubject ts = new TemperatureSubject();
        //定义温度数据观察者对象1和对象2
        TemperatureObserver1 to1 = new TemperatureObserver1();
        TemperatureObserver2 to2 = new TemperatureObserver2();
        //将观察者对象注册到主题对象中
        ts.addObserver(to1);
        ts.addObserver(to2);

        //模拟温度发生变化
        System.out.println("改变温度。。。");
        ts.temperatureChange(26.4f);
        ts.temperatureChange(26.4f);
        ts.temperatureChange(26.5f);
    }
}

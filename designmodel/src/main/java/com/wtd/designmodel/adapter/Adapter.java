package com.wtd.designmodel.adapter;

/**
 * 适配器类，目标接口的具体实现，当前类用于演示类适配器
 */
public class Adapter extends Adaptee implements Target {
    @Override
    public void adapterOperate() {
        System.out.println("适配器类方法");
        //操作适配者的方法
        //通过在这里对适配者的方法进行包装，使其能够适配用户的需求
        super.operate();
    }
}

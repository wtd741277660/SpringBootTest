package com.wtd.designmodel.adapter;

/**
 * 适配器类，目标接口的具体实现，当前类用于演示对象适配器
 */
public class AdapterForObj implements Target {

    /**
     * 此处将对象作为了一个成员变量来处理，也可以通过方法的入参来进行对象适配
     */
    private Adaptee adaptee;

    public AdapterForObj(Adaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public void adapterOperate() {
        System.out.println("适配器类方法");
        //操作适配者的方法
        //通过在这里对适配者的方法进行包装，使其能够适配用户的需求
        adaptee.operate();
    }
}

package com.wtd.designmodel.adapter;

public class AdapterTestForObj {

    public static void main(String[] args) {
        //通过适配器类对适配者类进行了包装，使其能够被客户端正常使用
        Adaptee adaptee = new Adaptee();
        Target target = new AdapterForObj(adaptee);
        target.adapterOperate();
    }
}

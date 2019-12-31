package com.wtd.designmodel.adapter;

public class AdapterTest {

    public static void main(String[] args) {
        //通过适配器类对适配者类进行了包装，使其能够被客户端正常使用
        Target target = new Adapter();
        target.adapterOperate();
    }
}

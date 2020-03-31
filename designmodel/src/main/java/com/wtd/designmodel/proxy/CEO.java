package com.wtd.designmodel.proxy;

/**
 * 静态代理委托类，处理具体的业务
 */
public class CEO implements Leader{
    @Override
    public void sign() {
        System.out.println("CEO签署文件");
    }
}

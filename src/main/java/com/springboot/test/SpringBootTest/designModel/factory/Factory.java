package com.springboot.test.SpringBootTest.designModel.factory;

/**
 * 抽象工厂类
 */
public abstract class Factory {

    /**
     * 创建一个具体的产品
     * @param c 入参是产品具体的产品类
     * @param <T>
     * @return
     */
    public abstract <T extends Product> T createProduct(Class<T> c);
}

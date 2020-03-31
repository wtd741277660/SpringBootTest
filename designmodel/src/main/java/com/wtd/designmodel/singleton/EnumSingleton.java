package com.wtd.designmodel.singleton;

/**
 * 枚举模式创建单例
 */
public enum EnumSingleton {

    INSTANCE;

    public EnumSingleton getInstance(){
        return INSTANCE;
    }
}

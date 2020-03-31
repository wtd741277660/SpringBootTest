package com.springboot.test.SpringBootTest.jvm.java8;

@FunctionalInterface
public interface MyFunctionalInterface {

    int aa(int a);

    boolean equals(Object o);

    default String defaultMethod(String str){
        return "default method:" + str;
    }

    static String staticMethod(String str){
        return "static method:" + str;
    }
}

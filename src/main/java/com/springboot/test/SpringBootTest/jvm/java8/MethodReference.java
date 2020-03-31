package com.springboot.test.SpringBootTest.jvm.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 测试java8中的方法引用
 */
public class MethodReference {

    public static void main(String[] args) {
        Car car = Car.create(Car::new);
        List<Car> list = Arrays.asList(car);

        list.forEach(Car::staticMethod);
        list.forEach(Car::methodWidthoutParameter);
    }


}
class Car {

    public static Car create(Supplier<Car> supplier){
        return supplier.get();
    }
    public static void staticMethod(Car car){
        System.out.println("static method");
    }

    public void methodWidthParameter(String parameter){
        System.out.println("method parameter:" + parameter);
    }

    public void methodWidthoutParameter(){
        System.out.println("method widthout parameter");
    }
}

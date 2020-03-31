package com.springboot.test.SpringBootTest.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FullGCTest {

    private static long counter = 0;

    private static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < availableProcessors; i++) {
            new Thread(() -> {
                while (true) {
                    list.add(Person.newInstance("cmy", 29, "天润城"));
                    list.add(Person.newInstance("wj", 28, "天润城2"));
                    counter += 2;
                    System.out.println("counter : " + counter);
                }
            }).start();
        }
    }
}

class Person {

    private String name;
    private Integer age;
    private String address;
    private String name2;
    private Integer age2;
    private String address2;

    private Person(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    static Object newInstance(String name, Integer age, String address) {
        Person person = new Person(name, age, address);
        person.name2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        person.age2 = 33333;
        person.address2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        return person;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", name2='" + name2 + '\'' +
                ", age2=" + age2 +
                ", address2='" + address2 + '\'' +
                '}';
    }
}

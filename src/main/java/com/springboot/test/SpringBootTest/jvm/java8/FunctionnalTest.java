package com.springboot.test.SpringBootTest.jvm.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionnalTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(23);
        list.add(20);
        list.add(40);

        List<Integer> result = list.stream().filter(i -> i > 10)
                                            .filter(i -> i < 40)
                                            .collect(Collectors.toList());

//        System.out.println(list.stream().map(Integer::floatValue).collect(Collectors.toList()));
//        System.out.println(list.stream().limit(2).collect(Collectors.toList()));
        System.out.println(list.stream().sorted(Integer::compareTo).collect(Collectors.toList()));
//        System.out.println(list.stream().max(Integer::compareTo).get());
//        System.out.println(list.stream().filter(i -> i> 10).findFirst().get());
//        System.out.println(list.stream().reduce((x,y) -> x + y).get());
//        System.out.println(list.stream().reduce(10,(x,y) -> x + y));
//        System.out.println(list.stream().reduce(Integer::sum).get());
//        System.out.println(list.stream().map(i -> i+"").collect(Collectors.joining(",")));
    }
}

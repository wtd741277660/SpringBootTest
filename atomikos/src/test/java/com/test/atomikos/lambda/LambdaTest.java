package com.test.atomikos.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;

public class LambdaTest {

    public static void main(String[] args) {

//        Runnable r1 = () -> System.out.println("runnable 1");
//        process(r1);
//
//        process(() -> System.out.println("哈哈哈哈"));

//        process1("11111",(String str) -> {
//            return str;
//        });

//        Predicate<String> notEmpty = (String s) -> !s.isEmpty();
//        List<String> list = Arrays.asList(new String[]{"1","2","","3"});
//        List<String> result = filter(list,notEmpty);
//        System.out.println(result);

        /*List<String> stringList = Arrays.asList("a","f","c","e");
        //第一种
//        stringList.sort((String s1,String s2) -> s1.compareToIgnoreCase(s2));
        //第二种
        stringList.sort((s1,s2) -> s1.compareToIgnoreCase(s2));
        //第三种
        stringList.sort(String::compareToIgnoreCase);
        System.out.println(stringList);*/

    }

    public static void process(Runnable r){
        r.run();
    }

    public static void process1(String str,StringProcessor sp){
        System.out.println("收到：" + sp.process(str));
    }

    public static <T>List<T> filter(List<T> list,Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T t:list){
            if(p.test(t)){
                result.add(t);
            }
        }
        return result;
    }
}

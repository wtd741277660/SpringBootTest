package com.springboot.test.SpringBootTest;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class CollectionTest {

    public static void main(String[] args) {
//        Set set = new LinkedHashSet();
        Set set = new HashSet();
        set.add("aa");
        set.add("订单");
        set.add("ss");
        set.add("ee");
        set.add("rr");
        set.add("啊啊");

//        System.out.println(set);
        for(Object o:set){
            System.out.println(o + " >> " + getHash(o));
        }

    }

    public static int getHash(Object o){
        int h = 0;
        return (o.hashCode()) ^ (h >>> 16);
    }
}

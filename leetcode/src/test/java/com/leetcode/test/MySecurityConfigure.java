package com.leetcode.test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MySecurityConfigure{

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String date = "2016-12-30";
        System.out.println(sdf.parse(date));
    }
}

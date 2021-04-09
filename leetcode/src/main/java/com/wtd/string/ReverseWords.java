package com.wtd.string;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 翻转字符串中的单词
 */
public class ReverseWords {

    public static void main(String[] args) {
        String str = "a good   example";
        String result = new ReverseWords().reverseWords(str);
        System.out.println(result);
    }

    public String reverseWords(String s) {
        List<String> list = new ArrayList<>();
        String[] array = s.split(" ");
        for(int i = array.length - 1;i >= 0;i--){
            if(!array[i].isEmpty()){
                list.add(array[i]);
            }
        }
        return "";
    }

    public String reverseWords1(String s) {
        StringBuilder sb = new StringBuilder();
        String[] array = s.split(" ");
        for(int i = array.length - 1;i >= 0;i--){
            if(!array[i].isEmpty()){
                if(sb.length()> 0){
                    sb.append(" " + array[i]);
                }else{
                    sb.append(array[i]);
                }
            }
        }
        return sb.toString();
    }
}

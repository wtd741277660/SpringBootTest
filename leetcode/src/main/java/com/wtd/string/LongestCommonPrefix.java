package com.wtd.string;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 获取字符串数组中的最长公共前缀
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        String result = new LongestCommonPrefix().longestCommonPrefix(strs);
        System.out.println(result);
    }

    public String longestCommonPrefix(String[] strs) {
        String result = "";
        if(strs.length == 0){
            return result;
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        String first = strs[0];
        boolean flag = true;
        int j = 1;
        int length = strs.length;
        String temp = "";
        for(int i = first.length();i >= 0;i--){
            temp = first.substring(0,i);
            for(;j < length;j++){
                if(!strs[j].startsWith(temp)){
                    flag = false;
                    break;
                }
            }
            if(!flag){
                flag = true;
            }else{
                result = temp;
                break;
            }
        }
        return result;
    }
}

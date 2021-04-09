package com.wtd.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并数组区间：[[1,3],[2,6],[8,10],[15,18]]合并为[[1,6],[8,10],[15,18]]
 */
public class MergeArray {

    public static void main(String[] args) {

    }

    public int[][] merge(int[][] intervals) {
        if(intervals.length < 2){
           return intervals;
        }
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals,Comparator.comparingInt(x -> x[0]));
        int[] temp = intervals[0];
        for(int i = 1,length = intervals.length;i < length;i++){
            if(intervals[i][0] <= temp[1]){
                if(intervals[i][1] > temp[1]){
                    temp[1] = intervals[i][1];
                }
            }else{
                list.add(Arrays.copyOf(temp,2));
                temp = intervals[i];
            }
            if(i == length - 1){
                list.add(temp);
            }
        }
        return list.toArray(new int[list.size()][2]);
    }
}

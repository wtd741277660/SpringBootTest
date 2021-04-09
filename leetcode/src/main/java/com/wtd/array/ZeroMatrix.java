package com.wtd.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 将矩阵中为0的数据所在的行与列都置为0
 */
public class ZeroMatrix {

    public static void main(String[] args) {
        int[][] data = new int[][]{
                {1,2,3,4},
                {5,6,7,0},
                {0,10,11,12},
                {13,14,15,16}
        };
        new ZeroMatrix().setZeroes(data);
    }

    public void setZeroes(int[][] matrix) {
        int length = matrix.length;
        if(length == 0){
            return;
        }
        int i,j;
        Set<Integer> zeroRows = new HashSet<>();
        Set<Integer> zeroColumns = new HashSet<>();
        for(i = 0;i < length;i++){
            for(j = 0;j < matrix[0].length;j++){
                if(matrix[i][j] == 0){
                    zeroRows.add(i);
                    zeroColumns.add(j);
                }
            }
        }
        for(Integer r:zeroRows){
            for(j = 0;j < matrix[0].length;j++){
                matrix[r][j] = 0;
            }
        }
        for(Integer c:zeroColumns){
            for(i = 0;i < length;i++){
                matrix[i][c] = 0;
            }
        }
        print(matrix);
    }

    public static void print(int temp [][]) {
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                System.out.print(temp[i][j] + "\t") ;
            }
            System.out.println() ;
        }
    }
}

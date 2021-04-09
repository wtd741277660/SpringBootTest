package com.wtd.array;

import java.util.Arrays;

/**
 * 根据对角线查询M*N的矩阵
 */
public class DiagonalOrderMatrix {

    public static void main(String[] args) {
        int[][] data = new int[][]{
                {1,2,3,4},
                {5,6,7,0},
                {0,10,11,12},
                {13,14,15,16}
        };
        new DiagonalOrderMatrix().findDiagonalOrder(data);
    }

    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length == 0){
            return new int[0];
        }
        int[] result = new int[matrix.length * matrix[0].length];
        int rowNum = matrix.length;
        int columnNum = matrix[0].length;
        int count = 0,i = 0,x = 0,y = 0;
        while(count <= rowNum + columnNum - 2){
            if(count %2 == 0){
                if(x > rowNum - 1){
                    x = rowNum - 1;
                }
                y = count - x;
                if(y > columnNum - 1 || y < 0 || x < 0){
                    count++;
                    x = 0;
                    y = count;
                }else{
                    result[i++] = matrix[x--][y];
                }
            }else{
                if(y > columnNum - 1){
                    y = columnNum - 1;
                }
                x = count - y;
                if(x > rowNum - 1 || x < 0 || y < 0){
                    count++;
                    x = count;
                    y = 0;
                }else{
                    result[i++] = matrix[x][y--];
                }
            }
        }
        return result;
    }
}

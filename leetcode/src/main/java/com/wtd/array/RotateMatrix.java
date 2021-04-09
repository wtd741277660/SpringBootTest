package com.wtd.array;

/**
 * 将二维数组顺时针旋转90度
 */
public class RotateMatrix {

    public static void main(String[] args) {
        int[][] data = new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };
        new RotateMatrix().rotate(data);
    }

    public void rotate(int[][] matrix) {
        int length = matrix.length;
        if(length <= 1){
            return ;
        }
        int temp = 0;
        int halfLength = length / 2;
        int i,j;
        for(i = 0;i < length;i++){
            for(j = 0;j < halfLength;j++){
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][length - 1 - j];
                matrix[i][length - 1 - j] = temp;
            }
        }
        print(matrix);
        for(i = 0;i < length;i++){
            for(j = 0;j < length - i;j++){
                temp = matrix[i][j];
                matrix[i][j] = matrix[length - 1 - j][length - 1 - i];
                matrix[length - 1 - j][length - 1 - i] = temp;
            }
        }
        System.out.println("---------------------");
        print(matrix);
    }

    public void rotate1(int[][] matrix) {
        int length = matrix.length;
        if(length <= 1){
            return ;
        }
        int[][] newMatrix = new int[length][length];
        for(int i = 0;i < length;i++){
            for(int j = 0;j < length;j++){
                newMatrix[i][j] = matrix[length - 1 - j][i];
            }
        }
        print(newMatrix);
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

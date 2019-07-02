package com.wtd.queue;

/**
 * 使用BFS判断岛屿的数量
 */
public class NumIslands {

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};
        int count = new NumIslands().numIslands(grid);
        System.out.println(count);
    }

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0
            || grid[0].length == 0){
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        for(int i = 0;i < rows;i++){
            for(int j = 0;j < cols;j++){
                //从最开始判断，遇到1就说明有岛屿
                if(grid[i][j] == '1'){
                    count++;
                    bfsSearch(grid,i,j,rows,cols);
                }
            }
        }
        return count;
    }

    public void bfsSearch(char[][] grid,int i,int j,int rows,int cols){
        if(i < 0 || i >= rows || j < 0 || j >= cols){
            return;
        }
        //遇到0说明岛屿在这个方向上到头
        if(grid[i][j] != '1'){
            return;
        }
        //将走过的岛屿路径置为0，防止其他判断走重
        grid[i][j] = '0';
        //当前位置还是岛屿，然后再以当前点向四周临近点判断是否为岛屿
        bfsSearch(grid,i+1,j,rows,cols);
        bfsSearch(grid,i-1,j,rows,cols);
        bfsSearch(grid,i,j+1,rows,cols);
        bfsSearch(grid,i,j-1,rows,cols);
    }
}

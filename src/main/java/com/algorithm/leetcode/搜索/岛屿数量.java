package com.algorithm.leetcode.搜索;

/**
 * 【200】
 * 输入：grid = [ ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"] ]
 * 输出：1
 *
 * @author rensong.pu
 * @date 2023/9/6
 */
public class 岛屿数量 {

    private boolean inArea(char[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }

    public void dfs(char[][] grid, int r, int c) {
        if (!inArea(grid, r, c)) {
            return;
        }
        if (grid[r][c] == '0') {
            return;
        }
        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
        dfs(grid, r + 1, c);
    }

    /**
     * 思路: dfs 的次数，就是岛屿的数量
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0 ) {
            return 0;
        }
        int result=0;
        for (int i = 0; i < grid.length; i++) {
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]=='1'){
                    result++;
                    dfs(grid,i,j);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        int i = new 岛屿数量().numIslands(grid);
        System.out.println("岛屿数量:" + i);
    }
}

package com.algorithm.leetcode.dp;
//一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
//
// 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
//
// 问总共有多少条不同的路径？
//
//
//
// 示例 1：
//
//
//输入：m = 3, n = 7
//输出：28
//
// 示例 2：
//
//
//输入：m = 3, n = 2
//输出：3
//解释：
//从左上角开始，总共有 3 条路径可以到达右下角。
//1. 向右 -> 向下 -> 向下
//2. 向下 -> 向下 -> 向右
//3. 向下 -> 向右 -> 向下
//
//
// 示例 3：
//
//
//输入：m = 7, n = 3
//输出：28
//
//
// 示例 4：
//
//
//输入：m = 3, n = 3
//输出：6
//
//
//
// 提示：
//
//
// 1 <= m, n <= 100
// 题目数据保证答案小于等于 2 * 109
//
// Related Topics 数学 动态规划 组合数学
// 👍 1122 👎 0

import java.util.Arrays;

/**
 * @author rensong.pu
 * @date 2021/9/23 11:20 星期四
 **/
public class 不同路径 {
    public static void main(String[] args) {
        System.out.print(new 不同路径().uniquePaths(3,2) + "\t"); //1916797311
//        System.out.print(new 不同路径().F(51,9) + "\t"); //1916797311

//        for (int i = 1; i <= 10; i++) {
//            for (int j = 1; j <= 10; j++) {
//                System.out.print(new 不同路径().uniquePaths(i, j) + "\t");
//            }
//            System.out.println();
//        }
    }

    /**
     * [1,1] = 1
     * [2,1] = 1
     * [1,2] = 1
     * ...
     * [1,n] = 1
     * [m,1] = 1
     * [m,n] = [m-1,n] + [m,n-1] (m,n>=2)
     * 优化：因为我们每次只需要 `dp[i-1][j],dp[i][j-1] `
     *
     * 所以我们只要记录这两个数
     */

    public int uniquePaths(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur,1);
        for (int i = 1; i < m;i++){
            for (int j = 1; j < n; j++){
                cur[j] += cur[j-1] ;
            }
        }
        return cur[n-1];
    }


    /**
     * 会超时!!!
     * @param m
     * @param n
     * @return
     */
    public int F(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        return F(m - 1, n) + F(m, n - 1);
    }
}

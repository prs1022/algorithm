package com.algorithm.leetcode.动态规划;

import java.util.Arrays;

/**
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * <p>
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * <p>
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 * <p>
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 * <p>
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 * @author rensong.pu
 * @date 2023/10/12
 */
public class 最长公共子序列 {
    /**
     * 动态规划的思路
     * 将text1,text2转为字符数组c1,c2 ， 假定c1作为长字符串，c2作为相对短字符串
     * dp(i)表示数组c1从下标0到i的长度内它和c2的公共子序列长度值
     * dp(0)=0,1 取决于 c1(0)与c2(k) for k in [0,len2) 是否相同，
     * 探讨前后转化关系， 已知dp(i) 怎么求 dp(i+1）
     * 当c1(i+1)==c2(k) for k in (i2,len2) dp(i+1) = max(dp(i+1),dp(i)+1)  这里i2记录的是最后一个公共字符在c2的位置
     * <p>
     * abcebced
     * 01233334
     * <p>
     * bced
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int M = text1.length();
        int N = text2.length();
        int[][] dp = new int[M + 1][N + 1];
        for (int i = 1; i <= M; ++i) {
            for (int j = 1; j <= N; ++j) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[M][N];
    }

    public static void main(String[] args) {
        最长公共子序列 instance = new 最长公共子序列();
        int i = instance.longestCommonSubsequence("abcbcba", "abcba");
        System.out.println(i);
    }
}

/**
 * yrkzavgdmdgtqpg
 * 111122222222222
 * 1111222222
 * <p>
 * ylqpejqbalahwr
 * |
 */


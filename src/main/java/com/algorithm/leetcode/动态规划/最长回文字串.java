package com.algorithm.leetcode.动态规划;

/**
 * @author rensong.pu
 * @date 2023/8/22
 */
public class 最长回文字串 {

    /**
     * S(i,j) 表示 第i个 到 第j个字符组成的字符串是回文串，
     * S(i-1,j+1) = S(i,j) && S(i-1)==S(j+1)
     * 边界: S(i,i) = true, S(i,i+2) = S(i)==S(i+2)
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        if (n == 1) {
            return s;
        }
        boolean[][] dp = new boolean[n][n];

        for (int k = 0; k < n; k++) {
            dp[k][k] = true;
        }

        int maxLen = 0;
        int start = 0;

        for (int L = 2; L <= n; L++) {
            for (int begin = 0; begin < n; begin++) {
                // 长度为L，起点是begin， 终点是i-begin-1
                int end = L + begin - 1;
                if (end >= n) {
                    break;
                }
//                System.out.println("begin:" + begin + ",end:" + end + ",len:" + L);
                if (chars[begin] == chars[end]) {
                    if (end - begin < 3) {
                        // 长度在2，3都可以直接判断
                        dp[begin][end] = true;
                        System.out.println("true!,begin:" + begin + ",end:" + end + ",len:" + L);
                    } else {
                        dp[begin][end] = dp[begin + 1][end - 1];
//                        System.out.println("向内传播，begin:" + begin + ",end:" + end + ",len:" + L);
                    }
                }
                if (dp[begin][end] && (end - begin + 1) > maxLen) {
                    maxLen = end - begin + 1;
                    start = begin;
//                    System.out.println("记录！begin:" + begin + ",end:" + end + ",len:" + L);
                }
            }
        }
        return s.substring(start, maxLen + start);
    }

    public static void main(String[] args) {
        String s = "abba";
        System.out.println(new 最长回文字串().longestPalindrome(s));
    }
}

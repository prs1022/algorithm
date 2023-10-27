package com.algorithm.leetcode.动态规划;

import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 示例 1：
 * <p>
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 * <p>
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 * @author rensong.pu
 * @date 2023/10/16
 */
public class 零钱兑换 {
    private int[] dp;

    public int coinChange(int[] coins, int amount) {
        dp = new int[amount+1];
        recursion(coins,amount);
        return dp[amount];
    }

    public int recursion(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (dp[amount] != 0) {
            return dp[amount];
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int subProblem = recursion(coins, amount - coins[i]);
            if (subProblem == -1) {
                continue;
            }
            res = Math.min(subProblem + 1, res);
        }
        if (res == Integer.MAX_VALUE) {
            res = -1;
        }
        dp[amount] = res;
        return res;
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        零钱兑换 instance = new 零钱兑换();
        int i = instance.coinChange(coins, 13);
        System.out.println("需要最少的总币数:" + i);
    }
}

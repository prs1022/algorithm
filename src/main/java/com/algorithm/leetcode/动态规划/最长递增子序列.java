package com.algorithm.leetcode.动态规划;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * <p>
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * @author rensong.pu
 * @date 2023/10/8
 */
public class 最长递增子序列 {

    /**
     * 动态规划的思路
     * 设定S(i)表示 下标从0到i的最长子序列的长度， 那么S(i+1) 的值 有两种情况
     * 1.当nums[i]<=nums[i+1] , S(i+1) = S(i)
     * 2.当nums[i+1]>nums[i], S(i+1) = S(i)+1
     * 初始值S(0) = 1
     * <p>
     * 有个问题， 这个按上述规则是反例：[2,500,100,400，3，4]
     * S(0) = 1, S(1)=2, S(2)=2, S(3)=3, 而正确的S(3) = 2
     * <p>
     * 问题出在 应该比较的是前面序列的最小值，最大值
     * <p>
     * 正确思路，dp(i) 表示以 nums[i]结尾的最大子序列长度，初始值dp(...)=1
     * nums[i]>nums[j] dp(i)=dp(j)+1
     * 每次遍历都更新 dp(i) =  max(dp(i),dp(j)+1) for j in [0,i)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public int lengthOfLIS2(int[] nums) {
        int[] tails = new int[nums.length];
        int res = 0;
        for (int num : nums) {
            int i = 0, j = res;
            while (i < j) {
                int m = (i + j) / 2;
                if (tails[m] < num) {
                    i = m + 1;
                } else {
                    j = m;
                }
            }
            tails[i] = num;
            if (res == j) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        最长递增子序列 instance = new 最长递增子序列();
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int ans = instance.lengthOfLIS(nums);
        System.out.println("最长递增子序列的长度:" + ans);
    }

}

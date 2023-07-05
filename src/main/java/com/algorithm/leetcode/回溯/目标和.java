package com.algorithm.leetcode.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rensong.pu
 * @date 2023/6/29
 */
public class 目标和 {

    /**
     * 思路:
     * 每个数作为树的一层，都有两种选择 "+/-" 进入到下一层，每进入一层，就算出当前的累和 Sum
     * 当到最后一层时， 判断Sum=target 时，count+1
     * 不相等，回溯，走另一种选择，直到递归结束，最终返回count
     *
     * @param nums
     * @param target
     */
    public void dfs(int[] nums, int target, int index, int sum, int result) {
        if (index == nums.length - 1 && sum == target) {
            result++;
            return;
        }
        for (int i = index; i < nums.length; i++) {
            sum += nums[i];
            dfs(nums, target, i + 1, sum, result);
            sum -= nums[i];
            sum -= nums[i];
            dfs(nums, target, i + 1, sum, result);
            sum += nums[i];
        }
    }

    public int res = 0;
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
        }
        if(sum%2==0&&target%2==1){
            return 0;
        }
        if(sum%2==1&&target%2==0){
            return 0;
        }

        target = (sum-target)/2;
        backTracking(nums,0,target);
        return res;
    }

    public void backTracking(int[] nums,int start,int target){
        if(target==0){
            res++;
        }
        if(target<0){
            return;
        }
        for (int i = start; i < nums.length; i++) {
            backTracking(nums,i+1,target-nums[i]);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 1, 1};
        int target = 3;
        int targetSumWays = new 目标和().findTargetSumWays(nums, target);
        System.out.println(targetSumWays);
    }
}

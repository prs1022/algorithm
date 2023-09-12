package com.algorithm.leetcode.排序;

import java.util.Random;

/**
 * 【912】排序数组
 *
 * @author rensong.pu
 * @date 2023/8/28
 */
public class 排序数组 {
    private static final Random random = new Random();
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        //递归退出条件
        if (left >= right) {
            return;
        }
        //随机选取法
        int RandomIndex = left + random.nextInt(right - left + 1);
        swap(nums, left, RandomIndex);
        int pivot = nums[left];
        int less = left;
        int more = right + 1;
        // 循环不变量：这里是左闭右闭区间
        // 小于nums[pivot]区间：[left + 1, less]
        // 等于nums[pivot]区间：[less + 1, i]
        // 大于nums[pivot]区间：[more, right]
        int i = left + 1;
        while (i < more) {
            if (nums[i] < pivot) {
                less++;
                swap(nums, i, less);
                i++;
            } else if (nums[i] == pivot) {
                i++;
            } else {
                //这里不i++很重要！因为我们无法确定从尾部换来的元素是否小于nums[pivot]
                more--;
                swap(nums, i, more);
            }
        }
        //less最后指向的一定是小于nums[pivot]的元素
        swap(nums, left, less);
        //同理more指向大于nums[pivot]的元素
        quickSort(nums, left, less - 1);
        quickSort(nums, more, right);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {

        int[] nums = new int[]{5, 1, 1, 2, 0, 0};
        int[] ints = new 排序数组().sortArray(nums);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}

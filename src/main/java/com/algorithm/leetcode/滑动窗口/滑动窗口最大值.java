package com.algorithm.leetcode.滑动窗口;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 【239】 滑动窗口最大值
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 *
 * @author rensong.pu
 * @date 2023/9/16
 */
public class 滑动窗口最大值 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        PriorityQueue<Integer> queue = new PriorityQueue<>(3, Comparator.reverseOrder());
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (queue.size() == 3) {
                result[index] = queue.peek();
                queue.poll();
            }
            queue.offer(nums[i]);
        }
        return result;
    }
}

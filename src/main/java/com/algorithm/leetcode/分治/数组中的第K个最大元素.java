package com.algorithm.leetcode.分治;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author rensong.pu
 * @date 2023/7/5
 */
public class 数组中的第K个最大元素 {

    /**
     * 思路:
     * 要找第K个最大的，那么一次遍历，将数字逐个收集到长度为k的列表里，如果列表满了，遇到的数字比当前列表里最小的还要小，则丢弃，
     * 否则加入，把最小的剔除。
     * 遍历完成后，列表里最小的数字 应该就是第K个最大的
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        // 这里我们需要小根堆
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            if(i<k){
                // 先放入k个进入队列，堆顶元素为
                queue.offer(nums[i]);
            }else{
                // 比较后续数组中的数与 堆顶的大小，比堆顶大，则更新堆，否则不处理
                if(nums[i]>queue.peek()){
                    queue.poll();
                    queue.offer(nums[i]);
                }
            }
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        int[] examples = {3, 2, 1, 5, 6, 4};
        System.out.println(new 数组中的第K个最大元素().findKthLargest(examples,2));
    }
}

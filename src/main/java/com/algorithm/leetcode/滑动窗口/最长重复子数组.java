package com.algorithm.leetcode.滑动窗口;

/**
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * 输出：3
 * 解释：长度最长的公共子数组是 [3,2,1] 。
 * 示例 2：
 * <p>
 * 输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * 输出：5
 * 提示：
 * <p>
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 *
 * @author rensong.pu
 * @date 2023/10/31
 */
public class 最长重复子数组 {

    /**
     * h[i]表示前i个字符串的hash值
     * h[i,i+k-1] 这个长度k区间的 hash值 = h[i+k-1]-h[i-1]
     * <p>
     * "aabb"
     * <p>
     * h[i] 前i个字符串的hash值, 'a'的对应数字是1，‘b’的对应是2
     * <p>
     * h[0] = 0
     * <p>
     * h[1] = 1*13^0
     * <p>
     * h[2] = 1*13^0+1*13^1
     * <p>
     * h[3] = 1*13^0+1*13^1+2*13^2
     * <p>
     * h[4] = 1*13^0+1*13^1+2*13^2+ 2*13^3
     * <p>
     * "aa " bb"
     * <p>
     * <p>
     * 子串"bb"的 h值怎么算？
     * h[3,4] = 2*13^0+2*13^1 实际
     * <p>
     * <p>
     * 推算
     * h[3,4] = h[4]-h[3-1] = h[4] - h[2] =  ( 1*13^0+1*13^1+2*13^2+ 2*13^3)  -  (1*13^0+1*13^1) = 2*13^2+ 2*13^3
     * 结果得除以 13^2 , 即13^k， k为子串的长度
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return 0;
        }
        if (nums2.length > nums1.length) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
        int[] h1 = new int[nums1.length + 1];
        int[] h2 = new int[nums2.length + 1];
        int P = 1313;
        int[] p = new int[nums1.length];
        p[0] = 1;
        int i = 1;
        for (; i < h1.length; i++) {
            h1[i] = nums1[i - 1] * p[i - 1] + h1[i - 1];
            if (i < h2.length) {
                h2[i] = nums2[i - 1] * p[i - 1] + h2[i - 1];
            }
            p[i] = p[i - 1] * P;
        }
        int m = 0, n = 0;
        int maxLen = 1;
        for (int j = 1; j + maxLen - 1 <= nums1.length; j++) {

        }

        return 0;
    }

    public static void main(String[] args) {
        最长重复子数组 instance = new 最长重复子数组();
        int[] nums1 = new int[]{1, 3, 4, 4, 5, 6};
        int[] nums2 = new int[]{7, 8, 3, 5, 6, 6};
        System.out.println(instance.findLength(nums1, nums2));
    }
}

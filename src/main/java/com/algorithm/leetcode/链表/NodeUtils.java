package com.algorithm.leetcode.链表;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 生成链表的工具类
 *
 * @author rensong.pu
 * @date 2023/9/27
 */
public class NodeUtils {

    /**
     * @param k
     * @param N
     * @param order >0 升序， <0 降序 ，=0 随机
     * @return
     */
    public static ListNode generateNode(int k, int N, int order) {
        List<Integer> nums = generateRandomNumbers(k, N, order);
        ListNode root = new ListNode(nums.get(0));
        ListNode p = root;
        ListNode next;
        for (int i = 1; i < nums.size(); i++) {
            next = new ListNode(nums.get(i));
            p.next = next;
            p = next;
        }
        return root;
    }

    private static List<Integer> generateRandomNumbers(int m, int N, int order) {
        List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();

        // 生成1到N之间的随机数
        int randomNumber = random.nextInt(N) + 1;
        for (int i = 0; i < m; i++) {
            if (order == 0) {
                randomNumber = random.nextInt(N) + 1;
                randomNumbers.add(randomNumber);
            } else if (order > 0) {
                if (i == 0) {
                    randomNumbers.add(randomNumber);
                } else {
                    randomNumbers.add(randomNumbers.get(i - 1) + 1);
                }
            } else {
                if (i == 0) {
                    randomNumbers.add(randomNumber);
                } else {
                    randomNumbers.add(randomNumbers.get(i - 1) - 1);
                }
            }
        }

        return randomNumbers;
    }

}

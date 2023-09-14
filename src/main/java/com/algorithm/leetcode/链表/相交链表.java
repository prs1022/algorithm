package com.algorithm.leetcode.链表;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 【160】
 *
 * @author rensong.pu
 * @date 2023/9/14
 */
public class 相交链表 {

    /**
     * 自定义评测：
     * <p>
     * 评测系统 的输入如下（你设计的程序 不适用 此输入）：
     * <p>
     * intersectVal - 相交的起始节点的值。如果不存在相交节点，这一值为 0
     * listA - 第一个链表
     * listB - 第二个链表
     * skipA - 在 listA 中（从头节点开始）跳到交叉节点的节点数
     * skipB - 在 listB 中（从头节点开始）跳到交叉节点的节点数
     *
     * @return
     */
    public ListNode[] generateListNode(int intersectVal, int m, int n, int skipA, int skipB) {
        ListNode[] nodes = new ListNode[2];
        if (intersectVal == 0) {
            nodes[0] = generateNode(m, null);
            nodes[1] = generateNode(n, null);
        } else {
            ListNode crossNode = new ListNode(intersectVal);
            ListNode listNodeA = generateNode(skipA, crossNode);
            ListNode listNodeB = generateNode(skipB, crossNode);
            crossNode.next = generateNode(3, null);
            nodes[0] = listNodeA;
            nodes[1] = listNodeB;
        }
        return nodes;
    }

    /**
     * 生成 长度为k 的随机链表
     *
     * @param k
     * @return
     */
    private ListNode generateNode(int k, ListNode appendNode) {
        List<Integer> nums = generateRandomNumbers(k);
        ListNode root = new ListNode(nums.get(0));
        ListNode p = root;
        ListNode next;
        for (int i = 1; i < nums.size(); i++) {
            next = new ListNode(nums.get(i));
            p.next = next;
            p = next;
        }
        if (appendNode != null) {
            p.next = appendNode;
        }
        return root;
    }

    public static List<Integer> generateRandomNumbers(int m) {
        List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < m; i++) {
            // 生成1到100之间的随机数
            int randomNumber = random.nextInt(100) + 1;
            randomNumbers.add(randomNumber);
        }

        return randomNumbers;
    }

    /**
     * 链表A:     48->69->15->1->18->92->59
     * <p>
     * 链表B:100->41->67->50->1->18->92->59
     * 思路： 双指针，从A，B的表头开始遍历，走到最后，换到另一方从头开始，假设有交点，则一定会相遇，否则同时走到A，B的末尾
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA;
        ListNode q = headB;
        while (p != null || q != null) {
            if (p == null) {
                p = headB;
            }
            if (q == null) {
                q = headA;
            }
            if (p == q) {
                return p;
            }
            q = q.next;
            p = p.next;
        }
        return null;
    }

    public static void main(String[] args) {
        相交链表 instance = new 相交链表();
        ListNode[] listNodes = instance.generateListNode(1, 0, 0, 1, 13);
//        ListNode[] listNodes = instance.generateListNode(0, 6, 5, 3, 4);
        System.out.println("链表A:" + listNodes[0]);
        System.out.println("链表B:" + listNodes[1]);
        System.out.println("交点:"+instance.getIntersectionNode(listNodes[0],listNodes[1]));
    }
}

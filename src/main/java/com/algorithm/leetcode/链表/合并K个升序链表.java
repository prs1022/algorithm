package com.algorithm.leetcode.链表;

import java.util.PriorityQueue;

/**
 * @author rensong.pu
 * @date 2023/9/27
 */
public class 合并K个升序链表 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (int i = 0; i < lists.length; i++) {
            if(null!=lists[i]){
                priorityQueue.offer(lists[i]);
            }
        }
        ListNode root = new ListNode(0);
        ListNode p = root;
        while (!priorityQueue.isEmpty()) {
            ListNode tmp = priorityQueue.poll();
            p.next = tmp;
            p = tmp;
            if (tmp.next != null) {
                priorityQueue.offer(tmp.next);
            }
        }
        return root.next;
    }


    public static void main(String[] args) {
        ListNode listNode = NodeUtils.generateNode(3, 10, 1);
        ListNode listNode2 = NodeUtils.generateNode(4, 10, 1);
        ListNode listNode3 = NodeUtils.generateNode(5, 10, 1);

        System.out.println(listNode);
        System.out.println(listNode2);
        System.out.println(listNode3);


        ListNode[] list = new ListNode[3];
        list[0] = listNode;
        list[1] = listNode2;
        list[2] = listNode3;
        System.out.println(new 合并K个升序链表().mergeKLists(list));
    }
}

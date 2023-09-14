package com.algorithm.leetcode.链表;

/**
 * @author rensong.pu
 * @date 2023/9/13
 */
public class 反转链表参考 {

    // 双指针
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 虚拟头节点
        ListNode dummyHead = new ListNode(-1);
        // 让虚拟节点指向原链表头部
        dummyHead.next = head;
        // 设置一个指针，指向以虚拟头节点为链表的头部位置
        ListNode pre = dummyHead;
        // 设置一个指针，指向原链表的头部位置
        ListNode cur = head;
        // 从虚拟头节点出发，pre走left -1步找到需要翻转的左区间
        // for循环结束后,pre的右节点是需要翻转的节点
        // for循环结束后,cur指向的就是需要翻转的节点
        for (int i = 0; i < left - 1; i++) {
            // pre不断的向右移动，直到走到翻转的左区间为止
            pre = pre.next;
            // cur不断的向右移动，找到了需要翻转的第一个节点
            cur = cur.next;
        }
        // 开始翻转节点
        for (int i = 0; i < right - left; i++) {
            // 设置临时变量保存当前需要翻转节点的后面的节点
            ListNode temp = cur.next;
            // 让temp和cur两个节点翻转一下
            cur.next = cur.next.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode b2 = new ListNode(2, a1);
        ListNode c3 = new ListNode(3, b2);
        ListNode d4 = new ListNode(4, c3);
//        System.out.println(d4);
//        new 反转链表().reverseListNode(d4,a1,a1);
//        System.out.println(b2);

        System.out.println(d4);
        ListNode listNode = new 反转链表参考().reverseBetween(d4, 1, 3);
        System.out.println(listNode);
    }
}


package com.algorithm.leetcode.链表;

/**
 * 【 92】
 * <p>
 * l     r
 * 1->2->3->4->5
 * <p>
 * 1->4->3->2->5
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * 示例 2：
 * <p>
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *
 * @author rensong.pu
 * @date 2023/9/12
 */
public class 反转链表 {

    private void reverseListNode(ListNode node, ListNode prev, ListNode endNode) {
        ListNode curr = node;
        while (curr != endNode) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode prev = new ListNode(501);
        prev.next = head;
        ListNode p = prev;
        ListNode l;
        int left2 = left;
        while (left > 0) {
            if (left == 1) {
                prev = p;
            }
            p = p.next;
            left--;
        }
        // p移动到了 left的位置上,记录当前位置 l
        l = p;

        while (right > left2) {
            p = p.next;
            right--;
        }


        // 反转l到r这段链表
        reverseListNode(l, p.next, p.next);

        if (prev.val != 501) {
            prev.next = p;
            return head;
        }
        return p;
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
        ListNode listNode = new 反转链表().reverseBetween(d4, 1, 3);
        System.out.println(listNode);
    }


}

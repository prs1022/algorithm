package com.algorithm.leetcode.链表;

import java.util.HashMap;
import java.util.Map;

/**
 * 【142】
 *
 * @author rensong.pu
 * @date 2023/9/14
 */
public class 环形链表 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        Map<ListNode, Integer> map = new HashMap<>();
        while (fast != null) {
            if (slow == fast) {
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null) {
            return null;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}

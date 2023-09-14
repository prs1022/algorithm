package com.algorithm.leetcode.链表;

/**
 * @author rensong.pu
 * @date 2023/9/13
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        ListNode curr = this;
        StringBuilder stringBuilder = new StringBuilder();
        while (curr != null) {
            stringBuilder.append(curr.val);
            curr = curr.next;
            if (curr != null) {
                stringBuilder.append("->");
            }
        }
        return stringBuilder.toString();
    }
}

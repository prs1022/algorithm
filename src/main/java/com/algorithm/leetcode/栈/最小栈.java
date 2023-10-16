package com.algorithm.leetcode.栈;

import java.util.*;

/**
 * @author rensong.pu
 * @date 2023/10/14
 */
public class 最小栈 {

    private Deque<Integer> deque;
    private Deque<Integer> dequeMin;

    public 最小栈() {
        deque = new LinkedList();
        dequeMin = new LinkedList();
        dequeMin.add(Integer.MAX_VALUE);
    }

    public void push(int val) {
        deque.add(val);
        dequeMin.add(Math.min(val,dequeMin.peekLast()));
    }

    public void pop() {
        deque.pollLast();
        dequeMin.pollLast();
    }

    public int top() {
        return deque.peekLast();
    }

    public int getMin() {
        return dequeMin.peekLast();
    }

    public static void main(String[] args) {
        最小栈 instance = new 最小栈();
        instance.push(-2);
        instance.push(0);
        instance.push(-3);
        System.out.println("getmin:" + instance.getMin());
        instance.pop();
        System.out.println("getmin:" + instance.getMin());
    }
}

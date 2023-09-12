package com.algorithm.leetcode.栈;

import java.util.Stack;

/**
 * 【20】给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * @author rensong.pu
 * @date 2023/9/7
 */
public class 有效的括号 {

    /**
     * 匹配两个括号是否是同一组
     *
     * @param a
     * @param b
     * @return
     */
    private boolean match(char a, char b) {
        return (a == '(' && b == ')') || (a == '{' && b == '}') || (a == '[' && b == ']');
    }

    public boolean isValid(String s) {
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(' || arr[i] == '{' || arr[i] == '[') {
                stack.push(arr[i]);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!match(stack.pop(),arr[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "()[]{}";
        System.out.println(new 有效的括号().isValid(s));
    }
}

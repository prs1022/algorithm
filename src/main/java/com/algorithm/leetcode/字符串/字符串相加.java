package com.algorithm.leetcode.字符串;

import java.util.Stack;

/**
 * 【415】
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 * <p>
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 * <p>
 * 示例 1：
 * <p>
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 * 示例 2：
 * <p>
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 * 示例 3：
 * <p>
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 *
 * @author rensong.pu
 * @date 2023/9/13
 */
public class 字符串相加 {
    public String addStrings(String num1, String num2) {
        // arr1 放长的字符串
        char[] arr1;
        char[] arr2;
        if (num1.length() > num2.length()) {
            arr1 = num1.toCharArray();
            arr2 = num2.toCharArray();
        } else {
            arr1 = num2.toCharArray();
            arr2 = num1.toCharArray();
        }
        int jw = 0;
        int i = arr1.length - 1;
        for (; i >= arr1.length-arr2.length; i--) {
            int n1 = arr1[i] - '0';
            int n2 = arr2[arr2.length-(arr1.length-i)] - '0';
            arr1[i] = (char) ((n1 + n2 + jw) % 10+'0');
            jw = (n1 + n2 + jw) / 10;
        }
        for (; i >= 0; i--) {
            int n1 = arr1[i] - '0';
            arr1[i] = (char) ((n1 + jw) % 10+'0');
            jw = (n1 + jw) / 10;
        }
        if (jw != 0) {
            return '1'+String.valueOf(arr1, 0, arr1.length);
        }


        return String.valueOf(arr1, 0, arr1.length);
    }

    public static void main(String[] args) {
        String num1 = "11";
        String num2 = "123";
        System.out.println(new 字符串相加().addStrings(num1, num2));
    }
}

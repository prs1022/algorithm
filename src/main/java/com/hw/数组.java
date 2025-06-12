package com.hw;

/**
 * @author rensong.pu
 * @date 2025/6/12
 */
public class 数组 {
    // 定义一个长度9的数组，初始化，其中相同的数字只有两个，只有一个数字是1个
    public static void main(String[] args) {
        int[] arr = new int[]{9,2,3,2,3,4,5,4,5};
        int res = arr[0];
        for(int i = 1; i < arr.length; i++){
            res = res ^ arr[i];
        }
        System.out.println(res);
    }
}

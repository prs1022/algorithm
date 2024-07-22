package com.algorithm.leetcode.dp;

/**
 * 斐波那契 记忆化搜索
 *
 * @author rensong.pu
 * @date 2021/9/8 15:39 星期三
 **/
public class Feibonachi {

    //避免多次计算，例如算F（3）的时候 和算F(4)的时候 都算了一次F(3),多次的F(2)以及F(1)
    public static int[] f = new int[46];

    static{
        f[0] = 1;
        f[1] = 1;
    }

    public static final int F(int n) {
        if (f[n] != 0) {
            return f[n];
        }
        f[n] =  F(n - 1) + F(n - 2);
        return f[n];
    }


    public static void main(String[] args) {
        for (int i = 0; i < 46; i++) {
            System.out.println(F(i));
        }
    }

}

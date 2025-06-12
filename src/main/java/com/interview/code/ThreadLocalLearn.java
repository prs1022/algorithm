package com.interview.code;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * 缺点: get是hash 线性探测，比较耗时，可以参考hashmap红黑树的优化
 * 弱引用，保证资源释放会产生entry key为null，调用get的时候会清理无用内存，key多的时候比较耗时；
 * @author rensong.pu
 * @date 2024/7/22
 */
public class ThreadLocalLearn {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("hello");

        // get的时候会清理无用内存，key多的时候比较耗时
        System.out.println(threadLocal.get());


        FastThreadLocal<String> fastThreadLocal = new FastThreadLocal<>();
        // 创建是放在数组里， O（1）复杂度
        fastThreadLocal.set("hello");
        System.out.println(fastThreadLocal.get());
    }
}

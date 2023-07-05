package com.algorithm.leetcode.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * n,k 输入，返回[1,n]这n个数里的k个数的所有组合
 *
 * @author rensong.pu
 * @date 2023/6/21
 */
public class 组合 {


    /**
     * 当前数可以要或者不要，要的话记录+1，path加入当前数
     * 退出条件是 记录=k ，并记录path结果
     *
     * @param n
     * @param k
     * @return
     */
    public void dfs(int n, int k, int curr, List<Integer> path,List<List<Integer>> result) {
        if (k==0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = curr; i <= n-k+1; i++) {
            path.add(i);
            System.out.println("回溯前:"+path);
            dfs(n, k-1, i + 1, path,result);
            path.remove(path.size() - 1);
            System.out.println("回溯后:"+path);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(n, k, 1, new ArrayList<>(),result);
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> combine = new 组合().combine(5, 3);
        for (int i = 0; i < combine.size(); i++) {
            for (int j = 0; j < combine.get(i).size(); j++) {
                System.out.print(combine.get(i).get(j).toString() + " ");
            }
            System.out.println();
        }
    }

}

package com.algorithm.leetcode.回溯;

import java.util.Scanner;

/**
 * @author rensong.pu
 * @date 2023/6/13
 */
public class 背包问题 {
    public int cw = 0;//表示当前船上装的货物重量
    public int bestw = 0;//能装载的最大货物重量
    public int[] bestx = new int[100]; //记录所装货物

    /**
     *
     * @param t t表示层级
     * @param w w表示货物的重量
     * @param W W表示船最大载重
     * @param n n输入的货物数
     * @param r r表示剩余货物的重量
     * @param x x[i]表示货物装不装
     */
    public void Backtrack(int t, int[] w, int W, int n, int r, int[] x) {//t表示层级，w表示货物的重量，W表示船最大载重，n输入的货物数
//r表示剩余货物的重量 ,x[i]表示货物装不装
        if (t > n - 1) {//已经搜索到末尾
            if (cw > bestw && cw <= W) {
                bestw = cw;//最大的重量记录
                for (int i = 0; i < n; i++) {
                    bestx[i] = x[i];
                }
            }
            return;

        } else {
            r = r - w[t];//已经访问该货物了。它不属于剩下的物品了
            if (cw < W) {//还能装下货物
                x[t] = 1;
                cw = cw + w[t];//装、左子树
                Backtrack(t + 1, w, W, n, r, x);
                cw = cw - w[t];//不装、右子树 cw之前添加了w，必须减回去
                x[t] = 0;
                r = r + w[t];
                Backtrack(t + 1, w, W, n, r, x);
            }
            if(cw==W) {
                bestw = W;
            }
            return;
        }
    }


    /**
     *
     * @param t t表示层级
     * @param w w表示货物的 重量
     * @param W W表示船最大载重
     * @param n n输入的货物数
     */
    public void Backtrack(int t,int[] w,int W,int n){//t表示层级，w表示货物的重量，W表示船最大载重，n输入的货物数
        if(t>n-1){//已经搜索到末尾
            if(cw>bestw&&cw<=W) {
                bestw=cw;//最大的重量记录
            }
            return;
        }else{
            if(cw<W){//还能装下货物
                cw=cw+w[t];//装、左子树
                Backtrack(t+1,w,W,n);
                cw=cw-w[t];//不装、右子树 cw之前添加了w，必须减回去
                Backtrack(t+1,w,W,n);
            }
            if(cw==W){
                bestw=cw;
            }//cw>W直接回溯
            return;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入W，货船总的最大载重");
        int W = scanner.nextInt();
        scanner = new Scanner(System.in);
        System.out.println("输入n，总共的货物数");
        int n = scanner.nextInt();
        System.out.println("接下来输入" + n + "个货物的各自重量,以空格分开");
        scanner = new Scanner(System.in);
        String data = scanner.nextLine();
        String[] s = data.split(" ");
        int[] arr = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        背包问题 背包问题 = new 背包问题();
        int[] x = new int[n];
//        背包问题.Backtrack(0,arr,W,n);
        背包问题.Backtrack(0,arr,W,n,W,x);

        System.out.println("最终结果为:" + 背包问题.bestw);
        for (int i = 0; i < n; i++) {
            System.out.print(背包问题.bestx[i] + "\t");
        }


    }
}

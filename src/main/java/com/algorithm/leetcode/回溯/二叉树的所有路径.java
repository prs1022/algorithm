package com.algorithm.leetcode.回溯;

import com.algorithm.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rensong.pu
 * @date 2023/6/16
 */
public class 二叉树的所有路径 {


    public List<String> result = new ArrayList();

    public void dfs(TreeNode node, List<String> path) {
        if (node != null) {
            // 记录沿途路径点
            path.add(String.valueOf(node.val));
        }

        if (node.left == null && node.right == null) {
            // 到达叶子节点 收集结果
            result.add(String.join("->", path));
            return;
        }
        if (node.left != null) {
            // 左子树不空进入递归
            dfs(node.left, path);
            // 如果从左子树出来要删除原来加入的路径点
            path.remove(path.size()-1);
        }
        if (node.right != null) {
            // 柚子树不空进入递归
            dfs(node.right, path);
            // 如果从柚子树出来要删除原来加入的路径点
            path.remove(path.size()-1);
        }
        return;
    }

    /**
     * 这种不需要有回溯的代码，更加精简
     * @param node
     * @param path
     */
    public void dfs(TreeNode node, String path) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            // 到达叶子节点 收集结果
            result.add(path+node.val);
            return;
        }

        dfs(node.left, path+node.val+"->");
        dfs(node.right, path+node.val+"->");
    }

    public List<String> binaryTreePaths(TreeNode root) {
        dfs(root, "");
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.val = 1;
        TreeNode left = new TreeNode();
        left.val = 2;
        root.left = left;

        TreeNode right = new TreeNode();
        right.val = 3;
        root.right = right;

        TreeNode left1 = new TreeNode();
        left1.val = 5;
        left.left = left1;

        List<String> strings = new 二叉树的所有路径().binaryTreePaths(root);
        for (int i = 0; i <strings.size(); i++) {
            System.out.println(strings.get(i));
        }
    }
}

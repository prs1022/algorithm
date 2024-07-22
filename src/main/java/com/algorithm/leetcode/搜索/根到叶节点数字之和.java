package com.algorithm.leetcode.搜索;

import com.algorithm.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rensong.pu
 * @date 2024/1/25
 */
public class 根到叶节点数字之和 {

    private int trace(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int tmp = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return tmp;
        }
        return trace(root.left, sum) + trace(root.right, sum);
    }


    public int sumNumbers(TreeNode root) {
        return trace(root,0);
    }
}

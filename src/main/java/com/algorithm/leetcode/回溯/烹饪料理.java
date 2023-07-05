package com.algorithm.leetcode.回溯;

/**
 * @author rensong.pu
 * @date 2023/6/16
 */
public class 烹饪料理 {

    public int bestYummy = -1;// 最大美味度

    /**
     * 考虑用深度优先搜索回溯,枚举所有可能性，
     * 每个元素(料理)可以考虑加入或者不加入
     * 代码逻辑为(详见注释)：
     * 1.遇到 id 为 n 结束循环, limit被用完更新最大值
     * 2.第一个分支为不考虑当前 id 的元素
     * 3.第二个分支为考虑当前 id 的元素,判断食材足够即可继续向下dfs
     *
     * @param materials
     */
    public void dfs(int[] materials, int[][] cookbooks, int[][] attribute, int[] usedMaterials, int limit, int yummy, int id) {
        if (limit <= 0) {
            bestYummy = Math.max(bestYummy, yummy);
        }

        if (id >= cookbooks.length) {
            return;
        }
        // 不加入，不做当前id的料理
        dfs(materials, cookbooks, attribute, usedMaterials, limit, yummy, id + 1);
        int[] clone = materials.clone();
        for (int i = 0; i < cookbooks[id].length; i++) {
            clone[i]-=cookbooks[id][i];
            if(clone[i]<0){
                return;
            }
        }
        dfs(clone, cookbooks, attribute, usedMaterials, limit - attribute[id][1], yummy + attribute[id][0], id + 1);
    }


    public int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        dfs(materials, cookbooks, attribute, new int[materials.length], limit, 0, 0);
        return bestYummy;
    }

    /**
     * 测试用例:[10,10,10,10,10]
     * [[1,1,1,1,1],[3,3,3,3,3],[10,10,10,10,10]]
     * [[5,5],[6,6],[10,10]]
     * 1
     * <p>
     * 测试用例:[3,2,4,1,2]
     * [[1,1,0,1,2],[2,1,4,0,0],[3,2,4,1,0]]
     * [[3,2],[2,4],[7,6]]
     * 5
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] materials = new int[]{3, 2, 4, 1, 2};
        int[][] cookbooks = new int[][]{
                new int[]{1, 1, 0, 1, 2},
                new int[]{2, 1, 4, 0, 0},
                new int[]{3, 2, 4, 1, 0}
        };
        int[][] attribute = new int[][]{
                new int[]{3, 2},
                new int[]{2, 4},
                new int[]{7, 6},
        };
        int limit = 5;
        int i = new 烹饪料理().perfectMenu(materials, cookbooks, attribute, limit);
        System.out.println(i);
    }
}

package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author rensong.pu
 * @date 2023/6/14
 */
public class Test {

    public static void main(String[] args) {
        List<String> stringList = new Solution().restoreIpAddresses("1111");
        for (int i = 0; i < stringList.size(); i++) {
            System.out.println(stringList.get(i));
        }
    }
}
class Solution {

    // 最终结果
    public List<String> result = new ArrayList<>();
    // 存放每个块的终止下标
    public List<Integer> indexList = Arrays.asList(new Integer[4]);

    /**
     * @param arr
     * @param group       第几组，总共四组，从1开始
     * @param totalLength 目前已经有的长度
     */
    public void trace(char[] arr, int group, int totalLength) {
        // 终止条件:四组的长度和等于arr的大小
        if ( group > 4) {
            if(totalLength == arr.length){
                //记录结果
                int begin = 0;
                List<String> tmp = new ArrayList<>();
                for (int i = 0; i < indexList.size(); i++) {
                    int end = indexList.get(i);
                    if(end==-1){
                        return;
                    }
                    tmp.add(String.valueOf(arr, begin, end - begin + 1));
                    begin = end + 1;
                }
                result.add(String.join(".",tmp));
            }
            return;
        }

        for (int i = 1; i <= 3; i++) {
            //判断是否符合条件
            if (validNum(arr, totalLength, totalLength + i - 1)) {
                //符合条件，加入到indexList
                // 记录当前组所到的终止下标
                indexList.set(group - 1, totalLength+i-1);
                // 更新已用长度
                totalLength += i;
                trace(arr, group + 1, totalLength);// 尝试下一组
                //回溯
                totalLength -= i;
                indexList.set(group - 1,-1);
            }
        }
    }

    public boolean validNum(char[] arr, int begin, int end) {
        if(end>=arr.length||end<0||begin<0||begin>=arr.length){
            return false;
        }
        // 0~255之间，不含前导0
        if (end - begin != 0 && arr[begin] == '0') {
            return false;
        }
        int sum=0;
        int jinwei = 1;
        for (int i = end; i >= begin; i--) {
            sum += (arr[i] - '0') * jinwei;
            jinwei *= 10;
        }
        if (sum >= 0 && sum <= 255) {
            return true;
        }
        return false;
    }

    public List<String> restoreIpAddresses(String s) {
        char[] arr = s.toCharArray();
        trace(arr,  1,0);
        return result;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


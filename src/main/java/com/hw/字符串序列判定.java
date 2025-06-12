package com.hw;

import java.util.Scanner;

/**输入两个字符串S和L，都只包含英文小写字母。S长度<=100，L长度<=500,000。判定S是否是L的有效字串。

 判定规则:

 S中的每个字符在L中都能找到(可以不连续)，且S在L中字符的前后顺序与S中顺序要保持一致。
 (例如，S="ace"是L="abcde”的一个子序列且有效字符是a、c、e，而”aec"不是有效子序列，且有效字符只有a、e)
 输入描述
 输入两个字符串S和L，都只包含英文小写字母。S长度<=100，L长度<=500,000 先输入S，再输入L，每个字符串占一行。

 输出描述
 S串最后一个有效字符在L中的位置。 (首位从0开始计算，无有效字符返回-1)

 作者：code5bug
 链接：https://www.nowcoder.com/discuss/646262925467865088
 来源：牛客网
 * @author rensong.pu
 * @date 2025/6/12
 */
public class 字符串序列判定 {
    public static void main(String[] args) {
        Scanner  scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String l = scanner.nextLine();
        System.out.println(judge(s, l));
    }

    /**
     * 遍历l，s逐个比较，相同，移动s，不同，移动l，记录l的相同下标
     * @param s
     * @param l
     * @return
     */
    private static int judge(String s, String l) {

        int j=0;
        for (int i = 0; i < l.length(); i++){
            if(s.charAt(j)==l.charAt(i)){
                j++;
                if(j==s.length()){
                    return i;
                }
            }
        }
        return -1;

    }


}

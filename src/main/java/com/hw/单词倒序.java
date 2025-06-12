package com.hw;

/**
 * 输入单行英文句子，里面包含英文字母，空格以及,.?
 * 三种标点符号，请将句子内每个单词进行倒序，并输出倒序后的语句
 * 输入描述
 * 输入字符串S，S 的长度1≤N≤100
 * 输出描述
 * 输出逆序后的字符串
 * @author rensong.pu
 * @date 2025/6/11
 */
public class 单词倒序 {

    public static void main(String[] args){
        System.out.println(reverseWordsInSentence("I am a student."));
    }

    /**
     * 将句子中每个单词进行倒序，保持标点符号和空格位置不变
     * @param sentence 输入句子
     * @return 处理后的句子
     */
    public static String reverseWordsInSentence(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return sentence;
        }

        char[] chars = sentence.toCharArray();
        int n = chars.length;
        int start = 0;

        for (int i = 0; i <= n; i++) {
            // 遇到非字母字符或到达字符串末尾时，处理当前单词
            if (i == n || !Character.isLetter(chars[i])) {
                if (start < i) {
                    // 倒序当前单词
                    reverseWord(chars, start, i - 1);
                }
                start = i + 1; // 下一个单词的开始位置
            }
        }

        return new String(chars);
    }

    /**
     * 倒序字符数组中指定范围的字符
     * @param chars 字符数组
     * @param start 开始位置（包含）
     * @param end 结束位置（包含）
     */
    private static void reverseWord(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }
}

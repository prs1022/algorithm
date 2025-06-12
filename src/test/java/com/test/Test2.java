package com.test;

/**
 * @author rensong.pu
 * @date 2023/8/22
 */
public class Test2 {

    public String multiplyOne(char[] c1, char c2) {
        if (c2 == '0') {
            return "0";
        }
        int i2 = c2 - '0';
        StringBuilder sb = new StringBuilder();
        int jinwei = 0;
        for (int i = c1.length - 1; i >= 0; i--) {
            int i1 = c1[i] - '0';
            sb.append((i1 * i2 + jinwei) % 10);
            jinwei = (i1 * i2 + jinwei) / 10;
        }
        if (jinwei != 0) {
            sb.append(jinwei);
        }
        return sb.reverse().toString();
    }

    public String addString(String s1, String s2, int wei) {
        if (s1.equals("0")) {
            return s2;
        }
        StringBuilder zero = new StringBuilder();
        while (wei-- > 0) {
            zero.append("0");
        }
        s1 = s1.concat(zero.toString());
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int jinwei = 0;
        StringBuilder sum = new StringBuilder();
        int i = c1.length - 1, j = c2.length - 1;
        for (; i >= 0 && j >= 0; i--, j--) {
            int i1 = c1[i] - '0' + (c2[j] - '0');
            sum.append((i1 + jinwei) % 10);
            jinwei = (i1 + jinwei) / 10;
        }
        while (i >= 0) {
            if (jinwei > 0) {
                sum.append((c1[i] - '0' + jinwei) % 10);
                jinwei = (c1[i] - '0' + jinwei) / 10;
            } else {
                sum.append(c1[i]);
            }
            i--;
        }
        while (j >= 0) {
            if (jinwei > 0) {
                sum.append((c2[j] - '0' + jinwei)%10);
                jinwei = (c2[j] - '0' + jinwei)/10;
            } else {
                sum.append(c2[j]);
            }
            j--;
        }
        if(jinwei>0){
            sum.append("1");
        }
        return sum.reverse().toString();
    }

    public String multiply(String num1, String num2) {
        char[] c1 = num1.toCharArray();
        char[] c2 = num2.toCharArray();
        String sum = "0";
        int wei = 0;
        for (int i = c2.length - 1; i >= 0; i--) {
            String i1 = multiplyOne(c1, c2[i]);
            sum = addString(i1, sum, wei);
            wei++;
        }
        return String.valueOf(sum);
    }

    public static void main(String[] args) {
        String multiply = new Test2().multiply("123456789", "987654321");
        System.out.println(multiply);
//        System.out.println(new Test2().addString("143", "457",0));
    }
}

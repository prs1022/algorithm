package com.java;

import java.util.Arrays;
import java.util.List;

/**
 * @author rensong.pu
 * @date 2024/10/14
 */
public class TestJava21 {
    public static void main(String[] args) {
        String[] strings = Arrays.asList("foo", "bar").toArray(new String[0]);
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i].toString());
        }
    }
}

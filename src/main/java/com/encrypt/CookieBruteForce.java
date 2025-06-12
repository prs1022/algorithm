package com.encrypt;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class CookieBruteForce {


    /**
     * id=s%3AVCoKGXIjuY3tIJHsOcM8eGZi4A-LQEwZ.U8RMZqENBkFlG8ZND0n8MWjyT9HQ%2F2g4ZlHqo5cAQYE
     *
     * {
     *     "id": "67d33577f96015ef2d781d38",
     *     "name": "",
     *     "pic": "avatar",
     *     "picbase": "/static/upload/member/",
     *     "phone": "1967789014@qq.com",
     *     "zip": "",
     *     "my_code": "CW2WC3",
     *     "showcaseDate": "2025-03-18T15:59:59.000Z",
     *     "strategyDate": "2025-03-18T15:59:59.000Z",
     *     "setupsDate": "2025-03-18T15:59:59.000Z",
     *     "memberLevel": 0,
     *     "webKey": "1d91dc4eed9c1db1774ee",
     *     "appKey": "b093382f6c96a6be",
     *     "deviceId": null
     * }
     */


    /**
     *
     * id=s%3A3UM-38A7-Q0Va1wRTtP1v2_UlZS4Dbt4.1dXt2o%2FEuhiafjL%2Bga3P1DnEVVRp7IRiHOhc26hCevw
     * {
     *     "id": "67d41e24f96015ef2d786b00",
     *     "name": "",
     *     "pic": "avatar",
     *     "picbase": "/static/upload/member/",
     *     "phone": "prs1022@126.com",
     *     "zip": "",
     *     "my_code": "Z7VFM2",
     *     "showcaseDate": "2025-03-22T15:59:59.000Z",
     *     "strategyDate": "2025-03-22T15:59:59.000Z",
     *     "setupsDate": "2025-03-22T15:59:59.000Z",
     *     "memberLevel": 0,
     *     "webKey": "2ff2f8c67dd07f988fc67",
     *     "appKey": "3793aaf6ab7777e4",
     *     "deviceId": null
     * }
     */




    private static final String TARGET_COOKIE = "s%3Af6I_yzPfWv6eWdQ6eHsFJJFlTMXfU3w7.OqcyceikwM1yAVUYEURs%2BfGtcoePwytcGmTK5pVhPtM";
    private static final String WEB_KEY = "5e95e28b808a7ee3bf8b8";
    private static final String APP_KEY = "849a8ec2edb6ae56";
    private static final String MY_CODE = "CW2WC3";
    private static final String USERNAME = "1967789014@qq.com";
    private static final String PASSWORD = "guan851320";

    public static void main(String[] args) {
        // 生成所有可能的组合
        List<String> combinations = generateCombinations();

        // 对每个组合进行哈希和比对
        for (String combination : combinations) {
            String hash = sha256(combination);
            String encodedHash = URLEncoder.encode(hash, StandardCharsets.UTF_8);
            String cookieValue = "s%3A" + encodedHash + "." + URLEncoder.encode(combination, StandardCharsets.UTF_8);

            if (cookieValue.equals(TARGET_COOKIE)) {
                System.out.println("找到匹配的组合: " + combination);
                System.out.println("生成的cookie值: " + cookieValue);
                return;
            }
        }

        System.out.println("未找到匹配的组合");
    }

    private static List<String> generateCombinations() {
        List<String> combinations = new ArrayList<>();

        // 尝试不同的组合方式
        combinations.add(WEB_KEY + APP_KEY + MY_CODE + USERNAME + PASSWORD);
        combinations.add(USERNAME + PASSWORD + WEB_KEY + APP_KEY + MY_CODE);
        combinations.add(PASSWORD + USERNAME + WEB_KEY + APP_KEY + MY_CODE);
        combinations.add(WEB_KEY + USERNAME + PASSWORD + APP_KEY + MY_CODE);
        combinations.add(APP_KEY + USERNAME + PASSWORD + WEB_KEY + MY_CODE);
        combinations.add(MY_CODE + USERNAME + PASSWORD + WEB_KEY + APP_KEY);
        combinations.add(USERNAME + WEB_KEY + APP_KEY + PASSWORD + MY_CODE);
        combinations.add(PASSWORD + WEB_KEY + APP_KEY + USERNAME + MY_CODE);
        combinations.add(WEB_KEY + APP_KEY + PASSWORD + USERNAME + MY_CODE);
        combinations.add(APP_KEY + WEB_KEY + USERNAME + PASSWORD + MY_CODE);
        combinations.add(USERNAME + PASSWORD + APP_KEY + WEB_KEY + MY_CODE);
        combinations.add(MY_CODE + PASSWORD + USERNAME + WEB_KEY + APP_KEY);
        combinations.add(WEB_KEY + MY_CODE + APP_KEY + USERNAME + PASSWORD);
        combinations.add(APP_KEY + MY_CODE + WEB_KEY + USERNAME + PASSWORD);
        combinations.add(USERNAME + MY_CODE + PASSWORD + WEB_KEY + APP_KEY);
        combinations.add(PASSWORD + MY_CODE + USERNAME + WEB_KEY + APP_KEY);
        combinations.add(WEB_KEY + APP_KEY + MY_CODE + PASSWORD + USERNAME);
        combinations.add(APP_KEY + WEB_KEY + MY_CODE + PASSWORD + USERNAME);
        combinations.add(USERNAME + MY_CODE + APP_KEY + PASSWORD + WEB_KEY);
        combinations.add(MY_CODE + USERNAME + APP_KEY + PASSWORD + WEB_KEY);

        // 添加更多可能的组合方式
        combinations.add(WEB_KEY + APP_KEY + MY_CODE);
        combinations.add(APP_KEY + WEB_KEY + MY_CODE);
        combinations.add(MY_CODE + WEB_KEY + APP_KEY);
        combinations.add(USERNAME + PASSWORD + MY_CODE);
        combinations.add(PASSWORD + USERNAME + MY_CODE);
        combinations.add(MY_CODE + USERNAME + PASSWORD);
        combinations.add(WEB_KEY + USERNAME + MY_CODE);
        combinations.add(USERNAME + WEB_KEY + MY_CODE);
        combinations.add(APP_KEY + USERNAME + MY_CODE);
        combinations.add(USERNAME + APP_KEY + MY_CODE);
        combinations.add(WEB_KEY + PASSWORD + MY_CODE);
        combinations.add(PASSWORD + WEB_KEY + MY_CODE);
        combinations.add(APP_KEY + PASSWORD + MY_CODE);
        combinations.add(PASSWORD + APP_KEY + MY_CODE);
        combinations.add(MY_CODE + PASSWORD + APP_KEY);
        combinations.add(MY_CODE + WEB_KEY + PASSWORD);
        combinations.add(WEB_KEY + MY_CODE + PASSWORD);
        combinations.add(APP_KEY + MY_CODE + PASSWORD);
        combinations.add(MY_CODE + APP_KEY + PASSWORD);

        return combinations;
    }

    private static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
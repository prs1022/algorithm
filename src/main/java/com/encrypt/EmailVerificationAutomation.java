package com.encrypt;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 邮箱接码自动注册示例
 */
public class EmailVerificationAutomation {

    private static final String EMAIL_API = "https://api.tempmail.lol"; // 小鸟接码平台API
    private static final String REGISTER_URL = "https://www.tqzb.me/register";
    private static final String API_ENDPOINT = "https://www.tqzb.me/api/member";
    private static String email = "";
    private static String password = "";

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/upuphone/Downloads/chromedriver-mac-arm64/chromedriver");

        // 生成随机邮箱
        generateCredentials();

        // 配置浏览器选项
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        try {
            automateRegistration(driver);
        } finally {
            driver.quit();
        }
    }

    private static void generateCredentials() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(EMAIL_API + "/generate"))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            email = response.body().split("\"")[3]; // 解析生成的邮箱
            System.out.println("生成的临时邮箱: " + email);
        } catch (Exception e) {
            System.err.println("Error generating email: " + e.getMessage());
            throw e;
        }

        // 生成密码
        password = generateRandomPassword(8);
    }

    private static String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private static void automateRegistration(WebDriver driver) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 访问注册页面
        driver.get(REGISTER_URL);

        // 填写邮箱
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        WebElement emailInput = driver.findElement(By.id("email"));
        humanizedType(emailInput, email);

        // 点击发送验证码
        WebElement sendCodeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'获取电邮验证码')]")));
        humanizedClick(driver, sendCodeBtn);

        // 获取验证码
        String verificationCode = pollForVerificationCode();
        System.out.println("获取到的验证码: " + verificationCode);

        // 填写注册信息
        WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        WebElement repasswordInput = driver.findElement(By.id("repassword"));
        WebElement codeInput = driver.findElement(By.id("code"));

        humanizedType(passwordInput, password);
        humanizedType(repasswordInput, password);
        humanizedType(codeInput, verificationCode);

        // 提交注册
        submitRegistration(driver);

        // 输出结果
        System.out.println("注册成功！账户信息：");
        System.out.println("用户名: " + email);
        System.out.println("密码: " + password);
    }

    private static void submitRegistration(WebDriver driver) {
        // 提交表单
        WebElement submitBtn = driver.findElement(By.xpath("//button[contains(text(), '注册')]"));
        humanizedClick(driver, submitBtn);
    }

    private static String pollForVerificationCode() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        Pattern codePattern = Pattern.compile("验证码[为是]?\\s*(\\d{6})");

        for (int i = 0; i < 15; i++) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(EMAIL_API + "/messages?email=" + email))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (!response.body().equals("[]")) {
                String messageContent = response.body();
                Matcher matcher = codePattern.matcher(messageContent);
                if (matcher.find()) {
                    return matcher.group(1);
                }
            }
            Thread.sleep(5000);
        }
        throw new RuntimeException("验证码获取超时");
    }

    // 模拟人类输入
    private static void humanizedType(WebElement element, String text) {
        for (char c : text.toCharArray()) {
            element.sendKeys(String.valueOf(c));
            try {
                Thread.sleep((long)(Math.random() * 100 + 50));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // 模拟人类点击
    private static void humanizedClick(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element)
                .pause(Duration.ofMillis(500))
                .click()
                .perform();
    }
}
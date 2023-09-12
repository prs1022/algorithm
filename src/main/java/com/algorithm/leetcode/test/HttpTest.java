package com.algorithm.leetcode.test;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * @author rensong.pu
 * @date 2023/8/31
 */
public class HttpTest {
    public static void main(String[] args) throws InterruptedException {
        int id = 1272667925;
        String url = "https://s13.cnzz.com/z_stat.php?id=#id#&web_id=#id#";

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (; id > 1000000000; id--) {

            int finalId = id;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
//                    System.out.println(finalId);
                    String resp = getHttp(url.replace("#id#", String.valueOf(finalId)));
                    if (resp != null && resp.trim().length() > 0) {
                        System.out.println("请求" + finalId + ",resp:" + resp);
                    }
                }
            });
            sleep(5);

        }

        executorService.awaitTermination(1, TimeUnit.HOURS);
        executorService.shutdown();

    }

    public static String getHttp(String url) {
//        System.out.println(url);
        //创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建httpGet对象，设置url地址：
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            //使用httpClient发起请求 获取 response
            response = httpClient.execute(httpGet);

            //解析响应
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf8");

                return content;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            try {
                //关闭httpClient
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                //关闭response
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return null;

    }
}

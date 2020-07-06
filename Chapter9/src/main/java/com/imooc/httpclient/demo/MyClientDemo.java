package com.imooc.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyClientDemo {

    @Test
    public void test() throws IOException {
        String result;
        // 创建Get请求
        HttpGet get = new HttpGet("http://wwww.baidu.com");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        ////根据字符集重新编码成正确的
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.print(result);
    }
}

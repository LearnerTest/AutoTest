package com.imooc.httpclient.cookie;

import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PostCookie {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;
    //获取URL
    @BeforeTest
    public void getUrl(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test_url");
    }
    @Test
    public void getCookie() throws IOException {
        String uri = bundle.getString("get_cookie");
        String getUrl = this.url + uri;
        HttpGet get = new HttpGet(getUrl);
        //HttpClient无法获取cookie信息;HttpClient client = new DefaultHttpClient();
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        //以下代码是获取cookie信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for(Cookie cookie:cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name:" + name+"\n cookie value:"+value);
        }
    }
    @Test(dependsOnMethods = "getCookie")
    public void getMethodByCookie() throws IOException {
        String uri = bundle.getString("post_whith_cookie");
        String testUrl = this.url + uri;
        //声明一个Client对象，用来进行方法的执行
        DefaultHttpClient  client = new DefaultHttpClient();
        //声明一个方法，这个方法就是post方法
        HttpPost post = new HttpPost(testUrl);
        //添加参数
        JSONObject param = new JSONObject();
        param.put("name","zhangsan");
        param.put("age","19");
        //设置请求头信息，设置header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse response = client.execute(post);
        //获取响应结果
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //处理结果，就是判断返回结果是否符合预期
        //将返回的响应结果字符串转化成为json对象
        JSONObject resultJson = new JSONObject(result);
        //获取到结果值
        String sucess = (String) resultJson.get("name");
        String code = (String) resultJson.get("kfg");
        //具体的判断返回结果的值
        Assert.assertEquals(sucess,"wang");
        Assert.assertEquals(code,"1");
    }
}

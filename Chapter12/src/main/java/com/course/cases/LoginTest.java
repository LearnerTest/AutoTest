package com.course.cases;

//import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.interfaceName;
import com.course.model.loginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    @BeforeTest(groups = "loginTrue", description = "测试准备工作,获取HttpClient对象")
    public void beforeTest() {
        TestConfig.addUserUrl = ConfigFile.getUrl(interfaceName.ADDUSER);
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(interfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(interfaceName.GETUSERLIST);
        TestConfig.loginUrl = ConfigFile.getUrl(interfaceName.LOGIN);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(interfaceName.UPDATEISERINFO);
        TestConfig.defaultHttpClient = new DefaultHttpClient();
    }

    @Test(groups = "loginTrue", description = "用户成功登陆接口")
    public void loginTrue() throws IOException {
        //从数据库读取参数
        SqlSession session = DatabseUtil.getSqlSession();
        loginCase logincase = session.selectOne("loginCase", 1);
        //打印参数，打印url
        System.out.println(logincase.toString());
        System.out.println(TestConfig.loginUrl);
        //获取期望值，从数据库读出
        String expected = logincase.getExpected();
//        System.out.println(expected);
        //调用getresult方法获取result值
        String result = getResult(logincase);
        System.out.println(result);
        //assert判断结果和期望是否一致
        Assert.assertEquals(expected, result);
    }

    @Test(description = "登录失败接口")
    public void loginfalse() throws IOException {
        SqlSession session = DatabseUtil.getSqlSession();
        loginCase logincase = session.selectOne("loginCase", 2);
        System.out.println(logincase.toString());
        System.out.println(TestConfig.loginUrl);
        String result = getResult(logincase);
//        Assert.assertEquals(logincase.getExpected(), result);
        Assert.assertNotEquals(logincase.getExpected(), result);
    }

    public static String getResult(loginCase logincase) throws IOException {
        //下边的代码为写完接口的测试代码，声明一个post方法
        HttpPost post = new HttpPost(TestConfig.loginUrl);
//        HttpGet get = new HttpGet(TestConfig.loginUrl);
        //添加参数
        JSONObject param = new JSONObject();
        //需要和mock的接口参数名一致
        param.put("name", logincase.getUserName());
        param.put("password", logincase.getPassword());
        //设置请求头信息，设置header
        post.setHeader("content-type", "application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        //以下代码是获取cookie信息
        TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
        return result;
    }
}

package com.course.cases;

//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.Users;
import com.course.model.getUserInfoCase;
import com.course.utils.DatabseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetUserInfoTest {

    @Test(dependsOnGroups="loginTrue",description = "获取userId为1的用户信息")
    public void getUserInfo() throws IOException, InterruptedException {
        SqlSession session = DatabseUtil.getSqlSession();
        getUserInfoCase getUserInfo = session.selectOne("getUserInfoCase",1);
//        System.out.println(getUserInfo.toString());
//        System.out.println(TestConfig.getUserInfoUrl);
        JSONArray resultJson = getJsonResult(getUserInfo);
        Thread.sleep(2000);
        Users user = session.selectOne(getUserInfo.getExpected(),getUserInfo);
        System.out.println("自己查数据库信息："+user.toString());
//        List userList = new ArrayList();
//        userList.add(user);
//        JSONArray jsonArray = new JSONArray(userList);
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.add(user);
//        System.out.println("查询获取用户信息："+jsonArray.toString());
//        System.out.println("接口调用获取用户信息："+resultJson.toString());
        List userList = new ArrayList();
        userList.add(user);
        JSONArray jsonArray = new JSONArray(userList);
//        JSONArray jsonArray1 = new JSONArray(resultJson.getString(0));
        Assert.assertEquals(jsonArray,resultJson.toString());
    }

    private JSONArray getJsonResult(getUserInfoCase userinfo) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        //添加参数,JSONObject不能选错了
        JSONObject param = new JSONObject();
        param.put("userId",userinfo.getUserId());
        //设置请求头信息，设置header
        post.setHeader("content-type", "application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("调用接口的result："+result);
        //用Arrays.asList将数组转成list
        List resultlist = Arrays.asList(result);
        //将返回的响应结果字符串转化成为json对象
        JSONArray array = new JSONArray(resultlist);
        System.out.println("结果返回:"+ array.toString());
        return array;
    }
}

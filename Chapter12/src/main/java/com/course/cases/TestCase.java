package com.course.cases;


//import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.Users;
import com.course.model.getUserListCase;
import com.course.utils.DatabseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class TestCase {

    @Test(dependsOnGroups = "loginTrue",description = "测试方法")
    public void getUserInfo() throws IOException {
        SqlSession session = DatabseUtil.getSqlSession();
        getUserListCase getUserList = session.selectOne("getUserListCase",1);
        JSONArray resultJson = getResultJson(getUserList);
        List<Users> users = session.selectList(getUserList.getExpected(),getUserList);
        for(Users u:users){
            System.out.println(u.toString());
        }
        JSONArray userListJson = new JSONArray(users);
        Assert.assertEquals(resultJson.length(),userListJson.length());
        for(int i = 0 ;i<resultJson.length();i++){
            JSONObject expect = (JSONObject) resultJson.get(i);
            JSONObject actual = (JSONObject) userListJson.get(i);
            Assert.assertEquals(expect.toString(),actual.toString());
        }
    }

    private JSONArray getResultJson(getUserListCase getUserList) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("userName",getUserList.getUserName());
        param.put("sex",getUserList.getSex());
        param.put("age",getUserList.getAge());
        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        JSONArray jsonArray = new JSONArray(result);
        return  jsonArray;
    }

}
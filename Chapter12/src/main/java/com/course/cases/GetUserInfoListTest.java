package com.course.cases;

//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.Users;
import com.course.model.getUserListCase;
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

public class GetUserInfoListTest {

    @Test(dependsOnGroups = "loginTrue",description = "获取性别为男的用户信息")
    public void getUserListInfo() throws IOException, InterruptedException {
        SqlSession session = DatabseUtil.getSqlSession();
        getUserListCase getUserList = session.selectOne("getUserListCase",1);
        JSONArray resultJson = getJsonResult(getUserList);
//        Thread.sleep(2000);
        List<Users> userList = session.selectList(getUserList.getExpected(),"0");
// for(Users u:userList){
//            System.out.println("获取的user："+u.toString());
//        }
//        JSONArray userListJson = JSONArray.parseArray(userList.toString());
        JSONObject expect1 =  resultJson.getJSONObject(0);
        Assert.assertEquals(expect1.get("userName"),"zhangsan");
        for(int i = 0 ;i<resultJson.length();i++){
            JSONObject expect =  resultJson.getJSONObject(i);
            System.out.println(expect.get("userName"));
        }
    }

    private JSONArray getJsonResult(getUserListCase getuserlist) throws IOException {
        HttpPost post = new HttpPost(TestConfig.getUserListUrl);
        System.out.println(TestConfig.getUserListUrl);
        JSONObject param = new JSONObject();
        param.put("sex",getuserlist.getSex());
//        param.put("userName",getuserlist.getUserName());
//        param.put("age",getuserlist.getAge());
        //设置header信息
        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(param.toString());
        post.setEntity(entity);
        //设置cookie
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        //执行post方法将结果赋值给result
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
//        List resultlist = Arrays.asList(result);
        JSONArray jsonArray = new JSONArray(result);
        return jsonArray;
    }
}

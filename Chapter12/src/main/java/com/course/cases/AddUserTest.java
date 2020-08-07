package com.course.cases;

import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.addUserCase;
import com.course.utils.DatabseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {

    @Test(dependsOnGroups = "loginTrue",description = "增加用户")
    public void addUser() throws IOException {
        SqlSession session = DatabseUtil.getSqlSession();
        addUserCase addUser = session.selectOne("addUserCase",1);
        String result = getResult(addUser);
        System.out.println(result.toString());

    }

    private String getResult(addUserCase adduser) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        System.out.println(TestConfig.addUserUrl);
        JSONObject param= new JSONObject();
        param.put("userName",adduser.getUserName());
        param.put("password",adduser.getPassword());
        param.put("sex",adduser.getSex());
        param.put("age",adduser.getAge());
        param.put("permission",adduser.getPermission());
        param.put("isDelete",adduser.getIsDelete());
        //设置请求头信息，设置header
        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        return result;
    }
}

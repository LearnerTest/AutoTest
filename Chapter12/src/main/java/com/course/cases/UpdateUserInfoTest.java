package com.course.cases;

import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.updateUserInfoCase;
import com.course.utils.DatabseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserInfoTest {
    @Test(dependsOnGroups = "loginTrue",description = "更新用户信息")
    public void updateUserInfo() throws IOException, InterruptedException {
        SqlSession session = DatabseUtil.getSqlSession();
        updateUserInfoCase updateuserinfo=session.selectOne("updateUserInfoCase",1);
        String result = getResult(updateuserinfo);
        System.out.println(result);
    }
    @Test(dependsOnGroups = "loginTrue",description = "删除用户")
    public void deleteUser() throws IOException, InterruptedException {
        SqlSession session = DatabseUtil.getSqlSession();
        updateUserInfoCase deleteuser = session.selectOne("updateUserInfoCase",2);
        String result = getResult(deleteuser);
        System.out.println(result);
    }

    private String getResult(updateUserInfoCase updateuserinfo) throws IOException, InterruptedException {
        HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
        System.out.println(TestConfig.updateUserInfoUrl);
        JSONObject param = new JSONObject();
//        param.put("id",updateuserinfo.getId());
        param.put("userName",updateuserinfo.getUserName());
//        param.put("age",updateuserinfo.getAge());
//        param.put("sex",updateuserinfo.getSex());
//        param.put("permission",updateuserinfo.getPermission());
        param.put("userId",updateuserinfo.getUserId());
//        param.put("isDelete",updateuserinfo.getIsDelete());
        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        String result;
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        Thread.sleep(1000);
//        return Integer.parseInt(result);
        return  result;
    }

}

package com.course;

import com.course.model.interfaceName;
import com.course.model.loginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabseUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {
//        String url ;
//        url = ConfigFile.getUrl(interfaceName.ADDUSER);
//        System.out.println(url);

        SqlSession s =DatabseUtil.getSqlSession();
        loginCase logincase= s.selectOne("loginCase",1);
        System.out.println(logincase.getExpected());
    }
}

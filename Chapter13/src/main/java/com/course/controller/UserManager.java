package com.course.controller;


import com.course.model.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Log4j
@RestController
@Api(value = "v1",description = "用户管理系统")
@RequestMapping("v1")
public class UserManager {

    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value = "登陆借口",httpMethod = "POST")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public boolean login(HttpServletResponse response, @RequestBody Users user){
        int result = template.selectOne("login",user);
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        log.info("查询到的结果是："+result);
        if(result==1){
            log.info("登陆的用户是："+user.getUserName());
            return true;
        }
        return false;
    }

    @ApiOperation(value = "添加用户接口",httpMethod = "POST")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request,@RequestBody Users user){
        Boolean x = verifyCookies(request);
        int result =0;
        if (x != null){
            result = template.insert("addUser",user);
        }
        if (result > 0){
            log.info("添加用户数量："+result);
            return true;
        }
        return false;
    }

    @ApiOperation(value = "获取用户信息接口",httpMethod = "POST")
    @RequestMapping(value = "/getUserInfo")
    public List<Users> getUserInfo(HttpServletRequest request,@RequestBody Users user){
        Boolean x = verifyCookies(request);
        if(x==true){
            List<Users> users = template.selectList("getUserInfo",user);
            log.info("获取到的用户数量是："+ ((List) users).size());
            return users;
        }
        return null;
    }

    @ApiOperation(value = "更新/删除用户信息接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request,@RequestBody Users user){
        Boolean x = verifyCookies(request);
        int i =0;
        if(x==true){
            i =template.update("updateUserInfo",user);
        }
        log.info("更新用户数目："+i);
        return i;
    }

    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            log.info("cookie为空");
            return false;
        }
        for(Cookie cookie:cookies){
            if (cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                log.info("cookie通过验证");
                return true;
            }
        }return false;
    }
}

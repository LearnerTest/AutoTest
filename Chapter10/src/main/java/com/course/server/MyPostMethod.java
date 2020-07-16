package com.course.server;

import com.sun.deploy.net.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/", description = "这是我的全部post请求")
@RequestMapping("v1")
public class MyPostMethod {

    private static Cookie cookie, cookie1;

    @RequestMapping(value = "/get_cookie", method = RequestMethod.POST)
    @ApiOperation(value = "获取post登录接口的cookie", httpMethod = "POST")
    public String getCookie(HttpServletResponse response,
                            //定义接口是否必传，required等于true必传
                            @RequestParam(value = "username", required = true) String username,
                            @RequestParam(value = "password", required = true) String password) {
        if (username.equals("wanger") && password.equals("123456")) {
            cookie = new Cookie("login", "false");
            cookie1 = new Cookie("login", "true");
            response.addCookie(cookie);
            response.addCookie(cookie1);
            return "登录访问cookie成功";
        } else
            return "你的用户名或密码错误";
    }
}

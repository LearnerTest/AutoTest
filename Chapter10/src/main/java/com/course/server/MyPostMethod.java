package com.course.server;

import com.course.beaan.User;
import com.sun.deploy.net.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
//            cookie1 = new Cookie("login", "true");
            response.addCookie(cookie);
//            response.addCookie(cookie1);
            return "登录访问cookie成功";
        } else
            return "你的用户名或密码错误";
    }
    @RequestMapping(value = "/get_list",method = RequestMethod.POST)
    @ApiOperation(value = "获取user列表数据",httpMethod = "POST")
    public String getUserList(HttpServletRequest request){
        User user = new User();
        //获取cookies
        Cookie[] cookie = request.getCookies();
        for(Cookie c:cookie){
            //验证cookie是否合法
            if(c.getName().equals("login")&&c.getValue().equals("false")){
                user.setName("赵鹏");
                user.setAge("18");
                user.setSex("man");
                return user.toString();
            }
        }
        return "参数不合法";
    }
}

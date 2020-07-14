package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {

    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "这是获取cookie的接口",httpMethod="GET")
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest 装请求信息的类
        //HttpServerletResponse  装响应信息的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "恭喜访问cookie成功";
    }
    /**
     * 要求客户端携带cookies访问
     * 这是一个需要携带cookies信息才能访问的get请求
     */
    @ApiOperation(value = "这是通过cookie访问接口",httpMethod = "GET")
    @RequestMapping(value = "/get_whith_cookies",method = RequestMethod.GET)
    public String getWhithCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "必须携带cookies";
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "这是一个需要携带cookies信息才能访问的get请求!";
            }
        }
        return "你必须携带cookies信息来";
    }
    /**
     * 开发一个需要携带参数才能访问的get请求。
     * 第一种实现方式 url: key=value&key=value
     * 我们来模拟获取商品列表
     */
    @ApiOperation(value = "这是第一种参数接口",httpMethod = "GET")
    @RequestMapping(value = "get_whith_param",method = RequestMethod.GET)
    public Map<String,Integer> getList(@RequestParam Integer start,@RequestParam Integer end){
            Map<String,Integer> mylist = new HashMap<>();
            mylist.put("小浣熊",1);
            mylist.put("米老鼠",200);
            mylist.put("nike",300);
            return mylist;

    }
    /**
     * 第二种需要携带参数访问的get请求
     * url:ip:port/get/with/param/10/20
     */
    @ApiOperation(value = "这是第二种参数接口",httpMethod = "GET")
    @RequestMapping(value = "get/whith/param/{start}/{end}")
    //通过 @PathVariable 可以将URL中占位符参数{xxx}绑定到处理器类的方法形参中@PathVariable(“xxx“)
    public Map<String,Integer> getMyList(@PathVariable Integer start,@PathVariable Integer end){
        Map<String,Integer> getmylist = new HashMap<>();
        getmylist.put("小浣熊",1);
        getmylist.put("米老鼠",200);
        getmylist.put("nike",300);
        return getmylist;
    }


    //带参数、cookie的get请求
    @ApiOperation(value = "这是通过cookie访问接口",httpMethod = "GET")
    @RequestMapping(value = "/get_whith_para",method = RequestMethod.GET)
    public String getWhithparam(HttpServletRequest request,@RequestParam Integer start,@RequestParam Integer end){
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            return "必须携带cookies";
        }
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("login")&&cookie.getValue().equals("true")){
                return "这是一个需要携带cookies信息才能访问的get请求!";
            }
        }
        return "你必须携带cookies信息来";
    }
}

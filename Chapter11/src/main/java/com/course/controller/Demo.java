package com.course.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "v1",description = "这是第一个版本demmo")
@RequestMapping("v1")
public class Demo {

    @Autowired
    private SqlSessionTemplate template;
    @RequestMapping(value = "/getdemo",method = RequestMethod.GET)
    @ApiOperation(value = "第一个demo",httpMethod = "GET")
    public int getUserCount(){
        return template.selectOne("getUserCount");
    }

}

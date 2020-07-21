package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.testng.annotations.Parameters;

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

    @RequestMapping(value = "/addName",method = RequestMethod.POST)
    @ApiOperation(value ="增加用户",httpMethod = "POST")
    public int addName(@RequestBody User user){
//        int result = template.insert("addUser",user);
//        return result;
        return template.insert("addUser",user);
    }

    @RequestMapping(value = "/udateName",method = RequestMethod.POST)
    @ApiOperation(value = "更新用户",httpMethod = "POST")
    public int updateName(@RequestBody User user){
        return template.update("updateUser",user);
    }

    @RequestMapping(value = "/deleteValue",method = RequestMethod.GET)
    @ApiOperation(value = "删除操作",httpMethod = "GET")
    public int deleteValue(@RequestParam String AAC001){
        return template.delete("deleteUser",AAC001);
    }
}

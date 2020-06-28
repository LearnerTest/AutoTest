package com.imooc.testng.parameter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//参数化测试
public class ParameterTest {
    @Test
    @Parameters({"name","age"})
    public void paraTest(String name,int age){
        System.out.println("名字："+name+"，年龄："+age);
    }

}

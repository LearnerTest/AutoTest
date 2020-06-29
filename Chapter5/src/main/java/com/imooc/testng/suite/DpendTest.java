package com.imooc.testng.suite;

import org.testng.annotations.Test;

public class DpendTest {

    @Test
    public void test1(){
        System.out.println("test1");
        throw new RuntimeException();
    }
    //依赖测试如果测试1失败，测试2会忽略
    @Test(dependsOnMethods = {"test1"})
    public void test2(){
        System.out.println("test2");
    }
}

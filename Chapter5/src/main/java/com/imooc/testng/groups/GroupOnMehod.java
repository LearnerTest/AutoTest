package com.imooc.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupOnMehod {

    @Test(groups = "server")
    public void group1() {
        System.out.println("这是服务端测试111111");
    }

    @Test(groups = "server")
    public void group2() {
        System.out.println("这是服务端测试222222");
    }

    @Test(groups = "client")
    public void group3() {
        System.out.println("这是客户端测试333333");
    }

    @Test(groups = "client")
    public void group4() {
        System.out.println("这是客户端测试444444");
    }

    /**
     * groups分组测试
     */
    @BeforeGroups("server")
    public void beforeOnMehod () {
        System.out.println("这是服务端测试！！！！！");
    }
    @AfterGroups ("server")
    public void afterOnMehod () {
        System.out.println("这是服务端测试~~~~~~~~~~~");
    }
    @BeforeGroups("client")
    public void beforeOnMehod1 () {
        System.out.println("这是客户端测试！！！！！");
    }
    @AfterGroups ("client")
    public void afterOnMehod2 () {
        System.out.println("这是客户端测试~~~~~~~~~~~");
    }
}
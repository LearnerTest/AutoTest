package com.imooc.testng.suite;

import org.testng.annotations.*;

public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite 运行");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite 运行");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("BeforeTest 运行");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("AfterTest 运行");
    }
    @BeforeMethod
    public void beforeM() {
        System.out.println("beforeMehod测试前执行");
    }

}

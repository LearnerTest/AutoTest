package com.imooc.testng;

import org.testng.annotations.*;

public class BasicAnnotation<test> {

    @Test
    public void testCase1() {
        System.out.println("这是测试用例1");
    }

    @Test
    public void testCase2() {
        System.out.println("这是测试用例2");
    }
    /**
    1、BeforeMethod在每条用例前都会执行
    2、AfterMethod在每条用例后都会执行
     */
    @BeforeMethod
    public void beforeTest() {
        System.out.println("这是测试前执行");
    }

    @AfterMethod
    public void afterTest() {
        System.out.println("这是测试后执行");

    }

    /**
     * 1、BeforeClass在类前执行
     * 2、AfterClass在类后执行
     */
    @BeforeClass
    public void beforeClass() {
        System.out.println("这是类之前执行");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("这是类之后执行");
    }

    /**
     * 1、BeforeSuite在所有类前执行
     * 2、AfterSuite在所有类后执行
     */
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("这是beforeSuite执行");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("这是afterSuite执行");
    }
}

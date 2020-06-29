package com.imooc.testng.suite;

import org.testng.annotations.Test;

/**
 * 使用场景：期望得到异常，如传入不合法参数导致异常
 */
public class ExpectedException {
    //失败的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void expectExceptionFail(){
        System.out.println("这是一个异常测试!!!!");
    }
    //成功的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void expectExceptionSusscess(){
        System.out.println("这是一个异常测试!!!!");
        throw new RuntimeException();
    }
}

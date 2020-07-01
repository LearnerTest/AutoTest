package com.imooc.extents.demo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class ExtentsDemo {
    @Test
    public void test1(){
        Assert.assertEquals(1,1);
    }
    @Test
    public void test2(){
        Assert.assertEquals(1,2);
    }
    @Test
    public void logDemo(){
        Reporter.log("日志打印");
        throw new RuntimeException("运行异常抛出");
    }
}

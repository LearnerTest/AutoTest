package com.imooc.testng.suite;

import org.testng.annotations.Test;

public class TimeOutTest {
    @Test(timeOut = 3000)
    public void success() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test(timeOut = 2000)
    public void failed() throws InterruptedException{
        Thread.sleep(3000);
    }

}

package com.imooc.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnAnnotion {
    /**
     * 多线程测试，没有关联的用例可以使用多线程，减少执行时间
     * invocationCount表示执行次数，threadPoolSize表示线程池大小
     */
    @Test(invocationCount = 10,threadPoolSize = 4)
    public void test(){
        System.out.println(1);
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
    }
}

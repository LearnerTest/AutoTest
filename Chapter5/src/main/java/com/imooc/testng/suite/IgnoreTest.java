
package com.imooc.testng.suite;

import org.testng.annotations.Test;

public class IgnoreTest {
    @Test
    public void ignore1(){
        System.out.println("ignore1");
    }

    /**
     * 忽略测试，enabled =false 不执行，enable=true时执行
     */
    @Test(enabled = false)
    public void  ignore2(){
        System.out.println("ignore2");
    }
    @Test(enabled = true)
    public void ignore3(){
        System.out.println("ignore3");
    }
}

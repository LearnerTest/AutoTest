package com.imooc.testng.parameter;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviderTest {

    @Test(dataProvider = "data")
    public void paraTest(String name, int age) {
        System.out.println("名字：" + name + "，年龄：" + age);
    }

    @DataProvider(name = "data")
    public Object[][] datapro() {
        Object[][] ob = new Object[][]{
                {"王凯", 12},
                {"赵鹏", 2},
                {"孙宇", 21}
        };
        return ob;
    }
    //类的参数化方法，通过方法名传递
    @Test(dataProvider = "methodData")
    public void dataMehod1(String name,int age) {
        System.out.println("方法1111名字：" + name + "，年龄：" + age);
    }

    @Test(dataProvider = "methodData")
    public void dataMehod2(String name,int age) {
        System.out.println("方法2222名字：" + name + "，年龄：" + age);
    }

    @DataProvider(name = "methodData")
    public Object[][] dataM(Method method) {
        Object[][] res = null;
        if (method.getName().equals("dataMehod1")) {
            res = new Object[][]{
                    {"仓鼠", 223},
                    {"阿黄", 333}
            };
        } else if (method.getName().equals("dataMehod2")) {
            res = new Object[][]{
                    {"黄油", 1223},
                    {"绿水", 1333}
            };
        }
        return res;
    }
}
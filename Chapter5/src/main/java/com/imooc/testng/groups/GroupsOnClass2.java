package com.imooc.testng.groups;

import org.testng.annotations.Test;

@Test(groups = "stu")
public class GroupsOnClass2 {
    public void stu1(){
        System.out.println("GroupsOnClass2测试111111");
    }
    public void stu2(){
        System.out.println("GroupsOnClass2测试222222");
    }
}

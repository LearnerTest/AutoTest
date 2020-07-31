package com.course.model;

public class Users {

    private int id;
    private String userName;
    private String password;
    private String age;
    private String sex;
    private String permission;
    private String isDelete;

    @Override
    public String toString() {
        return ("{id:"+id+"," +
                "userName:"+userName +"," +
                "passWord:"+password +","+
                "age:"+age +","+
                "sex:"+sex +","+
                "permission:"+permission +","+
                "isDelete:"+isDelete +"}"


    );
    }
}

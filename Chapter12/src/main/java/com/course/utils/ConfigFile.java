package com.course.utils;

import com.course.model.interfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {

    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);
    public static String getUrl(interfaceName name){
        String address = bundle.getString("test.url");
        String url ="";
        String test_url;
        if(name == interfaceName.GETUSERLIST){
            url=bundle.getString("getUserList.uri");
        }
        if(name == interfaceName.ADDUSER){
            url = bundle.getString("addUser.uri");
        }
        if (name == interfaceName.GETUSERINFO){
            url = bundle.getString("getUserInfo.uri");
        }
        if (name == interfaceName.LOGIN){
            url = bundle.getString("login.uri");
        }
        if(name == interfaceName.UPDATEISERINFO){
            url = bundle.getString("updateUserInfo.uri");
        }
        test_url=address+url;

        return test_url;
    }
}

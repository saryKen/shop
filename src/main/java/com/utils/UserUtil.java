package com.utils;


import com.model.user.UserExt;

public class UserUtil {



    /**
     * 将user 信息存入session
     * @param value
     */
    public static void saveUserInfo(Object value){
        HttpUtil.addSession("loginUser",value);

    }

    /**
     * 将user 信息 从 session 中取出
     */
    public static UserExt getUserInfo(){
        return (UserExt) HttpUtil.getSession("loginUser");
    }


}

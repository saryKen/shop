package com.form.user;

import com.utils.CheckUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class LoginForm {


    @Data
    public static class directForm {
        @ApiModelProperty(value = "账号",required=true,example = "123456")
        private String number;  // 账号
        @ApiModelProperty(value = "密码",required=true,example = "123456")
        private String password;  // 密码



        public Map<String,String> getErrorInfo(){
            Map<String,String> errorInfos = new HashMap<>();

            if(!CheckUtil.checkNumber(number)){
                errorInfos.put("number","账号长度必须在5-16之间，且不能包含非法字符*，&，#,/");
            }
            if(!CheckUtil.checkPassword(password)){
                errorInfos.put("password","密码错误");
            }

            return errorInfos;
        }
    }

}

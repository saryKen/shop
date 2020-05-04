package com.controller.user;

import com.form.user.RegisterForm;
import com.result.Result;
import com.service.user.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@Api(value = "注册操作 接口控制器",tags = {"注册操作 接口"})
@RestController
@RequestMapping("json/register")
public class RegisterController {
    @Autowired
    RegisterService registerService;

    /**
     * 直接输入账号密码注册，只需验证账号是否已存在，长度、字符是否合法
     * @param form
     * @return
     */
    @ApiOperation(value = "直接输入账号密码注册",notes = "只需验证账号是否已存在，长度、字符是否合法")
    @PostMapping("/direct")
    public Result direct(RegisterForm.directForm form){

        return registerService.direct(form);
    }

}

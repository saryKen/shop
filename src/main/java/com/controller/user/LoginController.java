package com.controller.user;

import com.form.user.LoginForm;
import com.model.user.UserExt;
import com.result.Result;
import com.result.ResultStatus;
import com.service.user.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@Api(value = "登录操作 接口控制器",tags = {"登录操作 接口"})
@RestController
@RequestMapping("json/login")
public class LoginController {
    @Autowired
    LoginService loginService;


    /**
     * 用户登录，直接输入账号密码登录，只需验证账号密码是否正确
     * @param form
     * @return
     */
    @ApiOperation(value = "用户登录",notes ="直接输入账号密码登录，只需验证账号密码是否正确")
    @GetMapping("/user")
    public Result<UserExt> direct(LoginForm.directForm form, HttpSession session){
        Result result = loginService.direct(form);
        if(result.getCode() == ResultStatus.SUCCESS.getCode()){
            session.setAttribute("loginUser",result.getData());
        }

        return result;
    }

    /**
     * 管理员登录,直接输入账号密码登录，只需验证账号密码是否正确
     * @param form
     * @return
     */
    @ApiOperation(value = "管理员登录",notes ="直接输入账号密码登录，只需验证账号密码是否正确")
    @GetMapping("/admin")
    public Result<UserExt> admin(LoginForm.directForm form, HttpSession session){
        Result result = loginService.direct(form);
        if(result.getCode() == ResultStatus.SUCCESS.getCode()){
            UserExt loginUser = (UserExt) result.getData();
            if (loginUser.isAdmin()){
                session.setAttribute("loginAdmin",loginUser);
                return result;
            }
            return Result.fail(ResultStatus.ERROR_Login_Direct.getCode(),"请输入正确的管理员账号");
        }

        return result;
    }
}

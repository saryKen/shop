package com.controller.user;

import com.model.user.UserExt;
import com.result.Result;
import com.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@Api(value = "用户相关操作 接口控制器",tags = {"用户相关操作 接口"})
@RestController
@RequestMapping("json/user")
public class UserController {
    @Autowired
    UserService userService;



    /**
     * 获取当前登录用户的信息
     * @return
     */
    @ApiOperation(value = "获取当前登录用户的信息",notes = "获取当前登录用户的信息")
    @GetMapping("/getUserInfo")
    public Result<UserExt> getUserInfo(HttpSession session){
        UserExt loginUser = (UserExt) session.getAttribute("loginUser");
        loginUser = userService.getUserById(loginUser.getUserId());

        session.setAttribute("loginUser",loginUser);

        return Result.success(loginUser);
    }

    /**
     * 获取当前登录管理员的信息
     * @return
     */
    @ApiOperation(value = "获取当前登录管理员的信息",notes = "获取当前登录管理员的信息")
    @GetMapping("/getAdminInfo")
    public Result<UserExt> getAdminInfo(HttpSession session){
        UserExt loginUser = (UserExt) session.getAttribute("loginAdmin");
        loginUser = userService.getUserById(loginUser.getUserId());

        session.setAttribute("loginAdmin",loginUser);

        return Result.success(loginUser);
    }
}

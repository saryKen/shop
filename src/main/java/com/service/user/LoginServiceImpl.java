package com.service.user;

import cn.hutool.crypto.SecureUtil;
import com.form.user.LoginForm;
import com.mapper.user.UserMapper;
import com.model.user.User;
import com.model.user.UserExample;
import com.model.user.UserExt;
import com.result.Result;
import com.result.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    /**
     * 直接输入账号密码登录，只需验证账号密码是否正确
     * @param form
     * @return
     */
    @Override
    public Result direct(LoginForm.directForm form) {
        // 首先检查用户注册信息的合法性，如有非法输入，返回错误信息
        if(!form.getErrorInfo().isEmpty()){
            return Result.fail(form.getErrorInfo(), ResultStatus.ERROR_Login_Direct);
        }

        // 如果通过字符检查，则查找指定账号和密码的用户
        UserExample example = new UserExample();
        example.createCriteria().andNumberEqualTo(form.getNumber()).andPasswordEqualTo(SecureUtil.md5(form.getPassword()));
        List<User> users = userMapper.selectByExample(example);
        // 如果查不出任何用户，则说明账号或密码错误
        if(users.isEmpty()){
            return Result.fail(0,ResultStatus.ERROR_Login_Number_Password);
        }

        // 查询用户全部信息
        UserExt loginUser = userService.getUserById(users.get(0).getUserId());

        return Result.success(loginUser);
    }

}

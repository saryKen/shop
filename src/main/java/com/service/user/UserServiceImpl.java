package com.service.user;

import com.mapper.user.RoleMapper;
import com.mapper.user.UserExtMapper;
import com.mapper.user.UserMapper;
import com.model.user.UserExt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    RoleMapper roleMapper;
    @Resource
    UserExtMapper userExtMapper;



    /**
     * 根据id查询用户详情信息
     * @param userId
     * @return
     */
    @Override
    public UserExt getUserById(int userId) {
        UserExt userExt = userExtMapper.selectUserExtById(userId);
        if(userExt!=null){
            userExt.setPassword("");
        }

        return userExt;
    }



}

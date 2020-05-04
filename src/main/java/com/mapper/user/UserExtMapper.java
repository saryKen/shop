package com.mapper.user;

import com.model.user.UserExt;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserExtMapper {

    UserExt selectUserExtById(int userId);
}

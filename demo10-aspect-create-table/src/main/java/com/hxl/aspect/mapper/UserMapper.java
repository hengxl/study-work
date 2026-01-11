package com.hxl.aspect.mapper;

import com.hxl.aspect.entity.User;
import com.hxl.aspect.entity.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 获取 User 信息
     */
    // @TableCreation
    List<User> queryUser(User user);

    /**
     * 获取 UserDTO 信息
     */
    List<UserDTO> queryUserDto(UserDTO userDto);
}

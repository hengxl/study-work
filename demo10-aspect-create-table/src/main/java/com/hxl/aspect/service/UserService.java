package com.hxl.aspect.service;

import com.hxl.aspect.entity.User;
import com.hxl.aspect.entity.UserDTO;

import java.util.List;

public interface UserService {
    /**
     * 获取 User 信息
     */
    List<User> getUser(String tableSuffix);

    /**
     * 获取 UserDTO 信息
     */
    List<UserDTO> getUserDto(String tableSuffix);
}
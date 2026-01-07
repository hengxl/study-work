package com.hxl.aspect.service.impl;

import com.hxl.aspect.entity.User;
import com.hxl.aspect.entity.UserDTO;
import com.hxl.aspect.mapper.UserMapper;
import com.hxl.aspect.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getUser(String tableSuffix) {
        String tableName = validAndGetName(tableSuffix);

        User user = new User();
        user.setTableName(tableName);

        return userMapper.queryUser(user);
    }

    @Override
    public List<UserDTO> getUserDto(String tableSuffix) {
        String tableName = validAndGetName(tableSuffix);

        UserDTO userDTO = new UserDTO();
        userDTO.setTableName(tableName);

        return userMapper.queryUserDto(userDTO);
    }

    private static @NonNull String validAndGetName(String tableSuffix) {
        // 校验表名是否合法
        if (!StringUtils.hasText(tableSuffix)) {
            throw new IllegalArgumentException("表名不能为空");
        }
        log.info("校验表名完成...");

        //----------- 关键方法 ---------
        return "t_user_" + tableSuffix;
    }
}
